package model;

public class Soldado {


    public enum Heroi{RECRUIT, TANKY, EXPERT}

    private Heroi selected;
    private final Heroi[] herois = Heroi.values();


    public Soldado(){
        selected = Heroi.RECRUIT;
    }

    public Heroi getSelected() {
        return selected;
    }

    public void setSelected(Heroi selected) {
        this.selected = selected;
    }


    public Heroi[] getHerois() {
        return herois;
    }


}
