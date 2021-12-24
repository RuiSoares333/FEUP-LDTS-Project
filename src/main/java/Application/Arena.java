package Application;

import Entity.*;
import Entity.Hero.Hero;
import Properties.Position;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Arena {

    private final int width;
    private final int height;

    Hero hero = new Hero(10, 10);

    private final List<Wall> walls;
    private final List<Coin> coins;
    private final List<Monster> monsters;

    public Arena(int width, int height){
        //creating the deadly arena
        this.width = width;
        this.height = height;

        walls = createWalls();          // creating the imposing walls
        coins = createCoins();          // creating the shiny coins
        monsters = createMonsters();    // creating the fierce monsters
    }

    public void draw(TextGraphics graphics){
        // paint the arena floor with CIN paint, not sponsored
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        hero.draw(graphics);            // draw the mighty hero

        for (Wall wall : walls)         // draw the imposing walls
            wall.draw(graphics);
        for(Coin coin : coins)          // draw the shiny coins
            coin.draw(graphics);
        for(Monster monster: monsters)  // draw the fierce monsters
            monster.draw(graphics);
    }

    public boolean processKey(KeyStroke key) {
        switch (key.getKeyType()){
            case Character:
                if (key.getCharacter() == 'q'){
                    return false;
                }
                break;
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
            retrieveCoins(position);
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

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        boolean flag;
        for (int i = 0; i < 5; i++) {
            flag = true;
            Position novaPosicao = new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            for (Coin coin : coins) {
                if (novaPosicao.equals(hero.getPosition()) || novaPosicao.equals(coin.getPosition())) {
                    i--;
                    flag = false;
                    break;
                }
            }
            if(flag)
                coins.add(new Coin(novaPosicao));
        }
        return coins;
    }

    public void retrieveCoins(Position position){
        List<Coin> remover = new ArrayList<>();

        for(Coin coin: coins)
            if(position.equals(coin.getPosition()))
                remover.add(coin);

        coins.removeAll(remover);
    }

    public List<Monster> createMonsters(){
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        boolean flag;
        for (int i = 0; i < 3; i++) {
            flag = true;
            Position novaPosicao = new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            for (Monster monster : monsters) {
                if (novaPosicao.equals(hero.getPosition()) || novaPosicao.equals(monster.getPosition())) {
                    i--;
                    flag = false;
                    break;
                }
            }
            if(flag)
                monsters.add(new Monster(novaPosicao));
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

}