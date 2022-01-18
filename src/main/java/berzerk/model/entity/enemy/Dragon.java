package berzerk.model.entity.enemy;

import berzerk.model.entity.properties.Position;

import java.util.Random;

public class Dragon extends Enemy {

    public Dragon(Position position) {
        super(position);
    }

    public Dragon(int x, int y){
        super(new Position(x, y));
    }

    @Override
    public Position move(Position heroPosition) {
        return switch (getRandInt()) {
            case 0 -> new Position(getPosition().getX(), getPosition().getY() - 1);
            case 1 -> new Position(getPosition().getX() + 1, getPosition().getY());
            case 2 -> new Position(getPosition().getX(), getPosition().getY() + 1);
            default -> new Position(getPosition().getX() - 1, getPosition().getY());
        };
    }

    public int getRandInt(){
        Random random = new Random();
        return random.nextInt(4);
    }

    @Override
    public int kill() {
        return 50;
    }



}