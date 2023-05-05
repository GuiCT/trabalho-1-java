package view;

import game.Cor;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Casa extends JButton {

    private int pos;
    private Color cor;

    public Casa(){
        this.pos = -1;
        setPreferredSize(new Dimension(50, 50));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (cor != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(cor);

            g2d.fillOval(10, 10, 30, 30);
            g2d.dispose();
        }
    }








    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
//        this.cor = cor;
        setBackground(cor);
    }

//    public void setPeao(Cor cor) {
//        this.cor = cor;
//    }
}
