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
    }

    public void hostMatch() {
        receberConexaoThread = new Thread(() -> {
            try {
                awaitConnection();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(mainWindow, e, "Erro ao estabelecer host", JOptionPane.ERROR_MESSAGE);
            } finally {
                JOptionPane.showMessageDialog(mainWindow, "Conexão estabelecida com oponente", "Sucesso!",
                        JOptionPane.INFORMATION_MESSAGE);
                initGame();
            }
        });
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
        this.turn = false;
        dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
        dataInputStream = new DataInputStream(this.socket.getInputStream());
        initGame();
    }

    public void moverPeao(int valorDado) throws IOException {
        if (this.turn) {
            moverPeaoEEnviarMensagem(peaoSelecionado, valorDado);
        }
    }

    public void escutarOponente() throws IOException {
        if (!this.turn) {
            String movimento[] = dataInputStream.readUTF().trim().split("\\s+");
            int peaoMovido = Integer.parseInt(movimento[1]);
            int valorDado = Integer.parseInt(movimento[0]);
            JOptionPane.showMessageDialog(mainWindow, "O oponente moveu o peão " + peaoMovido + " em " + valorDado + " casas", "Movimento do oponente", JOptionPane.INFORMATION_MESSAGE);
            Posicao posicaoAnterior = jogo.getPosicoesPeoesOponente()[peaoMovido].clone();
            jogo.realizarMovimento(false, peaoMovido, valorDado);
            Posicao posicaoAtual = jogo.getPosicoesPeoesOponente()[peaoMovido];
            tabuleiro.atualizarPeoes(posicaoAnterior, posicaoAtual, jogo.getCorOponente());
            this.turn = true;
        }
    }

    public boolean verificarVitoria(boolean jogador) {
        return jogo.jogoFinalizado(jogador);
    }

    private void initGame() {
        tabuleiro.setControllerJogo(this);
        tabuleiro.inicializarPeoes(getPosicoesPeoesJogador(), getPosicoesPeoesOponente());
        jogoThread = new Thread(() -> {
            while (true) {
                try {
                    escutarOponente();
                } catch (EOFException e) {
                    JOptionPane.showMessageDialog(mainWindow, "A conexão com o oponente foi encerrada", "Erro de conexão", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(mainWindow, e, "Erro ao escutar oponente", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jogoThread.start();
    }

    private void moverPeaoEEnviarMensagem(int numPeao, int valorDado) throws IOException {
        String movimento = Integer.toString(valorDado) + " " + Integer.toString(numPeao);
        // Recupera status e posição do peão antes do movimento
        Posicao posicaoAnterior = jogo.getPosicoesPeoesJogador()[numPeao].clone();
        // Realiza o movimento
        jogo.realizarMovimento(true, numPeao, valorDado);
        // Recupera status e posição do peão após o movimento
        Posicao posicaoAtual = jogo.getPosicoesPeoesJogador()[numPeao];
        // Atualiza a view
        tabuleiro.atualizarPeoes(posicaoAnterior, posicaoAtual, jogo.getCorJogador());
        turn = false;
        JOptionPane.showMessageDialog(mainWindow, "Você moveu o peão " + numPeao + " em " + valorDado + " casas", "Movimento realizado", JOptionPane.INFORMATION_MESSAGE);
        dataOutputStream.writeUTF(movimento);
    }

    private void awaitConnection() throws IOException {
        JOptionPane.showMessageDialog(
                mainWindow,
                "Aguardando conexão no endereço: " + java.net.InetAddress.getLocalHost().getHostAddress());
        serverSocket = new ServerSocket(7000);
        socket = serverSocket.accept();
        jogo = new Jogo(Cor.AMARELO);
        turn = true;
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
        serverSocket.close();
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