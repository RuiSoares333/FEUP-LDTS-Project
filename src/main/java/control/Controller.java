package control;

import control.menu.state.ControllerState;
import control.menu.state.FactoryState;
import model.Soldado;

import java.io.IOException;

public class Controller {
    ControllerState<?> state;
    Soldado soldado = new Soldado();

    public Controller(FactoryState factory) throws IOException {
        state = factory.genMenuState(soldado);
    }

    public void run() throws IOException, InterruptedException {
        do{
            state = state.run();
        }while (state != null);
    }
}
