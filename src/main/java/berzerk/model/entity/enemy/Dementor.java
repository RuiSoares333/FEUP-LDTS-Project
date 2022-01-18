package berzerk.model.entity.enemy;

import berzerk.model.entity.properties.Position;

public class Dementor extends Enemy {

    public Dementor(Position position) {
        super(position);
    }

    @Override
    public Position move(Position heroPosition){
        if(getPosition().getX() > heroPosition.getX()){
            return new Position(getPosition().getX()-1,  getPosition().getY());
        }
        else if(getPosition().getX() < heroPosition.getX()){
            return new Position(getPosition().getX()+1,  getPosition().getY());
        }
        else if(getPosition().getY() > heroPosition.getY()){
            return new Position(getPosition().getX(),  getPosition().getY() - 1);
        }
        else if(getPosition().getY() < heroPosition.getY()){
            return new Position(getPosition().getX(),  getPosition().getY() + 1);
        }
        else{
            return new Position(getPosition().getX(), getPosition().getY());
        }
    }

    @Override
    public int kill() {
        return 100;
    }

}
