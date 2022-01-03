import Entity.Monster;
import Application.Arena;
import Properties.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroDieMonster {

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

}
