package berzerk.control.state;

import berzerk.control.MenuCommand;
import berzerk.model.Arena;
import berzerk.model.Soldado;
import berzerk.view.GameView;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameState extends ControllerState<Arena>{

    Arena arena;

    public GameState(FactoryState state, Soldado soldado, GameView view, Arena arena) {
        super(state, soldado, view);
        this.arena = arena;
    }

    @Override
    public ControllerState<?> run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        getView().draw(0);
        return processKey(getView().getCommand());
    }

    public ControllerState<?> processKey(MenuCommand.COMMAND key) throws IOException, URISyntaxException, FontFormatException {
        ControllerState<?> newState = this;
        switch (key) {
            case LEFT -> arena.moveHero(arena.getHero().moveLeft());
            case RIGHT -> arena.moveHero(arena.getHero().moveRight());
            case UP -> arena.moveHero(arena.getHero().moveUp());
            case DOWN -> arena.moveHero(arena.getHero().moveDown());
        }
        manageCommand(newState);
        return newState;
    }
}
