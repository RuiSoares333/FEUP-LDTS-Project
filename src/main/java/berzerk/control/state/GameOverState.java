package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import berzerk.model.ranking.GameOverModel;
import berzerk.model.ranking.RankingModel;
import berzerk.view.View;
import berzerk.view.menu.MenuView;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameOverState extends ControllerState<GameOverModel> {

    protected final GameOverModel model;

    public GameOverState(FactoryState state, Soldado soldado, View<GameOverModel> view) {
        super(state, soldado, view);
        model = view.getModel();
    }

    @Override
    public ControllerState<?> run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        view.draw(0);
        manageCommand(processKey(view.getCommand()));
        return getState().genMenuState(soldado, new MenuView(new MenuModel(), new Ecra()));
    }

    @Override
    ControllerState<?> processKey(Command.COMMAND key) throws IOException {
        return null;
    }
}
