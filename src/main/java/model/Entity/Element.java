package model.Entity;

import com.googlecode.lanterna.graphics.TextGraphics;
import model.Entity.Properties.Position;

public abstract class Element {
    private Position position;

    public Element(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    abstract public void draw(TextGraphics graphics);

}
