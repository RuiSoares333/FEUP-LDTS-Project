package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import berzerk.view.View;
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
    Command command;
    FactoryState factoryState;
    Soldado soldado;
    Ecra ecra;

    @BeforeEach
    public void initMenuState() throws IOException {
        command = mock(Command.class);
        factoryState = spy(new FactoryState());
        model = spy(new MenuModel());
        ecra = mock(Ecra.class);
        doThrow(new RuntimeException()).when(ecra).startScreen();
        view = mock(MenuView.class);
        when(view.getModel()).thenReturn(model);
        soldado = mock(Soldado.class);
        state = spy(new MenuState(factoryState, soldado, view));
        doNothing().when(view).draw(anyInt());
        doReturn(command).when(view).getCommand(command);
    }

    @AfterEach
    public void closeScreen() throws IOException {
        view.close();
    }


    @Test
    public void processExit() throws IOException {
        doReturn(Command.COMMAND.QUIT).when(command).getCommand();

        assertNull(state.processKey(command));
    }


    @Test
    public void processKey(){
        try{
            doReturn(Command.COMMAND.UP).when(command).getCommand();
            assertEquals(state, state.run());

            doReturn(Command.COMMAND.DOWN).when(command).getCommand();
            assertEquals(state, state.run());

            assertEquals(state, state.run());

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyPlay(){
        try{
            doReturn(Command.COMMAND.SELECT).when(command).getCommand();

            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeySettings(){
        try{
            doReturn(Command.COMMAND.DOWN).when(command).getCommand();
            state.run();

            doReturn(Command.COMMAND.SELECT).when(command).getCommand();
            doReturn(new SettingsState(mock(FactoryState.class), mock(Soldado.class), mock(View.class))).when(factoryState).genSettingsMenuState(mock(Soldado.class), mock(SettingsView.class));

            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyRanking(){
        try{
            doReturn(Command.COMMAND.DOWN).when(command).getCommand();
            state.run();
            state.run();

            doReturn(Command.COMMAND.SELECT).when(command).getCommand();
            when(factoryState.genSettingsMenuState(mock(Soldado.class), mock(SettingsView.class))).thenAnswer(invocation -> SettingsState.class);

            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void processKeyExit() {
        try {
            when(model.getSelected()).thenReturn(MenuModel.Opcao.EXIT);
            doReturn(Command.COMMAND.QUIT).when(command).getCommand();
            assertNull(state.processKey(command));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void manageCommandTest() throws Exception {
        try {
            ControllerState<?> expected = mock(ControllerState.class);
            assertEquals(expected, state.manageCommand(expected));

            Mockito.verify(state, times(1)).manageCommand(expected);
        } catch (Exception e) {
            throw new Exception();
        }

    }

    @Test
    public void positions(){
        assertEquals(10, state.getPosition(MenuModel.Opcao.PLAY));
        assertEquals(12, state.getPosition(MenuModel.Opcao.SETT));
        assertEquals(14, state.getPosition(MenuModel.Opcao.RANKS));
        assertEquals(18, state.getPosition(MenuModel.Opcao.EXIT));
        assertEquals(0, state.getPosition(null));
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
    public void processKeyUp(){
        try {
            doReturn(Command.COMMAND.UP).when(command).getCommand();
            state.processKey(command);
            verify(model, atLeastOnce()).previousSelected();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyDown(){
        try {
            doReturn(Command.COMMAND.DOWN).when(command).getCommand();
            state.processKey(command);
            verify(model, atLeastOnce()).nextSelected();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeySelectPlay(){
        try {
            doReturn(MenuModel.Opcao.PLAY).when(model).getSelected();
            doReturn(Command.COMMAND.SELECT).when(command).getCommand();

            assertNotNull(state.processKey(command).getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeySelectSettings(){
        try {
            doReturn(MenuModel.Opcao.SETT).when(model).getSelected();
            doReturn(Command.COMMAND.SELECT).when(command).getCommand();

            assertNotNull(state.processKey(command).getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeySelectRanking(){
        try {
            doReturn(MenuModel.Opcao.RANKS).when(model).getSelected();
            doReturn(Command.COMMAND.SELECT).when(command).getCommand();

            assertNotNull(state.processKey(command).getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeySelectExit(){
        try {
            doReturn(MenuModel.Opcao.EXIT).when(model).getSelected();
            doReturn(Command.COMMAND.SELECT).when(command).getCommand();

            assertNull(state.processKey(command));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeySelectNull(){
        try {
            doReturn(null).when(model).getSelected();
            doReturn(Command.COMMAND.SELECT).when(command).getCommand();

            assertNull(state.processKey(command));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
