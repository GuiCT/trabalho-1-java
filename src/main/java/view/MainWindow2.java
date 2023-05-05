package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainWindow2 extends JFrame {
    private JButton buttonDado;
    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private JMenuItem menuEntrarPartida;
    private JMenu menuJogar;
    private JMenu menuRegras;
    private JMenuItem menuSerHost;
    private JMenuItem menuVisualizarRegras;
    private JPanel panelJogadas;
    private JPanel panelTabuleiro;
    private JTextArea textAreaJogadas;
    
    public static void main(String[] args) {
        MainWindow2 mainWindow = new MainWindow2();
        mainWindow.setVisible(true);
    }

    public MainWindow2() {
        buttonDado = new JButton("Girar dado");
        scrollPane = new JScrollPane();
        menuBar = new JMenuBar();
        menuEntrarPartida = new JMenuItem("Entrar em partida");
        menuJogar = new JMenu("Jogar");
        menuRegras = new JMenu("Regras");
        menuSerHost = new JMenuItem("Ser host");
        menuVisualizarRegras = new JMenuItem("Visualizar");
        panelJogadas = new JPanel();
        panelTabuleiro = new JPanel();
        textAreaJogadas = new JTextArea();

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

        menuJogar.add(menuEntrarPartida);
        menuJogar.add(menuSerHost);
        menuBar.add(menuJogar);
        menuRegras.add(menuVisualizarRegras);
        menuBar.add(menuRegras);
        setJMenuBar(menuBar);

        pack();
    }
}
