package berzerk.model.entity;

import berzerk.model.entity.properties.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{

    public Monster(Position position) {
        super(position);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#24A120"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(super.getPosition().getX(), super.getPosition().getY()), "@");
    }

    public Position move(){
        Random random = new Random();
        int movement = random.nextInt(4);
        switch (movement){
            case 0:
                return new Position(getPosition().getX(), getPosition().getY()-1);
            case 1:
                return new Position(getPosition().getX()+1, getPosition().getY());
            case 2:
                return new Position(getPosition().getX(), getPosition().getY()+1);
            case 3:
                return new Position(getPosition().getX()-1, getPosition().getY());
        }
        return getPosition();
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
    }
}
