package berzerk.model.game;

import berzerk.model.Constants;
import berzerk.model.Model;
import berzerk.model.Soldado;
import berzerk.model.entity.*;
import berzerk.model.entity.enemy.Dementor;
import berzerk.model.entity.enemy.Dementors;
import berzerk.model.entity.enemy.Dragon;
import berzerk.model.entity.enemy.Dragons;
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

    //Número de monstros a criar
    private final int numMonstros = 15;
    private final int numDementors = 5;
    private final int numVoldemorts = 2;

    private final Position initialPosition;
    private final int nivel;

    private final Hero hero;
    private final List<Wall> walls;
    private final List<Wall> exit;

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
        monsters = createMonsters();
        dementors = createDementors();

        bullets = new ArrayList<>();
        stones = new ArrayList<>();

        this.score = score;
        totalMonstrosMortos = 0;
        totalDementorsMortos = 0;

        System.out.println("Monsters: " + monsters.size());
    }


    private void positionHero(){
        hero.setPosition(initialPosition);
    }

    public Hero getHero(){
        return hero;
    }

    public List<Monster> getMonsters(){
        return monsters;
    }

    public List<Monster> getDementors(){
        return dementors;
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

    //------------------------------------- MONSTROS -----------------------------------------------------

    private List<Monster> createMonsters(){
        Random random = new Random();
        int width = Constants.WIDTH, height = Constants.HEIGHT;

        ArrayList<Monster> monsters = new ArrayList<>();

        while(monsters.size() < numMonstros){
            Position novaPosicao = new Position(random.nextInt(width-2) + 2, random.nextInt(height-4) + 4);

            if(verifyCollision(novaPosicao, walls) && verifyCollision(novaPosicao, exit) && verifyCollision(novaPosicao, monsters))
                monsters.add(new Monster(novaPosicao));
        }

        return monsters;
    }

    public void scheduleMonsterMovement(View<GameModel> view){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                monsters = moveMonsters(monsters);
                System.out.println(hero.getPosition());
                try {
                    view.draw(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000);
    }

    public List<Monster> moveMonsters(List<Monster> monsters){
        if(hero!=null) {
            for (Monster monster : monsters) {
                Position novaPosicao = monster.move();
                if (monster.getPosition().getX() == hero.getPosition().getX()) {
                    Bullet bullet;
                    if (monster.getPosition().getY() > hero.getPosition().getY()) {
                        bullet = new Bullet(monster.getPosition().getX(), (monster.getPosition().getY() - 1), 1);
                    } else {
                        bullet = new Bullet(monster.getPosition().getX(), (monster.getPosition().getY() + 1), 3);
                    }
                    addBullet(bullet);
                } else if (monster.getPosition().getY() == hero.getPosition().getY()) {
                    Bullet bullet;
                    if (monster.getPosition().getX() > hero.getPosition().getX()) {
                        bullet = new Bullet(monster.getPosition().getX() - 1, (monster.getPosition().getY()), 4);
                    } else {
                        bullet = new Bullet(monster.getPosition().getX() + 1, (monster.getPosition().getY()), 2);
                    }
                    addBullet(bullet);
                }

                if (verifyCollision(novaPosicao, walls) && verifyCollision(novaPosicao, exit) && verifyCollision(novaPosicao, bullets)) {
                    monster.setPosition(novaPosicao);
                }
            }
        }

        return monsters;
    }

    //------------------------------------- DEMENTORS -----------------------------------------------------

    private List<Monster> createDementors(){
        Random random = new Random();
        int width = Constants.WIDTH, height = Constants.HEIGHT;

        ArrayList<Monster> dementors = new ArrayList<>();

        while(dementors.size() < numDementors){
            Position novaPosicao = new Position(random.nextInt(width-2) + 2, random.nextInt(height-4) + 4);

            if(verifyCollision(novaPosicao, walls) && verifyCollision(novaPosicao, exit) && verifyCollision(novaPosicao, monsters) )
                dementors.add(new Monster(novaPosicao));
        }

        return dementors;
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

    public List<Monster> moveDementors(List<Monster> monsters){
        if(hero!=null) {
            for (Monster monster : dementors) {

                Position novaPosicao = monster.move();

                if (monster.getPosition().getX() == hero.getPosition().getX()) {
                    Bullet bullet;
                    if (monster.getPosition().getY() > hero.getPosition().getY()) {
                        bullet = new Bullet(monster.getPosition().getX(), (monster.getPosition().getY() - 1), 1);
                    } else {
                        bullet = new Bullet(monster.getPosition().getX(), (monster.getPosition().getY() + 1), 3);
                    }
                    addBullet(bullet);
                } else if (monster.getPosition().getY() == hero.getPosition().getY()) {
                    Bullet bullet;
                    if (monster.getPosition().getX() > hero.getPosition().getX()) {
                        bullet = new Bullet(monster.getPosition().getX() - 1, (monster.getPosition().getY()), 4);
                    } else {
                        bullet = new Bullet(monster.getPosition().getX() + 1, (monster.getPosition().getY()), 2);
                    }
                    addBullet(bullet);
                }

                if (verifyCollision(novaPosicao, walls) && verifyCollision(novaPosicao, exit) && verifyCollision(novaPosicao, bullets)) {
                    monster.setPosition(novaPosicao);
                }
            }
        }

        return dementors;
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

            if(verifyCollision(novaPosicao, walls) && verifyCollision(novaPosicao, exit) && verifyCollision(novaPosicao, monsters) && verifyCollision(novaPosicao, stones) && verifyCollision(novaPosicao, dementors)){
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

            eliminateMonster(novaPosicao, monsters);

            eliminateDementor(novaPosicao, dementors);
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
        if(!verifyCollision(position, monsters) || !verifyCollision(position, dementors) || !verifyCollision(position, walls) || !verifyCollision(position, bullets)){
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

    //---------------------------------------- Eliminar monstros quando sao atingidos -------------------------------------

    public void eliminateMonster(Position position, List<? extends Element> elements){
        List<Monster> newMonsters = new ArrayList<>();
        if(position!=null && !elements.isEmpty())
            for(Element e: elements)
                if (!position.equals(e.getPosition())){
                    newMonsters.add((Monster) e);
                } else {
                    totalMonstrosMortos++;
                    score += 50;
                }
        monsters = newMonsters;
    }

    public void eliminateDementor(Position position, List<? extends Element> elements){
        List<Monster> newDementors = new ArrayList<>();
        if(position!=null && !elements.isEmpty())
            for(Element e: elements)
                if (!position.equals(e.getPosition())){
                    newDementors.add((Monster) e);
                } else {
                    totalMonstrosMortos++;
                    score += 100;
                }
        dementors = newDementors;
    }

    //calcular o total do score do jogador
    public int calculateTotalScore(){
        return totalMonstrosMortos * Constants.VALOR_CADA_MONSTRO;
    }


}