package game;

import java.util.ArrayList;
import java.util.List;
import view.Mappings;

public class Jogo {
    private static final int[] posicoesEspeciais = { 0, 8, 21, 26, 34, 47 };
    private static final int casasAteFim = 50;
    private static final int casasFila = 5;
    // ------------------------------------------------------------------------
    private Cor corJogador;
    private Cor corOponente;
    private Posicao[] posicoesPeoesJogador;
    private Posicao[] posicoesPeoesOponente;

    public Jogo(Cor corJogador) {
        this.corJogador = corJogador;
        this.corOponente = corJogador == Cor.AMARELO ? Cor.VERMELHO : Cor.AMARELO;
        this.posicoesPeoesJogador = new Posicao[4];
        this.posicoesPeoesOponente = new Posicao[4];

        for (int i = 0; i < 4; i++) {
            this.posicoesPeoesJogador[i] = new Posicao(Status.BASE, i);
            this.posicoesPeoesOponente[i] = new Posicao(Status.BASE, i);
        }
    }

    public Cor getCorJogador() {
        return this.corJogador;
    }

    public Cor getCorOponente() {
        return this.corOponente;
    }

    public Posicao[] getPosicoesPeoesJogador() {
        return this.posicoesPeoesJogador;
    }

    public Posicao[] getPosicoesPeoesOponente() {
        return this.posicoesPeoesOponente;
    }

    public void realizarMovimento(boolean jogador, int peao, int valorDado) {
        Posicao posicaoBaseAtual;

        if (jogador) {
            posicaoBaseAtual = this.posicoesPeoesJogador[peao];
        } else {
            posicaoBaseAtual = this.posicoesPeoesOponente[peao];
        }

        // Decisão é feita com base no status atual
        if (posicaoBaseAtual.status == Status.BASE) {
            // Peão só pode sair da base se o valor do dado for 6
            // É colocado na posição 0
            if (valorDado == 6) {
                posicaoBaseAtual.status = Status.TABULEIRO;
                posicaoBaseAtual.posicao = 0;
            }
        } else if (posicaoBaseAtual.status == Status.TABULEIRO) {
            // Se o peão estiver no tabuleiro, é somado o valor do dado à sua posição atual
            posicaoBaseAtual.posicao += valorDado;

            // Se o peão passar da última casa, ele é colocado na fila final
            // E a posição atual é atualizada para a posição da fila final
            if (posicaoBaseAtual.posicao > casasAteFim) {
                posicaoBaseAtual.status = Status.FILA;
                posicaoBaseAtual.posicao -= casasAteFim + 1;

                if (posicaoBaseAtual.posicao == casasFila) {
                    // Chegou no final!
                    posicaoBaseAtual.status = Status.FINAL;
                    posicaoBaseAtual.posicao = 0;
                } else if (posicaoBaseAtual.posicao > casasFila) {
                    // Se passar da última casa da fila
                    // Ele é colocado na última casa antes do fim
                    posicaoBaseAtual.posicao = casasFila - 1;
                }
            }else{
                // Verifica se há algum peão do oponente na posição atingida
                // Se houver, esse pẽao é colocado na base
                // Caso seja uma posição especial, não é verificado se há peão
                int posicaoReal = Mappings.calcularPosicao(jogador ? corJogador : corOponente, posicaoBaseAtual.posicao);

                if (!verificarPosicaoEspecial(posicaoReal)) {
                    List<Integer> conflitos = verificarConflito(jogador, posicaoReal);
                    for (int i : conflitos) {
                        if (jogador) {
                            this.posicoesPeoesOponente[i].status = Status.BASE;
                            this.posicoesPeoesOponente[i].posicao = i;
                        } else {
                            this.posicoesPeoesJogador[i].status = Status.BASE;
                            this.posicoesPeoesJogador[i].posicao = i;
                        }
                    }
                }               
            }
        } else if (posicaoBaseAtual.status == Status.FILA) {
            // Se o peão passar da última casa, ele é colocado na casa de chegada
            if (posicaoBaseAtual.posicao + valorDado <= casasFila) {
                posicaoBaseAtual.posicao = posicaoBaseAtual.posicao + valorDado;
                if (posicaoBaseAtual.posicao == casasFila) {
                    // Chegou no final!
                    posicaoBaseAtual.status = Status.FINAL;
                    posicaoBaseAtual.posicao = 0;
                }
            }
        }

        if (jogador) {
            this.posicoesPeoesJogador[peao] = posicaoBaseAtual;
        } else {
            this.posicoesPeoesOponente[peao] = posicaoBaseAtual;
        }
    }

    private List<Integer> verificarConflito(boolean jogador, int posicao) {
        List<Integer> conflitos = new ArrayList<>();

        // Se jogador for true, verifica se há conflito com oponente
        // Se jogador for false, verifica se há conflito com jogador
        Posicao[] posicoes = jogador ? this.posicoesPeoesOponente : this.posicoesPeoesJogador;
        for (int i = 0; i < 4; i++) {
            if(posicoes[i].status == Status.TABULEIRO){
                int posicaoBase = posicoes[i].posicao;
                int posicaoReal = Mappings.calcularPosicao(jogador ? this.corOponente : this.corJogador, posicaoBase);
                if (posicaoReal == posicao) {
                    conflitos.add(i);
                }
            }
        }

        return conflitos;
    }

    private boolean verificarPosicaoEspecial(int posicao) {
        for (int posicaoEspecial : posicoesEspeciais) {
            if (posicao == posicaoEspecial) {
                return true;
            }
        }

        return false;
    }

    // verifica se o movimento do jogador resultou em vitoria
    public boolean jogoFinalizado(boolean jogador) {
        if (jogador) {
            for (Posicao posicoesPeao : posicoesPeoesJogador) {
                if (posicoesPeao.status != Status.FINAL) {
                    return false;
                }
            }
            return true;
        } else {
            for (Posicao posicoesPeao : posicoesPeoesOponente) {
                if (posicoesPeao.status != Status.FINAL) {
                    return false;
                }
            }
            return true;
        }
    }
}
