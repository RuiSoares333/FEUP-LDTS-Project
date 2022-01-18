package berzerk.control.state;


import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import berzerk.model.settings.SettingsModel;
import berzerk.view.View;
import berzerk.view.menu.MenuView;

import java.io.IOException;


public class SettingsState extends ControllerState<SettingsModel> {

    protected SettingsModel model;

    public SettingsState(FactoryState state, Soldado soldado, View<SettingsModel> view){
        super(state, soldado, view);
        model = view.getModel();
    }

    @Override
    public ControllerState<?> run() throws IOException {
        view.draw(getPosition(model.getSelected()));
        return processKey(view.getCommand(new Command()));
    }

    @Override
    public ControllerState<?> processKey(Command command) throws IOException {
        ControllerState<?> newState = this;
        switch (command.getCommand()) {
            case LEFT -> model.previousSelected();
            case RIGHT -> model.nextSelected();
            case SELECT, QUIT -> newState = state.genMenuState(soldado, new MenuView(new MenuModel(), new Ecra()));
        }
        return manageCommand(newState);
    }


    protected int getPosition(Soldado.Heroi selected){
        if(selected!=null) {
            return switch (selected) {
                case TANKY -> 39;
                case EXPERT -> 63;
                default -> 14;
            };
        }
        return 0;
    }

}
