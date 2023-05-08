package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import game.ControllerJogo;
import game.Cor;
import game.Posicao;

public class UITabuleiro extends JPanel {
    private Casa[][] casas;
    private ControllerJogo controllerJogo;

    public UITabuleiro() {
        setLayout(new GridLayout(15, 15));
        casas = new Casa[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                casas[i][j] = new Casa(new Posicao2D(j, i));
                add(casas[i][j]);
            }
        }

        pintarCasas();
    }

    public void pintarCasas() {
        // Verde
        // Base
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                casas[i][j].setCor(Color.GREEN);
            }
        }

        // Casas
        for (Posicao2D pos : Mappings.posicaoCasas.get(Cor.VERDE)) {
            casas[pos.y][pos.x].setCor(Color.WHITE);
        }

        // Fila
        for (Posicao2D pos : Mappings.posicaoFilas.get(Cor.VERDE)) {
            casas[pos.y][pos.x].setCor(Color.GREEN);
        }

        // Amarelo
        // Base
        for (int i = 0; i < 6; i++) {
            for (int j = 9; j < 15; j++) {
                casas[i][j].setCor(Color.YELLOW);
            }
        }

        // Casas
        for (Posicao2D pos : Mappings.posicaoCasas.get(Cor.AMARELO)) {
            casas[pos.y][pos.x].setCor(Color.WHITE);
        }

        // Fila
        for (Posicao2D pos : Mappings.posicaoFilas.get(Cor.AMARELO)) {
            casas[pos.y][pos.x].setCor(Color.YELLOW);
        }

        // Vermelho
        // Base
        for (int i = 9; i < 15; i++) {
            for (int j = 0; j < 6; j++) {
                casas[i][j].setCor(Color.RED);
            }
        }

        // Casas
        for (Posicao2D pos : Mappings.posicaoCasas.get(Cor.VERMELHO)) {
            casas[pos.y][pos.x].setCor(Color.WHITE);
        }

        // Fila
        for (Posicao2D pos : Mappings.posicaoFilas.get(Cor.VERMELHO)) {
            casas[pos.y][pos.x].setCor(Color.RED);
        }

        // Azul
        // Base
        for (int i = 9; i < 15; i++) {
            for (int j = 9; j < 15; j++) {
                casas[i][j].setCor(Color.BLUE);
            }
        }

        // Casas
        for (Posicao2D pos : Mappings.posicaoCasas.get(Cor.AZUL)) {
            casas[pos.y][pos.x].setCor(Color.WHITE);
        }

        // Fila
        for (Posicao2D pos : Mappings.posicaoFilas.get(Cor.AZUL)) {
            casas[pos.y][pos.x].setCor(Color.BLUE);
        }

        // Casas especiais
        for (int casaEspecial : Mappings.posicoesEspeciais)
            casas[Mappings.posicoesTabuleiro[casaEspecial].y][Mappings.posicoesTabuleiro[casaEspecial].x]
                    .setCor(Color.GRAY);

        // Tabuleiro, casa de saída de cada cor
        casas[Mappings.posicoesTabuleiro[0].y][Mappings.posicoesTabuleiro[0].x].setCor(Color.YELLOW);
        casas[Mappings.posicoesTabuleiro[13].y][Mappings.posicoesTabuleiro[13].x].setCor(Color.BLUE);
        casas[Mappings.posicoesTabuleiro[26].y][Mappings.posicoesTabuleiro[26].x].setCor(Color.RED);
        casas[Mappings.posicoesTabuleiro[39].y][Mappings.posicoesTabuleiro[39].x].setCor(Color.GREEN);

        // 9 casas do centro serão pretas
        for (int i = 6; i < 9; i++) {
            for (int j = 6; j < 9; j++) {
                casas[i][j].setCor(Color.BLACK);
            }
        }
    }

    public void setControllerJogo(ControllerJogo controllerJogo) {
        this.controllerJogo = controllerJogo;

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                casas[i][j].addActionListener(this::atualizarPeaoSelecionado);
            }
        }
    }

    public void inicializarPeoes(Posicao[] posicoesJogador, Posicao[] posicoesOponente) {
        Posicao2D[] posicaoCasasJogador = Mappings.posicaoCasas.get(controllerJogo.getCorJogador());
        Posicao2D[] posicaoCasasOponente = Mappings.posicaoCasas.get(controllerJogo.getCorOponente());
        Cor corJogador = controllerJogo.getCorJogador();
        Cor corOponente = controllerJogo.getCorOponente();
        for (int i = 0; i < 4; i++) {
            Posicao2D posicao2DJogador = posicaoCasasJogador[posicoesJogador[i].posicao];
            casas[posicao2DJogador.y][posicao2DJogador.x].adicionarPeao(corJogador);
            Posicao2D posicao2DOponente = posicaoCasasOponente[posicoesOponente[i].posicao];
            casas[posicao2DOponente.y][posicao2DOponente.x].adicionarPeao(corOponente);
        }
    }

    // Atualiza TODOS os peões de uma determinada cor
    public void atualizarPeoes(Posicao[] posicoesAnteriores, Posicao[] posicoesAtuais, Cor cor) {
        for (int i = 0; i < 4; i++)
            atualizarPeao(posicoesAnteriores[i], posicoesAtuais[i], cor);
    }

    // Atualiza apenas um único peão de uma determinada cor
    public void atualizarPeao(Posicao posicaoAnterior, Posicao posicaoAtual, Cor cor) {
        // Atualizar posição anterior para não conter peão
        switch (posicaoAnterior.status) {
            case BASE -> {
                Posicao2D posicao2DAnterior = Mappings.posicaoCasas.get(cor)[posicaoAnterior.posicao];
                casas[posicao2DAnterior.y][posicao2DAnterior.x].removerPeao(cor);
            }
            case TABULEIRO -> {
                Posicao2D posicao2DAnterior = Mappings.posicoesTabuleiro[Mappings.calcularPosicaoRealTabuleiro(cor,
                        posicaoAnterior.posicao)];
                casas[posicao2DAnterior.y][posicao2DAnterior.x].removerPeao(cor);
            }
            case FILA -> {
                Posicao2D posicao2DAnterior = Mappings.posicaoFilas.get(cor)[posicaoAnterior.posicao];
                casas[posicao2DAnterior.y][posicao2DAnterior.x].removerPeao(cor);
            }
            case FINAL -> {
            }
        }
        // Colocar peão na posição nova
        switch (posicaoAtual.status) {
            case BASE -> {
                Posicao2D posicao2Dnova = Mappings.posicaoCasas.get(cor)[posicaoAtual.posicao];
                casas[posicao2Dnova.y][posicao2Dnova.x].adicionarPeao(cor);
            }
            case TABULEIRO -> {
                Posicao2D posicao2Dnova = Mappings.posicoesTabuleiro[Mappings.calcularPosicaoRealTabuleiro(cor,
                        posicaoAtual.posicao)];
                casas[posicao2Dnova.y][posicao2Dnova.x].adicionarPeao(cor);
            }
            case FILA -> {
                Posicao2D posicao2Dnova = Mappings.posicaoFilas.get(cor)[posicaoAtual.posicao];
                casas[posicao2Dnova.y][posicao2Dnova.x].adicionarPeao(cor);
            }
            case FINAL -> {
            }
        }
    }

    private void atualizarPeaoSelecionado(ActionEvent evt) {
        Casa casa = (Casa) evt.getSource();
        Posicao2D posicao2DSelecionada = casa.posicao;
        Posicao[] posicoesPeoes = controllerJogo.getPosicoesPeoesJogador();
        for (int i = 0; i < 4; i++) {
            Posicao2D posicao2DPeao = switch (posicoesPeoes[i].status) {
                case BASE -> Mappings.posicaoCasas.get(controllerJogo.getCorJogador())[posicoesPeoes[i].posicao];
                case TABULEIRO -> Mappings.posicoesTabuleiro[Mappings.calcularPosicaoRealTabuleiro(controllerJogo.getCorJogador(),
                        posicoesPeoes[i].posicao)];
                case FILA -> Mappings.posicaoFilas.get(controllerJogo.getCorJogador())[posicoesPeoes[i].posicao];
                case FINAL -> null;
            };
            if (posicao2DPeao != null && posicao2DPeao.equals(posicao2DSelecionada)) {
                controllerJogo.setPeaoSelecionado(i);
                return;
            }
        }
        controllerJogo.setPeaoSelecionado(null);
    }
}
