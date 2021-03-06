package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.ranking.RankingModel;
import berzerk.view.menu.RankingView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


public class RankingStateTest {

    RankingState state;
    RankingModel model;
    RankingView view;
    FactoryState factoryState;
    Soldado soldado;
    Ecra ecra;
    Command command;

    @BeforeEach
    public void initMenuState() throws IOException {
        factoryState = spy(new FactoryState());
        soldado = mock(Soldado.class);
        model = spy(new RankingModel());
        ecra = mock(Ecra.class);
        doThrow(new RuntimeException()).when(ecra).startScreen();
        view = spy(new RankingView(model, ecra));
        state = new RankingState(factoryState, soldado, view);
        doNothing().when(view).draw(anyInt());
        command = mock(Command.class);
        doReturn(command).when(view).getCommand(command);
    }

    @AfterEach
    public void closeScreen() throws IOException {
        view.close();
    }

    @Test
    public void processKeyArrows(){
        try {
            doReturn(Command.COMMAND.UP).when(command).getCommand();
            assertNotNull(state.run().getClass());

            doReturn(Command.COMMAND.RIGHT).when(command).getCommand();
            assertNotNull(state.run().getClass());

            doReturn(Command.COMMAND.DOWN).when(command).getCommand();
            assertNotNull(state.run().getClass());

            doReturn(Command.COMMAND.LEFT).when(command).getCommand();
            assertNotNull(state.run().getClass());

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyOtherSpecials() {
        try {
            doReturn(Command.COMMAND.SELECT).when(command).getCommand();
            assertEquals(MenuState.class, state.run().getClass());

            doReturn(Command.COMMAND.NONE).when(command).getCommand();
            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void draw() {
        try {
            assertNotNull(state.run().getClass());
            verify(view, atLeast(1)).draw(anyInt());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
