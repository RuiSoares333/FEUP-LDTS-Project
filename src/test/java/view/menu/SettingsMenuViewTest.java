package view.menu;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.Constants;
import model.Menu.MenuModel;
import model.Soldado;
import model.settings.SettingsModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.View;

import java.io.IOException;

public class SettingsMenuViewTest {

    View view;
    SettingsModel model;

    @BeforeEach
    public void initView() throws IOException {
        Soldado soldado = new Soldado();
        model = new SettingsModel(soldado);
        view = Mockito.spy(new SettingsMenuView(model));
    }

    @Test
    public void drawTest() throws IOException {
        view.draw(0);
        Mockito.verify((SettingsMenuView)view, Mockito.times(1)).graphicSettings(Mockito.any());
        Mockito.verify((SettingsMenuView)view, Mockito.times(1)).drawHeader(Mockito.any());

        Mockito.verify((SettingsMenuView)view, Mockito.times(1)).drawRecruit(Mockito.any());
        Mockito.verify((SettingsMenuView)view, Mockito.times(1)).drawRecruitNome(Mockito.any());

        Mockito.verify((SettingsMenuView)view, Mockito.times(1)).drawTanky(Mockito.any());
        Mockito.verify((SettingsMenuView)view, Mockito.times(1)).drawTankyNome(Mockito.any());

        Mockito.verify((SettingsMenuView)view, Mockito.times(1)).drawExpert(Mockito.any());
        Mockito.verify((SettingsMenuView)view, Mockito.times(1)).drawExpertNome(Mockito.any());

        Mockito.verify((SettingsMenuView)view, Mockito.times(1)).draw(Mockito.anyInt());
    }
}
