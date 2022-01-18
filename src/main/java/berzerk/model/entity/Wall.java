package berzerk.model.entity;

import berzerk.model.entity.properties.Position;

public class Wall extends Element {

    public Wall(int x, int y){
        super(new Position(x, y));
    }

    public Wall(Position position, int orientation){
        super();
        int x = position.getX();
        int y = position.getY();

        if(orientation == 1) y -= 1;
        else if(orientation == 2) x += 1;
        else if(orientation == 3) y += 1;
        else x -= 1;

        this.setPosition(new Position(x, y));
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }
}