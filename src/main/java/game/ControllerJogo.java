package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

import view.MainWindow;
import view.UITabuleiro;
import java.net.ServerSocket;

public class ControllerJogo {
    private MainWindow mainWindow;
    private UITabuleiro tabuleiro;
    private Thread receberConexaoThread;
    private Thread jogoThread;
    private ServerSocket serverSocket;
    private boolean turn;
    private Socket socket;
    private Jogo jogo;
    private Integer peaoSelecionado;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public ControllerJogo(MainWindow mainWindow, UITabuleiro tabuleiro) {
        this.mainWindow = mainWindow;
        this.tabuleiro = tabuleiro;

        receberConexaoThread = new Thread(() -> {
            try {
                awaitConnection();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(mainWindow, e, "Erro ao estabelecer host", JOptionPane.ERROR_MESSAGE);
            } finally {
                initGame();
                JOptionPane.showMessageDialog(mainWindow, "Conexão estabelecida com oponente", "Sucesso!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        jogoThread = new Thread(() -> {
            while (true) {
                try {
                    escutarOponente();
                } catch (EOFException e) {
                    JOptionPane.showMessageDialog(mainWindow, "A conexão com o oponente foi encerrada",
                            "Erro de conexão", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(mainWindow, e, "Erro ao escutar oponente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public Jogo getJogo() {
        return jogo;
    }

    public Cor getCorJogador() {
        return jogo.getCorJogador();
    }

    public Cor getCorOponente() {
        return jogo.getCorOponente();
    }

    public Integer getPeaoSelecionado() {
        return peaoSelecionado;
    }

    public Posicao[] getPosicoesPeoesJogador() {
        return jogo.getPosicoesPeoesJogador();
    }

    public Posicao[] getPosicoesPeoesOponente() {
        return jogo.getPosicoesPeoesOponente();
    }

    public void setPeaoSelecionado(Integer peaoSelecionado) {
        this.peaoSelecionado = peaoSelecionado;
        if (peaoSelecionado != null) {
            mainWindow.appendToJogadas("Agora o peão selecionado é: " + peaoSelecionado + "!\n");
        } else {
            mainWindow.appendToJogadas("Agora não há nenhum peão selecionado!\n");
        }
    }

    public void hostMatch() {
        receberConexaoThread.start();
    }

    public void joinMatch(String address) throws IOException {
        if (receberConexaoThread != null && receberConexaoThread.isAlive()) {
            try {
                closeServerSocket();
                closeSocket();
            } catch (IOException e) {
                throw e;
            }
            receberConexaoThread.interrupt();
        }

        this.socket = new Socket(address, 7000);
        this.jogo = new Jogo(Cor.VERMELHO);
        passarVez();
        dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
        dataInputStream = new DataInputStream(this.socket.getInputStream());
        initGame();
    }

    public void moverPeao(int valorDado) throws IOException {
        if (this.turn) {
            if (peaoSelecionado == null)
                JOptionPane.showMessageDialog(mainWindow, "Selecione um peão primeiro!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            moverPeaoEEnviarMensagem(peaoSelecionado, valorDado);
        }
    }

    public void escutarOponente() throws IOException {
        String movimento[] = dataInputStream.readUTF().trim().split("\\s+");
        int peaoMovido = Integer.parseInt(movimento[1]);
        int valorDado = Integer.parseInt(movimento[0]);

        // Recupera status e posição dos peões antes do movimento
        // Tanto do jogador quanto do oponente
        Posicao[] posicoesAnterioresJogador = new Posicao[4];
        Posicao[] posicoesAnterioresOponente = new Posicao[4];
        for (int i = 0; i < 4; i++) {
            posicoesAnterioresJogador[i] = jogo.getPosicoesPeoesJogador()[i].clone();
            posicoesAnterioresOponente[i] = jogo.getPosicoesPeoesOponente()[i].clone();
        }

        // Realiza o movimento
        jogo.realizarMovimento(false, peaoMovido, valorDado);

        // Recupera posições dos peões após o movimento
        Posicao[] posicoesAtuaisJogador = new Posicao[4];
        Posicao[] posicoesAtuaisOponente = new Posicao[4];
        for (int i = 0; i < 4; i++) {
            posicoesAtuaisJogador[i] = jogo.getPosicoesPeoesJogador()[i].clone();
            posicoesAtuaisOponente[i] = jogo.getPosicoesPeoesOponente()[i].clone();
        }

        // Atualiza a view
        tabuleiro.atualizarPeoes(posicoesAnterioresJogador, posicoesAtuaisJogador, getCorJogador());
        tabuleiro.atualizarPeoes(posicoesAnterioresOponente, posicoesAtuaisOponente, getCorOponente());

        if (jogo.jogoFinalizado(false)) {
            JOptionPane.showMessageDialog(mainWindow, "Você Perdeu! KKKKKKK PATO!", "Fim de Jogo",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        receberVez();
        mainWindow.appendToJogadas("[Oponente] - Peão: " + peaoMovido + ", valor do dado: " + valorDado + "!\n");
    }

    public boolean verificarVitoria(boolean jogador) {
        return jogo.jogoFinalizado(jogador);
    }

    private void initGame() {
        tabuleiro.setControllerJogo(this);
        tabuleiro.inicializarPeoes(getPosicoesPeoesJogador(), getPosicoesPeoesOponente());
        jogoThread.start();
    }

    private void moverPeaoEEnviarMensagem(int numPeao, int valorDado) throws IOException {
        String movimento = Integer.toString(valorDado) + " " + Integer.toString(numPeao);

        // Recupera status e posição dos peões antes do movimento
        // Tanto do jogador quanto do oponente
        Posicao[] posicoesAnterioresJogador = new Posicao[4];
        Posicao[] posicoesAnterioresOponente = new Posicao[4];
        for (int i = 0; i < 4; i++) {
            posicoesAnterioresJogador[i] = jogo.getPosicoesPeoesJogador()[i].clone();
            posicoesAnterioresOponente[i] = jogo.getPosicoesPeoesOponente()[i].clone();
        }

        // Realiza o movimento
        jogo.realizarMovimento(true, numPeao, valorDado);

        // Recupera posições dos peões após o movimento
        Posicao[] posicoesAtuaisJogador = new Posicao[4];
        Posicao[] posicoesAtuaisOponente = new Posicao[4];
        for (int i = 0; i < 4; i++) {
            posicoesAtuaisJogador[i] = jogo.getPosicoesPeoesJogador()[i].clone();
            posicoesAtuaisOponente[i] = jogo.getPosicoesPeoesOponente()[i].clone();
        }

        // Atualiza a view
        tabuleiro.atualizarPeoes(posicoesAnterioresOponente, posicoesAtuaisOponente, getCorOponente());
        tabuleiro.atualizarPeoes(posicoesAnterioresJogador, posicoesAtuaisJogador, getCorJogador());

        passarVez();
        mainWindow.appendToJogadas("[Você] - Peão: " + numPeao + ", valor do dado: " + valorDado + "!\n");
        dataOutputStream.writeUTF(movimento);
        if (jogo.jogoFinalizado(true)) {
            JOptionPane.showMessageDialog(mainWindow, "Você ganhou! Xis Dê", "Fim de Jogo",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void awaitConnection() throws IOException {
        JOptionPane.showMessageDialog(
                mainWindow,
                "Aguardando conexão no endereço: " + java.net.InetAddress.getLocalHost().getHostAddress());
        serverSocket = new ServerSocket(7000);
        socket = serverSocket.accept();
        jogo = new Jogo(Cor.AMARELO);
        receberVez();
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
        serverSocket.close();
    }

    private void receberVez() {
        peaoSelecionado = null;
        turn = true;
        mainWindow.setLabelVez("Sua vez");
    }

    private void passarVez() {
        turn = false;
        mainWindow.setLabelVez("Vez do oponente");
    }

    private void closeServerSocket() throws IOException {
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }
    }

    private void closeSocket() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}