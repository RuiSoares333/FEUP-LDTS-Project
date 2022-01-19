package berzerk.model.entity.hero;

import berzerk.model.entity.Element;
import berzerk.model.entity.properties.Position;
import berzerk.model.game.Attributes;
import berzerk.model.game.Enemies;
import berzerk.model.game.Shooter;
import berzerk.model.game.Terrain;

public class Hero extends Element implements Attributes {

    private final Position initialPosition; // Initial Position

    private int hp; // Health Points
    private int orientation; // 1-UP 2-RIGHT 3-DOWN 4-LEFT

    //Inicializar orientação para RIGHT
    public Hero(Position initialPosition, int hp){
        super(new Position(initialPosition.getX(), initialPosition.getY()));
        this.hp = hp;
        orientation = 2;
        this.initialPosition = initialPosition;
    }

    //Getters e Setter orientação
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getOrientation(){
        return orientation;
    }

    public void setPosition(Position position) {
        super.setPosition(position);
    }

    //Mudança para fazer update da orientação
    public Position moveRight(){
        setOrientation(2);
        return new Position(super.getPosition().getX()+1, super.getPosition().getY());
    }

    public Position moveLeft(){
        setOrientation(4);
        return new Position(super.getPosition().getX()-1, super.getPosition().getY());
    }

    public Position moveUp(){
        setOrientation(1);
        return new Position(super.getPosition().getX(), super.getPosition().getY()-1);
    }

    public Position moveDown(){
        setOrientation(3);
        return new Position(super.getPosition().getX(), super.getPosition().getY()+1);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void move(Shooter shooter, Terrain terrain, Enemies enemies, Position position){
        if(!condition(terrain, enemies, shooter, position))
            killHero();
        else{
            if(verifyCollision(position, terrain.getStones()))
                setPosition(position);
        }
    }

    public boolean condition(Terrain terrain, Enemies enemies, Shooter shooter, Position pos){
        boolean condition1 = verifyCollision(pos, terrain.getWalls());
        boolean condition3 = verifyCollision(pos, enemies.getDragons());
        boolean condition4 = verifyCollision(pos, enemies.getDementors());
        boolean condition5 = verifyCollision(pos, shooter.getBullets());
        return condition1 && condition3 && condition4 && condition5;
    }

    public void killHero(){
        setHp(getHp()-1);
        setPosition(initialPosition);
    }


}