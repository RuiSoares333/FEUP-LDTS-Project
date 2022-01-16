package berzerk.view;

import berzerk.model.Constants;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class IndicadorViewTest {

    TextGraphics graphics;
    IndicadorView indicador;

    @BeforeEach
    public void initIndicador(){
        graphics = mock(TextGraphics.class);
        indicador = spy(new IndicadorView(10, 10, graphics));
    }

    @Test
    public void drawTest() {
        indicador.draw(0,0);

        verify(graphics, atLeastOnce()).setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
        verify(graphics, atLeastOnce()).setForegroundColor(TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR));

        verify(graphics, atLeastOnce()).drawLine(any(TerminalPosition.class), any(TerminalPosition.class), anyChar());

        verify(indicador, atLeastOnce()).drawTop(any(TerminalPosition.class), any(TerminalPosition.class));
        verify(indicador, atLeastOnce()).drawBottom(any(TerminalPosition.class), any(TerminalPosition.class));
    }

    @Test
    public void positions(){
        TerminalPosition p1 = indicador.topLeft(0, 0);

        TerminalPosition p2 = indicador.topRight(0, 0);

        TerminalPosition p3 = indicador.botLeft(0, 0);

        TerminalPosition p4 = indicador.botRight(0, 0);

        assertEquals(0, p1.getColumn());
        assertEquals(0, p1.getRow());

        assertEquals(9, p2.getColumn());
        assertEquals(0, p2.getRow());

        assertEquals(0, p3.getColumn());
        assertEquals(9, p3.getRow());

        assertEquals(9, p4.getColumn());
        assertEquals(9, p4.getRow());
    }


}
