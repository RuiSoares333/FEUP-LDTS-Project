package berzerk.model.entity;

import berzerk.model.entity.properties.Position;

public abstract class Element {
    private Position position;

    public Element(Position position){
        this.position = position;
    }

    protected Element() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


}
