package berzerk.model.entity.enemy;

import berzerk.model.entity.Element;
import berzerk.model.entity.properties.Position;

import java.util.Random;

public abstract class Enemy extends Element {

    public Enemy(Position position) {
        super(position);
    }

    public abstract Position move(Position heroPosition);

    public abstract int kill();


    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
    }
}
