package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.entity.Bullet;
import berzerk.model.entity.Stone;
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

    public GameState(FactoryState state, Soldado soldado, View<GameModel> view) {
        super(state, soldado, view);
        if(view.getModel()!=null) {
            model = view.getModel();
            model.scheduleMonsterMovement(view);
            model.scheduleDementorMovement(view);
            model.scheduleBulletMovement(view);
        }
    }

    public ControllerState<?> run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        view.draw(0);
        if(model.getHero().getHp()==0){
            GameOverView gameOverView = new GameOverView(new GameOverModel(model.getScore(), true), new Ecra());
            return manageCommand(state.genGameOverState(soldado, gameOverView));
        }
        if(!model.verifyCollision(model.getHero().getPosition(), model.getExit())){
            GameView newView = new GameView(new GameModel(soldado, model.getNivel()+1, model.getScore()), new Ecra());
            return manageCommand(state.genGameState(soldado, newView));
        }
        return processKey(view.getCommand());
    }

    public ControllerState<?> processKey(Command.COMMAND key) throws IOException {
        ControllerState<?> newState = this;
        switch (key) {
            case LEFT -> model.moveHero(model.getHero().moveLeft());
            case RIGHT -> model.moveHero(model.getHero().moveRight());
            case UP -> model.moveHero(model.getHero().moveUp());
            case DOWN -> model.moveHero(model.getHero().moveDown());
            case SPACE -> model.addBullet(new Bullet(model.getHero().getPosition().getX(), model.getHero().getPosition().getY(), model.getHero().getOrientation()));
            case CONSTRUCT -> model.addStone(new Stone(model.getHero().getPosition(), model.getHero().getOrientation()));
            case QUIT -> newState = state.genMenuState(soldado, new MenuView(new MenuModel(), new Ecra()));
        }
        manageCommand(newState);
        return newState;
    }
}
