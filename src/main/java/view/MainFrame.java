package view;

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

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

}

