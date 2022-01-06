package control.state;

import control.MenuCommand;
import model.Model;
import model.Soldado;
import view.View;

import java.io.IOException;

public abstract class ControllerState <T extends Model>{

    FactoryState state;
    Soldado soldado;

    protected View view;
    protected MenuCommand command = new MenuCommand();

    public ControllerState(FactoryState state, Soldado soldado, View view){
        this.state = state;
        this.soldado = soldado;
        this.view = view;
    }

    protected ControllerState<?> manageCommand(ControllerState<?> newState) throws IOException {
        if (newState != this)
            view.close();
        return newState;
    }

    public abstract ControllerState<?> run() throws IOException, InterruptedException;

}
