package berzerk.view.menu;

import berzerk.model.Ecra;
import berzerk.model.ranking.GameOverModel;
import berzerk.model.ranking.RankingModel;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class GameOverViewTest {

    GameOverView view;
    GameOverModel model;
    Screen screen;
    TextGraphics graphics;
    Ecra ecra;

    @BeforeEach
    public void initView(){
        model = mock(GameOverModel.class);
        model.setName("ABC");
        ecra = mock(Ecra.class);
        screen = mock(Screen.class);
        graphics = mock(TextGraphics.class);
        view = spy(new GameOverView(model, ecra));
        when(view.getScreen()).thenReturn(screen);
        when(view.getGraphics()).thenReturn(graphics);
    }

    @AfterEach
    public void closeScreen() throws IOException {
        view.close();
    }

    @Test
    public void drawHUDTest() {
        view.drawHUD(graphics, model);

        verify(graphics, atLeastOnce()).putString(any(TerminalPosition.class), anyString());

    }

    @Test
    public void drawNameTest(){
        doReturn("CA").when(model).getName();
        view.drawName(graphics, model);
        verify(graphics, atLeastOnce()).putString(any(TerminalPosition.class), anyString());
    }


    @Test
    public void drawTest() throws IOException {
        doReturn("CA").when(model).getName();
        view.draw(anyInt());
        verify(graphics, atLeastOnce()).putString(any(TerminalPosition.class), anyString());
        verify(screen, atLeastOnce()).clear();
        verify(screen, atLeastOnce()).refresh();
    }

}
