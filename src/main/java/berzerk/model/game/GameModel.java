package berzerk.model.game;

import berzerk.model.Constants;
import berzerk.model.Model;
import berzerk.model.Soldado;
import berzerk.model.entity.*;
import berzerk.model.entity.enemy.Dementor;
import berzerk.model.entity.enemy.Dragon;
import berzerk.model.entity.enemy.Voldemort;
import berzerk.model.entity.hero.Expert;
import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.hero.Recruit;
import berzerk.model.entity.hero.Tanky;
import berzerk.model.entity.properties.Position;
import berzerk.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GameModel implements Model {

    private final Position initialPosition;
    private final int nivel;

    private final Hero hero;
    private final List<Wall> walls;
    private final List<Wall> exit;

    //Número de monstros a criar
    private final int numDragons = 15;
    private final int numDementors = 5;
    private final int numVoldemorts = 2;

    //Criação de monstros
    private List<Dragon> dragons;
    private List<Dementor> dementors;
    private List<Voldemort> voldemorts;

    private List<Bullet> bullets;
    private List<Stone> stones;

    //score do jogador
    private int score;
    private int totalMonstrosMortos;
    private int totalDementorsMortos;

    public GameModel(Soldado soldado, int nivel, int score, int hp) throws IOException {
        this.nivel = nivel;

        this.initialPosition = getInitialPosition();
        this.hero = createHero(soldado);
        hero.setHp(hp);

        List<List<Wall>> wallList = createWalls();
        walls = wallList.get(0);
        exit = wallList.get(1);

        dragons = createDragons();
        dementors = createDementors();
        voldemorts = createVoldemorts();

        bullets = new ArrayList<>();
        stones = new ArrayList<>();

        this.score = score;
        totalMonstrosMortos = 0;
        totalDementorsMortos = 0;
    }


    private void positionHero(){
        hero.setPosition(initialPosition);
    }

    public Hero getHero(){
        return hero;
    }

    public List<Dragon> getDragons(){
        return dragons;
    }

    public List<Dementor> getDementors(){
        return dementors;
    }

    public List<Voldemort> getVoldemorts(){
        return voldemorts;
    }

    public List<Bullet> getBullets(){
        return bullets;
    }

    public List<Stone> getStones(){
        return stones;
    }

    public List<Wall> getWalls(){
        return walls;
    }

    public List<Wall> getExit() {
        return exit;
    }

    public int getNivel(){
        return nivel;
    }

    private Position getInitialPosition(){
        if(nivel<=2) return new Position(50, 35);
        else return new Position(5, 8);
    }

    public int getTotalMonstrosMortos() {
        return totalMonstrosMortos;
    }

    public void setTotalMonstrosMortos(int totalMonstrosMortos) {
        this.totalMonstrosMortos = totalMonstrosMortos;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    //---------------------------------- PAREDES -----------------------------------------------------------

    private List<List<Wall>> createWalls() throws IOException {
        List<List<Wall>> paredes = new ArrayList<>();
        List<Wall> eletrificadas = new ArrayList<>();
        List<Wall> saida = new ArrayList<>();

        BufferedReader reader = getReader();
        int y = 0;
        for (String line; (line = reader.readLine()) != null; y++) {
            for(int x=0; x<line.length(); x++){
                addToList(line.charAt(x), eletrificadas, saida, x, y);
            }
        }
        paredes.add(eletrificadas);
        paredes.add(saida);
        return paredes;
    }

    private void addToList(char c, List<Wall> eletrificadas, List<Wall> saida, int x, int y){
        if(c == 'p') eletrificadas.add(new Wall(x, y));
        if(c == 'e') saida.add(new Wall(x, y));
    }

    private BufferedReader getReader(){
        String mapa = "nivel" + nivel + ".txt";
        InputStream is = ClassLoader.getSystemResourceAsStream(mapa);
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(streamReader);
    }

    // CRIAR INIMIGOS

    private List<Dragon> createDragons(){
        Random random = new Random();
        int width = Constants.WIDTH, height = Constants.HEIGHT;
        ArrayList<Dragon> dragons = new ArrayList<>();
        while(dragons.size() < numDragons){
            Position novaPosicao = new Position(random.nextInt(width-2) + 2, random.nextInt(height-4) + 4);
            if(verifyCollision(novaPosicao, walls) && verifyCollision(novaPosicao, exit) && verifyCollision(novaPosicao, dragons))
                dragons.add(new Dragon(novaPosicao));
        }
        return dragons;
    }

    private List<Dementor> createDementors(){
        Random random = new Random();
        int width = Constants.WIDTH, height = Constants.HEIGHT;
        ArrayList<Dementor> dementors = new ArrayList<>();
        while(dementors.size() < numDementors){
            Position novaPosicao = new Position(random.nextInt(width-2) + 2, random.nextInt(height-4) + 4);
            if(verifyCollision(novaPosicao, walls) && verifyCollision(novaPosicao, exit) && verifyCollision(novaPosicao, dementors) )
                dementors.add(new Dementor(novaPosicao));
        }
        return dementors;
    }

    private List<Voldemort> createVoldemorts(){
        Random random = new Random();
        int width = Constants.WIDTH, height = Constants.HEIGHT;
        ArrayList<Voldemort> voldemorts = new ArrayList<>();
        while(voldemorts.size() < numVoldemorts){
            Position novaPosicao = new Position(random.nextInt(width-2) + 2, random.nextInt(height-4) + 4);
            if(verifyCollision(novaPosicao, walls) && verifyCollision(novaPosicao, exit) && verifyCollision(novaPosicao, voldemorts) )
                voldemorts.add(new Voldemort(novaPosicao));
        }
        return voldemorts;
    }

    // TEMPORIZAR MOVIMENTO INIMIGOS

    public void scheduleDragonMovement(View<GameModel> view){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dragons = moveDragons(dragons);
                System.out.println(hero.getPosition());
                try {
                    view.draw(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000);
    }

    public List<Dragon> moveDragons(List<Dragon> dragons){
        if(hero!=null) {
            for (Dragon dragon : dragons) {
                Position novaPosicao = dragon.move();
                if (dragon.getPosition().getX() == hero.getPosition().getX()) {
                    Bullet bullet;
                    if (dragon.getPosition().getY() > hero.getPosition().getY()) {
                        bullet = new Bullet(dragon.getPosition().getX(), (dragon.getPosition().getY() - 1), 1);
                    } else {
                        bullet = new Bullet(dragon.getPosition().getX(), (dragon.getPosition().getY() + 1), 3);
                    }
                    addBullet(bullet);
                } else if (dragon.getPosition().getY() == hero.getPosition().getY()) {
                    Bullet bullet;
                    if (dragon.getPosition().getX() > hero.getPosition().getX()) {
                        bullet = new Bullet(dragon.getPosition().getX() - 1, (dragon.getPosition().getY()), 4);
                    } else {
                        bullet = new Bullet(dragon.getPosition().getX() + 1, (dragon.getPosition().getY()), 2);
                    }
                    addBullet(bullet);
                }
                if (verifyCollision(novaPosicao, walls) && verifyCollision(novaPosicao, exit) && verifyCollision(novaPosicao, bullets)) {
                    dragon.setPosition(novaPosicao);
                }
            }
        }
        return dragons;
    }

    public void scheduleDementorMovement(View<GameModel> view){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dementors = moveDementors(dementors);
                System.out.println(hero.getPosition());
                try {
                    view.draw(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 700);
    }

    public List<Dementor> moveDementors(List<Dementor> dementors){
        if(hero!=null) {
            for (Dementor dementor : dementors) {

                Position novaPosicao = dementor.move();

                if (dementor.getPosition().getX() == hero.getPosition().getX()) {
                    Bullet bullet;
                    if (dementor.getPosition().getY() > hero.getPosition().getY()) {
                        bullet = new Bullet(dementor.getPosition().getX(), (dementor.getPosition().getY() - 1), 1);
                    } else {
                        bullet = new Bullet(dementor.getPosition().getX(), (dementor.getPosition().getY() + 1), 3);
                    }
                    addBullet(bullet);
                } else if (dementor.getPosition().getY() == hero.getPosition().getY()) {
                    Bullet bullet;
                    if (dementor.getPosition().getX() > hero.getPosition().getX()) {
                        bullet = new Bullet(dementor.getPosition().getX() - 1, (dementor.getPosition().getY()), 4);
                    } else {
                        bullet = new Bullet(dementor.getPosition().getX() + 1, (dementor.getPosition().getY()), 2);
                    }
                    addBullet(bullet);
                }

                if (verifyCollision(novaPosicao, walls) && verifyCollision(novaPosicao, exit) && verifyCollision(novaPosicao, bullets) && verifyCollision(novaPosicao, voldemorts) && verifyCollision(novaPosicao, dragons)) {
                    dementor.setPosition(novaPosicao);
                }
            }
        }
        return dementors;
    }

    public void scheduleVoldemorteMovement(View<GameModel> view){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                voldemorts = moveVoldemorts(voldemorts);
                try {
                    view.draw(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 500);
    }

    public List<Voldemort> moveVoldemorts(List<Voldemort> voldemorts){
        if(hero!=null) {
            for (Voldemort voldemort : voldemorts) {

                Position novaPosicao = voldemort.move();

                if (voldemort.getPosition().getX() == hero.getPosition().getX()) {
                    Bullet bullet;
                    if (voldemort.getPosition().getY() > hero.getPosition().getY()) {
                        bullet = new Bullet(voldemort.getPosition().getX(), (voldemort.getPosition().getY() - 1), 1);
                    } else {
                        bullet = new Bullet(voldemort.getPosition().getX(), (voldemort.getPosition().getY() + 1), 3);
                    }
                    addBullet(bullet);
                } else if (voldemort.getPosition().getY() == hero.getPosition().getY()) {
                    Bullet bullet;
                    if (voldemort.getPosition().getX() > hero.getPosition().getX()) {
                        bullet = new Bullet(voldemort.getPosition().getX() - 1, (voldemort.getPosition().getY()), 4);
                    } else {
                        bullet = new Bullet(voldemort.getPosition().getX() + 1, (voldemort.getPosition().getY()), 2);
                    }
                    addBullet(bullet);
                }
                if (verifyCollision(novaPosicao, walls) && verifyCollision(novaPosicao, exit) && verifyCollision(novaPosicao, bullets)  && verifyCollision(novaPosicao, dementors)  && verifyCollision(novaPosicao, dragons)) {
                    voldemort.setPosition(novaPosicao);
                }
            }
        }
        return voldemorts;
    }

    //------------------------------------- BULLETS -----------------------------------------------------

    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

    public void scheduleBulletMovement(View<GameModel> view){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                bullets = moveBullet(bullets);
                try {
                    view.draw(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 50);
    }

    public List<Bullet> moveBullet(List<Bullet> bullets){
        List<Bullet> newBullets = new ArrayList<>();
        for (Bullet bullet: bullets) {

            Position novaPosicao = bullet.move();

            if(verifyCollision(novaPosicao, walls) && verifyCollision(novaPosicao, exit) && verifyCollision(novaPosicao, dragons) && verifyCollision(novaPosicao, stones) && verifyCollision(novaPosicao, dementors)  && verifyCollision(novaPosicao, voldemorts)){
                bullet.setPosition(novaPosicao);
                newBullets.add(bullet);
            }

            if(bullet.getPosition().equals(hero.getPosition())){
                System.out.println("Game Over!");
                hero.setHp(hero.getHp()-1);
                positionHero();
            }

            List<Stone> newStones = new ArrayList<>();
                for(Stone stone: stones)
                    if (!novaPosicao.equals(stone.getPosition())){
                        newStones.add(stone);
                    }
            stones = newStones;

            eliminateDragon(novaPosicao, dragons);
            eliminateDementor(novaPosicao, dementors);
            eliminateVoldemort(novaPosicao, voldemorts);
        }
        return newBullets;
    }

    //------------------------------------- STONES -----------------------------------------------------

    public void addStone(Stone stone){
        stones.add(stone);
    }

    //---------------------------------------- HEROI --------------------------------------------------------------

    private Hero createHero(Soldado heroi){
        int x = initialPosition.getX(), y = initialPosition.getY();
        Hero hero = new Recruit(x, y);
        if(heroi.getSelected() != null) {
            switch (heroi.getSelected()) {
                case TANKY -> hero = new Tanky(x, y);
                case EXPERT -> hero = new Expert(x, y);
                default -> hero = new Recruit(x, y);
            }
        }
        return hero;
    }

    public void moveHero(Position position) {
        if(!verifyCollision(position, dragons) || !verifyCollision(position, dementors) || !verifyCollision(position, voldemorts) || !verifyCollision(position, walls) || !verifyCollision(position, bullets)){
            System.out.println("Game Over!");
            hero.setHp(hero.getHp()-1);
            positionHero();
        }
        else hero.setPosition(position);
    }

    public boolean verifyCollision(Position position, List<? extends Element> elements){
        if(position!=null && !elements.isEmpty())
            for(Element e: elements)
                if (position.equals(e.getPosition())) return false;

        return true;
    }

    // ELIMINAR INIMIGOD

    public void eliminateDragon(Position position, List<? extends Element> elements){
        List<Dragon> newDragons = new ArrayList<>();
        if(position!=null && !elements.isEmpty())
            for(Element e: elements)
                if (!position.equals(e.getPosition())){
                    newDragons.add((Dragon) e);
                } else {
                    totalMonstrosMortos++;
                    score += 50;
                }
        dragons = newDragons;
    }

    public void eliminateDementor(Position position, List<? extends Element> elements){
        List<Dementor> newDementors = new ArrayList<>();
        if(position!=null && !elements.isEmpty())
            for(Element e: elements)
                if (!position.equals(e.getPosition())){
                    newDementors.add((Dementor) e);
                } else {
                    totalMonstrosMortos++;
                    score += 100;
                }
        dementors = newDementors;
    }

    public void eliminateVoldemort(Position position, List<? extends Element> elements){
        List<Voldemort> newVoldemorts = new ArrayList<>();
        if(position!=null && !elements.isEmpty())
            for(Element e: elements)
                if (!position.equals(e.getPosition())){
                    newVoldemorts.add((Voldemort) e);
                } else {
                    totalMonstrosMortos++;
                    score += 200;
                }
        voldemorts = newVoldemorts;
    }

    //calcular o total do score do jogador
    public int calculateTotalScore(){
        return totalMonstrosMortos * Constants.VALOR_CADA_MONSTRO;
    }


}