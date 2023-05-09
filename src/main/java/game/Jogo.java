package game;

import java.util.ArrayList;
import java.util.List;
import view.Mappings;

public class Jogo {
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
        Posicao posicaoBaseAtual = jogador ? posicoesPeoesJogador[peao] : posicoesPeoesOponente[peao];

        // Decisão é feita com base no status atual
        posicaoBaseAtual = switch (posicaoBaseAtual.status) {
            case BASE -> realizarMovimentoDaBase(posicaoBaseAtual, valorDado);
            case TABULEIRO -> realizarMovimentoDoTabuleiro(jogador, posicaoBaseAtual, valorDado);
            case FILA -> realizarMovimentoDaFila(posicaoBaseAtual, valorDado);
            case FINAL -> posicaoBaseAtual;
        };

        if (jogador) {
            posicoesPeoesJogador[peao] = posicaoBaseAtual;
        } else {
            posicoesPeoesOponente[peao] = posicaoBaseAtual;
        }
    }

    public Posicao realizarMovimentoDaBase(Posicao posicaoBase, int valorDado) {
        // Peão só pode sair da base se o valor do dado for 6
        // É colocado na posição 0
        if (valorDado == 6) {
            posicaoBase.status = Status.TABULEIRO;
            posicaoBase.offset = 0;
        }

        return posicaoBase;
    }

    public Posicao realizarMovimentoDoTabuleiro(boolean jogador, Posicao posicaoBase, int valorDado) {
        posicaoBase.offset += valorDado;

        // Se o peão passar da última casa, ele é colocado na fila final
        // E a posição atual é atualizada para a posição da fila final
        if (posicaoBase.offset > 50) {
            posicaoBase.status = Status.FILA;
            posicaoBase.offset -= 51;

            if (posicaoBase.offset == 5) {
                // Chegou no final!
                posicaoBase.status = Status.FINAL;
                posicaoBase.offset = 0;
            } else if (posicaoBase.offset > 5) {
                // Se passar da última casa da fila
                // Ele é colocado na última casa antes do fim
                posicaoBase.offset = 4;
            }
        } else {
            // Verifica se há algum peão do oponente na posição atingida
            // Se houver, esse pẽao é colocado na base
            // Caso seja uma posição especial, não é verificado se há peão
            int posicaoReal = Mappings.calcularPosicaoRealTabuleiro(jogador ? corJogador : corOponente,
                    posicaoBase.offset);

            if (!verificarPosicaoEspecial(posicaoReal)) {
                List<Integer> conflitos = verificarConflito(jogador, posicaoReal);
                for (int i : conflitos) {
                    if (jogador) {
                        posicoesPeoesOponente[i].status = Status.BASE;
                        posicoesPeoesOponente[i].offset = i;
                    } else {
                        posicoesPeoesJogador[i].status = Status.BASE;
                        posicoesPeoesJogador[i].offset = i;
                    }
                }
            }
        }

        return posicaoBase;
    }

    public Posicao realizarMovimentoDaFila(Posicao posicaoBase, int valorDado) {
        // Se o peão passar da última casa, ele é colocado na casa de chegada
        if (posicaoBase.offset + valorDado <= 5) {
            posicaoBase.offset = posicaoBase.offset + valorDado;
            if (posicaoBase.offset == 5) {
                // Chegou no final!
                posicaoBase.status = Status.FINAL;
                posicaoBase.offset = 0;
            }
        }

        return posicaoBase;
    }

    private List<Integer> verificarConflito(boolean jogador, int posicao) {
        List<Integer> conflitos = new ArrayList<>();

        // Se jogador for true, verifica se há conflito com oponente
        // Se jogador for false, verifica se há conflito com jogador
        Posicao[] posicoes = jogador ? this.posicoesPeoesOponente : this.posicoesPeoesJogador;
        for (int i = 0; i < 4; i++) {
            if (posicoes[i].status == Status.TABULEIRO) {
                int posicaoBase = posicoes[i].offset;
                int posicaoReal = Mappings.calcularPosicaoRealTabuleiro(jogador ? this.corOponente : this.corJogador,
                        posicaoBase);
                if (posicaoReal == posicao) {
                    conflitos.add(i);
                }
            }
        }

        return conflitos;
    }

    private boolean verificarPosicaoEspecial(int posicao) {
        for (int posicaoEspecial : Mappings.posicoesEspeciais) {
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
