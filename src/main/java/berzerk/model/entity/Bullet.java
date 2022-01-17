package berzerk.model.entity;

import berzerk.model.entity.properties.Position;

public class Bullet extends Element{

    private int orientation;

    public Bullet(int x, int y, int orientation){
        super(new Position(x, y));
        this.orientation = orientation;
    }

    public int getOrientation(){
        return orientation;
    }

    public Position move(){

        int orientation = getOrientation();
        Position position = getPosition();
        Position newPosition;

        if(orientation == 1) newPosition = new Position(position.getX(),position.getY() - 1);
        else if(orientation == 2) newPosition = new Position(position.getX() + 1,position.getY());
        else if(orientation == 3) newPosition = new Position(position.getX(),position.getY() + 1);
        else newPosition = new Position(position.getX() - 1,position.getY());

        return newPosition;
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
    }

}
