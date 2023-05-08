package view;

import game.Cor;
import javax.swing.*;
import java.awt.*;

public class Casa extends JButton {
    public final Posicao2D posicao;
    private Color cor;
    private Cor corPeao;

    public Casa(Posicao2D posicao) {
        setPreferredSize(new Dimension(50, 50));
        setBackground(Color.WHITE);
        this.posicao = posicao;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (corPeao != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            if (corPeao == Cor.AZUL) {
                g2d.setColor(Color.BLUE);
            } else if (corPeao == Cor.VERDE) {
                g2d.setColor(Color.GREEN);
            } else if (corPeao == Cor.VERMELHO) {
                g2d.setColor(Color.RED);
            } else if (corPeao == Cor.AMARELO) {
                g2d.setColor(Color.YELLOW);
            } else {
                g2d.setColor(Color.WHITE);
            }

            g2d.fillOval(10, 10, 30, 30);
            // Draws the border
            if(corPeao != Cor.BRANCO)
                g2d.setColor(Color.BLACK);
            g2d.drawOval(10, 10, 30, 30);

            g2d.dispose();
        }
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        setBackground(cor);
    }

    public void setCorPeao(Cor corPeao) {
        this.corPeao = corPeao;
        // Force redraw
        repaint();
    }

    public Cor getCorPeao() {
        return corPeao;
    }
}
