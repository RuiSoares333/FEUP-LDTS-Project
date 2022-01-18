package berzerk.model.game;

import berzerk.model.entity.Bullet;
import berzerk.model.entity.enemy.Dementor;
import berzerk.model.entity.enemy.Dragon;
import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.properties.Position;

import java.util.ArrayList;
import java.util.List;

public class Shooter implements Attributes{

    private List<Bullet> bullets;

    public Shooter() {
        bullets = new ArrayList<>();
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }


    public void fire(Position monsterPosition, Position heroPosition){
        if (monsterPosition.getX() == heroPosition.getX()) {
            bullets.add(fireX(monsterPosition, heroPosition));
        } else if (monsterPosition.getY() == heroPosition.getY()) {
            bullets.add(fireY(monsterPosition, heroPosition));
        }
    }

    public Bullet fireX(Position monsterPosition, Position heroPosition){
        if (monsterPosition.getY() > heroPosition.getY()) {
            return new Bullet(monsterPosition.getX(), (monsterPosition.getY() - 1), 1);
        } else {
            return new Bullet(monsterPosition.getX(), (monsterPosition.getY() + 1), 3);
        }
    }

    public Bullet fireY(Position monsterPosition, Position heroPosition){
        if (monsterPosition.getX() > heroPosition.getX()) {
            return new Bullet(monsterPosition.getX() - 1, (monsterPosition.getY()), 4);
        } else {
            return new Bullet(monsterPosition.getX() + 1, (monsterPosition.getY()), 2);
        }
    }

    public List<Bullet> moveBullets(Terrain terrain, Enemies enemies,  Hero hero) {
        List<Bullet> newBullets = new ArrayList<>();
        for(Bullet b : bullets){
            Position newPosition = b.move();
            boolean playerCollision = newPosition.equals(hero.getPosition());

            if(condition(newPosition, terrain, enemies) && !playerCollision){
                b.setPosition(newPosition);
                newBullets.add(b);

            }
            else {
                if (playerCollision)
                    hero.killHero();
                terrain.setStones(terrain.removeStone(newPosition, terrain.getStones()));
                enemies.setDragons(enemies.killDragon(newPosition, enemies.getDragons()));
                enemies.setDementors(enemies.killDementor(newPosition, enemies.getDementors()));
            }

        }
        return newBullets;
    }

    public boolean condition(Position pos, Terrain terrain, Enemies enemies){
        boolean condition1 = verifyCollision(pos, terrain.getWalls());
        boolean condition2 = verifyCollision(pos, terrain.getExit());
        boolean condition3 = verifyCollision(pos, enemies.getDragons());
        boolean condition4 = verifyCollision(pos, enemies.getDementors());
        boolean condition5 = verifyCollision(pos, terrain.getStones());
        return condition1 && condition2 && condition3 && condition4 && condition5;
    }


    public void heroFire(Hero hero) {
        int x = hero.getPosition().getX();
        int y = hero.getPosition().getY();
        switch (hero.getOrientation()){
            case 1 -> bullets.add(new Bullet(x, y-1, 1));
            case 2 -> bullets.add(new Bullet(x+1, y, 2));
            case 3 -> bullets.add(new Bullet(x, y+1, 3));
            case 4 -> bullets.add(new Bullet(x-1, y, 4));
        }
    }


}
