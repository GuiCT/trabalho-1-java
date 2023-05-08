package view;

import game.Cor;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends javax.swing.JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainFrame(){
        UITabuleiro tabuleiro = new UITabuleiro();
        add(tabuleiro);
        this.add(tabuleiro, BorderLayout.CENTER);
        tabuleiro.peaoDaBaseParaTabuleiro(0, Cor.VERDE);
        tabuleiro.peaoDaBaseParaTabuleiro(1, Cor.VERDE);
        tabuleiro.peaoDaBaseParaTabuleiro(0, Cor.AZUL);
        tabuleiro.peaoDaBaseParaTabuleiro(0, Cor.VERMELHO);
        tabuleiro.peaoDaBaseParaTabuleiro(0, Cor.AMARELO);

        tabuleiro.moverPeca(6, 1, Cor.VERDE, 3);
        tabuleiro.moverPeca(6, 4, Cor.VERDE, 6);
        tabuleiro.moverPeca(1, 6, Cor.VERDE, 6);
        tabuleiro.moverPeca(3, 8, Cor.VERDE, 6);
        tabuleiro.moverPeca(6, 12, Cor.VERDE, 6);
        tabuleiro.moverPeca(8, 12, Cor.VERDE, 4);
        tabuleiro.moverPeca(9, 8, Cor.VERDE, 4);
        tabuleiro.moverPeca(13, 8, Cor.VERDE, 4);
        tabuleiro.moverPeca(13, 6, Cor.VERDE, 5);
//        tabuleiro.moverPeca(8, 5, Cor.VERDE, 3);
//        tabuleiro.moverPeca(8, 2, Cor.VERDE, 6);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

}

