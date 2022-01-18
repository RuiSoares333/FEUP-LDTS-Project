package berzerk.model.entity.hero;

import berzerk.model.entity.Bullet;
import berzerk.model.entity.Wall;
import berzerk.model.entity.enemy.Dementor;
import berzerk.model.entity.enemy.Dragon;
import berzerk.model.entity.properties.Position;
import berzerk.model.game.Enemies;
import berzerk.model.game.Shooter;
import berzerk.model.game.Terrain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class HeroTest {

    Hero hero;
    Position initPos;
    Terrain terrain;
    Enemies enemies;
    Shooter shooter;

    @BeforeEach
    public void initHero(){
        terrain = mock(Terrain.class);
        enemies = mock(Enemies.class);
        shooter = mock(Shooter.class);
        initPos = new Position(4, 4);
        hero = new Hero(initPos, 5);
    }

    @Test
    public void heroInitialOrientation(){
        assertEquals(2, hero.getOrientation());
    }

    @Test
    public void heroOrientationMoveDown(){
        hero.moveDown();
        assertEquals(3, hero.getOrientation());
    }

    @Test
    public void heroOrientationMoveLeft(){
        hero.moveLeft();
        assertEquals(4, hero.getOrientation());
    }

    @Test
    public void heroOrientationMoveUp(){
        hero.moveUp();
        assertEquals(1, hero.getOrientation());
    }

    @Test
    public void heroOrientationMoveRight(){
        hero.moveRight();
        assertEquals(2, hero.getOrientation());
    }

    @Test
    public void killHero(){
        hero.killHero();
        assertEquals(4, hero.getHp());
        hero.killHero();
        assertEquals(3, hero.getHp());
    }

    @Test
    public void heroCondition(){
        assertTrue(hero.condition(terrain, enemies, shooter, hero.getPosition()));
    }

    @Test
    public void heroCondition1(){
        List<Wall> walls = new ArrayList<>();

        doReturn(walls).when(terrain).getWalls();
        doReturn(walls).when(terrain).getStones();

        walls.add(new Wall(0,0));
        walls.add(new Wall(1,1));
        walls.add(new Wall(2,2));
        walls.add(new Wall(3,3));

        assertTrue(hero.condition(terrain, enemies, shooter, hero.getPosition()));

        walls.add(new Wall(4,4));
        assertFalse(hero.condition(terrain, enemies, shooter, hero.getPosition()));
    }

    @Test
    public void heroCondition2(){
        List<Dragon> dragons = new ArrayList<>();
        List<Dementor> dementors = new ArrayList<>();

        doReturn(dragons).when(enemies).getDragons();
        doReturn(dementors).when(enemies).getDementors();

        dragons.add(new Dragon(0, 0));
        dragons.add(new Dragon(1, 1));
        dragons.add(new Dragon(2, 2));
        dragons.add(new Dragon(3, 3));

        assertTrue(hero.condition(terrain, enemies, shooter, hero.getPosition()));

        dragons.add(new Dragon(4, 4));
        assertFalse(hero.condition(terrain, enemies, shooter, hero.getPosition()));
    }

    @Test
    public void heroCondition3(){
        List<Bullet> bullets = new ArrayList<>();

        doReturn(bullets).when(shooter).getBullets();

        bullets.add(new Bullet(0, 0, 1));
        bullets.add(new Bullet(1, 1, 1));
        bullets.add(new Bullet(2, 2, 1));
        bullets.add(new Bullet(3, 3, 1));


        assertTrue(hero.condition(terrain, enemies, shooter, hero.getPosition()));

        bullets.add(new Bullet(4, 4, 1));
        assertFalse(hero.condition(terrain, enemies, shooter, hero.getPosition()));
    }

    @Test
    public void heroMove(){
        List<Dragon> dragons = new ArrayList<>();
        List<Dementor> dementors = new ArrayList<>();

        doReturn(dragons).when(enemies).getDragons();
        doReturn(dementors).when(enemies).getDementors();

        dragons.add(new Dragon(0, 0));
        dragons.add(new Dragon(1, 1));
        dragons.add(new Dragon(2, 2));
        dragons.add(new Dragon(3, 3));

        Position pos = new Position(5, 5);
        hero.move(shooter, terrain, enemies, pos);
        assertEquals(5, hero.getHp());
        assertEquals(pos, hero.getPosition());

        pos = new Position(3, 3);
        hero.move(shooter, terrain, enemies, pos);
        assertEquals(4, hero.getHp());
        assertEquals(initPos, hero.getPosition());
    }
}
