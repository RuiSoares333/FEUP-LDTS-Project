package berzerk.model.menu;

import berzerk.model.Model;

public class MenuModel implements Model {
    public enum Opcao {PLAY, SETT, RANKS, EXIT}

    private Opcao selected;
    private final Opcao[] opc = Opcao.values();

    public MenuModel() {
        this.selected = Opcao.PLAY;
    }

    public int getPosicaoOpcao(Opcao target) {
        for(int i=0; i<opc.length; i++){
            if(opc[i] == target){
                return i;
            }
        }
        return -1;
    }

    public Opcao getSelected() {
        return selected;
    }

    public void setSelected(Opcao selected) {
        this.selected = selected;
    }

    public void nextSelected() {
        if (selected == Opcao.EXIT) selected = Opcao.PLAY;
        else {
            int i = getPosicaoOpcao(selected);
            i++;
            setSelected(opc[i]);
        }
    }

    public void previousSelected() {
        if (selected == Opcao.PLAY){
            selected = Opcao.EXIT;
        }
        else {
            int i = getPosicaoOpcao(selected);
            i--;
            setSelected(opc[i]);
        }
    }
}
