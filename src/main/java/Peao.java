public class Peao {
    private Cor cor;
    private int posicao;
    private int offset; // indica qual o jogador o peao pertence
    private int tamanhoTabuleiro;
    Peao(int jogador, int tamanhoTabuleiro) {
        switch(jogador){
            case 1:
                this.offset = 0;
                break;
            case 2:
                this.offset = tamanhoTabuleiro/2;
            break;
            default:
                break;
        }

    }

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

    public int getTamanhoTabuleiro() {
        return tamanhoTabuleiro;
    }

    public void mover(int numeroCasas){
        if(this.posicao != -1){
            this.posicao = this.posicao + numeroCasas;
        }
        else if(numeroCasas == 6){
            this.posicao = offset;
        }
    }

    public int posicaoTabuleiro(){
        return (this.posicao + this.offset) % this.tamanhoTabuleiro;
    }

    public static boolean conflitantes(Peao meuPeao, Peao peaoInimigo) {
        //caso o peão inimigo estiver a pelo menos 6 casas de distância do ponto final,
        //o peão estará no meio do tabuleiro e já não pode mais ser capturado pelo inimigo
        if(peaoInimigo.posicaoTabuleiro() == meuPeao.posicaoTabuleiro() && peaoInimigo.getPosicao() < peaoInimigo.tamanhoTabuleiro-6){
            return true;
        }
        return false;
    }

}
