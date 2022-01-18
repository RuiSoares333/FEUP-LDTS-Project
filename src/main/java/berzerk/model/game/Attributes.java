package berzerk.model.game;

import berzerk.model.entity.Element;
import berzerk.model.entity.properties.Position;

import java.util.List;

public interface Attributes {

    default boolean verifyCollision(Position newPosition, List<? extends Element> elements){
        if(elements!=null)
            for(Element e : elements){
                if(e.getPosition().equals(newPosition)) return false;
            }
        return true;
    }
}
