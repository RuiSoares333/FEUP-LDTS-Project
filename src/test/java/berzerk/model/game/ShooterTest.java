package berzerk.model.game;

import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShooterTest {
    Shooter shooter;
    Hero hero;
    Position pos;


    @BeforeEach
    public void initShooter(){
        shooter = spy(new Shooter());
        hero = mock(Hero.class);
        pos = new Position(5, 5);
    }



    @Test
    public void heroFireSize(){
        doReturn(pos).when(hero).getPosition();
        doReturn(1).when(hero).getOrientation();
        shooter.heroFire(hero);
        assertEquals(1, shooter.getBullets().size());

        doReturn(2).when(hero).getOrientation();
        shooter.heroFire(hero);
        assertEquals(2, shooter.getBullets().size());

        doReturn(3).when(hero).getOrientation();
        shooter.heroFire(hero);
        assertEquals(3, shooter.getBullets().size());

        doReturn(4).when(hero).getOrientation();
        shooter.heroFire(hero);
        assertEquals(4, shooter.getBullets().size());
    }

    @Test
    public void fireX(){
        Position monsterPos1 = new Position(5, 0);
        Position monsterPos2 = new Position(5, 10);

        shooter.fire(monsterPos1, pos);
        shooter.fire(monsterPos2, pos);

        assertEquals(new Position(5, 1), shooter.getBullets().get(0).getPosition());
        assertEquals(new Position(5, 9), shooter.getBullets().get(1).getPosition());
    }

    @Test
    public void fireY(){
        Position monsterPos1 = new Position(0, 5);
        Position monsterPos2 = new Position(10, 5);

        shooter.fire(monsterPos1, pos);
        shooter.fire(monsterPos2, pos);

        assertEquals(new Position(1, 5), shooter.getBullets().get(0).getPosition());
        assertEquals(new Position(9, 5), shooter.getBullets().get(1).getPosition());
    }
}
