package game;

public class Posicao {
    public Status status;
    public int posicao;

    public Posicao(Status status, int posicao) {
        this.status = status;
        this.posicao = posicao;
    }

    @Override
    public Posicao clone() {
        return new Posicao(this.status, this.posicao);
    }
}
