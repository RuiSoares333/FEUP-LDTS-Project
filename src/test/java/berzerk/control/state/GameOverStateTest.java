package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import berzerk.model.ranking.GameOverModel;
import berzerk.view.menu.GameOverView;
import berzerk.view.menu.MenuView;
import berzerk.view.menu.SettingsView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class GameOverStateTest {

    GameOverState state;
    GameOverModel model;
    GameOverView view;
    Command command;
    FactoryState factoryState;
    Soldado soldado;
    Ecra ecra;
    int score;

    @BeforeEach
    public void initGameOverState() throws IOException {
        score = 100;
        command = mock(Command.class);
        factoryState = spy(new FactoryState());
        model = spy(new GameOverModel(score));
        ecra = mock(Ecra.class);
        doThrow(new RuntimeException()).when(ecra).startScreen();
        view = mock(GameOverView.class);
        when(view.getModel()).thenReturn(model);
        soldado = mock(Soldado.class);
        state = spy(new GameOverState(factoryState, soldado, view));
        doNothing().when(view).draw(anyInt());
        doReturn(command).when(view).getCommand(command);

    }

    @AfterEach
    public void closeScreen() throws IOException {
        view.close();
    }

    @Test
    public void runAndProcessKeyTest(){
        try{
            doReturn(command).when(view).getCommand(command);

            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyDelete(){
        try{
            doReturn(Command.COMMAND.DELETE).when(command).getCommand();

            state.processKey(command);

            verify(model, atLeastOnce()).deleteLastCharacter();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeySelect(){
        try{
            String name = "ABC";
            model.setName(name);
            doReturn(Command.COMMAND.SELECT).when(command).getCommand();

            state.processKey(command);

            verify(model, atLeastOnce()).saveScores();
            when(factoryState.genMenuState(mock(Soldado.class), mock(MenuView.class))).thenAnswer(invocation -> MenuState.class);

            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyType(){
        try{
            doReturn(Command.COMMAND.TYPE).when(command).getCommand();

            state.processKey(command);

            verify(model, atLeastOnce()).addCharacter(command.getKey());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyQuit(){
        try{
            doReturn(Command.COMMAND.QUIT).when(command).getCommand();

            state.processKey(command);

            when(factoryState.genMenuState(mock(Soldado.class), mock(MenuView.class))).thenAnswer(invocation -> MenuState.class);

            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
