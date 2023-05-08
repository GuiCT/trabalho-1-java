package view;

public class Posicao2D {
    public int x;
    public int y;

    public Posicao2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Posicao2D)) {
            return false;
        }
        Posicao2D posicao2D = (Posicao2D) obj;
        return posicao2D.x == x && posicao2D.y == y;
    }
}
