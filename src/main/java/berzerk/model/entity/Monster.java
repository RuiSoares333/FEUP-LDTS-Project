package berzerk.model.entity;

import berzerk.model.entity.properties.Position;

import java.util.Random;

public class Monster extends Element{

    public Monster(Position position) {
        super(position);
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
