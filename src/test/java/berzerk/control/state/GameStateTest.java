package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.game.GameModel;
import berzerk.model.ranking.GameOverModel;
import berzerk.view.GameView;
import berzerk.view.menu.GameOverView;
import berzerk.view.menu.MenuView;
import berzerk.view.menu.RankingView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class GameStateTest {

    GameState state;
    GameModel model;
    GameView view;
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
        soldado = mock(Soldado.class);
        model = spy(new GameModel(soldado, 1, score, 3));
        ecra = mock(Ecra.class);
        doThrow(new RuntimeException()).when(ecra).startScreen();
        view = mock(GameView.class);
        when(view.getModel()).thenReturn(model);
        state = spy(new GameState(factoryState, soldado, view));
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
    public void processKeyLeft(){
        try{
            doReturn(Command.COMMAND.LEFT).when(command).getCommand();

            state.processKey(command);

            verify(model, atLeastOnce()).moveHero(model.getHero(), model.getShooter(), model.getTerrain(), model.getEnemies(), model.getHero().moveLeft());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyRight(){
        try{
            doReturn(Command.COMMAND.RIGHT).when(command).getCommand();

            state.processKey(command);

            verify(model, atLeastOnce()).moveHero(model.getHero(), model.getShooter(), model.getTerrain(), model.getEnemies(),model.getHero().moveRight());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyUp(){
        try{
            doReturn(Command.COMMAND.UP).when(command).getCommand();

            state.processKey(command);

            verify(model, atLeastOnce()).moveHero(model.getHero(), model.getShooter(), model.getTerrain(), model.getEnemies(),model.getHero().moveUp());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyDown(){
        try{
            doReturn(Command.COMMAND.DOWN).when(command).getCommand();

            state.processKey(command);

            verify(model, atLeastOnce()).moveHero(model.getHero(), model.getShooter(), model.getTerrain(), model.getEnemies(),model.getHero().moveDown());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeySpace(){
        try{
            doReturn(Command.COMMAND.SPACE).when(command).getCommand();

            state.processKey(command);

            verify(model, atLeastOnce()).getShooter().heroFire(model.getHero());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyConstruct(){
        try{
            doReturn(Command.COMMAND.CONSTRUCT).when(command).getCommand();

            state.processKey(command);

            verify(model, atLeastOnce()).getTerrain().placeStone(model.getHero());
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

    @Test
    public void runTest(){
//        try {
//            model.getHero().setHp(0);
////            verify(model, atLeastOnce()).endTimers();
//            assertNotNull(factoryState.genGameOverState(mock(Soldado.class), mock(GameOverView.class)).getClass());
//            assertEquals(GameOverState.class, factoryState.genGameOverState(mock(Soldado.class), mock(GameOverView.class)).getClass());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
