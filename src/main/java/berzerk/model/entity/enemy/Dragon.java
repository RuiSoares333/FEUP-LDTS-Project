package berzerk.model.entity.enemy;

import berzerk.model.entity.properties.Position;

import java.util.Random;

public class Dragon extends Enemy {

    public Dragon(Position position) {
        super(position);
    }

    @Override
    public Position move(Position heroPosition) {
        Random random = new Random();
        int movement = random.nextInt(4);
        return switch (movement) {
            case 0 -> new Position(getPosition().getX(), getPosition().getY() - 1);
            case 1 -> new Position(getPosition().getX() + 1, getPosition().getY());
            case 2 -> new Position(getPosition().getX(), getPosition().getY() + 1);
            case 3 -> new Position(getPosition().getX() - 1, getPosition().getY());
            default -> getPosition();
        };
    }

    @Override
    public int kill() {
        return 50;
    }



}