package berzerk.model;

import berzerk.model.entity.Bullet;
import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class bulletsTest {

    @Test
    public void addBullet(){
        List<Bullet> bullets = new ArrayList<>();

        bullets.add(new Bullet(1,2,1));

        assertEquals(1,bullets.size());
    }

    @Test
    public void bulletPosition(){
        List<Bullet> bullets = new ArrayList<>();

        bullets.add(new Bullet(1,2,1));

        Position positionExpected = new Position(1,2);

        assertEquals(positionExpected, bullets.get(0).getPosition());
    }

}

