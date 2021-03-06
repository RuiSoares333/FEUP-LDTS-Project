package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.game.GameModel;
import berzerk.model.menu.MenuModel;
import berzerk.model.ranking.RankingModel;
import berzerk.model.settings.SettingsModel;
import berzerk.view.GameView;
import berzerk.view.View;
import berzerk.view.menu.RankingView;
import berzerk.view.menu.SettingsView;

import java.io.IOException;


public class MenuState extends ControllerState<MenuModel> {

    private final MenuModel model;

    public MenuState(FactoryState state, Soldado soldado, View<MenuModel> view){
        super(state, soldado, view);
        model = view.getModel();
    }

    @Override
    public ControllerState<?> run() throws IOException {
        if(model!=null) view.draw(getPosition(model.getSelected()));
        return processKey(view.getCommand(new Command()));
    }

    @Override
    protected ControllerState<?> processKey(Command command) throws IOException{
        ControllerState<?> newState = this;
        if(command!=null) {
            switch (command.getCommand()) {
                case UP -> model.previousSelected();
                case DOWN -> model.nextSelected();
                case SELECT -> {
                    if (model.getSelected() != null) {
                        switch (model.getSelected()) {
                            case PLAY -> newState = state.genGameState(soldado, new GameView(new GameModel(soldado, 1), new Ecra(), soldado.toString()));
                            case RANKS -> newState = state.genRankingMenuState(soldado, new RankingView(new RankingModel(), new Ecra()));
                            case SETT -> newState = state.genSettingsMenuState(soldado, new SettingsView(new SettingsModel(soldado), new Ecra()));
                            case EXIT -> newState = null;
                        }
                    }
                    else newState = null;
                }
                case QUIT -> newState = null;
            }
            return manageCommand(newState);
        }
        else return newState;
    }



    public int getPosition(MenuModel.Opcao selected){
        if(selected != null)
        return switch (selected) {
            case SETT -> 12;
            case RANKS -> 14;
            case EXIT -> 18;
            default -> 10;
        };
        else {
            return 0;
        }
    }

}