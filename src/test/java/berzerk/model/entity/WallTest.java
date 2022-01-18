package berzerk.model.entity;

import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WallTest {

    @Test
    public void StonePositionUp() {
        Position position = new Position(2, 2);
        Wall stone = new Wall(position, 1);

        assertEquals(new Position(2, 1), stone.getPosition());
    }

    @Test
    public void StonePositionRight() {
        Position position = new Position(2, 2);
        Wall stone = new Wall(position, 2);

        assertEquals(new Position(3, 2), stone.getPosition());
    }

    @Test
    public void StonePositionDown() {
        Position position = new Position(2, 2);
        Wall stone = new Wall(position, 3);

        assertEquals(new Position(2, 3), stone.getPosition());
    }

    @Test
    public void StonePositionLeft() {
        Position position = new Position(2, 2);
        Wall stone = new Wall(position, 4);

        assertEquals(new Position(1, 2), stone.getPosition());
    }

}
