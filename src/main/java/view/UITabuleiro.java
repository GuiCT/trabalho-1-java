package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import game.ControllerJogo;
import game.Cor;
import game.Posicao;

public class UITabuleiro extends JPanel {
    private Casa[][] casas;
    private ControllerJogo controllerJogo;

    public UITabuleiro() {
        setLayout(new GridLayout(15, 15));
        casas = new Casa[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                casas[i][j] = new Casa(new Posicao2D(j, i));
                casas[i][j].addActionListener(this::atualizarPeaoSelecionado);
                add(casas[i][j]);
            }
        }

        pintarCasas();
    }

    public void pintarCasas() {
        // Verde
        // Base
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                casas[i][j].setCor(Color.GREEN);
            }
        }

        // Casas
        for (Posicao2D pos : Mappings.posicaoCasas.get(Cor.VERDE)) {
            casas[pos.y][pos.x].setCor(Color.WHITE);
        }

        // Fila
        for (Posicao2D pos : Mappings.posicaoFilas.get(Cor.VERDE)) {
            casas[pos.y][pos.x].setCor(Color.GREEN);
        }

        // Amarelo
        // Base
        for (int i = 0; i < 6; i++) {
            for (int j = 9; j < 15; j++) {
                casas[i][j].setCor(Color.YELLOW);
            }
        }

        // Casas
        for (Posicao2D pos : Mappings.posicaoCasas.get(Cor.AMARELO)) {
            casas[pos.y][pos.x].setCor(Color.WHITE);
        }

        // Fila
        for (Posicao2D pos : Mappings.posicaoFilas.get(Cor.AMARELO)) {
            casas[pos.y][pos.x].setCor(Color.YELLOW);
        }

        // Vermelho
        // Base
        for (int i = 9; i < 15; i++) {
            for (int j = 0; j < 6; j++) {
                casas[i][j].setCor(Color.RED);
            }
        }

        // Casas
        for (Posicao2D pos : Mappings.posicaoCasas.get(Cor.VERMELHO)) {
            casas[pos.y][pos.x].setCor(Color.WHITE);
        }

        // Fila
        for (Posicao2D pos : Mappings.posicaoFilas.get(Cor.VERMELHO)) {
            casas[pos.y][pos.x].setCor(Color.RED);
        }

        // Azul
        // Base
        for (int i = 9; i < 15; i++) {
            for (int j = 9; j < 15; j++) {
                casas[i][j].setCor(Color.BLUE);
            }
        }

        // Casas
        for (Posicao2D pos : Mappings.posicaoCasas.get(Cor.AZUL)) {
            casas[pos.y][pos.x].setCor(Color.WHITE);
        }

        // Fila
        for (Posicao2D pos : Mappings.posicaoFilas.get(Cor.AZUL)) {
            casas[pos.y][pos.x].setCor(Color.BLUE);
        }

        // Tabuleiro, casa de saída de cada cor
        casas[Mappings.posicoesTabuleiro[0].y][Mappings.posicoesTabuleiro[0].x].setCor(Color.YELLOW);
        casas[Mappings.posicoesTabuleiro[13].y][Mappings.posicoesTabuleiro[13].x].setCor(Color.BLUE);
        casas[Mappings.posicoesTabuleiro[26].y][Mappings.posicoesTabuleiro[26].x].setCor(Color.RED);
        casas[Mappings.posicoesTabuleiro[39].y][Mappings.posicoesTabuleiro[39].x].setCor(Color.GREEN);

        // 9 casas do centro serão pretas
        for (int i = 6; i < 9; i++) {
            for (int j = 6; j < 9; j++) {
                casas[i][j].setCor(Color.BLACK);
            }
        }
    }

    public void setControllerJogo(ControllerJogo controllerJogo) {
        this.controllerJogo = controllerJogo;
    }

    public void inicializarPeoes(Posicao[] posicoesJogador, Posicao[] posicoesOponente) {
        Posicao2D[] posicaoCasasJogador = Mappings.posicaoCasas.get(controllerJogo.getCorJogador());
        Posicao2D[] posicaoCasasOponente = Mappings.posicaoCasas.get(controllerJogo.getCorOponente());
        Cor corJogador = controllerJogo.getCorJogador();
        Cor corOponente = controllerJogo.getCorOponente();
        for (int i = 0; i < 4; i++) {
            Posicao2D posicao2DJogador = posicaoCasasJogador[posicoesJogador[i].posicao];
            casas[posicao2DJogador.y][posicao2DJogador.x].adicionarPeao(corJogador);
            Posicao2D posicao2DOponente = posicaoCasasOponente[posicoesOponente[i].posicao];
            casas[posicao2DOponente.y][posicao2DOponente.x].adicionarPeao(corOponente);
        }
    }

    // Atualiza TODOS os peões de uma determinada cor
    public void atualizarPeoes(Posicao[] posicoesAnteriores, Posicao[] posicoesAtuais, Cor cor) {
        for (int i = 0; i < 4; i++)
            atualizarPeao(posicoesAnteriores[i], posicoesAtuais[i], cor);
    }

    // Atualiza apenas um único peão de uma determinada cor
    public void atualizarPeao(Posicao posicaoAnterior, Posicao posicaoAtual, Cor cor) {
        // Atualizar posição anterior para não conter peão
        switch (posicaoAnterior.status) {
            case BASE -> {
                Posicao2D posicao2DAnterior = Mappings.posicaoCasas.get(cor)[posicaoAnterior.posicao];
                casas[posicao2DAnterior.y][posicao2DAnterior.x].removerPeao(cor);
            }
            case TABULEIRO -> {
                Posicao2D posicao2DAnterior = Mappings.posicoesTabuleiro[Mappings.calcularPosicao(cor,
                        posicaoAnterior.posicao)];
                casas[posicao2DAnterior.y][posicao2DAnterior.x].removerPeao(cor);
            }
            case FILA -> {
                Posicao2D posicao2DAnterior = Mappings.posicaoFilas.get(cor)[posicaoAnterior.posicao];
                casas[posicao2DAnterior.y][posicao2DAnterior.x].removerPeao(cor);
            }
            case FINAL -> {
            }
        }
        // Colocar peão na posição nova
        switch (posicaoAtual.status) {
            case BASE -> {
                Posicao2D posicao2Dnova = Mappings.posicaoCasas.get(cor)[posicaoAtual.posicao];
                casas[posicao2Dnova.y][posicao2Dnova.x].adicionarPeao(cor);
            }
            case TABULEIRO -> {
                Posicao2D posicao2Dnova = Mappings.posicoesTabuleiro[Mappings.calcularPosicao(cor,
                        posicaoAtual.posicao)];
                casas[posicao2Dnova.y][posicao2Dnova.x].adicionarPeao(cor);
            }
            case FILA -> {
                Posicao2D posicao2Dnova = Mappings.posicaoFilas.get(cor)[posicaoAtual.posicao - 1];
                casas[posicao2Dnova.y][posicao2Dnova.x].adicionarPeao(cor);
            }
            case FINAL -> {
            }
        }
    }

    private void atualizarPeaoSelecionado(ActionEvent evt) {
        Casa casa = (Casa) evt.getSource();
        Posicao2D posicao2DSelecionada = casa.posicao;
        Posicao[] posicoesPeoes = controllerJogo.getPosicoesPeoesJogador();
        for (int i = 0; i < 4; i++) {
            Posicao2D posicao2DPeao = switch (posicoesPeoes[i].status) {
                case BASE -> Mappings.posicaoCasas.get(controllerJogo.getCorJogador())[posicoesPeoes[i].posicao];
                case TABULEIRO -> Mappings.posicoesTabuleiro[Mappings.calcularPosicao(controllerJogo.getCorJogador(),
                        posicoesPeoes[i].posicao)];
                case FILA -> Mappings.posicaoFilas.get(controllerJogo.getCorJogador())[posicoesPeoes[i].posicao];
                case FINAL -> null;
            };
            if (posicao2DPeao != null && posicao2DPeao.equals(posicao2DSelecionada)) {
                controllerJogo.setPeaoSelecionado(i);
                return;
            }
        }
        controllerJogo.setPeaoSelecionado(null);
    }

    public void peaoDaBaseParaTabuleiro(int casa, Cor cor) {
        if (casa == 0)
            if (cor == Cor.VERDE)
                casas[1][1].adicionarPeao(Cor.BRANCO);
            else if (cor == Cor.AMARELO)
                casas[1][13].adicionarPeao(Cor.BRANCO);
            else if (cor == Cor.VERMELHO)
                casas[13][1].adicionarPeao(Cor.BRANCO);
            else if (cor == Cor.AZUL)
                casas[13][13].adicionarPeao(Cor.BRANCO);

        if (casa == 1)
            if (cor == Cor.VERDE)
                casas[1][4].adicionarPeao(Cor.VERDE);
            else if (cor == Cor.AMARELO)
                casas[1][10].adicionarPeao(Cor.AMARELO);
            else if (cor == Cor.VERMELHO)
                casas[10][1].adicionarPeao(Cor.VERMELHO);
            else if (cor == Cor.AZUL)
                casas[10][13].adicionarPeao(Cor.AZUL);

        if (casa == 2)
            if (cor == Cor.VERDE)
                casas[4][1].adicionarPeao(Cor.VERDE);
            else if (cor == Cor.AMARELO)
                casas[4][13].adicionarPeao(Cor.AMARELO);
            else if (cor == Cor.VERMELHO)
                casas[13][4].adicionarPeao(Cor.VERMELHO);
            else if (cor == Cor.AZUL)
                casas[13][10].adicionarPeao(Cor.AZUL);

        if (casa == 3)
            if (cor == Cor.VERDE)
                casas[4][4].adicionarPeao(Cor.VERDE);
            else if (cor == Cor.AMARELO)
                casas[4][10].adicionarPeao(Cor.AMARELO);
            else if (cor == Cor.VERMELHO)
                casas[10][4].adicionarPeao(Cor.VERMELHO);
            else if (cor == Cor.AZUL)
                casas[10][10].adicionarPeao(Cor.AZUL);

        if (cor == Cor.VERDE) {
            casas[6][1].adicionarPeao(Cor.VERDE);
        } else if (cor == Cor.AMARELO) {
            casas[1][8].adicionarPeao(Cor.AMARELO);
        } else if (cor == Cor.VERMELHO) {
            casas[13][6].adicionarPeao(Cor.VERMELHO);
        } else if (cor == Cor.AZUL) {
            casas[8][13].adicionarPeao(Cor.AZUL);
        }
    }

    public void moverPeca(int i, int j, Cor cor, int qtdCasa) {
        casas[i][j].setCorPeao(Cor.BRANCO);
        if (i == 6 && j < 6) {
            if ((j + qtdCasa) > 5) {
                int aux = j - 5;
                casas[(6 - (qtdCasa + aux))][6].setCorPeao(cor);
            } else {
                casas[6][j + qtdCasa].setCorPeao(cor);
            }
        } else if (i < 6 && j == 6) {
            if ((i - qtdCasa) < 0) {
                int aux = i - 6;
                if ((qtdCasa + aux + 6) > 8) {
                    if (cor == Cor.AMARELO) {
                        casas[1][7].setCorPeao(cor);
                    } else {
                        aux = aux - 2;
                        casas[qtdCasa + aux][8].setCorPeao(cor);
                    }
                } else {
                    if ((qtdCasa - aux) > 8) {
                        aux = 8 - (j - aux);
                        casas[qtdCasa + aux][8].setCorPeao(cor);

                    } else
                        casas[0][qtdCasa - aux].setCorPeao(cor);
                }
            } else {
                casas[i - qtdCasa][6].setCorPeao(cor);
            }
        } else if (i < 6 && j == 8) {
            if ((i + qtdCasa) > 5) {
                int aux = i - 5;
                casas[6][8 + qtdCasa + aux].setCorPeao(cor);

            } else {
                casas[i + qtdCasa][8].setCorPeao(cor);
            }
        } else if (i == 6 & j > 8) {
            if ((j + qtdCasa) > 14) {
                int aux = j - 8;
                if ((qtdCasa + aux) > 8) {
                    if (cor == Cor.AZUL) {
                        casas[7][13].setCorPeao(cor);
                    } else {
                        aux = qtdCasa + aux - 8;
                        casas[8][14 - aux].setCorPeao(cor);
                    }
                } else
                    casas[8 + (qtdCasa - aux)][14].setCorPeao(cor);
            } else {
                casas[6][j + qtdCasa].setCorPeao(cor);
            }

        } else if (i == 8 && j > 8) {
            if ((j - qtdCasa) < 9) {
                int aux = j - 9;
                casas[8 + (qtdCasa - aux)][8].setCorPeao(cor);

            } else {
                casas[8][j - qtdCasa].setCorPeao(cor);
            }
        } else if (i > 8 && j == 8) {
            if ((i + qtdCasa) > 14) {
                int aux = 14 - i;
                if ((qtdCasa - aux) < 6) {
                    if (cor == Cor.VERMELHO) {
                        casas[13][7].setCorPeao(cor);
                    } else {
                        aux = aux + 2;
                        casas[14 - (qtdCasa - aux)][6].setCorPeao(cor);
                    }
                } else
                    casas[14][6 + (qtdCasa - aux)].setCorPeao(cor);
            } else {
                casas[i + qtdCasa][8].setCorPeao(cor);
            }
        } else if (i > 8 & j == 6) {
            if ((i - qtdCasa) < 9) {
                int aux = i - 9;
                casas[8][6 - (qtdCasa - aux)].setCorPeao(cor);

            } else {
                casas[i - qtdCasa][6].setCorPeao(cor);
            }
        }

        if (i == 8 && j < 7) {
            if ((j - qtdCasa) < 1) {
                int aux = j - 6;
                if (i + aux < 6) {
                    if (cor == Cor.VERDE) {
                        casas[7][1].setCorPeao(cor);
                    } else {
                        aux = 6 - (i - aux);
                        casas[6][aux].setCorPeao(cor);
                    }
                } else
                    casas[i + aux][0].setCorPeao(cor);
            } else {
                casas[8][j - qtdCasa].setCorPeao(cor);
            }
        }
    }
}
