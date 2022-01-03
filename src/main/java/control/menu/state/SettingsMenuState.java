package control.menu.state;


import control.MenuCommand;

import model.Menu.MenuModel;
import model.Soldado;
import model.settings.SettingsModel;
import view.View;

import java.io.IOException;

public class SettingsMenuState extends ControllerState<SettingsModel> {

    SettingsModel model;

    public SettingsMenuState(FactoryState state, Soldado soldado, View view){
        super(state, soldado, view);
        model = (SettingsModel) view.getModel();
    }

    public ControllerState<?> run() throws IOException {

        view.draw(getPosition(model.getSelected()));
        return processKey(view.getCommand());

    }

    public ControllerState<?> processKey(MenuCommand key) throws IOException {
        ControllerState<?> newState = this;
        switch (key.getCommandEnum()) {
            case LEFT -> model.previousSelected();
            case RIGHT -> model.nextSelected();
            case SELECT, QUIT -> newState = state.genMenuState(soldado);
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
