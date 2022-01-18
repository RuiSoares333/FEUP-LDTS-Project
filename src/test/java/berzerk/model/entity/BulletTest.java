package berzerk.model.entity;

import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BulletTest {

    @Test
    public void addBullet(){
        List<Bullet> bullets = new ArrayList<>();

        bullets.add(new Bullet(1,2,1));

        assertEquals(1,bullets.size());
    }

    @Test
    public void bulletPosition1(){
        Bullet bullet = new Bullet(1,2,1);

        Position positionExpected = new Position(1,2);

        assertEquals(positionExpected, bullet.getPosition());
    }

    @Test
    public void bulletPosition4(){
        Bullet bullet = new Bullet(1, 2, 4);
        bullet.setPosition(bullet.move());

        Position positionExpected = new Position(0,2);
        assertEquals(positionExpected, bullet.getPosition());
    }

}