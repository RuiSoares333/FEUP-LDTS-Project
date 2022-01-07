package berzerk.view.menu;

import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.menu.MenuModel;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.NotNull;
import org.mockito.stubbing.Answer;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MenuViewTest {

    MenuView view;
    MenuModel model;
    Screen screen;
    TextGraphics graphics;
    Ecra ecra;

    @BeforeEach
    public void initView(){
        model = mock(MenuModel.class);
        ecra = mock(Ecra.class);
        screen = mock(Screen.class);
        graphics = mock(TextGraphics.class);
        view = new MenuView(model, ecra);
    }

    @Test
    public void drawTest() throws IOException {
        when(view.getEcra().getScreen()).thenAnswer(invocation -> screen);
        when(view.getEcra().getGraphics()).thenAnswer(invocation -> graphics);

        view.draw(0);
        Mockito.verify(graphics, Mockito.times(4+ Constants.GAME_NAME.length)).putString(any(TerminalPosition.class), anyString());
    }
}