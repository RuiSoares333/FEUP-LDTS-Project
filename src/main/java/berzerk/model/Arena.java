package berzerk.model;

import berzerk.model.entity.Monster;
import berzerk.model.entity.Wall;
import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.properties.Position;
import berzerk.view.GameView;
import berzerk.view.View;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

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

    private View<GameView> view;

    public Arena(Hero hero, int nivel){
        this.hero = hero;

        walls = carregarNivel(nivel);
        initialPosition = hero.getPosition();
        monsters = createMonsters();
    }

    public boolean moveHero(Position position) {
        if(canHeroMove(position)) {
            hero.setPosition(position);
            if(!verifyMonsterCollision(position) || !verifyHeroWallCollision(position)){
                System.out.println("Game Over!");
                return true;
            }
        }
        return true;
    }

    public void moveMonsters(){
        for (Monster monster: monsters) {

            Position novaPosicao = monster.move();

            if(canMonsterMove(novaPosicao))
                monster.setPosition(novaPosicao);

        }
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

    public boolean canHeroMove(Position position){
        for(Wall wall : walls){
            if(wall.getPosition().equals(position)) {
                System.out.println("Game Over!");
                positionHero();
                return false;
            }
        }
        return true;
    }

    public boolean canMonsterMove(Position position){
        for(Wall wall : walls){
            if(wall.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

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


    private List<Wall> carregarNivel(int nivel) {
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

    public void setView(View<GameView> view) {
        this.view = view;
    }
}
