package berzerk.model.game;

import berzerk.model.Soldado;
import berzerk.model.entity.Bullet;
import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.properties.Position;
import berzerk.view.View;
import com.googlecode.lanterna.graphics.BasicTextImage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameModelTest {

    GameModel model;
    Soldado soldado;
    View<GameModel> view;
    Terrain terrain;
    Enemies enemies;
    Shooter shooter;
    Hero hero;
    Position position;

    @BeforeEach
    public void initGameModel() throws IOException {
        terrain = mock(Terrain.class);
        enemies = mock(Enemies.class);
        doNothing().when(enemies).moveEnemies(any(), any(), any(), any());

        shooter = mock(Shooter.class);
        doNothing().when(shooter).setBullets(any());

        hero = mock(Hero.class);
        doNothing().when(hero).move(any(), any(), any(), any());

        view = mock(View.class);
        doNothing().when(view).draw(anyInt());

        soldado = mock(Soldado.class);
        model= spy(new GameModel(soldado, 1, 0,3));
    }

    @AfterEach
    public void endGame(){
        model.initTimers(view);
        model.endTimers();
    }


    //-------------------------------- SOLDADOS -----------------------------------------------

    @Test
    public void createHeroRecruit() throws IOException {
        when(soldado.getSelected()).thenReturn(Soldado.Heroi.RECRUIT);

        model= new GameModel(soldado, 1);

        assertEquals(6, model.createHero(soldado, new Position(0, 0)).getHp());
    }

    @Test
    public void createHeroTanky() throws IOException {
        when(soldado.getSelected()).thenReturn(Soldado.Heroi.TANKY);

        model= new GameModel(soldado, 1);

        assertEquals(9, model.createHero(soldado, new Position(0, 0)).getHp());
    }

    @Test
    public void createHeroExpert() throws IOException {
        when(soldado.getSelected()).thenReturn(Soldado.Heroi.EXPERT);

        model= new GameModel(soldado, 1);

        assertEquals(3, model.createHero(soldado, new Position(0, 0)).getHp());
    }

    // ---------------------------------- ATTRIBUTES --------------------------------------------

    @Test
    public void gettersNsetters(){
        model.setHero(hero);
        model.setEnemies(enemies);
        model.setShooter(shooter);
        model.setTerrain(terrain);

        assertEquals(hero, model.getHero());
        assertEquals(enemies, model.getEnemies());
        assertEquals(shooter, model.getShooter());
        assertEquals(terrain, model.getTerrain());
    }

    @Test
    public void moveHero(){
        position = mock(Position.class);
        doNothing().when(hero).move(shooter, terrain, enemies, position);

        model.moveHero(hero, shooter, terrain, enemies, position);
        verify(hero, atLeastOnce()).move(shooter, terrain, enemies, position);
    }


    @Test
    public void isLeaving(){
        doReturn(true).when(terrain).isLeaving(hero);
        assertTrue(model.isLeaving(hero, terrain));

        doReturn(false).when(terrain).isLeaving(hero);
        assertFalse(model.isLeaving(hero, terrain));

        assertFalse(model.isLeaving(null, null));
    }

}
