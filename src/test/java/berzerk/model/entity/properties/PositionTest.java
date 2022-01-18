package berzerk.model.entity.properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {

    Position pos;

    @BeforeEach
    public void initPosition(){
        Position position = new Position(5, 5);
        pos = new Position(position);
    }

    @Test
    public void setPos(){
        pos.setX(8);
        assertEquals(new Position(8, 5), pos);

        pos.setY(8);
        assertEquals(new Position(8, 8), pos);

        pos.setPosition(new Position(10, 10));
        assertEquals(new Position(10, 10), pos);
    }

    @Test
    public void toStringTest(){
        assertEquals("5 5", pos.toString());
    }
}
