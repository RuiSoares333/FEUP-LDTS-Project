package control.menu.state;

import control.MenuCommand;
import model.Model;
import view.View;

import java.io.IOException;

public abstract class ControllerState <T extends Model>{

    FactoryState state;
//    Settings settings;

    protected boolean running = true;

    protected View view;
    protected MenuCommand command = new MenuCommand();

    public ControllerState(FactoryState state){
        this.state = state;
    }

    public abstract ControllerState<?> run() throws IOException, InterruptedException;

}
