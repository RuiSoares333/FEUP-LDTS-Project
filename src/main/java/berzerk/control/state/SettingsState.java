package berzerk.control.state;


import berzerk.control.MenuCommand;
import berzerk.model.Soldado;
import berzerk.model.settings.SettingsModel;
import berzerk.view.View;

import java.io.IOException;

public class SettingsState extends ControllerState<SettingsModel> {

    SettingsModel model;

    public SettingsState(FactoryState state, Soldado soldado, View view){
        super(state, soldado, view);
        model = (SettingsModel) view.getModel();
    }

    public ControllerState<?> run() throws IOException {

        getView().draw(getPosition(model.getSelected()));
        return processKey(getView().getCommand());

    }

    public ControllerState<?> processKey(MenuCommand key) throws IOException {
        ControllerState<?> newState = this;
        switch (key.getCommandEnum()) {
            case LEFT -> model.previousSelected();
            case RIGHT -> model.nextSelected();
            case SELECT, QUIT -> newState = getState().genMenuState(getSoldado());
        }
        manageCommand(newState);
        return newState;
    }


    public int getPosition(Soldado.Heroi selected){
        return switch (selected) {
            case TANKY -> 39;
            case EXPERT -> 63;
            default -> 14;
        };
    }

    public SettingsModel getModel() {
        return model;
    }
}