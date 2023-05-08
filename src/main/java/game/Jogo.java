package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Jogo {
    private static final Map<Cor, Integer> offsets = Map.of(
            Cor.AMARELO, 26,
            Cor.VERMELHO, 0);
    private static final int[] posicoesEspeciais = { 8, 21, 34, 47 };
    private static final int casasAteFim = 50;
    private static final int casasFila = 6;
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
            if (posicaoBaseAtual.posicao >= casasAteFim) {
                posicaoBaseAtual.status = Status.FILA;
                posicaoBaseAtual.posicao -= casasAteFim;

                if (posicaoBaseAtual.posicao == casasFila) {
                    // Chegou no final!
                    posicaoBaseAtual.status = Status.FINAL;
                    posicaoBaseAtual.posicao = 0;
                } else if (posicaoBaseAtual.posicao > casasFila) {
                    // Se passar da última casa da fila
                    // Ele é colocado na última casa antes do fim
                    posicaoBaseAtual.posicao = casasFila - 1;
                }
            }

            // Verifica se há algum peão do oponente na posição atingida
            // Se houver, esse pẽao é colocado na base
            // Caso seja uma posição especial, não é verificado se há peão
            int posicaoReal = calcularPosicao(jogador, posicaoBaseAtual.posicao);

            if (!verificarPosicaoEspecial(posicaoReal)) {
                List<Integer> conflitos = verificarConflito(jogador, posicaoReal);
                for (int i : conflitos) {
                    if (jogador) {
                        this.posicoesPeoesOponente[i].status = Status.BASE;
                        this.posicoesPeoesOponente[i].posicao = 0;
                    } else {
                        this.posicoesPeoesJogador[i].status = Status.BASE;
                        this.posicoesPeoesJogador[i].posicao = 0;
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

    private int calcularPosicao(boolean jogador, int posicao) {
        int offset = offsets.get(jogador ? this.corJogador : this.corOponente);
        return (posicao + offset) % (casasAteFim + 2);
    }

    private List<Integer> verificarConflito(boolean jogador, int posicao) {
        List<Integer> conflitos = new ArrayList<>();

        // Se jogador for true, verifica se há conflito com oponente
        // Se jogador for false, verifica se há conflito com jogador
        for (int i = 0; i < 4; i++) {
            int posicaoBase = jogador ? this.posicoesPeoesOponente[i].posicao : this.posicoesPeoesJogador[i].posicao;
            int posicaoReal = calcularPosicao(!jogador, posicaoBase);
            if (posicaoReal == posicao) {
                conflitos.add(i);
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
