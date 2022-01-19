package berzerk.model.game;

import berzerk.model.entity.Wall;
import berzerk.model.entity.enemy.Dementor;
import berzerk.model.entity.enemy.Dragon;
import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class EnemiesTest {

    Terrain terrain;
    Enemies enemies;

    @BeforeEach
    public void initEnemies(){
        terrain = mock(Terrain.class);
        doReturn(new ArrayList<Wall>()).when(terrain).getExit();
        doReturn(new ArrayList<Wall>()).when(terrain).getWalls();
        enemies = new Enemies(terrain, 50);
    }

    @Test
    public void scoreConstructor(){
        assertEquals(50, enemies.getScore());
    }

    @Test
    public void gettersNsetters(){
        enemies = new Enemies(terrain);

        assertEquals(15, enemies.getDragons().size());
        assertEquals(5, enemies.getDementors().size());

        enemies.setDementors(new ArrayList<>());
        enemies.setDragons(new ArrayList<>());

        assertEquals(0, enemies.getDragons().size());
        assertEquals(0, enemies.getDementors().size());

    }

    @Test
    public void inBounds(){
        Position pos1 = new Position(2, 2);
        Position pos2 = new Position(95, 2);
        Position pos3 = new Position(2, 35);
        Position pos4 = new Position(95, 35);

        assertTrue(enemies.inBounds(pos1));
        assertTrue(enemies.inBounds(pos2));
        assertTrue(enemies.inBounds(pos3));
        assertTrue(enemies.inBounds(pos4));

        pos1 = new Position(1, 1);
        pos2 = new Position(100-3, 2);
        pos3 = new Position(2, 40-3);
        pos4 = new Position(100-3, 40-3);

        assertFalse(enemies.inBounds(pos1));
        assertFalse(enemies.inBounds(pos2));
        assertFalse(enemies.inBounds(pos3));
        assertFalse(enemies.inBounds(pos4));
    }

    @Test
    public void killDragon(){
        List<Dragon> dragons = new ArrayList<>();

        dragons.add(new Dragon(5, 5));
        dragons.add(new Dragon(10, 10));
        dragons.add(new Dragon(20, 20));

        enemies.setDragons(enemies.killDragon(new Position(5,5), dragons));
        assertEquals(100, enemies.getScore());

        enemies.setDragons(enemies.killDragon(new Position(5,5), enemies.getDragons()));
        assertEquals(100, enemies.getScore());

        enemies.setDragons(enemies.killDragon(new Position(10,10), enemies.getDragons()));
        assertEquals(150, enemies.getScore());
    }

    @Test
    public void killDementor(){
        List<Dementor> dementors = new ArrayList<>();

        dementors.add(new Dementor(new Position(5,5)));
        dementors.add(new Dementor(new Position(10,10)));
        dementors.add(new Dementor(new Position(20,20)));

        enemies.setDementors(enemies.killDementor(new Position(5,5), dementors));
        assertEquals(150, enemies.getScore());

        enemies.setDementors(enemies.killDementor(new Position(5,5), enemies.getDementors()));
        assertEquals(150, enemies.getScore());

        enemies.setDementors(enemies.killDementor(new Position(10,10), enemies.getDementors()));
        assertEquals(250, enemies.getScore());
    }
}
