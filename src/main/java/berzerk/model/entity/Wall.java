package berzerk.model.entity;

import berzerk.model.entity.properties.Position;

public class Wall extends Element {

    public Wall(int x, int y){
        super(new Position(x, y));
    }

    public Wall(Position position) {
        super(position);
    }

    public Position getPosition() {
        return super.getPosition();
    }
}