package game;

public class Posicao {
    public Status status;
    public int offset;

    public Posicao(Status status, int offset) {
        this.status = status;
        this.offset = offset;
    }

    @Override
    public Posicao clone() {
        return new Posicao(this.status, this.offset);
    }
}
