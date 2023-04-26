package game;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class ControllerJogo {
    private boolean turn;
    private Socket socket;
    private Jogo jogo;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;


    public void hostMatch() throws Exception{
        ServerSocket listenSocket = new ServerSocket(7000);
        this.socket = listenSocket.accept();
        this.jogo = new Jogo(Cor.AMARELO);
        dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
        dataInputStream = new DataInputStream(this.socket.getInputStream());
    }

    public void joinMatch(String address) throws Exception{
        this.socket = new Socket(address, 7000);
        this.jogo = new Jogo(Cor.VERMELHO);
        dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
        dataInputStream = new DataInputStream(this.socket.getInputStream());
    }

    public void moverPeao(int numPeao) throws Exception{
        if(this.turn){
            int valorDado = ((int)(Math.random() * 6)) + 1;
            String movimento = Integer.toString(valorDado) + " " + Integer.toString(numPeao);
            dataOutputStream.writeUTF(movimento);
            this.turn = false;
        }
    }

    public void escutarOponente() throws Exception{
        String movimento[] = dataInputStream.readUTF().trim().split("\\s+");
        jogo.realizarMovimento(false, Integer.parseInt(movimento[1]), Integer.parseInt(movimento[0]));
        this.turn = true;
    }

    public boolean verificarVitoria(boolean jogador){
        return jogo.jogoFinalizado(jogador);
    }
}