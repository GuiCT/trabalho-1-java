public class Peao {
    final public Cor cor;
    private int posicao;

    Peao(Cor m_Cor) {
        this.cor = m_Cor;
        this.posicao = -1;
    }

    public int getPosicao() {
        return posicao;
    }

    public Peao setPosicao(int m_Posicao) {
        this.posicao = m_Posicao;
        return this;
    }

    public static boolean conflitantes(Peao m_Peao1, Peao m_Peao2) {
        boolean mesmaCor = m_Peao1.cor == m_Peao2.cor;
        boolean mesmaPosicao = m_Peao1.getPosicao() == m_Peao2.getPosicao();
        boolean noTabuleiro = m_Peao1.getPosicao() != -1;
        return !mesmaCor && mesmaPosicao && noTabuleiro;
    }
}
