package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.entity.Bullet;
import berzerk.model.game.GameModel;
import berzerk.model.menu.MenuModel;
import berzerk.model.ranking.GameOverModel;
import berzerk.view.GameView;
import berzerk.view.View;
import berzerk.view.menu.GameOverView;
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

    public ControllerState<?> run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        view.draw(0);

        if(model.getHero().getHp()==0){
            GameOverView gameOverView = new GameOverView(new GameOverModel(model.getEnemies().getScore()), new Ecra());
            model.endTimers();
            return manageCommand(state.genGameOverState(soldado, gameOverView));
        }
        if(!model.isLeaving(model.getHero(), model.getTerrain())){
            GameView newView = new GameView(new GameModel(soldado, model.getTerrain().getLevel()+1, model.getEnemies().getScore(), model.getHero().getHp()), new Ecra());
            model.endTimers();
            return manageCommand(state.genGameState(soldado, newView));
        }
        return processKey(view.getCommand(new Command()));
    }

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
