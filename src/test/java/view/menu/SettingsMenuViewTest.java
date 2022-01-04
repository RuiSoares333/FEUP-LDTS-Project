package view.menu;

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
        Mockito.verify((SettingsMenuView)view, Mockito.times(1)).drawHeader();


        Mockito.verify((SettingsMenuView)view, Mockito.times(1)).drawTanky();
        Mockito.verify((SettingsMenuView)view, Mockito.times(1)).drawNomeSoldados();


        Mockito.verify((SettingsMenuView)view, Mockito.times(1)).draw(Mockito.anyInt());
    }
}
