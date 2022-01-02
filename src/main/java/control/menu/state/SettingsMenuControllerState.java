package control.menu.state;


import control.MenuCommand;

import model.settings.SettingsModel;
import view.menu.SettingsMenuView;

import java.io.IOException;

public class SettingsMenuControllerState extends ControllerState<SettingsModel> {

    SettingsModel model = new SettingsModel();
    SettingsMenuView view = new SettingsMenuView(model);

    public SettingsMenuControllerState(SettingsModel settingsModel, FactoryState state) throws IOException {
        super(state);
    }

    public ControllerState<?> run() throws IOException {

        view.draw(getPosition(model.getSelected()));
        return processKey(model, view.getCommand());

    }

    ControllerState<?> processKey(SettingsModel model, MenuCommand key) throws IOException {
        ControllerState<?> newState = this;
        switch (key.getCommandEnum()) {
            case LEFT -> model.previousSelected();
            case RIGHT -> model.nextSelected();
            case SELECT, QUIT -> newState = state.genMenuState();
        }
        return newState;
    }


    public int getPosition(SettingsModel.Heroi selected){
        return switch (selected) {
            case TANKY -> 336;
            case EXPERT -> 560;
            default -> 112;
        };
    }
}
