package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Soldado;
import berzerk.model.game.GameModel;
import berzerk.view.GameView;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameState extends ControllerState<GameModel>{

    private final GameModel model;

    public GameState(FactoryState state, Soldado soldado, GameView view) {
        super(state, soldado, view);
        model = view.getModel();
        model.scheduleMonsterMovement(view);
    }

    public ControllerState<?> run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        view.draw(0);
        if(!model.verifyCollision(model.getHero().getPosition(), model.getExit())) return manageCommand(getState().genGameState(getSoldado(), view.getModel().getNivel() +1));
        return processKey(view.getCommand());
    }

    public ControllerState<?> processKey(Command.COMMAND key) throws IOException {
        ControllerState<?> newState = this;
        switch (key) {
            case LEFT -> model.moveHero(model.getHero().moveLeft());
            case RIGHT -> model.moveHero(model.getHero().moveRight());
            case UP -> model.moveHero(model.getHero().moveUp());
            case DOWN -> model.moveHero(model.getHero().moveDown());
            case SPACE -> model.moveHero(model.getHero().moveDown());
            case QUIT -> newState = getState().genMenuState(getSoldado());
        }
        manageCommand(newState);
        return newState;
    }
}
