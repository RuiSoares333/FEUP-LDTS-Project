package berzerk.model.entity;

import berzerk.model.entity.properties.Position;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bullet extends Element{
    public Bullet(Position position) {
        super(position);
    }

    @Override
    public void draw(TextGraphics graphics) {

    }
}
