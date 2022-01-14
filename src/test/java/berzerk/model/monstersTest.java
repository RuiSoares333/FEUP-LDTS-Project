package berzerk.model;

import berzerk.model.entity.Monster;
import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class monstersTest {

    //Testar se um monstro morre se for atingido por uma bala
    @Test
    public void monsterHit(){

    }

    @Test
    public void monsterMove(){

        Position position = new Position(5,5);
        Position movedPosition;
        Monster monster = new Monster(position);
        movedPosition = monster.move();

        assertNotEquals(position, movedPosition);
    }

}