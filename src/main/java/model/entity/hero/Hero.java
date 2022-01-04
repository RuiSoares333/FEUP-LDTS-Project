package model.entity.hero;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.entity.Element;
import model.entity.properties.Position;

public class Hero extends Element {

    private int bulMult;
    private int hp;

    public Hero(int x, int y, int bulMult, int hp){
        super(new Position(x, y));
        this.bulMult = bulMult;
        this.hp = hp;
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(super.getPosition().getX(), super.getPosition().getY()), "}");
    }


    public void setPosition(Position position) {
        super.setPosition(position);
    }

    public Position moveRight(){
        return new Position(super.getPosition().getX()+1, super.getPosition().getY());
    }

    public Position moveLeft(){
        return new Position(super.getPosition().getX()-1, super.getPosition().getY());
    }

    public Position moveUp(){
        return new Position(super.getPosition().getX(), super.getPosition().getY()-1);
    }

    public Position moveDown(){
        return new Position(super.getPosition().getX(), super.getPosition().getY()+1);
    }


    public double getBulMult() {
        return bulMult;
    }

    public void setBulMult(int bulMult) {
        this.bulMult = bulMult;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}