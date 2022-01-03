import Entity.Hero.Hero;
import Application.Arena;
import Properties.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HeroDieWall {

    @Test
    public void heroDieWallTest(){
        Arena arena = new Arena(50,50);
        Position positionFinal1 = new Position(10+8, 10);
        Position positionFinal2 = new Position(10-10, 10);
        Position positionFinal3 = new Position(10, 10-8);
        Position positionFinal4 = new Position(10, 12);
        boolean result1, result2, result3, result4;

        arena.getHero().setPosition(positionFinal1);
        result1 = arena.canEntityMove(arena.getHero().getPosition());
        arena.getHero().setPosition(positionFinal2);
        result2 = arena.canEntityMove(arena.getHero().getPosition());
        arena.getHero().setPosition(positionFinal3);
        result3 = arena.canEntityMove(arena.getHero().getPosition());
        arena.getHero().setPosition(positionFinal4);
        result4 = arena.canEntityMove(arena.getHero().getPosition());


        assertEquals(false, result1);
        assertEquals(false, result2);
        assertEquals(false, result3);
        assertEquals(true, result4);

    }
}
