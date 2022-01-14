package berzerk.model.entity;

import berzerk.model.entity.properties.Position;

import java.util.Random;

public class Monster extends Element{

    public Monster(Position position) {
        super(position);
    }

    //Random move
    public Position move(){
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

    public Position smartMove(Position heroPosition){
        Position novaPosicao;

        if (getPosition().getX() == heroPosition.getX()) {
            if (getPosition().getY() > heroPosition.getY()) {
                novaPosicao = new Position(getPosition().getX(),  getPosition().getY() - 1);
            } else {
                novaPosicao = new Position(getPosition().getX(),  getPosition().getY() + 1);
            }
        } else if (getPosition().getY() == heroPosition.getY()) {
            Bullet bullet;
            if (getPosition().getX() > getPosition().getX()) {
                novaPosicao = new Position(getPosition().getX() - 1, getPosition().getY());
            } else {
                novaPosicao = new Position(getPosition().getX() + 1, getPosition().getY());
            }
        }

        novaPosicao = new Position(5,5);
        return novaPosicao;
    }


    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
    }
}
