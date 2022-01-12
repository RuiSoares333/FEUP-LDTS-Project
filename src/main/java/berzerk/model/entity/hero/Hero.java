package berzerk.model.entity.hero;

import berzerk.model.entity.Element;
import berzerk.model.entity.properties.Position;

public class Hero extends Element {

    private int bulMult;
    private int hp;
    //Orientação no sentido do relogios 1-UP 2-RIGHT 3-DOWN 4-LEFT
    private int orientation;

    //Inicializar orientação para RIGHT
    public Hero(int x, int y, int bulMult, int hp){
        super(new Position(x, y));
        this.bulMult = bulMult;
        this.hp = hp;
        orientation = 2;
    }

    //Getters e Setter orientação
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getOrientation(){
        return orientation;
    }

    public void setPosition(Position position) {
        super.setPosition(position);
    }

    //Mudança para fazer update da orientação

    public Position moveRight(){
        setOrientation(2);
        System.out.println("Orientacao: " + getOrientation());
        return new Position(super.getPosition().getX()+1, super.getPosition().getY());
    }

    public Position moveLeft(){
        setOrientation(4);
        System.out.println("Orientacao: " + getOrientation());
        return new Position(super.getPosition().getX()-1, super.getPosition().getY());
    }

    public Position moveUp(){
        setOrientation(1);
        System.out.println("Orientacao: " + getOrientation());
        return new Position(super.getPosition().getX(), super.getPosition().getY()-1);
    }

    public Position moveDown(){
        setOrientation(3);
        System.out.println("Orientacao: " + getOrientation());
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