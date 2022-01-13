package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import berzerk.view.menu.MenuView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MenuStateTest {

    MenuState state;
    MenuModel model;
    MenuView view;
    FactoryState factoryState;
    Soldado soldado;
    Ecra ecra;

    @BeforeEach
    public void initMenuState() throws IOException {
        factoryState = spy(new FactoryState());
        model = spy(new MenuModel());
        ecra = mock(Ecra.class);
        doThrow(new RuntimeException()).when(ecra).startScreen();
        view = spy(new MenuView(model, ecra));
        soldado = mock(Soldado.class);
        state = new MenuState(factoryState, soldado, view);
        Mockito.doNothing().when(view).draw(anyInt());
    }


    @Test
    public void processExit() throws IOException {
        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.QUIT);
        assertNull(state.run());
    }


    @Test
    public void processKey() throws IOException{
        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.UP);
        assertEquals(state, state.run());

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.DOWN);
        assertEquals(state, state.run());

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.DOWN);
        assertEquals(state, state.run());

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.DOWN);
        assertEquals(state, state.run());
    }

    @Test
    public void processKeyPlay() throws IOException {
        try{
            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.SELECT);

            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeySettings() throws IOException {
        try{
            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.DOWN);
            doRun(1);

            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.SELECT);
            when(factoryState.genSettingsMenuState(mock(Soldado.class))).thenAnswer(invocation -> SettingsState.class);

            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyRanking() throws IOException {
        try{
            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.DOWN);
            doRun(2);

            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.SELECT);
            when(factoryState.genSettingsMenuState(mock(Soldado.class))).thenAnswer(invocation -> SettingsState.class);

            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void processKeyExit() throws IOException {
        try {
            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.UP);
            doRun(1);

            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.SELECT);
            when(factoryState.genSettingsMenuState(mock(Soldado.class))).thenAnswer(invocation -> SettingsState.class);

            assertNull(state.run());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void doRun(int numVezes) throws IOException {
        for(int i=0; i<numVezes; i++){
            state.run();
        }
    }
}
