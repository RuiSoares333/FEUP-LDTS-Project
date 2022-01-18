package berzerk.control.state;

import berzerk.model.Soldado;
import berzerk.view.GameView;
import berzerk.view.menu.GameOverView;
import berzerk.view.menu.MenuView;
import berzerk.view.menu.RankingView;
import berzerk.view.menu.SettingsView;



public class FactoryState {

    public MenuState genMenuState(Soldado soldado, MenuView view){
        return new MenuState(this, soldado, view);
    }

    public RankingState genRankingMenuState(Soldado soldado, RankingView view){
        return new RankingState(this, soldado, view);
    }

    public SettingsState genSettingsMenuState(Soldado soldado, SettingsView view){
        return new SettingsState(this, soldado, view);
    }

    public GameState genGameState(Soldado soldado, GameView view) {
        return new GameState(this, soldado, view);
    }

    public GameOverState genGameOverState(Soldado soldado, GameOverView view){
        return new GameOverState(this, soldado, view);
    }
}
