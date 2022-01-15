package berzerk.model;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.ansi.ANSITerminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    public void initTerminal(){
        assertNotNull(ecra.initTerminal());
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

//    @Test
//    public void correctRuns(){
//        doNothing().when(ecra).startScreen();
//        ecra.initScreen();
//        verify(screen, atLeastOnce()).setCursorPosition(null);
//        verify(screen, atLeastOnce()).doResizeIfNecessary();
//        verify(ecra, atLeastOnce()).startScreen();
//    }


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
