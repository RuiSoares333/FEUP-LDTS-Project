package berzerk.control.state;

import berzerk.model.Model;
import berzerk.model.Soldado;
import berzerk.view.View;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class ControllerState <T extends Model>{

    private final FactoryState state;
    private final Soldado soldado;

    private final View<T> view;

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

    public abstract ControllerState<?> run() throws IOException, InterruptedException, URISyntaxException, FontFormatException;


    public FactoryState getState() {
        return state;
    }


    public Soldado getSoldado() {
        return soldado;
    }


    public View<T> getView(){
        return view;
    }



}
