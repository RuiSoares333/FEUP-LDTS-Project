package model.Entity;

import model.Entity.Properties.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{

    public Wall(int x, int y){
        super(new Position(x, y));
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#5C62F7"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#5C62F7"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(super.getPosition().getX(), super.getPosition().getY()), "H");
    }

    public Position getPosition() {
        return super.getPosition();
    }
}