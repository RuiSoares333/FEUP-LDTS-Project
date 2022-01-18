package berzerk.model;

import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EcraTest {

    Screen screen;
    TextGraphics graphics;
    Terminal terminal;
    Ecra ecra;


    @BeforeEach
    public void initEcra(){
        screen = mock(Screen.class);
        graphics = mock(TextGraphics.class);
        terminal = mock(AWTTerminal.class);
        ecra = spy(new Ecra(terminal, screen));
    }

    @Test
    public void getScreen(){
        assertEquals(screen, ecra.getScreen());
    }

    @Test
    public void getTerminal(){
        assertEquals(terminal, ecra.getTerminal());
    }

    @Test
    public void startScreen(){
        try {
            ecra.startScreen();
            verify(screen, atLeastOnce()).startScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void close(){
        try {
            ecra.close();
            verify(screen, atLeastOnce()).close();
            verify(terminal, atLeastOnce()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
