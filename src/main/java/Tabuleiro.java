public class Tabuleiro {
    private Peao[] peoesJogador1;
    private Peao[] peoesJogador2;
    private final int tamanhoTabuleiro = 48;

    public Tabuleiro(){
        peoesJogador1 = new Peao[4];
        peoesJogador2 = new Peao[4];
        //inicializa os peoes dos jogadores
        for(int i = 0; i < 4; i++){
            peoesJogador1[i] = new Peao(1, tamanhoTabuleiro);
        }
        for(int i = 0; i < 4; i++){
            peoesJogador2[i] = new Peao(2, tamanhoTabuleiro);
        }
    }
}
