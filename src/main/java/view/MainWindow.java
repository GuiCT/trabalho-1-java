package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import game.ControllerJogo;

public class MainWindow extends JFrame {
    private JButton buttonDado;
    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private JMenuItem menuEntrarPartida;
    private JMenu menuJogar;
    private JMenu menuRegras;
    private JMenuItem menuSerHost;
    private JMenuItem menuVisualizarRegras;
    private JPanel panelJogadas;
    private UITabuleiro panelTabuleiro;
    private JTextArea textAreaJogadas;
    private static final String regras = """
            1 - Cada jogador possui 4 peões.
            2 - Os peões de um determinado jogador podem ocupar o mesmo espaço.
            3 - Peões de jogadores diferentes não podem ocupar o mesmo espaço.
                3.1 - Se o peão (A) de um determinado jogador ocupar o mesmo espaço que um peão (B) de outro, o peão B volta para o início.
            4 - O jogo acaba quando um jogador colocar todos os peões no centro do tabuleiro.""";
    private ControllerJogo controllerJogo;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    public MainWindow() {
        buttonDado = new JButton("Girar dado");
        scrollPane = new JScrollPane();
        menuBar = new JMenuBar();
        menuEntrarPartida = new JMenuItem("Entrar em partida");
        menuJogar = new JMenu("Jogar");
        menuRegras = new JMenu("Regras");
        menuSerHost = new JMenuItem("Ser host");
        menuVisualizarRegras = new JMenuItem("Visualizar");
        panelJogadas = new JPanel();
        panelTabuleiro = new UITabuleiro();
        textAreaJogadas = new JTextArea();
        controllerJogo = new ControllerJogo(this, panelTabuleiro);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        BorderLayout mainLayout = new BorderLayout();
        getContentPane().setLayout(mainLayout);

        getContentPane().add(panelTabuleiro, BorderLayout.CENTER);

        BorderLayout panelJogadasLayout = new BorderLayout();
        panelJogadas.setLayout(panelJogadasLayout);

        textAreaJogadas.setEditable(false);
        textAreaJogadas.setColumns(20);
        textAreaJogadas.setRows(5);
        scrollPane.setViewportView(textAreaJogadas);

        panelJogadas.add(scrollPane, BorderLayout.CENTER);

        panelJogadas.add(buttonDado, BorderLayout.SOUTH);

        getContentPane().add(panelJogadas, BorderLayout.EAST);

        menuEntrarPartida.addActionListener(this::entrarPartida);
        menuJogar.add(menuEntrarPartida);

        menuSerHost.addActionListener(this::hostMatch);
        menuJogar.add(menuSerHost);
        menuBar.add(menuJogar);
        menuRegras.add(menuVisualizarRegras);
        menuBar.add(menuRegras);
        setJMenuBar(menuBar);

        pack();
        // Minimum size is the same as the preferred size but halved
        setMinimumSize(new Dimension(
                getPreferredSize().width / 2,
                getPreferredSize().height / 2));

        buttonDado.addActionListener(this::girarDado);
        menuVisualizarRegras.addActionListener(this::visualizarRegras);
    }

    private void visualizarRegras(ActionEvent evt) {
        JOptionPane.showMessageDialog(rootPane, regras);
    }

    private void girarDado(ActionEvent evt) {
        int generatedNumber = 1 + (int) (Math.random() * 6);
        String toAppend = Integer.toString(generatedNumber) + "\n";
        textAreaJogadas.append(toAppend);
        try {
            controllerJogo.moverPeao(generatedNumber);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e, "Erro ao mover peão", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hostMatch(ActionEvent evt) {
        controllerJogo.hostMatch();
    }

    private void entrarPartida(ActionEvent evt) {
        InserirIP inserirIP = new InserirIP(controllerJogo);
        inserirIP.setVisible(true);
    }
}
