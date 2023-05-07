package view;

import game.Cor;

import javax.swing.*;
import java.awt.*;

public class UITabuleiro extends javax.swing.JPanel{
    private Casa[][] casas;

    public UITabuleiro(){
        setLayout(new GridLayout(15, 15));
        casas = new Casa[15][15];

        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                casas[i][j] = new Casa();

                // Parte verde
                if(i < 6 & j < 6){
//                    casas[i][j].setPos(i*6 + j);
                    casas[i][j].setCor(Color.GREEN);
                }
                if(j < 6 & j > 0 & i == 7){
//                    casas[i][j].setPos(90 + j);
                    casas[i][j].setCor(Color.GREEN);
                }
                if(j == 1 & i == 6){
//                    casas[i][j].setPos(96);
                    casas[i][j].setCor(Color.GREEN);
                }

                // Parte vermelha
                if (i > 8 & j < 6) {
                    casas[i][j].setCor(Color.RED);
                }
                if (i < 14 & 8 < i & j == 7) {
                    casas[i][j].setCor(Color.RED);
                }
                if (j == 6 & i == 13) {
                    casas[i][j].setCor(Color.RED);
                }

                // Parte amarela
                if(i < 6 & j > 8){
//                    casas[i][j].setPos(i*6 + j);
                    casas[i][j].setCor(Color.YELLOW);
                }
                if(i < 6 & i > 0 & j == 7){
//                    casas[i][j].setPos(90 - i);
                    casas[i][j].setCor(Color.YELLOW);
                }
                if(i == 1 & j == 8){
//                    casas[i][j].setPos(96);
                    casas[i][j].setCor(Color.YELLOW);
                }

                // Parte azul
                if(i > 8 & j > 8){
//                    casas[i][j].setPos(i*6 + j);
                    casas[i][j].setCor(Color.BLUE);
                }
                if(j < 14 & j > 8 & i == 7){
//                    casas[i][j].setPos(90 + j);
                    casas[i][j].setCor(Color.BLUE);
                }
                if(j == 13 & i == 8){
//                    casas[i][j].setPos(96);
                    casas[i][j].setCor(Color.BLUE);
                }

                if ((i == 1 & j == 1) || (i == 1 & j == 4) || (i == 4 & j == 1) || (i == 4 & j == 4) )  {
                    casas[i][j].setCor(Color.WHITE);
                    casas[i][j].setCorPeao(Cor.VERDE);
                }

                if ((i == 1 & j == 10) || (i == 1 & j == 13)  ||(i == 4 & j == 10) || (i == 4 & j == 13) )  {
                    casas[i][j].setCor(Color.WHITE);
                    casas[i][j].setCorPeao(Cor.AMARELO);
                }

                if ((i == 10 & j == 1) ||(i == 10 & j == 4) || (i == 13 & j == 1) ||(i == 13 & j == 4)  )  {
                    casas[i][j].setCor(Color.WHITE);
                    casas[i][j].setCorPeao(Cor.VERMELHO);
                }

                if ((i == 10 & j == 10) || (i == 10 & j == 13) ||(i == 13 & j == 10) || (i == 13 & j == 13)  )  {
                    casas[i][j].setCor(Color.WHITE);
                    casas[i][j].setCorPeao(Cor.AZUL);
                }


                add(casas[i][j]);
            }
        }

        int pos = 0;
        for (int i = 13; i > 8; i--) {
            casas[i][6].setPos(pos);
            pos++;
        }
        for (int j = 5; j > -1; j--) {
            casas[8][j].setPos(pos);
            pos++;
        }
        casas[7][0].setPos(pos);
        pos++;
        for (int j = 0; j < 6; j++) {
            casas[6][j].setPos(pos);
            pos++;
        }
        for (int i = 5; i > -1; i--) {
            casas[i][6].setPos(pos);
            pos++;
        }
        casas[0][7].setPos(pos);
        pos++;
        for (int i = 0; i < 6; i++) {
            casas[i][8].setPos(pos);
            pos++;
        }
        for (int j = 9; j < 15; j++) {
            casas[6][j].setPos(pos);
            pos++;
        }
        casas[7][14].setPos(pos);
        pos++;
        for (int j = 14; j > 8; j--) {
            casas[8][j].setPos(pos);
            pos++;
        }
        for (int i = 9; i < 15; i++) {
            casas[i][8].setPos(pos);
            pos++;
        }
        casas[14][7].setPos(pos);
        pos++;
        casas[14][6].setPos(pos);

    }

    public void peaoDaBaseParaTabuleiro(int casa, Cor cor){
        if(casa == 0)
            if(cor == Cor.VERDE)
                casas[1][1].setCorPeao(Cor.BRANCO);
            else if(cor == Cor.AMARELO)
                casas[1][13].setCorPeao(Cor.BRANCO);
            else if(cor == Cor.VERMELHO)
                casas[13][1].setCorPeao(Cor.BRANCO);
            else if(cor == Cor.AZUL)
                casas[13][13].setCorPeao(Cor.BRANCO);

        if(casa == 1)
            if(cor == Cor.VERDE)
                casas[1][4].setCorPeao(Cor.VERDE);
            else if(cor == Cor.AMARELO)
                casas[1][10].setCorPeao(Cor.AMARELO);
            else if(cor == Cor.VERMELHO)
                casas[10][1].setCorPeao(Cor.VERMELHO);
            else if(cor == Cor.AZUL)
                casas[10][13].setCorPeao(Cor.AZUL);

        if(casa == 2)
            if(cor == Cor.VERDE)
                casas[4][1].setCorPeao(Cor.VERDE);
            else if(cor == Cor.AMARELO)
                casas[4][13].setCorPeao(Cor.AMARELO);
            else if(cor == Cor.VERMELHO)
                casas[13][4].setCorPeao(Cor.VERMELHO);
            else if(cor == Cor.AZUL)
                casas[13][10].setCorPeao(Cor.AZUL);

        if(casa == 3)
            if(cor == Cor.VERDE)
                casas[4][4].setCorPeao(Cor.VERDE);
            else if(cor == Cor.AMARELO)
                casas[4][10].setCorPeao(Cor.AMARELO);
            else if(cor == Cor.VERMELHO)
                casas[10][4].setCorPeao(Cor.VERMELHO);
            else if(cor == Cor.AZUL)
                casas[10][10].setCorPeao(Cor.AZUL);

        if(cor == Cor.VERDE){
            casas[6][1].setCorPeao(Cor.VERDE);
        }
        else if(cor == Cor.AMARELO){
            casas[1][8].setCorPeao(Cor.AMARELO);
        }
        else if(cor == Cor.VERMELHO){
            casas[13][6].setCorPeao(Cor.VERMELHO);
        }
        else if(cor == Cor.AZUL){
            casas[8][13].setCorPeao(Cor.AZUL);
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
                    if((qtdCasa + aux + 6) > 8) {
                        if (cor == Cor.AMARELO) {
                            casas[1][7].setCorPeao(cor);
                        } else {
                            aux = aux - 2;
                            casas[qtdCasa + aux][8].setCorPeao(cor);
                        }
                    }
                    else{
                        if ((qtdCasa - aux) > 8){
                            aux = 8 - (j - aux);
                            casas[qtdCasa + aux][8].setCorPeao(cor);

                        }else
                            casas[0][qtdCasa - aux].setCorPeao(cor);
                    }
            } else {
                    casas[i - qtdCasa][6].setCorPeao(cor);
            }
        }else if (i < 6 && j == 8) {
            if ((i + qtdCasa) > 5) {
                    int aux = i - 5;
                    casas[6][8 + qtdCasa + aux].setCorPeao(cor);

            } else {
                    casas[i + qtdCasa][8].setCorPeao(cor);
            }
        }  else if (i == 6 & j > 8) {
            if ((j + qtdCasa) > 14) {
                    int aux = j - 8;
                    if((qtdCasa + aux) > 8){
                    if(cor == Cor.AZUL){
                        casas[7][13].setCorPeao(cor);
                    } else{
                            aux = qtdCasa + aux - 8;
                            casas[8][14 - aux].setCorPeao(cor);
                        }
                    }
                    else
                        casas[8 + (qtdCasa - aux)][14].setCorPeao(cor);
            } else {
                    casas[6][j + qtdCasa].setCorPeao(cor);
            }

        } else if (i == 8 && j > 8) {
            if ((j - qtdCasa) < 9){
                int aux = j - 9;
                casas[8 + (qtdCasa - aux)][8].setCorPeao(cor);

            } else {
                casas[8][j - qtdCasa].setCorPeao(cor);
            }
        } else if (i > 8 && j == 8) {
            if ((i + qtdCasa) > 14) {
                int aux = 14 - i;
                if((qtdCasa - aux) < 6){
                if(cor == Cor.VERMELHO){
                    casas[13][7].setCorPeao(cor);
                }else{
                        aux = aux + 2;
                        casas[14 - (qtdCasa - aux)][6].setCorPeao(cor);
                    }
                } else
                casas[14][6 + (qtdCasa - aux)].setCorPeao(cor);
            } else {
                casas[i + qtdCasa][8].setCorPeao(cor);
            }
        } else if (i > 8 & j == 6){
            if((i - qtdCasa) < 9){
                    int aux = i - 9;
                    casas[8][6 - (qtdCasa - aux)].setCorPeao(cor);

            }else{
                    casas[i - qtdCasa][6].setCorPeao(cor);
            }
        }

        if(i == 8 && j < 7){
            if((j - qtdCasa) < 1){
                int aux = j - 6;
                if (i + aux < 6){
                    if (cor == Cor.VERDE){
                        casas[7][1].setCorPeao(cor);
                    }else {
                        aux = 6 - (i - aux);
                        casas[6][aux].setCorPeao(cor);
                    }
                }else
                    casas[i + aux][0].setCorPeao(cor);
            }else{
                casas[8][j - qtdCasa].setCorPeao(cor);
            }
        }
    }
}
