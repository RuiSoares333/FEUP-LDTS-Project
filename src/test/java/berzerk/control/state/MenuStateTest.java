package berzerk.control.state;

import berzerk.control.MenuCommand;
import berzerk.model.Arena;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.entity.hero.Hero;
import berzerk.model.menu.MenuModel;
import berzerk.view.GameView;
import berzerk.view.menu.MenuView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MenuStateTest {

    MenuState state;
    MenuModel model;
    MenuView view;
    GameView gameView;
    FactoryState factoryState;
    Soldado soldado;
    Ecra ecra;
    Arena arena;
    Hero hero;

    @BeforeEach
    public void initMenuState(){
        hero = new Hero(10,10,10,3);
        arena = new Arena(hero);
        factoryState = spy(new FactoryState());
        model = spy(new MenuModel());
        ecra = mock(Ecra.class);
        view = spy(new MenuView(model, ecra));
        gameView = spy(new GameView(ecra, arena));
        soldado = mock(Soldado.class);
        state = new MenuState(factoryState, soldado, view);
    }


    @Test
    public void processExit() throws IOException {
        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.QUIT);
        assertNull(state.run());
    }


    @Test
    public void processKey() throws IOException{
        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.UP);
        assertEquals(state, state.run());

        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.DOWN);
        assertEquals(state, state.run());

        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.DOWN);
        assertEquals(state, state.run());

        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.DOWN);
        assertEquals(state, state.run());
    }

//    @Test
//    public void processKeyPlay() throws IOException {
//        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.SELECT);
//
//        assertNotNull(GameState.class, state.run());
//    }

    @Test
    public void processKeySettings() throws IOException {
        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.DOWN);
        doRun(1);

        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.SELECT);
        when(factoryState.genSettingsMenuState(mock(Soldado.class))).thenAnswer(invocation -> SettingsState.class);

        assertNotNull(state.run().getClass());
    }

    @Test
    public void processKeyRanking() throws IOException {
        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.DOWN);
        doRun(2);

        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.SELECT);
        when(factoryState.genSettingsMenuState(mock(Soldado.class))).thenAnswer(invocation -> SettingsState.class);

        assertNotNull(state.run().getClass());
    }


    @Test
    public void processKeyExit() throws IOException {
        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.UP);
        doRun(1);

        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.SELECT);
        when(factoryState.genSettingsMenuState(mock(Soldado.class))).thenAnswer(invocation -> SettingsState.class);

        assertNull(state.run());
    }

    @Test
    public void processKeyGame() throws IOException {
        when(view.getCommand()).thenAnswer(invocation -> MenuCommand.COMMAND.UP);
        doRun(1);

        when(factoryState.genGameState(mock(Soldado.class))).thenAnswer(invocation -> GameState.class);

        assertNotNull(state.run());
    }

    private void doRun(int numVezes) throws IOException {
        for(int i=0; i<numVezes; i++){
            state.run();
        }
    }
}
