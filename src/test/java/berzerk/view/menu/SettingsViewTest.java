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
    }

    @AfterEach
    public void closeScreen() throws IOException {
        view.close();
    }

    @Test
    public void drawTest() throws IOException {
        when(view.getScreen()).thenAnswer(invocation -> screen);
        when(view.getGraphics()).thenAnswer(invocation -> graphics);

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
}
