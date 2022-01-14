package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Model;
import berzerk.model.Soldado;
import berzerk.view.View;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class ControllerState <T extends Model>{

    protected final FactoryState state;
    protected final Soldado soldado;

    protected final View<T> view;

    public ControllerState(FactoryState state, Soldado soldado, View<T> view){
        this.state = state;
        this.soldado = soldado;
        this.view = view;
    }


    protected ControllerState<?> manageCommand(ControllerState<?> newState) throws IOException {
        if (newState != this)
            view.close();
        return newState;
    }



    public FactoryState getState() {
        return state;
    }


    public Soldado getSoldado() {
        return soldado;
    }


    public View<T> getView(){
        return view;
    }


    public abstract ControllerState<?> run() throws IOException, InterruptedException, URISyntaxException, FontFormatException;
    abstract ControllerState<?> processKey(Command.COMMAND key) throws IOException;

}
