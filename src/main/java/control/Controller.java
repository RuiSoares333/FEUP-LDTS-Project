package control;

import control.menu.state.ControllerState;
import control.menu.state.FactoryState;

import java.io.IOException;

public class Controller {
    ControllerState<?> state;
    //Settings settings;

    public Controller(FactoryState factory) throws IOException {
        state = factory.genMenuState();
    }

    public void run() throws IOException, InterruptedException {
        do{
            state = state.run();
        }while (state != null);
    }
}
