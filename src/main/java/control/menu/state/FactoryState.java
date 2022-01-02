package control.menu.state;

import com.googlecode.lanterna.screen.Screen;
import model.settings.SettingsModel;

import java.io.IOException;

public class FactoryState {

    public ControllerState genMenuState() throws IOException {
        return new MenuState(this);
    }

    public ControllerState genRankingMenuState() throws IOException {
        return new RankingMenuControllerState(this);
    }

    public ControllerState genSettingsMenuState(SettingsModel model) throws IOException {
        return new SettingsMenuControllerState(model, this);
    }
}
