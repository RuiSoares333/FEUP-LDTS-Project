package control.state;

import model.Soldado;
import model.menu.MenuModel;
import model.ranking.RankingMenuModel;
import model.settings.SettingsModel;
import view.menu.MenuView;
import view.menu.RankingMenuView;
import view.menu.SettingsMenuView;

import java.io.IOException;

public class FactoryState {

    public MenuState genMenuState(Soldado soldado) throws IOException {
        MenuModel model = new MenuModel();
        MenuView view = new MenuView(model);
        return new MenuState(this, soldado, view);
    }

    public RankingMenuState genRankingMenuState(Soldado soldado) throws IOException {
        RankingMenuModel model = new RankingMenuModel();
        RankingMenuView view = new RankingMenuView(model);
        return new RankingMenuState(this, soldado, view);
    }

    public SettingsMenuState genSettingsMenuState(Soldado soldado) throws IOException {
        SettingsModel model = new SettingsModel(soldado);
        SettingsMenuView view = new SettingsMenuView(model);
        return new SettingsMenuState(this, soldado, view);
    }
}
