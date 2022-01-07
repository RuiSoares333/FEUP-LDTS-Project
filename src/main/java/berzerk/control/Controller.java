package berzerk.control;

import berzerk.control.state.ControllerState;
import berzerk.control.state.FactoryState;
import berzerk.model.Soldado;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Controller {
    ControllerState<?> state;
    Soldado soldado = new Soldado();

    public Controller(FactoryState factory) throws IOException, URISyntaxException, FontFormatException {
        state = factory.genMenuState(soldado);
    }

    public void run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        do{
            state = state.run();
        }while (state != null);
    }
}
