package berzerk.model;

import berzerk.model.entity.Monster;
import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArenaTest {

    @Test
    public void heroDieMonsterTest(){
        Arena arena = new Arena(50,50);
        Monster monster1 = arena.getMonsters().get(0);
        Position monster1Pos = monster1.getPosition();
        Monster monster2 = arena.getMonsters().get(1);
        Position monster2Pos = monster2.getPosition();
        Monster monster3 = arena.getMonsters().get(2);
        Position monster3Pos = monster3.getPosition();
        boolean result1, result2, result3;

        arena.getHero().setPosition(monster1Pos);
        result1 = arena.verifyMonsterCollision(arena.getHero().getPosition());
        arena.getHero().setPosition(monster2Pos);
        result2 = arena.verifyMonsterCollision(arena.getHero().getPosition());
        arena.getHero().setPosition(monster3Pos);
        result3 = arena.verifyMonsterCollision(arena.getHero().getPosition());

        assertEquals(false, result1);
        assertEquals(false, result2);
        assertEquals(false, result3);

    }

//    @Test
//    public void heroDieWallTest(){
//        Arena arena = new Arena(50,50);
//        Position positionFinal1 = new Position(10+8, 10);
//        Position positionFinal2 = new Position(10-10, 10);
//        Position positionFinal3 = new Position(10, 10-8);
//        Position positionFinal4 = new Position(10, 12);
//        boolean result1, result2, result3, result4;
//
//        arena.getHero().setPosition(positionFinal1);
//        result1 = arena.canEntityMove(arena.getHero().getPosition());
//        arena.getHero().setPosition(positionFinal2);
//        result2 = arena.canEntityMove(arena.getHero().getPosition());
//        arena.getHero().setPosition(positionFinal3);
//        result3 = arena.canEntityMove(arena.getHero().getPosition());
//        arena.getHero().setPosition(positionFinal4);
//        result4 = arena.canEntityMove(arena.getHero().getPosition());
//
//
//        assertEquals(false, result1);
//        assertEquals(false, result2);
//        assertEquals(false, result3);
//        assertEquals(true, result4);
//
//    }

}
