package berzerk.model;

import berzerk.model.entity.Monster;
import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArenaTest {

    Arena arena;
    Hero hero;

    @BeforeEach
    public void dados(){
        hero = new Hero(10,10,10,3);
        arena= new Arena(hero);
    }

    @Test
    public void heroDieMonsterTest(){
        Monster monster1 = arena.getMonsters().get(0);
        Position monster1Pos = monster1.getPosition();
        Monster monster2 = arena.getMonsters().get(1);
        Position monster2Pos = monster2.getPosition();
        Monster monster3 = arena.getMonsters().get(2);
        Position monster3Pos = monster3.getPosition();
        Position pass = new Position(0,0);
        boolean result1, result2, result3, result4;

        arena.getHero().setPosition(monster1Pos);
        result1 = arena.verifyMonsterCollision(arena.getHero().getPosition());
        arena.getHero().setPosition(monster2Pos);
        result2 = arena.verifyMonsterCollision(arena.getHero().getPosition());
        arena.getHero().setPosition(monster3Pos);
        result3 = arena.verifyMonsterCollision(arena.getHero().getPosition());
        pass.setX(arena.getHero().getPosition().getX()+1);
        pass.setY(arena.getHero().getPosition().getY());
        result4 = arena.verifyMonsterCollision(pass);

        assertEquals(false, result1);
        assertEquals(false, result2);
        assertEquals(false, result3);
        assertEquals(true, result4);

    }

    @Test
    public void heroDieWallTest(){
        Position positionFinal1 = new Position(10+5,10+2);
        Position positionFinal2 = new Position(10-10,10);
        Position positionFinal3 = new Position(10,10-8);
        Position positionFinal4 = new Position(10,12);
        Position positionFinal5 = new Position(10+5,10+5);
        boolean result1, result2, result3, result4, result5, result6;

        arena.getHero().setPosition(positionFinal1);
        result1 = arena.canEntityMove(arena.getHero().getPosition());
        arena.getHero().setPosition(positionFinal5);
        result2 = arena.verifyHeroWallCollision(arena.getHero().getPosition());
        arena.getHero().setPosition(positionFinal2);
        result3 = arena.canEntityMove(arena.getHero().getPosition());
        arena.getHero().setPosition(positionFinal3);
        result4 = arena.canEntityMove(arena.getHero().getPosition());
        arena.getHero().setPosition(positionFinal4);
        result5 = arena.canEntityMove(arena.getHero().getPosition());
        result6 = arena.verifyHeroWallCollision(arena.getHero().getPosition());


        assertEquals(false, result1);
        assertEquals(false, result2);
        assertEquals(false, result3);
        assertEquals(false, result4);
        assertEquals(true, result5);
        assertEquals(true, result6);

    }

    @Test
    public void canMonsterMoveTest(){
        Position position1 = new Position(10+5,10+5);
        Position position2 = new Position(11,15);
        boolean result1, result2;

        result1 = arena.canMonsterMove(position1);
        result2 = arena.canMonsterMove(position2);

        assertEquals(false, result1);
        assertEquals(true, result2);


    }


}
