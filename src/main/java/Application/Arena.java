package Application;

import Entity.*;
import Entity.Hero.Expert;
import Entity.Hero.Hero;
import Entity.Hero.Speedy;
import Properties.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;


public class Arena {

    private final int width;
    private final int height;

    Hero hero = new Expert(10, 10);

    private final List<Wall> walls = new ArrayList<>();
    private final List<Monster> monsters;

    public Arena(int width, int height){
        //creating the deadly arena
        this.width = width;
        this.height = height;

        carregarFich();
        monsters = createMonsters();    // creating the fierce monsters
    }

    public void draw(TextGraphics graphics){
        // paint the arena floor with CIN paint, not sponsored
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        hero.draw(graphics);            // draw the mighty hero

        for (Wall wall : walls)         // draw the imposing walls
            wall.draw(graphics);
        for(Monster monster: monsters)  // draw the fierce monsters
            monster.draw(graphics);
    }

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
                moveMonsters();
                return moveHero(hero.moveUp());
            case ArrowDown:
                moveMonsters();
                return moveHero(hero.moveDown());
            case ArrowRight:
                moveMonsters();
                return moveHero(hero.moveRight());
            case ArrowLeft:
                moveMonsters();
                return moveHero(hero.moveLeft());
        }
        return true;
    }

    private boolean moveHero(Position position) {
        if(canEntityMove(position)) {
            hero.setPosition(position);
            return verifyMonsterCollision(position);
        }
        return true;
    }

    private void moveMonsters(){
        for (Monster monster: monsters) {

            Position novaPosicao = monster.move();

            if(canEntityMove(novaPosicao))
                monster.setPosition(novaPosicao);

        }
    }

    private boolean canEntityMove(Position position){
        for(Wall wall : walls){
            if(wall.getPosition().equals(position))
                return false;
        }
        return true;
    }

    public List<Monster> createMonsters(){
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        boolean flag;

        while(monsters.size() < 4){
            flag = true;
            Position novaPosicao = new Position(random.nextInt(width-1), random.nextInt(height-1));
            for (Wall w: walls) {
                if(w.getPosition() == novaPosicao) flag = false;
            }

            if(flag) monsters.add(new Monster(novaPosicao));
        }

        return monsters;
    }

    public boolean verifyMonsterCollision(Position position){
        for(Monster monster: monsters)
            if(position.equals(monster.getPosition())) {
                System.out.println("Application.Game Over!");
                return false;
            }
            return true;
    }

    public void carregarFich() {
        // java.io.InputStream
        InputStream is = ClassLoader.getSystemResourceAsStream("nivel1.txt");
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