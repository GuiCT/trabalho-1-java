package testing;

public class TesteOponente {
    public static void main(String[] args) {
        try {
            game.ControllerJogo controllerJogo = new game.ControllerJogo();
            controllerJogo.joinMatch("localhost");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
