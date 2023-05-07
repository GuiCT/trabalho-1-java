package game;

import view.MainWindow;
import view.MainWindow2;

public class AuxController extends Thread{
    ControllerJogo controllerJogo;
    MainWindow2 mainWindow;

    private AuxController(ControllerJogo controllerJogo, MainWindow2 mainWindow){
        this.controllerJogo = controllerJogo;
        this.mainWindow = mainWindow;
        start();
    }

    public void run(){
        while(true){
            try{
                controllerJogo.escutarOponente();
                mainWindow.updateView();
            }catch (Exception e){
                System.out.println("Error: " + e);
            }
        }
    }
    //Somente algumas sugestões:
    //Adicionar um método updateView para que a thread possa atualizar o tabuleiro depois de escutar o oponente
    //Ao instaciar o ControllerJogo na interface, instanciar em seguida esta classe e passar como parâmetros os
    //a mainWindow e o ControllerJogo instanciado
}
