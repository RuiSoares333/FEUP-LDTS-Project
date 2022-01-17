package berzerk.view.menu;

import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.menu.MenuModel;
import berzerk.model.menu.MenuModelTest;
import berzerk.view.IndicadorView;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


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
        view = spy(new MenuView(model, ecra));
        view.setGraphics(graphics);
        when(view.getScreen()).thenReturn(screen);
        when(view.getGraphics()).thenReturn(graphics);
    }

    @AfterEach
    public void closeScreen() throws IOException {
        view.close();
    }

    @Test
    public void drawTest() throws IOException {
        view.draw(anyInt());
        verify(graphics, Mockito.times(4+ Constants.GAME_NAME.length)).putString(any(TerminalPosition.class), anyString());


        view.draw(anyInt());
        verify(graphics, Mockito.times((4+ Constants.GAME_NAME.length)*2)).putString(any(TerminalPosition.class), anyString());

        view.draw(anyInt());
        verify(graphics, Mockito.times((4+ Constants.GAME_NAME.length)*3)).putString(any(TerminalPosition.class), anyString());
    }

    @Test
    public void correctRuns() throws IOException {
        view.draw(anyInt());

        verify(screen, atLeastOnce()).clear();
        verify(screen, atLeastOnce()).refresh();
    }

    @Test
    public void correctRuns1(){
        TextGraphics graphics = mock(TextGraphics.class);
        IndicadorView iv = spy(new IndicadorView(0, 0, graphics));

        doNothing().when(iv).draw(44, 0);
        view.drawIndicador(iv, 0);

        verify(iv, atLeastOnce()).draw(44, 0);
    }


}
