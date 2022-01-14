package berzerk.model;

import berzerk.model.entity.hero.Hero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroOrientation {

    @Test
    public void heroInitialOrientation(){

        Hero hero = new Hero(5,5,1,1);

        assertEquals(2, hero.getOrientation());
    }

    @Test
    public void heroOrientationMoveDown(){
        Hero hero = new Hero(5,5,1,1);

        hero.moveDown();

        assertEquals(3, hero.getOrientation());
    }

    @Test
    public void heroOrientationMoveLeft(){
        Hero hero = new Hero(5,5,1,1);

        hero.moveLeft();

        assertEquals(4, hero.getOrientation());
    }

    @Test
    public void heroOrientationMoveUp(){
        Hero hero = new Hero(5,5,1,1);

        hero.moveUp();

        assertEquals(1, hero.getOrientation());
    }

    @Test
    public void heroOrientationMoveRight(){
        Hero hero = new Hero(5,5,1,1);

        hero.moveUp();

        assertEquals(2, hero.getOrientation());
    }
}
