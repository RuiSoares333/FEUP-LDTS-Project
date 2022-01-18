package berzerk.model.entity.enemy;

import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class DragonTest {

    Dragon dragon;
    Position pos;

    @BeforeEach
    public void initDragon(){
        pos = new Position(4, 4);
        dragon = spy(new Dragon(pos));
    }

    @Test
    public void dragonMove(){
        doReturn(0).when(dragon).getRandInt();
        Position movedPosition = dragon.move(new Position(0,0));;

        assertNotEquals(pos, movedPosition);

        doReturn(1).when(dragon).getRandInt();
        movedPosition = dragon.move(new Position(0,0));;

        assertNotEquals(pos, movedPosition);

        doReturn(2).when(dragon).getRandInt();
        movedPosition = dragon.move(new Position(0,0));;

        assertNotEquals(pos, movedPosition);

        doReturn(3).when(dragon).getRandInt();
        movedPosition = dragon.move(new Position(0,0));;

        assertNotEquals(pos, movedPosition);
    }

    @Test
    public void kill(){
        assertEquals(50, dragon.kill());
    }

    @Test
    public void randInt(){
        int randInt = dragon.getRandInt();
        boolean result = 0 <= randInt && randInt<= 3;
        assertTrue(result);
    }
}
