package model.settings;

import model.Model;

public class SettingsModel implements Model {
    public enum Heroi{RECRUIT, TANKY, EXPERT}

    public String[] nomesHerois = {"RECRUIT", "TANKY", "EXPERT"};

    private Heroi selected;
    private final Heroi[] opc = Heroi.values();


    public SettingsModel() {
        this.selected = Heroi.RECRUIT;
    }


    public String enumToString(Heroi availHero) {
        int position = getPosicaoOpcao(availHero);
        return nomesHerois[position];
    }

    public int getPosicaoOpcao(Heroi target) {
        for(int i=0; i<opc.length; i++){
            if(opc[i] == target){
                return i;
            }
        }
        return -1;
    }

    public Heroi getSelected() {
        return selected;
    }

    public void setSelected(Heroi selected) {
        this.selected = selected;
    }

    public void nextSelected() {
        if (selected == Heroi.EXPERT){
            selected = Heroi.RECRUIT;
        }
        else {
            int i = getPosicaoOpcao(selected);
            i++;
            setSelected(opc[i]);
        }
    }

    public void previousSelected() {
        if (selected == Heroi.RECRUIT){
            selected = Heroi.EXPERT;
        }
        else {
            int i = getPosicaoOpcao(selected);
            i--;
            setSelected(opc[i]);
        }
    }
}
