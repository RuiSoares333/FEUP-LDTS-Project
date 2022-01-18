package berzerk.model.game;

import berzerk.model.entity.Wall;
import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class TerrainTest {
    Terrain terrain;


    @Test
    public void attributes() throws IOException {
        terrain = new Terrain(1);
        assertNotNull(terrain.getReader());
        assertEquals(1, terrain.getLevel());
        assertEquals(new Position(50, 35), terrain.getInitialPosition());
        assertEquals(352, terrain.getWalls().size());
        assertEquals(24, terrain.getExit().size());
        assertEquals(0, terrain.getStones().size());

        terrain = new Terrain(2);
        assertNotNull(terrain.getReader());
        assertEquals(2, terrain.getLevel());
        assertEquals(new Position(50, 35), terrain.getInitialPosition());
        assertEquals(359, terrain.getWalls().size());
        assertEquals(9, terrain.getExit().size());
        assertEquals(0, terrain.getStones().size());


        terrain = new Terrain(3);
        assertNotNull(terrain.getReader());
        assertEquals(3, terrain.getLevel());
        assertEquals(new Position(5, 8), terrain.getInitialPosition());
        assertEquals(313, terrain.getWalls().size());
        assertEquals(24, terrain.getExit().size());
        assertEquals(0, terrain.getStones().size());
    }

    @Test
    public void isLeaving() throws IOException {
        terrain = new Terrain(1);
        Hero hero = mock(Hero.class);
        Position exit = terrain.getExit().get(0).getPosition();
        Position wall = terrain.getWalls().get(0).getPosition();

        doReturn(wall).when(hero).getPosition();
        assertTrue(terrain.isLeaving(hero));

        doReturn(exit).when(hero).getPosition();
        assertFalse(terrain.isLeaving(hero));
    }

    @Test
    public void stonePlacing() throws IOException {
        terrain = new Terrain(1);
        Hero hero = mock(Hero.class);

        assertEquals(0, terrain.getStones().size());

        doReturn(new Position(5, 5)).when(hero).getPosition();
        doReturn(1).when(hero).getOrientation();
        terrain.placeStone(hero);
        assertEquals(1, terrain.getStones().size());

        doReturn(new Position(10, 10)).when(hero).getPosition();
        doReturn(1).when(hero).getOrientation();
        terrain.placeStone(hero);
        assertEquals(2, terrain.getStones().size());

        doReturn(new Position(20, 20)).when(hero).getPosition();
        doReturn(1).when(hero).getOrientation();
        terrain.placeStone(hero);
        assertEquals(3, terrain.getStones().size());

        int newSize = terrain.removeStone(new Position(5, 4), terrain.getStones()).size();
        assertEquals(2, newSize);

        newSize = terrain.removeStone(new Position(7, 7), terrain.getStones()).size();
        assertEquals(3, newSize);
    }
}
