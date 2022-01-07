package berzerk.control.state;

import berzerk.control.MenuCommand;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import berzerk.model.settings.SettingsModel;
import berzerk.view.menu.MenuView;
import berzerk.view.menu.SettingsView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.LEFT);
        assertEquals(state, state.run());

        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.RIGHT);
        assertEquals(state, state.run());

        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.RIGHT);
        assertEquals(state, state.run());

        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.RIGHT);
        assertEquals(state, state.run());
    }

    @Test
    public void processKeySelect() throws IOException {
        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.LEFT);
        state.run();

        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.SELECT);
        when(factoryState.genSettingsMenuState(mock(Soldado.class))).thenAnswer(invocation -> MenuCommand.class);

        assertNotNull(state.run().getClass());
    }
}
