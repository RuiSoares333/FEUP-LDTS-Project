package berzerk.model;

import berzerk.model.entity.Stone;
import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class wallCreationTests {

    @Test
    public void StonePositionUp() {
        Position position = new Position(2, 2);
        Position newPosition;
        Stone stone = new Stone(position, 1);
        newPosition = new Position(2,1);

        assertEquals(stone.getPosition(), newPosition);
    }

    @Test
    public void StonePositionRight() {
        Position position = new Position(2, 2);
        Position newPosition;
        Stone stone = new Stone(position, 2);
        newPosition = new Position(3,2);

        assertEquals(stone.getPosition(), newPosition);
    }

    @Test
    public void StonePositionDown() {
        Position position = new Position(2, 2);
        Position newPosition;
        Stone stone = new Stone(position, 3);
        newPosition = new Position(2,3);

        assertEquals(stone.getPosition(), newPosition);
    }

    @Test
    public void StonePositionLeft() {
        Position position = new Position(2, 2);
        Position newPosition;
        Stone stone = new Stone(position, 4);
        newPosition = new Position(1,2);

        assertEquals(stone.getPosition(), newPosition);
    }

}
