package berzerk.control.state;

import berzerk.model.Arena;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.entity.hero.Hero;
import berzerk.model.menu.MenuModel;
import berzerk.model.ranking.RankingModel;
import berzerk.model.settings.SettingsModel;
import berzerk.view.GameView;
import berzerk.view.menu.MenuView;
import berzerk.view.menu.RankingView;
import berzerk.view.menu.SettingsView;


public class FactoryState {

    public MenuState genMenuState(Soldado soldado){
        MenuModel model = new MenuModel();
        MenuView view = new MenuView(model, new Ecra());
        return new MenuState(this, soldado, view);
    }

    public RankingState genRankingMenuState(Soldado soldado){
        RankingModel model = new RankingModel();
        RankingView view = new RankingView(model, new Ecra());
        return new RankingState(this, soldado, view);
    }

    public SettingsState genSettingsMenuState(Soldado soldado){
        SettingsModel model = new SettingsModel(soldado);
        SettingsView view = new SettingsView(model, new Ecra());
        return new SettingsState(this, soldado, view);
    }

    public GameState genGameState(Soldado soldado, int nivel){
        Hero hero = new Hero(10,10, 10, 3);
        Arena arena = new Arena(hero, nivel);
        GameView view = new GameView(new Ecra(), arena);
        arena.setView(view);
        return new GameState(this, soldado, view, arena);
    }
}
