package berzerk.model.game;

import berzerk.model.entity.Wall;
import berzerk.model.entity.hero.Hero;
import berzerk.model.entity.properties.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Terrain implements Attributes{

    private final int LEVEL;

    private List<Wall> walls;
    private List<Wall> exit;
    private List<Wall> stones;

    public Terrain(int nivel) throws IOException {
        this.LEVEL = nivel;
        populateWalls();
        stones = new ArrayList<>();
    }

    public List<Wall> getStones(){
        return stones;
    }

    public List<Wall> getWalls(){
        return walls;
    }

    public List<Wall> getExit() {
        return exit;
    }

    public int getLevel(){
        return LEVEL;
    }

    public void setStones(List<Wall> stones) {
        this.stones = stones;
    }

    public Position getInitialPosition(){
        if(LEVEL<=2) return new Position(50, 35);
        else return new Position(5, 8);
    }


    private void populateWalls() throws IOException {
        List<List<Wall>> wallList = createWalls();
        walls = wallList.get(0);
        exit = wallList.get(1);
    }

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

    public BufferedReader getReader(){
        String mapa = "nivel" + LEVEL + ".txt";
        InputStream is = ClassLoader.getSystemResourceAsStream(mapa);
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(streamReader);
    }

    public List<Wall> removeStone(Position position, List<Wall> stones){
        List<Wall> newStones = new ArrayList<>();
        for(Wall w : stones){
            if(!w.getPosition().equals(position)) newStones.add(w);
        }
        return newStones;
    }

    public void placeStone(Hero hero){
        stones.add(new Wall(hero.getPosition(), hero.getOrientation()));
    }

    public boolean isLeaving(Hero hero) {
        return verifyCollision(hero.getPosition(), exit);
    }
}
