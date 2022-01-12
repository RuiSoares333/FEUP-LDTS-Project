package berzerk.model.entity;

import berzerk.model.entity.properties.Position;

public class Stone extends Element{

    public Stone(Position position, int orientation){
        int x = position.getX();
        int y = position.getY();

        //UP
        if(orientation == 1) y -= 1;

            //RIGHT
        else if(orientation == 2) x += 1;

            //DOWN
        else if(orientation == 3) y += 1;

            //LEFT
        else x -= 1;

        this.setPosition(new Position(x, y));
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
    }


}
