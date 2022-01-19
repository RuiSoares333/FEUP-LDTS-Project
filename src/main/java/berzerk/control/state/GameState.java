package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.game.GameModel;
import berzerk.model.menu.MenuModel;
import berzerk.model.ranking.GameOverModel;
import berzerk.view.GameView;
import berzerk.view.View;
import berzerk.view.menu.GameOverView;
import berzerk.view.menu.LastLevelView;
import berzerk.view.menu.MenuView;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class GameState extends ControllerState<GameModel>{

    private GameModel model;

    public GameState(FactoryState state, Soldado soldado, View<GameModel> view){
        super(state, soldado, view);

        if(view.getModel()!=null) {
            model = view.getModel();
            model.initTimers(view);
        }
    }

    public ControllerState<?> newLevel() throws IOException {
        model.endTimers();
        if(model.getTerrain().getLevel()==4){
            GameOverView gameOverView = new GameOverView(new GameOverModel(model.getEnemies().getScore()), new Ecra());
            return manageCommand(state.genGameOverState(soldado, gameOverView));
        }
        else if(model.getTerrain().getLevel()==3){
            LastLevelView newView = new LastLevelView(new GameModel(soldado, model.getTerrain().getLevel() + 1, model.getEnemies().getScore(), model.getHero().getHp()), new Ecra(), soldado.toString());
            return manageCommand(state.genGameState(soldado, newView));
        } else {
            GameView newView = new GameView(new GameModel(soldado, model.getTerrain().getLevel() + 1, model.getEnemies().getScore(), model.getHero().getHp()), new Ecra(), soldado.toString());
            return manageCommand(state.genGameState(soldado, newView));
        }
    }

    @Override
    public ControllerState<?> run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        if(model.getHero().getHp()==0){
            model.endTimers();
            GameOverView gameOverView = new GameOverView(new GameOverModel(model.getEnemies().getScore()), new Ecra());
            return manageCommand(state.genGameOverState(soldado, gameOverView));
        }

        if(!model.isLeaving(model.getHero(), model.getTerrain())) return newLevel();

        return processKey(view.getCommand(new Command()));
    }

    @Override
    public ControllerState<?> processKey(Command command) throws IOException {
        ControllerState<?> newState = this;
        switch (command.getCommand()) {
            case LEFT -> model.moveHero(model.getHero(), model.getShooter(), model.getTerrain(), model.getEnemies(), model.getHero().moveLeft());
            case RIGHT -> model.moveHero(model.getHero(), model.getShooter(), model.getTerrain(), model.getEnemies(),model.getHero().moveRight());
            case UP -> model.moveHero(model.getHero(), model.getShooter(), model.getTerrain(), model.getEnemies(),model.getHero().moveUp());
            case DOWN -> model.moveHero(model.getHero(), model.getShooter(), model.getTerrain(), model.getEnemies(),model.getHero().moveDown());
            case SPACE -> model.getShooter().heroFire(model.getHero());
            case CONSTRUCT -> model.getTerrain().placeStone(model.getHero());
            case QUIT -> newState = state.genMenuState(soldado, new MenuView(new MenuModel(), new Ecra()));
        }
        manageCommand(newState);
        return newState;
    }
}
