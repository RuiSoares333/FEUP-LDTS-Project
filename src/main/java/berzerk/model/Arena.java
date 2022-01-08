package berzerk.model;

import berzerk.model.entity.Monster;
import berzerk.model.entity.Wall;
import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.properties.Position;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Arena implements Model {



    Position initialPosition;

    Hero hero;

    private final List<Wall> walls = new ArrayList<>();
    private final List<Monster> monsters;

    public Arena(Hero hero){
        //creating the deadly arena


        this.hero = hero;
        initialPosition = hero.getPosition();

        carregarFich();
        monsters = createMonsters();    // creating the fierce monsters
    }

//    public void draw(TextGraphics graphics){
//        // paint the arena floor with CIN paint, not sponsored
//        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
//        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
//
//        hero.draw(graphics);            // draw the mighty hero
//
//
//        for (Wall wall : walls)         // draw the imposing walls
//            wall.draw(graphics);
//        for(Monster monster: monsters)  // draw the fierce monsters
//            monster.draw(graphics);
////        moveMonsters();
//
//    }


    public boolean processKey(KeyStroke key) {
        switch (key.getKeyType()){
            case Character:
                char a = key.getCharacter();
                Character.toLowerCase(a);
                switch (a){
                    case 'q':
                        return false;
                    case ' ':
                        return moveHero(hero.moveUp());
                    default:
                        break;
                }
            case ArrowUp:
                return moveHero(hero.moveUp());
            case ArrowDown:
                return moveHero(hero.moveDown());
            case ArrowRight:
                return moveHero(hero.moveRight());
            case ArrowLeft:
                return moveHero(hero.moveLeft());
        }
        return true;
    }

    public boolean moveHero(Position position) {
        if(canEntityMove(position)) {
            hero.setPosition(position);
            if(!verifyMonsterCollision(position) || !verifyHeroWallCollision(position)){
                System.out.println("Game Over!");
                return true;
            }
        }
        return true;
    }

    /*public void moveMonsters(){
        for (Monster monster: monsters) {

            Position novaPosicao = monster.move();

            if(canEntityMove(novaPosicao))
                monster.setPosition(novaPosicao);

        }
    }*/
    
    //Move monster in random directions
//    public void moveMonstersRandom(Screen screen, Arena arena){
//        // And From your main() method or any other method
//        Timer timer = new Timer();
//
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//
//                        for (Monster monster: monsters) {
//
//                            Position novaPosicao = monster.move();
//
//                            if(canMonsterMove(novaPosicao))
//                                monster.setPosition(novaPosicao);
//
//                            screen.clear();
//                            arena.draw(screen.newTextGraphics());
//                            try {
//                                screen.refresh();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        draw(screen.newTextGraphics());
//
//                    }
//                }, 0, 200);
//    }

    public boolean canEntityMove(Position position){
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

        while(monsters.size() < 4){
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


    public void carregarFich() {
        // java.io.InputStream
        InputStream is = ClassLoader.getSystemResourceAsStream("nivel2.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        try {
            int i = 0;
            for (String line; (line = reader.readLine()) != null; i++) {
                int j = 0;
                for (char c: line.toCharArray()) {
                    if(c == 'p'){
                        walls.add(new Wall(j, i));
                    }
                    j++;
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
