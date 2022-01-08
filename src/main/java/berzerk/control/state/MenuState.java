package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import berzerk.view.menu.MenuView;

import java.io.IOException;


public class MenuState extends ControllerState<MenuModel> {

    private final MenuModel model;

    public MenuState(FactoryState state, Soldado soldado, MenuView view){
        super(state, soldado, view);
        model = view.getModel();
    }


    public ControllerState<?> run() throws IOException {
        if(model!=null) getView().draw(getPosition(model.getSelected()));
        Command.COMMAND key = getView().getCommand();
        return processKey(key);
    }


     private ControllerState<?> processKey(Command.COMMAND key) throws IOException{
        if(key != null && model!=null) {
            ControllerState<?> newState = this;
            switch (key) {
                case UP -> model.previousSelected();
                case DOWN -> model.nextSelected();
                case SELECT -> {
                    if (model.getSelected() != null) {
                        switch (model.getSelected()) {
                            case PLAY -> newState = getState().genGameState(getSoldado());

                            case RANKS -> newState = getState().genRankingMenuState(getSoldado());

                            case SETT -> newState = getState().genSettingsMenuState(getSoldado());

                            case EXIT -> newState = null;
                        }
                    }
                }
                case QUIT -> newState = null;
            }
            manageCommand(newState);
            return newState;
        }

        return null;
     }


    private int getPosition(MenuModel.Opcao selected){
        if(selected != null)
        return switch (selected) {
            case SETT -> 12;
            case RANKS -> 14;
            case EXIT -> 18;
            default -> 10;
        };

        return 0;
    }

}