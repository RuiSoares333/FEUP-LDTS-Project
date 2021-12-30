package view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class IndicadorBigViewTest {


    private Indicador indicador;

    @BeforeEach
    public void initIndicador() throws IOException {
        TerminalSize terminalSize = new TerminalSize(Constants.WIDTH, Constants.HEIGHT);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();

        Screen screen = new TerminalScreen(terminal);
        indicador = Mockito.spy(new IndicadorBigView(10, 10, screen.newTextGraphics()));
    }

    @Test
    public void drawTest(){
        indicador.draw(0,0);
        Mockito.verify(indicador, Mockito.times(1)).drawTop(0, 0);
        Mockito.verify(indicador, Mockito.times(1)).drawBottom(0, 0);
        Mockito.verify(indicador, Mockito.times(1)).drawLeft(0, 0);
        Mockito.verify(indicador, Mockito.times(1)).drawRight(0, 0);
        Mockito.verify(indicador, Mockito.times(1)).drawCorners(0, 0);
    }

    @Test
    public void drawTopTest(){
        indicador.drawTop(0,0);

        Mockito.verify(indicador, Mockito.times(1)).drawTop(0,0);
    }

    @Test
    public void drawLeftTest(){
        indicador.drawLeft(0,0);

        Mockito.verify(indicador, Mockito.times(1)).drawLeft(0,0);
    }

    @Test
    public void drawBottomTest(){
        indicador.drawBottom(0,0);

        Mockito.verify(indicador, Mockito.times(1)).drawBottom(0,0);
    }

    @Test
    public void drawRightTest(){
        indicador.drawRight(0,0);

        Mockito.verify(indicador, Mockito.times(1)).drawRight(0,0);
    }

    @Test
    public void drawCornersTest(){
        indicador.drawCorners(0,0);

        Mockito.verify(indicador, Mockito.times(1)).drawCorners(0,0);
    }
}
