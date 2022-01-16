package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import berzerk.model.settings.SettingsModel;
import berzerk.view.menu.SettingsView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class SettingsStateTest {

    SettingsState state;
    SettingsModel model;
    SettingsView view;
    FactoryState factoryState;
    Soldado soldado;
    Ecra ecra;
    Command command;

    @BeforeEach
    public void initMenuState() throws IOException {
        factoryState = spy(new FactoryState());
        soldado = mock(Soldado.class);
        model = spy(new SettingsModel(soldado));
        ecra = mock(Ecra.class);
        view = spy(new SettingsView(model, ecra));
        state = new SettingsState(factoryState, soldado, view);
        doNothing().when(view).draw(anyInt());
    }

    @AfterEach
    public void closeScreen() throws IOException {
        view.close();
    }

    @Test
    public void processKey() throws IOException{
        when(view.getCommand(command)).thenAnswer(invocation -> Command.COMMAND.LEFT);
        assertEquals(state, state.run());

        when(view.getCommand(command)).thenAnswer(invocation -> Command.COMMAND.RIGHT);
        assertEquals(state, state.run());

        when(view.getCommand(command)).thenAnswer(invocation -> Command.COMMAND.RIGHT);
        assertEquals(state, state.run());

        when(view.getCommand(command)).thenAnswer(invocation -> Command.COMMAND.RIGHT);
        assertEquals(state, state.run());

        when(view.getCommand(command)).thenAnswer(invocation -> Command.COMMAND.UP);
        assertEquals(state, state.run());

        when(view.getCommand(command)).thenAnswer(invocation -> Command.COMMAND.UP);
        assertEquals(state, state.run());

        when(view.getCommand(command)).thenAnswer(invocation -> Command.COMMAND.UP);
        assertEquals(state, state.run());

        when(view.getCommand(command)).thenAnswer(invocation -> Command.COMMAND.DOWN);
        assertEquals(state, state.run());

        when(view.getCommand(command)).thenAnswer(invocation -> Command.COMMAND.DOWN);
        assertEquals(state, state.run());

        when(view.getCommand(command)).thenAnswer(invocation -> Command.COMMAND.DOWN);
        assertEquals(state, state.run());
    }

    @Test
    public void processKeySelect1() {
        try {
            when(view.getCommand(command)).thenAnswer(invocation -> Command.COMMAND.LEFT);
            state.run();

            when(view.getCommand(command)).thenAnswer(invocation -> Command.COMMAND.SELECT);
            when(factoryState.genSettingsMenuState(mock(Soldado.class), mock(SettingsView.class))).thenAnswer(invocation -> Command.class);

            assertNotNull(state.run().getClass());
        } catch (Exception | OutOfMemoryError e) {
            e.printStackTrace();
        }

    }

    @Test
    public void draw(){
        try {
            assertNotNull(state.run().getClass());
            verify(view, times(1)).draw(anyInt());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getPosition(){
        assertEquals(14, state.getPosition(Soldado.Heroi.RECRUIT));
        assertEquals(39, state.getPosition(Soldado.Heroi.TANKY));
        assertEquals(63, state.getPosition(Soldado.Heroi.EXPERT));
        assertEquals(0, state.getPosition(null));
    }


    @Test
    public void processKeyLeft(){
        try {
            state.processKey(Command.COMMAND.LEFT);
            verify(model, atLeastOnce()).previousSelected();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyRight(){
        try {
            state.processKey(Command.COMMAND.RIGHT);
            verify(model, atLeastOnce()).nextSelected();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyExit(){
        try {

            assertNotNull(state.processKey(Command.COMMAND.QUIT).getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeySelect2(){
        try {

            assertNotNull(state.processKey(Command.COMMAND.SELECT).getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
