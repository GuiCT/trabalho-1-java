public class Jogo {
    Peao[] peoes;
    int[] posicoes;

    public static void main(String[] args) {
        Jogo instanciaJogo = new Jogo();
        instanciaJogo.run();
    }

    public void run() {
        peoes = new Peao[8];
        posicoes = new int[]{ -1, -1, -1, -1, -1, -1, -1, -1 };

        for (int i = 0; i < 4; i++) {
            peoes[i] = new Peao(Cor.VERMELHO);
        }
        for (int i = 4; i < 8; i++) {
            peoes[i] = new Peao(Cor.AZUL);
        }

        // Mesma cor na mesma posição -> Tranquilo.
        peoes[0].setPosicao(10);
        peoes[1].setPosicao(10);
        System.out.println(Peao.conflitantes(peoes[0], peoes[1]) ? "Conflito" : "Jogo prossegue");

        // Cores diferentes na mesma posição -> Deu ruim.
        peoes[3].setPosicao(20);
        peoes[4].setPosicao(20);
        System.out.println(Peao.conflitantes(peoes[3], peoes[4]) ? "Conflito" : "Jogo prossegue");
    }
}
