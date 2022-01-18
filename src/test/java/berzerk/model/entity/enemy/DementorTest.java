package berzerk.model.entity.enemy;

import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DementorTest {

    Dementor dementor;
    Position pos;

    @BeforeEach
    public void initDragon(){
        pos = new Position(4, 4);
        dementor = new Dementor(pos);
    }

    @Test
    public void dementorMoveX1(){
        Position movedPosition = dementor.move(new Position(0,0));;

        assertEquals(new Position(3, 4), movedPosition);
        assertNotEquals(pos, movedPosition);
    }

    @Test
    public void dementorMoveX2(){
        Position movedPosition = dementor.move(new Position(10,0));;

        assertEquals(new Position(5, 4), movedPosition);
        assertNotEquals(pos, movedPosition);
    }

    @Test
    public void dementorMoveY1(){
        Position movedPosition = dementor.move(new Position(4,0));;

        assertEquals(new Position(4, 3), movedPosition);
        assertNotEquals(pos, movedPosition);
    }

    @Test
    public void dementorMoveY2(){
        Position movedPosition = dementor.move(new Position(4,10));;

        assertEquals(new Position(4, 5), movedPosition);
        assertNotEquals(pos, movedPosition);
    }

    @Test
    public void dementorMove(){
        Position movedPosition = dementor.move(new Position(4,4));;

        assertEquals(pos, movedPosition);
    }

    @Test
    public void kill(){
        assertEquals(100, dementor.kill());
    }
}
