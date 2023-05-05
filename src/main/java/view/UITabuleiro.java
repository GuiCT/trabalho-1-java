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
                }

                if ((i == 1 & j == 10) || (i == 1 & j == 13)  ||(i == 4 & j == 10) || (i == 4 & j == 13) )  {
                    casas[i][j].setCor(Color.WHITE);
                }

                if ((i == 10 & j == 1) ||(i == 10 & j == 4) || (i == 13 & j == 1) ||(i == 13 & j == 4)  )  {
                    casas[i][j].setCor(Color.WHITE);
                }

                if ((i == 10 & j == 10) || (i == 10 & j == 13) ||(i == 13 & j == 10) || (i == 13 & j == 13)  )  {
                    casas[i][j].setCor(Color.WHITE);
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


}
