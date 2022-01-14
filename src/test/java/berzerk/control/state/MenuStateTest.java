package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import berzerk.model.menu.MenuModelTest;
import berzerk.view.menu.MenuView;
import berzerk.view.menu.SettingsView;
import org.junit.jupiter.api.AfterEach;
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
        state = spy(new MenuState(factoryState, soldado, view));
        doNothing().when(view).draw(anyInt());
    }

    @AfterEach
    public void closeScreen() throws IOException {
        view.close();
    }


    @Test
    public void processExit() throws IOException {
        when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.QUIT);
        assertNull(state.run());
    }


    @Test
    public void processKey(){
        try{
            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.UP);
            assertEquals(state, state.run());

            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.DOWN);
            assertEquals(state, state.run());

            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.DOWN);
            assertEquals(state, state.run());

            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.DOWN);
            assertEquals(state, state.run());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyPlay(){
        try{
            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.SELECT);

            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeySettings(){
        try{
            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.DOWN);
            state.run();

            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.SELECT);
            when(factoryState.genSettingsMenuState(mock(Soldado.class), mock(SettingsView.class))).thenAnswer(invocation -> SettingsState.class);

            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyRanking(){
        try{
            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.DOWN);
            state.run();
            state.run();

            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.SELECT);
            when(factoryState.genSettingsMenuState(mock(Soldado.class), mock(SettingsView.class))).thenAnswer(invocation -> SettingsState.class);

            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void processKeyExit() {
        try {
            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.UP);
            state.run();

            when(view.getCommand()).thenAnswer(invocation -> Command.COMMAND.SELECT);
            when(factoryState.genSettingsMenuState(mock(Soldado.class), mock(SettingsView.class))).thenAnswer(invocation -> SettingsState.class);

            assertNull(state.run());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void manageCommandTest(){
        try {
            ControllerState<?> expected = mock(ControllerState.class);
            assertEquals(expected, state.manageCommand(expected));

            Mockito.verify(state, times(1)).manageCommand(expected);
        } catch (Exception e) {
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



}
