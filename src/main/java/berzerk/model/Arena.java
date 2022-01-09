package berzerk.model;

import berzerk.model.entity.Monster;
import berzerk.model.entity.Wall;
import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.properties.Position;
import berzerk.view.GameView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Arena implements Model {

    Position initialPosition;

    Hero hero;

    private final List<Wall> walls;
    private final List<Monster> monsters;

    private GameView view;

    public Arena(Hero hero, int nivel){
        this.hero = hero;

        walls = createWalls(nivel);
        initialPosition = hero.getPosition();
        monsters = createMonsters();
    }

    public void positionHero(){
        hero.setPosition(initialPosition);
    }

    public Hero getHero(){
        return hero;
    }

    public List<Monster> getMonsters(){
        return monsters;
    }

    public List<Wall> getWalls(){
        return walls;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    //---------------------------------- PAREDES -----------------------------------------------------------

    private List<Wall> createWalls(int nivel) {
        List<Wall> paredes = new ArrayList<>();
        String mapa = "nivel" + nivel + ".txt";
        InputStream is = ClassLoader.getSystemResourceAsStream(mapa);
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        try {
            int i = 0;
            for (String line; (line = reader.readLine()) != null; i++) {
                int j = 0;
                for (char c: line.toCharArray()) {
                    if(c == 'p'){
                        paredes.add(new Wall(j, i));
                    }
                    j++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paredes;
    }

//---------------------------------------- HEROI --------------------------------------------------------------

    public void moveHero(Position position) {
        if(verifyWallCollision(position)) {
            hero.setPosition(position);
            if(!verifyMonsterCollision(position) || !verifyHeroWallCollision(position)){
                System.out.println("Game Over!");
            }
        }
    }

    public boolean verifyWallCollision(Position position){
        for(Wall wall : walls){
            if(wall.getPosition().equals(position)) {
                System.out.println("Game Over!");
                positionHero();
                return false;
            }
        }
        return true;
    }


    public boolean verifyMonsterCollision(Position position){
        for(Monster monster: monsters) {
            if (position.equals(monster.getPosition())) {
                positionHero();
                return false;
            }
        }
        return true;

    }

    public boolean verifyHeroWallCollision(Position position){
        for(Wall wall : walls){
            if(wall.getPosition().equals(position)){
                return false;
            }
        }
        return true;
    }


    //------------------------------------- MONSTROS -----------------------------------------------------

    public List<Monster> createMonsters(){
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        boolean flag;

        while(monsters.size() < 10){
            flag = true;
            Position novaPosicao = new Position(random.nextInt(Constants.WIDTH-1), random.nextInt(Constants.HEIGHT-1));
            for (Wall w: walls) {
                if(w.getPosition() == novaPosicao) flag = false;
            }

            if(flag) monsters.add(new Monster(novaPosicao));
        }

        return monsters;
    }


    //Move monster in random directions
    public void scheduleMonsterMovement(){
        // And From your main() method or any other method
        Timer timer = new Timer();

        // Agenda do procedimento
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                moveMonsters();

                // desenha o monstro
                try {
                    view.draw(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, 0, 1000);
    }

    public void moveMonsters(){
        for (Monster monster: monsters) {

            Position novaPosicao = monster.move();

            if(canMonsterMove(novaPosicao))
                monster.setPosition(novaPosicao);

        }
    }

    public boolean canMonsterMove(Position position){
        for(Wall wall : walls){
            if(wall.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }
}
