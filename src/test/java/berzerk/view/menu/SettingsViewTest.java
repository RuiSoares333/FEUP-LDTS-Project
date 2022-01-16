package berzerk.view.menu;

import berzerk.model.Ecra;
import berzerk.model.settings.SettingsModel;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class SettingsViewTest {

    SettingsView view;
    SettingsModel model;
    Screen screen;
    TextGraphics graphics;
    Ecra ecra;

    @BeforeEach
    public void initView(){
        model = mock(SettingsModel.class);
        ecra = mock(Ecra.class);
        screen = mock(Screen.class);
        graphics = mock(TextGraphics.class);
        view = spy(new SettingsView(model, ecra));
        when(view.getScreen()).thenAnswer(invocation -> screen);
        when(view.getGraphics()).thenAnswer(invocation -> graphics);
    }

    @AfterEach
    public void closeScreen() throws IOException {
        view.close();
    }

    @Test
    public void drawTest() throws IOException {
        view.draw(anyInt());
        Mockito.verify(graphics, Mockito.times((16*16)*3)).setBackgroundColor(any(TextColor.class));
        Mockito.verify(graphics, Mockito.times((16*16)*3+1)).fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), any(Character.class));
        Mockito.verify(graphics, Mockito.times(4)).putString(anyInt(), anyInt(), anyString());

        view.draw(anyInt());
        Mockito.verify(graphics, Mockito.times((16*16)*3*2)).setBackgroundColor(any(TextColor.class));
        Mockito.verify(graphics, Mockito.times(((16*16)*3+1)*2)).fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), any(Character.class));
        Mockito.verify(graphics, Mockito.times(4*2)).putString(anyInt(), anyInt(), anyString());

        view.draw(anyInt());
        Mockito.verify(graphics, Mockito.times((16*16)*3*3)).setBackgroundColor(any(TextColor.class));
        Mockito.verify(graphics, Mockito.times(((16*16)*3+1)*3)).fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), any(Character.class));
        Mockito.verify(graphics, Mockito.times(4*3)).putString(anyInt(), anyInt(), anyString());

    }

    @Test
    public void correctRuns() throws IOException {
        view.draw(0);
        verify(screen, atLeastOnce()).clear();
        verify(screen, atLeastOnce()).refresh();
    }

    @Test
    public void getTerminalPositionRecruit(){
        for(int y=0; y<16; y++)
            for(int x=0; x<16; x++) {
                TerminalPosition p1 = view.getTerminalPositionRecruit(x, y);
                assertEquals(x+15, p1.getColumn());
                assertEquals(y+8, p1.getRow());
            }
    }

    @Test
    public void getTerminalPositionTanky(){
        for(int y=0; y<16; y++)
            for(int x=0; x<16; x++) {
                TerminalPosition p1 = view.getTerminalPositionTanky(x, y);
                assertEquals(x+40, p1.getColumn());
                assertEquals(y+8, p1.getRow());
            }
    }

    @Test
    public void getTerminalPositionExpert(){
        for(int y=0; y<16; y++)
            for(int x=0; x<16; x++) {
                TerminalPosition p1 = view.getTerminalPositionExpert(x, y);
                assertEquals(x+64, p1.getColumn());
                assertEquals(y+8, p1.getRow());
            }
    }
}
