package berzerk.view.menu;

import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.ranking.RankingModel;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RankingViewTest {

    RankingView view;
    RankingModel model;
    Screen screen;
    TextGraphics graphics;
    Ecra ecra;

    @BeforeEach
    public void initView(){
        model = mock(RankingModel.class);
        ecra = mock(Ecra.class);
        screen = mock(Screen.class);
        graphics = mock(TextGraphics.class);
        view = new RankingView(model, ecra);
    }


    @Test
    public void drawTest() throws IOException {
        Map<String, String> novosJogadores = new HashMap<>();
        novosJogadores.put("A", "1");
        novosJogadores.put("B", "2");
        novosJogadores.put("C", "3");
        novosJogadores.put("D", "4");
        novosJogadores.put("E", "5");

        when(view.getEcra().getScreen()).thenAnswer(invocation -> screen);
        when(view.getEcra().getGraphics()).thenAnswer(invocation -> graphics);
        when(view.getModel().getJogadores()).thenAnswer(invocation -> novosJogadores);

        view.draw(0);
        Mockito.verify(graphics, Mockito.times(1+5+1)).putString(any(TerminalPosition.class), anyString());
    }

}
