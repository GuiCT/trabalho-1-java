package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Jogo {
    private static final Map<Cor, Integer> offsets = Map.of(
        Cor.AMARELO, 0,
        Cor.VERMELHO, 26
    );
    private static final int[] posicoesEspeciais = {0, 8, 13, 21, 26, 34, 39, 47};
    private static final int casasAteFim = 50;
    private static final int casasFila = 6;
    // ------------------------------------------------------------------------
    private Cor corJogador;
    private Cor corOponente;
    private boolean jogadorVencedor;
    private Status[] statusPeoesJogador;
    private Status[] statusPeoesOponente;
    private int[] posicoesJogador;
    private int[] posicoesOponente;

    public Jogo(Cor corJogador) {
        this.corJogador = corJogador;
        this.corOponente = corJogador == Cor.AMARELO ? Cor.VERMELHO : Cor.AMARELO;
        this.jogadorVencedor = false;
        this.statusPeoesJogador = new Status[4];
        this.statusPeoesOponente = new Status[4];

        for (int i = 0; i < 4; i++) {
            this.statusPeoesJogador[i] = Status.BASE;
            this.statusPeoesOponente[i] = Status.BASE;
        }

        this.posicoesJogador = new int[4];
        this.posicoesOponente = new int[4];
    }

    public boolean getJogadorVencedor() {
        return this.jogadorVencedor;
    }

    public void realizarMovimento(boolean jogador, int peao, int valorDado) {
        int posicaoBaseAtual;
        Status statusAtual;

        if (jogador) {
            posicaoBaseAtual = this.posicoesJogador[peao];
            statusAtual = this.statusPeoesJogador[peao];
        } else {
            posicaoBaseAtual = this.posicoesOponente[peao];
            statusAtual = this.statusPeoesOponente[peao];
        }
        
        // Decisão é feita com base no status atual
        if (statusAtual == Status.BASE) {
            // Peão só pode sair da base se o valor do dado for 6
            // É colocado na posição 0
            if (valorDado == 6) {
                statusAtual = Status.TABULEIRO;
                posicaoBaseAtual = 0;
            }
        } else if (statusAtual == Status.TABULEIRO) {
            // Se o peão estiver no tabuleiro, é somado o valor do dado à sua posição atual
            posicaoBaseAtual += valorDado;

            // Se o peão passar da última casa, ele é colocado na fila final
            // E a posição atual é atualizada para a posição da fila final
            if (posicaoBaseAtual >= casasAteFim) {
                statusAtual = Status.FILA;
                posicaoBaseAtual -= casasAteFim;
                
                if (posicaoBaseAtual == casasFila) {
                    // Chegou no final!
                    statusAtual = Status.FINAL;
                    posicaoBaseAtual = 0;
                } else if (posicaoBaseAtual > casasFila) {
                    // Se passar da última casa da fila
                    // Ele é colocado na última casa antes do fim
                    posicaoBaseAtual = casasFila - 1;
                }
            }

            // Verifica se há algum peão do oponente na posição atingida
            // Se houver, esse pẽao é colocado na base
            // Caso seja uma posição especial, não é verificado se há peão
            int posicaoReal = calcularPosicao(jogador, posicaoBaseAtual);

            if (!verificarPosicaoEspecial(posicaoReal)) {
                List<Integer> conflitos = verificarConflito(jogador, posicaoReal);
                for (int i : conflitos) {
                    if (jogador) {
                        this.statusPeoesOponente[i] = Status.BASE;
                        this.posicoesOponente[i] = 0;
                    } else {
                        this.statusPeoesJogador[i] = Status.BASE;
                        this.posicoesJogador[i] = 0;
                    }
                }
            }
        } else if (statusAtual == Status.FILA) {
            // Se o peão passar da última casa, ele é colocado na casa de chegada
            if (posicaoBaseAtual + valorDado <= casasFila) {
                posicaoBaseAtual = posicaoBaseAtual + valorDado;
                if (posicaoBaseAtual == casasFila) {
                    // Chegou no final!
                    statusAtual = Status.FINAL;
                    posicaoBaseAtual = 0;
                }
            }
        }

        if (jogador) {
            this.posicoesJogador[peao] = posicaoBaseAtual;
            this.statusPeoesJogador[peao] = statusAtual;
        } else {
            this.posicoesOponente[peao] = posicaoBaseAtual;
            this.statusPeoesOponente[peao] = statusAtual;
        }
    }

    private int calcularPosicao(boolean jogador, int posicao) {
        int offset = offsets.get(jogador ? this.corJogador : this.corOponente);
        return (posicao + offset) % (casasAteFim + 2);
    }

    private List<Integer> verificarConflito(boolean jogador, int posicao) {
        List<Integer> conflitos = new ArrayList<>();

        // Se jogador for true, verifica se há conflito com oponente
        // Se jogador for false, verifica se há conflito com jogador
        for (int i = 0; i < 4; i++) {
            int posicaoBase = jogador ?
                this.posicoesOponente[i] :
                this.posicoesJogador[i];
            int posicaoReal = calcularPosicao(!jogador, posicaoBase);
            if (posicaoReal == posicao) {
                conflitos.add(i);
            }
        }

        return conflitos;
    }

    private boolean verificarPosicaoEspecial(int posicao) {
        for (int posicaoEspecial : posicoesEspeciais) {
            if (posicao == posicaoEspecial) {
                return true;
            }
        }

        return false;
    }

    // Utilizando para teste
    // TODO: Remover após realizar todos os testes
    public static void main(String[] args) {
        // Testando o jogo
        Jogo jogo = new Jogo(Cor.AMARELO);
        // Jogador 1 joga o peão 0 e sai da base
        jogo.realizarMovimento(true, 0, 6);
        // Jogador 2 joga o peão 0 e sai da base
        jogo.realizarMovimento(false, 0, 6);
        // Jogador 1 joga o peão 0 e vai para a casa 1 (1)
        jogo.realizarMovimento(true, 0, 1);
        // Jogador 2 joga o peão 0 e vai para a casa 6 (32)
        jogo.realizarMovimento(false, 0, 6);
        // Jogador 1 joga o peão 0 e vai para a casa 2 (2)
        jogo.realizarMovimento(true, 0, 1);
        // Jogador 2 joga o peão 0 e vai para a casa 12 (38)
        jogo.realizarMovimento(false, 0, 6);
        // Jogador 1 joga o peão 0 e vai para a casa 3 (3)
        jogo.realizarMovimento(true, 0, 1);
        // Jogador 2 joga o peão 0 e vai para a casa 18 (44)
        jogo.realizarMovimento(false, 0, 6);
        // Jogador 1 joga o peão 0 e vai para a casa 4 (4)
        jogo.realizarMovimento(true, 0, 1);
        // Jogador 2 joga o peão 0 e vai para a casa 24 (50)
        jogo.realizarMovimento(false, 0, 6);
        // Jogador 1 joga o peão 0 e vai para a casa 5 (5)
        jogo.realizarMovimento(true, 0, 1);
        // Jogador 2 joga o peão 0 e vai para a casa 30 (4)
        jogo.realizarMovimento(false, 0, 6);
        // Jogador 1 joga o peão 0 e vai para a casa 6 (6)
        jogo.realizarMovimento(true, 0, 1);
        // Jogador 2 joga o peão 0 e vai para a casa 32 (6)
        // Conflito deve ocorrer.
        jogo.realizarMovimento(false, 0, 2);
    }

    //verifica se o movimento do jogador resultou em vitoria
    public boolean jogoFinalizado(boolean jogador){
        if(jogador){
            for(Status statusPeao: statusPeoesJogador){
                if(statusPeao != Status.FINAL){
                    return false;
                }
            }
            jogadorVencedor = true;
            return true;
        }
        else {
            for (Status statusPeao : statusPeoesOponente) {
                if (statusPeao != Status.FINAL) {
                    return false;
                }
            }
            jogadorVencedor = false;
            return true;
        }
    }
}
