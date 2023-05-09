package view;

import game.Cor;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Casa extends JButton {
    public final Posicao2D posicao;
    private Color cor;
    private Cor corPeao;

    private ArrayList<Cor> peoes;

    public Casa(Posicao2D posicao) {
        setPreferredSize(new Dimension(50, 50));
        setBackground(Color.WHITE);
        this.posicao = posicao;
        peoes = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        HashMap<Cor, Integer> coresDiferentes = new HashMap<Cor, Integer>();
        for (Cor cor : peoes) {
            if (!coresDiferentes.containsKey(cor)) {
                coresDiferentes.put(cor, 1);
            } else {
                coresDiferentes.replace(cor, coresDiferentes.get(cor) + 1);
            }
        }
        int differentColors = coresDiferentes.size();
        int coordinateX, coordinateY, coordinateXCircle, coordinateYCircle;

        if (differentColors == 0) {
            return;
        }
        // se houver apenas 1 cor de peão, coloque no centro, senão, divida em quatro e
        // coloque cada cor em um canto
        if (differentColors == 1) {
            coordinateX = (getWidth()) / 2;
            coordinateY = (getHeight()) / 2;
            coordinateXCircle = (getWidth()) / 4;
            coordinateYCircle = (getHeight()) / 4;
        } else {
            coordinateX = (getWidth()) / 4;
            coordinateY = (getHeight()) / 4;
            coordinateXCircle = (getWidth()) / 2;
            coordinateYCircle = (getHeight()) / 2;
        }

        // calcula tamanho dos peoes
        int diameterWidth = (getWidth()) / 2;
        int diameterHeight = (getHeight()) / 2;

        // count auxilia no mapeamento de peões
        int count = 0;
        for (Map.Entry<Cor, Integer> dadosCor : coresDiferentes.entrySet()) {
            Graphics2D g2d = (Graphics2D) g.create();
            switch (count) {
                case 0:
                    break;
                case 1:
                    coordinateX = (getWidth()) - coordinateX;
                    coordinateY = (getHeight()) - coordinateY;
                    coordinateXCircle = 0;
                    coordinateYCircle = 0;
                    break;
                case 2:
                    coordinateX = (getWidth()) - coordinateX;
                    coordinateXCircle = 0;
                    break;
                case 3:
                    coordinateY = (getHeight()) - coordinateY;
                    coordinateYCircle = 0;
                    break;
            }
            count++;
            g2d.setColor(Color.WHITE);
            g2d.fillOval(coordinateXCircle, coordinateYCircle, diameterWidth, diameterHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(coordinateXCircle, coordinateYCircle, diameterWidth, diameterHeight);

            Cor cor1 = dadosCor.getKey();
            if (cor1 == Cor.AZUL) {
                g2d.setColor(Color.BLUE);
            } else if (cor1 == Cor.VERDE) {
                g2d.setColor(Color.GREEN);
            } else if (cor1 == Cor.VERMELHO) {
                g2d.setColor(Color.RED);
            } else if (cor1 == Cor.AMARELO) {
                g2d.setColor(Color.YELLOW);
            } else {
                g2d.setColor(Color.WHITE);
            }
            g2d.drawString(String.valueOf(dadosCor.getValue()), coordinateX, coordinateY);
            g2d.dispose();
        }
    }

    public void paintPawn(Graphics g) {
        int diameterWidth = getWidth() / 2;
        int diameterHeight = getHeight() / 2;

        super.paintComponent(g);
        HashMap<Cor, Integer> coresDiferentes = new HashMap<Cor, Integer>();
        for (Cor cor : peoes) {
            if (!coresDiferentes.containsKey(cor)) {
                coresDiferentes.put(cor, 1);
            } else {
                coresDiferentes.replace(cor, coresDiferentes.get(cor) + 1);
            }
        }
        int differentColors = coresDiferentes.size();
        int coordinateX, coordinateY;
        // se houver apenas 1 cor de peão, coloque no centro, senão, divida em quatro e
        // coloque cada cor em um canto
        if (differentColors == 1) {
            coordinateX = (getWidth()) / 2;
            coordinateY = (getHeight()) / 2;
        } else {
            coordinateX = (getWidth()) / 4;
            coordinateY = (getHeight()) / 4;
        }
        // count auxilia no mapeamento de peões
        int count = 0;
        for (Map.Entry<Cor, Integer> dadosCor : coresDiferentes.entrySet()) {
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
            switch (count) {
                case 0:
                    break;
                case 1:
                    coordinateX = 3 * coordinateX;
                    coordinateY = 3 * coordinateY;
                    break;
                case 2:
                    coordinateX = 3 * coordinateX;
                    break;
                case 3:
                    coordinateY = 3 * coordinateY;
                    break;
            }
            count++;
            g2d.drawString(String.valueOf(dadosCor.getValue()), coordinateX, coordinateY);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(coordinateX, coordinateY, diameterWidth, diameterHeight);
            g2d.dispose();
        }
    }

    // on resize
    @Override
    public void invalidate() {
        super.invalidate();
        repaint();
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

    // adiciona um peao de uma determinada cor
    public void adicionarPeao(Cor cor) {
        peoes.add(cor);
        repaint();
    }

    // adiciona um peao de uma determinada cor
    public void removerPeao(Cor cor) {
        if (peoes.lastIndexOf(cor) != -1) {
            peoes.remove(peoes.lastIndexOf(cor));
        }
        repaint();
    }
}
