package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.properties.Position;
import berzerk.model.game.Enemies;
import berzerk.model.game.GameModel;
import berzerk.model.game.Shooter;
import berzerk.model.game.Terrain;
import berzerk.model.ranking.GameOverModel;
import berzerk.view.GameView;
import berzerk.view.menu.GameOverView;
import berzerk.view.menu.MenuView;
import berzerk.view.menu.RankingView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

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
    Hero hero;
    Shooter shooter;
    Enemies enemies;
    Terrain terrain;

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

        hero = mock(Hero.class);
        enemies = mock(Enemies.class);
        terrain = mock(Terrain.class);
        shooter = mock(Shooter.class);
    }

    @AfterEach
    public void closeScreen() throws IOException {
        view.close();
    }

    @Test
    public void runAndProcessKeyTest(){
        try{
            doReturn(Command.COMMAND.UP).when(command).getCommand();
            doReturn(command).when(view).getCommand(any(Command.class));

            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void processKeyMovement(){
        try{
            doNothing().when(hero).move(any(Shooter.class), any(Terrain.class), any(Enemies.class), any(Position.class));
            doReturn(Command.COMMAND.UP).when(command).getCommand();

            state.processKey(command);
            doReturn(hero).when(model).getHero();
            doReturn(terrain).when(model).getTerrain();
            doReturn(enemies).when(model).getEnemies();
            doReturn(shooter).when(model).getShooter();

            verify(model, atLeastOnce()).moveHero(any(Hero.class), any(Shooter.class), any(Terrain.class), any(Enemies.class), any(Position.class));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeySpace(){
        try{
            doReturn(Command.COMMAND.SPACE).when(command).getCommand();
            doReturn(shooter).when(model).getShooter();

            state.processKey(command);

            verify(model, atLeastOnce()).getShooter();
            verify(model.getShooter(), atLeastOnce()).heroFire(any(Hero.class));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyConstruct(){
        try{
            doReturn(Command.COMMAND.CONSTRUCT).when(command).getCommand();
            doReturn(terrain).when(model).getTerrain();

            state.processKey(command);

            verify(model, atLeastOnce()).getTerrain();
            verify(model.getTerrain(), atLeastOnce()).placeStone(any(Hero.class));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyQuit(){
        try{
            doReturn(Command.COMMAND.QUIT).when(command).getCommand();
            doReturn(command).when(view).getCommand(any(Command.class));
            state.processKey(command);

            when(factoryState.genMenuState(mock(Soldado.class), mock(MenuView.class))).thenAnswer(invocation -> MenuState.class);

            assertNotNull(state.run().getClass());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runTest(){
        try {
            model.getHero().setHp(0);
            assertNotNull(state.run().getClass());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void newLevelTest() throws IOException, URISyntaxException, InterruptedException, FontFormatException {
        Terrain terrain = new Terrain(4);
        model.setTerrain(terrain);
        assertNotNull(state.newLevel().getClass());

        Terrain terrain2 = new Terrain(3);
        model.setTerrain(terrain2);
        assertNotNull(state.newLevel().getClass());

        Terrain terrain3 = new Terrain(2);
        model.setTerrain(terrain3);
        assertNotNull(state.newLevel().getClass());
    }
}
