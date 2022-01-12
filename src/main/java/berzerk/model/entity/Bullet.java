package berzerk.model.entity;

import berzerk.model.entity.properties.Position;

import java.util.Random;

public class Bullet extends Element{

    private int orientation;

    //Inicializar orientação para RIGHT
    public Bullet(int x, int y, int orientation){
        super(new Position(x, y));
        this.orientation = orientation;
    }

    //Getters e Setter orientação
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getOrientation(){
        return orientation;
    }

    public Position move(){

        int orientation = getOrientation();
        Position position = getPosition();
        Position newPosition;

        //UP
        if(orientation == 1) newPosition = new Position(position.getX(),position.getY() - 1);

        //RIGHT
        else if(orientation == 2) newPosition = new Position(position.getX() + 1,position.getY());

        //DOWN
        else if(orientation == 3) newPosition = new Position(position.getX(),position.getY() + 1);

        //LEFT
        else newPosition = new Position(position.getX() - 1,position.getY());

        return newPosition;
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
    }

}
