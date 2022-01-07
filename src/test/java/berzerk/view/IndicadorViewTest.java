package berzerk.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;

public class IndicadorViewTest {

    TextGraphics graphics;
    IndicadorView indicador;

    @BeforeEach
    public void initIndicador(){
        graphics = mock(TextGraphics.class);

        indicador = new IndicadorView(10, 10, graphics);
    }

    @Test
    public void drawTest() {
        indicador.draw(anyInt(),anyInt());

        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(any(TextColor.class));
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(any(TextColor.class));

        Mockito.verify(graphics, Mockito.times(2)).drawLine(any(TerminalPosition.class), any(TerminalPosition.class), anyChar());
        Mockito.verify(graphics, Mockito.times(4)).putString(anyInt(), anyInt(), anyString());

        indicador.draw(anyInt(),anyInt());

        Mockito.verify(graphics, Mockito.times(1)).setBackgroundColor(any(TextColor.class));
        Mockito.verify(graphics, Mockito.times(1)).setForegroundColor(any(TextColor.class));

        Mockito.verify(graphics, Mockito.times(4)).drawLine(any(TerminalPosition.class), any(TerminalPosition.class), anyChar());
        Mockito.verify(graphics, Mockito.times(8)).putString(anyInt(), anyInt(), anyString());
    }


}
