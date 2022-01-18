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
        command = mock(Command.class);
    }

    @AfterEach
    public void closeScreen() throws IOException {
        view.close();
    }

    @Test
    public void processKey() throws IOException{
        doReturn(command).when(view).getCommand(command);
        doReturn(Command.COMMAND.LEFT).when(command).getCommand();
        assertEquals(state, state.run());

        doReturn(Command.COMMAND.RIGHT).when(command).getCommand();
        assertEquals(state, state.run());

        doReturn(Command.COMMAND.UP).when(command).getCommand();
        assertEquals(state, state.run());

        doReturn(Command.COMMAND.DOWN).when(command).getCommand();
        assertEquals(state, state.run());
    }

    @Test
    public void processKeySelect1() {
        try {
            doReturn(command).when(view).getCommand(command);
            doReturn(Command.COMMAND.LEFT).when(command).getCommand();
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
            when(command.getCommand()).thenReturn(Command.COMMAND.LEFT);
            state.processKey(command);
            verify(model, atLeastOnce()).previousSelected();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyRight(){
        try {
            when(command.getCommand()).thenReturn(Command.COMMAND.RIGHT);

            state.processKey(command);
            verify(model, atLeastOnce()).nextSelected();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyExit(){
        try {
            when(command.getCommand()).thenReturn(Command.COMMAND.QUIT);
            assertNotNull(state.processKey(command).getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeySelect2(){
        try {
            when(command.getCommand()).thenReturn(Command.COMMAND.SELECT);
            assertNotNull(state.processKey(command).getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
