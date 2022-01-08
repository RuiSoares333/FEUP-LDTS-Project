package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.settings.SettingsModel;
import berzerk.view.menu.SettingsView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class SettingsStateTest {

    SettingsState state;
    SettingsModel model;
    SettingsView view;
    FactoryState factoryState;
    Soldado soldado;
    Ecra ecra;

    @BeforeEach
    public void initMenuState(){
        factoryState = spy(new FactoryState());
        soldado = mock(Soldado.class);
        model = spy(new SettingsModel(soldado));
        ecra = mock(Ecra.class);
        view = spy(new SettingsView(model, ecra));
        state = new SettingsState(factoryState, soldado, view);
    }

    @Test
    public void processKey() throws IOException{
        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.LEFT);
        assertEquals(state, state.run());

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.RIGHT);
        assertEquals(state, state.run());

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.RIGHT);
        assertEquals(state, state.run());

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.RIGHT);
        assertEquals(state, state.run());

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.UP);
        assertEquals(state, state.run());

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.UP);
        assertEquals(state, state.run());

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
    public void processKeySelect() throws IOException {
        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.LEFT);
        doRun(1);

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.SELECT);
        when(factoryState.genSettingsMenuState(mock(Soldado.class))).thenAnswer(invocation -> Command.class);

        assertNotNull(state.run().getClass());

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.LEFT);
        doRun(3);

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.SELECT);
        when(factoryState.genSettingsMenuState(mock(Soldado.class))).thenAnswer(invocation -> Command.class);

        assertNotNull(state.run().getClass());

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.LEFT);
        doRun(5);

        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.SELECT);
        when(factoryState.genSettingsMenuState(mock(Soldado.class))).thenAnswer(invocation -> Command.class);

        assertNotNull(state.run().getClass());
    }

    private void doRun(int numVezes) throws IOException {
        for(int i=0; i<numVezes; i++){
            state.run();
        }
    }
}
