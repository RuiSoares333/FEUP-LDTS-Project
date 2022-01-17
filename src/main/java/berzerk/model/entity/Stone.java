package berzerk.model.entity;

import berzerk.model.entity.properties.Position;

public class Stone extends Element{

    public Stone(Position position, int orientation){
        int x = position.getX();
        int y = position.getY();

        if(orientation == 1) y -= 1;
        else if(orientation == 2) x += 1;
        else if(orientation == 3) y += 1;
        else x -= 1;

        this.setPosition(new Position(x, y));
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
    }
}