package berzerk.model.game;

import berzerk.model.Model;
import berzerk.model.Soldado;
import berzerk.model.entity.*;
import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.properties.Position;
import berzerk.view.View;

import java.io.IOException;
import java.util.*;

public class GameModel implements Model {



    private Hero hero; // hero

    private Terrain terrain; // terrain
    private Enemies enemies; // enemies
    private Shooter shooter; // shooter

    // timers
    private final Timer MM_TIMER;
    private final Timer BM_TIMER;
    private final Timer D_TIMER;
    private final Timer DM_TIMER;

    public GameModel(Soldado soldado, int nivel, int score, int hp) throws IOException {
        shooter = new Shooter();
        terrain = new Terrain(nivel);
        enemies = new Enemies(terrain, score);

        hero = createHero(soldado, terrain.getInitialPosition());
        hero.setHp(hp);

        MM_TIMER = new Timer();
        BM_TIMER = new Timer();
        D_TIMER = new Timer();
        DM_TIMER = new Timer();
    }

    public GameModel(Soldado soldado, int nivel) throws IOException {
        shooter = new Shooter();
        terrain = new Terrain(nivel);
        enemies = new Enemies(terrain);

        hero = createHero(soldado, terrain.getInitialPosition());

        MM_TIMER = new Timer();
        BM_TIMER = new Timer();
        D_TIMER = new Timer();
        DM_TIMER = new Timer();
    }

    public Hero getHero(){
        return hero;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public Enemies getEnemies() {
        return enemies;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void setEnemies(Enemies enemies) {
        this.enemies = enemies;
    }

    public void setShooter(Shooter shooter) {
        this.shooter = shooter;
    }

    //---------------------------------- TIMERS ------------------------------------------------------------

    public void initTimers(View<GameModel> view){
        initMonsterTimer();
        initBulletTimer();
        initDrawTimer(view);
    }

    public void endTimers(){
        MM_TIMER.cancel();
        DM_TIMER.cancel();
        BM_TIMER.cancel();
        D_TIMER.cancel();
    }

    public void initMonsterTimer(){
        MM_TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                moveMonsters(enemies, terrain, shooter, hero);
            }
        }, 0, 1000);

        DM_TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                moveDementors(enemies, terrain, shooter, hero);
            }
        }, 0, 700);
    }

    public void initBulletTimer(){
        BM_TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                moveBullet(shooter, terrain, enemies, hero);
            }
        }, 0, 50);
    }

    public void initDrawTimer(View<GameModel> view){
        D_TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    view.draw(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 25);
    }

    //------------------------------------- MONSTERS -----------------------------------------------------


    public void moveMonsters(Enemies enemies, Terrain terrain, Shooter shooter, Hero hero){
        enemies.moveEnemies(enemies.getDragons(), terrain, shooter, hero);
    }

    public void moveDementors(Enemies enemies, Terrain terrain, Shooter shooter,  Hero hero){
        enemies.moveEnemies(enemies.getDementors(), terrain, shooter, hero);
    }

    //------------------------------------- BULLETS -----------------------------------------------------

    public void moveBullet(Shooter shooter,Terrain terrain, Enemies enemies, Hero hero){
        shooter.setBullets(shooter.moveBullets(terrain, enemies, hero));
    }

    //---------------------------------------- HERO --------------------------------------------------------------

    public Hero createHero(Soldado heroi, Position initialPosition){
        Hero hero = new Hero(initialPosition, 6);
        if(heroi.getSelected() != null) {
            switch (heroi.getSelected()) {
                case TANKY -> hero = new Hero(initialPosition, 9);
                case EXPERT -> hero = new Hero(initialPosition, 3);
                default -> hero = new Hero(initialPosition, 6);
            }
        }
        return hero;
    }

    public void moveHero(Hero hero, Shooter shooter,Terrain terrain, Enemies enemies, Position position) {
        hero.move(shooter, terrain, enemies, position);
    }

    public boolean isLeaving(Hero hero, Terrain terrain){
        if(terrain!= null) return terrain.isLeaving(hero);
        else return false;
    }
}