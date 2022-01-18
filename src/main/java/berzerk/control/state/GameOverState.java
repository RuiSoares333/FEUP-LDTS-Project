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
        return processKey(view.getCommand(new Command()));
    }

    @Override
    ControllerState<?> processKey(Command command) throws IOException {
        ControllerState<?> nextState = this;
        switch (command.getCommand()) {
            case DELETE -> model.deleteLastCharacter();

            case SELECT -> {
                if (model.getName().length() == 3) {
                    model.saveScores();
                    nextState = getState().genMenuState(soldado, new MenuView(new MenuModel(), new Ecra()));
                }
            }
            case TYPE -> {
                if (model.getName().length() != 3)
                    model.addCharacter(command.getKey());
            }

            case QUIT -> nextState = getState().genMenuState(soldado, new MenuView(new MenuModel(), new Ecra()));
        }

        return manageCommand(nextState);
    }
}
