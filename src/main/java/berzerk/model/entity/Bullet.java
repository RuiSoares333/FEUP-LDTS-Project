package berzerk.model.entity;

import berzerk.model.entity.properties.Position;

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

    public void setPosition(Position position) {
        super.setPosition(position);
    }

}
