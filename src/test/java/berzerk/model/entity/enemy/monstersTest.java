package berzerk.model.entity.enemy;

import berzerk.model.entity.enemy.Enemy;
import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class monstersTest {

    //Testar se um monstro morre se for atingido por uma bala
    @Test
    public void monsterHit(){

    }

    @Test
    public void enemyMove(){

        Position position = new Position(5,5);
        Position movedPosition;
        Enemy enemy = new Enemy(position);
        movedPosition = enemy.move();

        assertNotEquals(position, movedPosition);
    }

}