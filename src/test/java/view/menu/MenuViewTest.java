package view.menu;

import model.Menu.MenuModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.View;

import java.io.IOException;

public class MenuViewTest {

    View view;
    MenuModel model;

    @BeforeEach
    public void initView() throws IOException {
        model = new MenuModel();
        view = Mockito.spy(new MenuView(model));
    }

    @Test
    public void drawTest() throws IOException {
        view.draw(Mockito.anyInt());
        Mockito.verify((MenuView) view, Mockito.times(1)).graphicSettings();
        Mockito.verify((MenuView) view, Mockito.times(1)).drawGameName();
        Mockito.verify((MenuView) view, Mockito.times(1)).drawOptions();

    }
}
