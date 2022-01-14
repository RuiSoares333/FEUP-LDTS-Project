package berzerk.control;

import berzerk.control.state.ControllerState;
import berzerk.control.state.FactoryState;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import berzerk.view.menu.MenuView;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Controller {
    ControllerState<?> state;
    Soldado soldado = new Soldado();

    public Controller(FactoryState factory){
        state = factory.genMenuState(soldado, new MenuView(new MenuModel(), new Ecra()));
    }

    public void run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        do{
            state = state.run();
        }while (state != null);
    }
}
