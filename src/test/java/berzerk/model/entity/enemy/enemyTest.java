package berzerk.model.entity.enemy;

import berzerk.model.entity.Bullet;
import berzerk.model.entity.enemy.Enemy;
import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class enemyTest {

    @Test
    public void dragonMove(){
        Position position = new Position(5,5);
        Position movedPosition;
        Dragon dragon = new Dragon(position);
        movedPosition = dragon.move();

        assertNotEquals(position, movedPosition);
    }

    @Test
    public void VoldemortMove(){
        Position position = new Position(5,5);
        Position movedPosition;
        Voldemort voldemort = new Voldemort(position);
        movedPosition = voldemort.move();

        assertNotEquals(position, movedPosition);
    }

    @Test
    public void DementorMove(){
        Position position = new Position(5,5);
        Position movedPosition;
        Dementor dementor = new Dementor(position);
        movedPosition = dementor.move();

        assertNotEquals(position, movedPosition);
    }

}