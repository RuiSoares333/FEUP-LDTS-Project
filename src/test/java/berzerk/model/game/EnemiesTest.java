package berzerk.model.game;

import berzerk.model.entity.Wall;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class EnemiesTest {

    @Test
    public void scoreConstructor(){
        Terrain terrain = mock(Terrain.class);
        doReturn(new ArrayList<Wall>()).when(terrain).getExit();
        doReturn(new ArrayList<Wall>()).when(terrain).getWalls();
        Enemies enemies = new Enemies(terrain, 50);

        assertEquals(50, enemies.getScore());
    }

}
