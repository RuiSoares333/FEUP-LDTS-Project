package berzerk.model.game;

import berzerk.model.Soldado;
import berzerk.model.entity.Bullet;
import berzerk.model.entity.Monster;
import berzerk.model.entity.Wall;
import berzerk.model.entity.hero.Expert;
import berzerk.model.entity.hero.Recruit;
import berzerk.model.entity.hero.Tanky;
import berzerk.model.entity.properties.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class GameModelTest {

    GameModel model;
    Soldado soldado;

    @BeforeEach
    public void dados() throws IOException {
        soldado = mock(Soldado.class);
        model= spy(new GameModel(soldado, 1));
    }


    //------------------------------- NIVEL 1 ------------------------------------------------
    @Test
    public void getInitialPositionTestNivel1(){
        Position expected = new Position(50, 35);

        assertEquals(expected, model.getHero().getPosition());
    }

    @Test
    public void numAtributosNivel1(){
        int expected = 352;

        assertEquals(expected, model.getWalls().size());
    }

    @Test
    public void getNivel1(){
        int expected = 1;
        assertEquals(expected, model.getNivel());
    }

    @Test
    public void createWallsNivel1() throws IOException {
        List<Wall> paredes = model.getWalls();

        BufferedReader reader = getReader(1);
        List<Wall> expected = new ArrayList<>();
        int y = 0;
        for (String line; (line = reader.readLine()) != null; y++) {
            for(int x=0; x<line.length(); x++){
                if(line.charAt(x)=='p') expected.add(new Wall(x, y));
            }
        }

        assertEquals(expected.size(), paredes.size());

        for (int i=0; i<expected.size(); i++) {
            assertEquals(expected.get(i).getPosition(), paredes.get(i).getPosition());
        }
    }



    //------------------------------- NIVEL 2 ------------------------------------------------

    @Test
    public void getInitialPositionTestNivel2() throws IOException {
        model = new GameModel(soldado, 2);
        Position expected = new Position(50, 35);

        assertEquals(expected, model.getHero().getPosition());
    }

    @Test
    public void numAtributosNivel2() throws IOException {
        model = new GameModel(soldado, 2);
        int expected = 359;

        assertEquals(expected, model.getWalls().size());
    }

    @Test
    public void getNivel2() throws IOException {
        model = new GameModel(soldado, 2);
        int expected = 2;

        assertEquals(expected, model.getNivel());
    }

    @Test
    public void createWallsNivel2() throws IOException {
        model = new GameModel(soldado, 2);
        List<Wall> paredes = model.getWalls();

        BufferedReader reader = getReader(2);
        List<Wall> expected = new ArrayList<>();
        int y = 0;
        for (String line; (line = reader.readLine()) != null; y++) {
            for(int x=0; x<line.length(); x++){
                if(line.charAt(x)=='p') expected.add(new Wall(x, y));
            }
        }

        assertEquals(expected.size(), paredes.size());

        for (int i=0; i<expected.size(); i++) {
            assertEquals(expected.get(i).getPosition(), paredes.get(i).getPosition());
        }
    }

    //------------------------------- NIVEL 3 ------------------------------------------------

    @Test
    public void getInitialPositionTestNivel3() throws IOException {
        model = new GameModel(soldado, 3);
        Position expected = new Position(5, 8);

        assertEquals(expected, model.getHero().getPosition());
    }

    @Test
    public void numAtributosNivel3() throws IOException {
        model = new GameModel(soldado, 3);
        int expected = 313;

        assertEquals(expected, model.getWalls().size());
    }

    @Test
    public void getNivel3() throws IOException {
        model = new GameModel(soldado, 3);
        int expected = 3;

        assertEquals(expected, model.getNivel());
    }

    @Test
    public void createWallsNivel3() throws IOException {
        model = new GameModel(soldado, 3);
        List<Wall> paredes = model.getWalls();

        BufferedReader reader = getReader(3);
        List<Wall> expected = new ArrayList<>();
        int y = 0;
        for (String line; (line = reader.readLine()) != null; y++) {
            for(int x=0; x<line.length(); x++){
                if(line.charAt(x)=='p') expected.add(new Wall(x, y));
            }
        }

        assertEquals(expected.size(), paredes.size());

        for (int i=0; i<expected.size(); i++) {
            assertEquals(expected.get(i).getPosition(), paredes.get(i).getPosition());
        }
    }


    //-------------------------------- SOLDADOS -----------------------------------------------

    @Test
    public void createHeroRecruit() throws IOException {
        when(soldado.getSelected()).thenReturn(Soldado.Heroi.RECRUIT);

        model= new GameModel(soldado, 1);
        Object expected = Recruit.class;

        assertEquals(expected, model.getHero().getClass());
    }

    @Test
    public void createHeroTanky() throws IOException {
        when(soldado.getSelected()).thenReturn(Soldado.Heroi.TANKY);

        model= new GameModel(soldado, 1);
        Object expected = Tanky.class;

        assertEquals(expected, model.getHero().getClass());
    }

    @Test
    public void createHeroExpert() throws IOException {
        when(soldado.getSelected()).thenReturn(Soldado.Heroi.EXPERT);

        model= new GameModel(soldado, 1);
        Object expected = Expert.class;

        assertEquals(expected, model.getHero().getClass());
    }


    //----------------------------- MONSTERS ----------------------------------------------------

    @Test
    public void moveMonsters(){
//        List<Monster> monstros = new ArrayList<>();
//        Position pos2 = new Position(model.getWalls().get(1).getPosition());
//
//        Monster monster1 = mock(Monster.class);
//        when(monster1.move()).thenReturn(new Position(5,5));
//
//        Monster monster2 = mock(Monster.class);
//        when(monster2.move()).thenReturn(pos2);
//
//        Monster monster3 = mock(Monster.class);
//        when(monster3.move()).thenReturn(pos2);
//
//        monstros.add(monster1);
//        monstros.add(monster2);
//        monstros.add(monster3);
//
//        assertEquals(3, monstros.size());
//
//        List<Monster> novosMonstros = model.moveMonsters(monstros);
//
//        assertEquals(1, novosMonstros.size());
    }


    // ----------------------------------- HERO ------------------------------------------------

    @Test
    public void verifyCollision(){
        List<Monster> monstros = new ArrayList<>();
        List<Wall> paredes = new ArrayList<>();

        monstros.add(new Monster(new Position(5, 5)));
        monstros.add(new Monster(new Position(20, 20)));
        monstros.add(new Monster(new Position(45, 30)));
        monstros.add(new Monster(new Position(90, 25)));

        paredes.add(new Wall(new Position(5, 5)));
        paredes.add(new Wall(new Position(20, 20)));
        paredes.add(new Wall(new Position(45, 30)));
        paredes.add(new Wall(new Position(90, 25)));

        Position pos1 = new Position(5,5);
        Position pos2 = new Position(25, 25);
        Position pos3 = new Position(45, 30);
        Position pos4 = new Position(95, 30);

        assertFalse(model.verifyCollision(pos1, monstros));
        assertTrue(model.verifyCollision(pos2, monstros));
        assertFalse(model.verifyCollision(pos3, monstros));
        assertTrue(model.verifyCollision(pos4, monstros));

        assertFalse(model.verifyCollision(pos1, paredes));
        assertTrue(model.verifyCollision(pos2, paredes));
        assertFalse(model.verifyCollision(pos3, paredes));
        assertTrue(model.verifyCollision(pos4, paredes));
    }

    @Test
    public void moveHero(){
        when(model.verifyCollision(any(Position.class),anyList())).thenReturn(false);
        model.moveHero(new Position(0, 0));

        Position expected = new Position(50, 35);
        assertEquals(expected, model.getHero().getPosition());

        when(model.verifyCollision(any(Position.class),anyList())).thenReturn(true);
        model.moveHero(new Position(0, 0));

        expected = new Position(0, 0);
        assertEquals(expected, model.getHero().getPosition());
    }



    private BufferedReader getReader(int nivel){
        String mapa = "nivel" + nivel + ".txt";
        InputStream is = ClassLoader.getSystemResourceAsStream(mapa);
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(streamReader);
    }

    //--------------------------------------------- SCORE --------------------------------------------------------------

    @Test
    public void scoreTest(){
        List<Monster> monstros = new ArrayList<>();
        List<Bullet> balas = new ArrayList<>();
        int expected;

        monstros.add(new Monster(new Position(5, 5)));
        monstros.add(new Monster(new Position(20, 20)));
        monstros.add(new Monster(new Position(45, 30)));
        monstros.add(new Monster(new Position(90, 25)));
        monstros.add(new Monster(new Position(15, 55)));
        monstros.add(new Monster(new Position(10, 35)));

        balas.add(new Bullet(1,2,1));
        balas.add(new Bullet(5,5,1));
        balas.add(new Bullet(10,25,1));
        balas.add(new Bullet(90,25,1));
        balas.add(new Bullet(15,55,1));

        model.eliminateMonster(balas.get(0).getPosition(), monstros);
        model.eliminateMonster(balas.get(1).getPosition(), monstros);
        model.eliminateMonster(balas.get(2).getPosition(), monstros);
        model.eliminateMonster(balas.get(3).getPosition(), monstros);
        model.eliminateMonster(balas.get(4).getPosition(), monstros);

        expected = 150;

        assertEquals(expected, model.getScore());

        model.setScore(200);

        expected = 200;

        assertEquals(expected, model.getScore());

    }

    @Test
    public void totalScoreTest(){
        int numMonstrosMortos, expected;

        numMonstrosMortos = 3;
        model.setTotalMonstrosMortos(numMonstrosMortos);

        expected = numMonstrosMortos * 50;

        assertEquals(expected, model.calculateTotalScore());

        numMonstrosMortos = 0;
        model.setTotalMonstrosMortos(numMonstrosMortos);

        expected = numMonstrosMortos * 50;

        assertEquals(expected, model.calculateTotalScore());

        model.setTotalMonstrosMortos(10);
        numMonstrosMortos = model.getTotalMonstrosMortos();

        expected = numMonstrosMortos * 50;

        assertEquals(expected, model.calculateTotalScore());
    }
}
