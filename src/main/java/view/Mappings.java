package view;

import java.util.Map;

import game.Cor;

public class Mappings {
    public static final Posicao2D[] casasVerde = {
        new Posicao2D(1, 1),
        new Posicao2D(1, 4),
        new Posicao2D(4, 1),
        new Posicao2D(4, 4)
    };
    public static final Posicao2D[] casasAmarelo = {
        new Posicao2D(10, 1),
        new Posicao2D(13, 1),
        new Posicao2D(10, 4),
        new Posicao2D(13, 4)
    };
    public static final Posicao2D[] casasVermelho = {
        new Posicao2D(1, 10),
        new Posicao2D(4, 10),
        new Posicao2D(1, 13),
        new Posicao2D(4, 13)
    };
    public static final Posicao2D[] casasAzul = {
        new Posicao2D(10, 10),
        new Posicao2D(10, 13),
        new Posicao2D(13, 10),
        new Posicao2D(13, 13)
    };
    public static final Map<Cor, Posicao2D[]> posicaoCasas = Map.of(
        Cor.VERDE, casasVerde,
        Cor.AMARELO, casasAmarelo,
        Cor.VERMELHO, casasVermelho,
        Cor.AZUL, casasAzul
    );
    // ======================================================================
    public static final Posicao2D[] filaVerde = {
        new Posicao2D(1, 7),
        new Posicao2D(2, 7),
        new Posicao2D(3, 7),
        new Posicao2D(4, 7),
        new Posicao2D(5, 7)
    };
    public static final Posicao2D[] filaAmarelo = {
        new Posicao2D(7, 1),
        new Posicao2D(7, 2),
        new Posicao2D(7, 3),
        new Posicao2D(7, 4),
        new Posicao2D(7, 5)
    };
    public static final Posicao2D[] filaVermelho = {
        new Posicao2D(7, 13),
        new Posicao2D(7, 12),
        new Posicao2D(7, 11),
        new Posicao2D(7, 10),
        new Posicao2D(7, 9)
    };
    public static final Posicao2D[] filaAzul = {
        new Posicao2D(13, 7),
        new Posicao2D(12, 7),
        new Posicao2D(11, 7),
        new Posicao2D(10, 7),
        new Posicao2D(9, 7)
    };
    public static final Map<Cor, Posicao2D[]> posicaoFilas = Map.of(
        Cor.VERDE, filaVerde,
        Cor.AMARELO, filaAmarelo,
        Cor.VERMELHO, filaVermelho,
        Cor.AZUL, filaAzul
    );
    // ======================================================================
    public static final Posicao2D[] posicoesTabuleiro = {
        // 8, 1 até 8, 5
        new Posicao2D(8, 1),
        new Posicao2D(8, 2),
        new Posicao2D(8, 3),
        new Posicao2D(8, 4),
        new Posicao2D(8, 5),
        // 9, 6 até 14, 6
        new Posicao2D(9, 6),
        new Posicao2D(10, 6),
        new Posicao2D(11, 6),
        new Posicao2D(12, 6),
        new Posicao2D(13, 6),
        new Posicao2D(14, 6),
        // 14, 7
        new Posicao2D(14, 7),
        // 14, 8 até 9,8
        new Posicao2D(14, 8),
        new Posicao2D(13, 8),
        new Posicao2D(12, 8),
        new Posicao2D(11, 8),
        new Posicao2D(10, 8),
        new Posicao2D(9, 8),
        // 8, 9 até 8, 14
        new Posicao2D(8, 9),
        new Posicao2D(8, 10),
        new Posicao2D(8, 11),
        new Posicao2D(8, 12),
        new Posicao2D(8, 13),
        new Posicao2D(8, 14),
        // 7, 14
        new Posicao2D(7, 14),
        // 6, 14 até 6, 9
        new Posicao2D(6, 14),
        new Posicao2D(6, 13),
        new Posicao2D(6, 12),
        new Posicao2D(6, 11),
        new Posicao2D(6, 10),
        new Posicao2D(6, 9),
        // 5, 8 até 0, 8
        new Posicao2D(5, 8),
        new Posicao2D(4, 8),
        new Posicao2D(3, 8),
        new Posicao2D(2, 8),
        new Posicao2D(1, 8),
        new Posicao2D(0, 8),
        // 0, 7
        new Posicao2D(0, 7),
        // 0, 6 até 5, 6
        new Posicao2D(0, 6),
        new Posicao2D(1, 6),
        new Posicao2D(2, 6),
        new Posicao2D(3, 6),
        new Posicao2D(4, 6),
        new Posicao2D(5, 6),
        // 6, 5 até 6, 0
        new Posicao2D(6, 5),
        new Posicao2D(6, 4),
        new Posicao2D(6, 3),
        new Posicao2D(6, 2),
        new Posicao2D(6, 1),
        new Posicao2D(6, 0),
        // 7, 0
        new Posicao2D(7, 0),
        // 8, 0, fim do caminho principal
        new Posicao2D(8, 0)
    };
}
