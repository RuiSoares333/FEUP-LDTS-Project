package berzerk.model;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        ecra = spy(new Ecra(terminal, screen));
        doNothing().when(ecra).startScreen();
    }

    @Test
    public void getScreen(){
        Assertions.assertEquals(screen, ecra.getScreen());
    }

    @Test
    public void getTerminal(){
        Assertions.assertEquals(terminal, ecra.getTerminal());
    }
}
