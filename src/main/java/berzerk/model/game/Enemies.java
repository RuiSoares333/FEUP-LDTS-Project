package berzerk.model.game;

import berzerk.model.Constants;
import berzerk.model.entity.enemy.Dementor;
import berzerk.model.entity.enemy.Dragon;
import berzerk.model.entity.enemy.Enemy;
import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.properties.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemies implements Attributes{

    private final int NUM_DRAGONS = 15;
    private final int NUM_DEMENTORS = 5;

    private int score;

    private List<Dragon> dragons;
    private List<Dementor> dementors;

    public Enemies(Terrain terrain, int score){
        populateMonsters(terrain);
        this.score = score;
    }

    public Enemies(Terrain terrain){
        populateMonsters(terrain);
        score = 0;
    }

    public List<Dragon> getDragons(){
        return dragons;
    }

    public List<Dementor> getDementors(){
        return dementors;
    }

    public void setDragons(List<Dragon> dragons) {
        this.dragons = dragons;
    }

    public void setDementors(List<Dementor> dementors) {
        this.dementors = dementors;
    }

    public int getScore(){
        return score;
    }

    private void populateMonsters(Terrain terrain){
        dragons = (List<Dragon>) createEnemies(NUM_DRAGONS, terrain, false);
        dementors = (List<Dementor>) createEnemies(NUM_DEMENTORS, terrain, true);
    }


    private List<? extends Enemy> createEnemies(int numMonstros, Terrain terrain, boolean dementor){
        Random random = new Random();
        List<Enemy> enemies = new ArrayList<>();

        while(enemies.size() < numMonstros){
            Position novaPosicao = new Position(random.nextInt(Constants.WIDTH-3), random.nextInt(Constants.HEIGHT-3));

            if(condition(novaPosicao, terrain)) {
                if(dementor){
                    enemies.add(new Dementor(novaPosicao));
                }
                else{
                    enemies.add(new Dragon(novaPosicao));
                }
            }
        }
        return enemies;
    }

    public boolean condition(Position pos, Terrain terrain){
        boolean condition1 = verifyCollision(pos, terrain.getWalls());
        boolean condition2 = verifyCollision(pos, terrain.getExit());
        boolean condition5 = inBounds(pos);
        return condition1 && condition2 && condition5;
    }

    public boolean inBounds(Position pos){
        boolean condition1 = pos.getX() > 1;
        boolean condition2 = pos.getX() < Constants.WIDTH-3;
        boolean condition3 = pos.getY() > 1;
        boolean condition4 = pos.getY() < Constants.HEIGHT-3;
        return condition1 && condition2 && condition3 && condition4;
    }

    public void moveEnemies(List<? extends Enemy> enemies, Terrain terrain, Shooter shooter, Hero hero){
        List<? extends Enemy> newMonsters = new ArrayList<>();
        for (Enemy e: enemies) {
            Position newPosition = e.move(hero.getPosition());
            boolean playerCollision = newPosition.equals(hero.getPosition());

            e.setPosition(newPosition);
            shooter.fire(newPosition, hero.getPosition());

            if(playerCollision) hero.killHero();
        }
    }


    public List<Dragon> killDragon(Position pos, List<Dragon> enemies){
        List<Dragon> newMonsters = new ArrayList<>();
        for (Dragon m: dragons){
                if (!m.getPosition().equals(pos)) newMonsters.add(m);

                else score += m.kill();
        }

        return newMonsters;
    }

    public List<Dementor> killDementor(Position pos, List<Dementor> enemies){
        List<Dementor> newMonsters = new ArrayList<>();
        for (Dementor m: dementors){
            if (!m.getPosition().equals(pos)) newMonsters.add(m);

            else score += m.kill();
        }

        return newMonsters;
    }


}
