package berzerk.control.state;

import berzerk.control.MenuCommand;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.ranking.RankingModel;
import berzerk.view.menu.RankingView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


public class RankingStateTest {

    RankingState state;
    RankingModel model;
    RankingView view;
    FactoryState factoryState;
    Soldado soldado;
    Ecra ecra;

    @BeforeEach
    public void initMenuState(){
        factoryState = spy(new FactoryState());
        soldado = mock(Soldado.class);
        model = spy(new RankingModel());
        ecra = mock(Ecra.class);
        view = spy(new RankingView(model, ecra));
        state = new RankingState(factoryState, soldado, view);
    }

    @Test
    public void processKeyArrows() throws IOException, URISyntaxException, FontFormatException {
        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.UP);
        assertEquals(MenuState.class, state.run().getClass());

        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.RIGHT);
        assertEquals(MenuState.class, state.run().getClass());

        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.DOWN);
        assertEquals(MenuState.class, state.run().getClass());

        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.RIGHT);
        assertEquals(MenuState.class, state.run().getClass());
    }

    @Test
    public void processKeyOtherSpecials() throws IOException, URISyntaxException, FontFormatException {
        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.SELECT);
        assertEquals(MenuState.class, state.run().getClass());

        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.NONE);
        assertEquals(MenuState.class, state.run().getClass());
    }
}
