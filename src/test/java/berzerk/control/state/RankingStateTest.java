package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.ranking.RankingModel;
import berzerk.view.menu.RankingView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class RankingStateTest {

    RankingState state;
    RankingModel model;
    RankingView view;
    FactoryState factoryState;
    Soldado soldado;
    Ecra ecra;

    @BeforeEach
    public void initMenuState() throws IOException {
        factoryState = spy(new FactoryState());
        soldado = mock(Soldado.class);
        model = spy(new RankingModel());
        ecra = mock(Ecra.class);
        view = spy(new RankingView(model, ecra));
        state = new RankingState(factoryState, soldado, view);
        Mockito.doNothing().when(view).draw(anyInt());
    }

    @Test
    public void processKeyArrows() throws IOException, URISyntaxException, FontFormatException {
        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.UP);
        assertEquals(MenuState.class, state.run().getClass());

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.RIGHT);
        assertEquals(MenuState.class, state.run().getClass());

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.DOWN);
        assertEquals(MenuState.class, state.run().getClass());

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.RIGHT);
        assertEquals(MenuState.class, state.run().getClass());
    }

    @Test
    public void processKeyOtherSpecials() throws IOException, URISyntaxException, FontFormatException {
        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.SELECT);
        assertEquals(MenuState.class, state.run().getClass());

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.NONE);
        assertEquals(MenuState.class, state.run().getClass());
    }
}
