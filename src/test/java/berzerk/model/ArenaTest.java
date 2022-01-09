package berzerk.model;

import berzerk.model.entity.Monster;
import berzerk.model.entity.Wall;
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
        arena= new Arena(hero, 1);
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

        assertEquals(false,result1);
        assertEquals(false,result2);
        assertEquals(false,result3);
        assertEquals(true,result4);

    }

    @Test
    public void heroDieWallTest(){
        Wall wall1 = arena.getWalls().get(0);
        Position wall1Position = wall1.getPosition();
        Wall wall2 = arena.getWalls().get(1);
        Position wall2Position = wall2.getPosition();
        Wall wall3 = arena.getWalls().get(2);
        Position wall3Position = wall3.getPosition();
        Wall wall4 = arena.getWalls().get(3);
        Position wall4Position= wall4.getPosition();
        Position notWallPosition = new Position(10,10);
        boolean result1, result2, result3, result4, result5, result6;

        arena.getHero().setPosition(wall1Position);
        result1 = arena.verifyWallCollision(arena.getHero().getPosition());
        arena.getHero().setPosition(wall2Position);
        result2 = arena.verifyHeroWallCollision(arena.getHero().getPosition());
        arena.getHero().setPosition(wall3Position);
        result3 = arena.verifyWallCollision(arena.getHero().getPosition());
        arena.getHero().setPosition(notWallPosition);
        result4 = arena.verifyWallCollision(arena.getHero().getPosition());
        arena.getHero().setPosition(wall4Position);
        result5 = arena.verifyHeroWallCollision(arena.getHero().getPosition());
        arena.getHero().setPosition(notWallPosition);
        result6 = arena.verifyHeroWallCollision(arena.getHero().getPosition());


        assertEquals(false, result1);
        assertEquals(false, result2);
        assertEquals(false, result3);
        assertEquals(true, result4);
        assertEquals(false, result5);
        assertEquals(true, result6);

    }

    @Test
    public void canMonsterMoveTest(){
        Wall wall1 = arena.getWalls().get(0);
        Position position1 = wall1.getPosition();
        Wall wall2 = arena.getWalls().get(1);
        Position position2 = wall2.getPosition();
        Position notWallPosition = new Position(10,10);
        boolean result1, result2, result3;

        result1 = arena.canMonsterMove(position1);
        result2 = arena.canMonsterMove(position2);
        result3 = arena.canMonsterMove(notWallPosition);

        assertEquals(false,result1);
        assertEquals(false,result2);
        assertEquals(true,result3);


    }


}
