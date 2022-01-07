package berzerk.model.settings;

import berzerk.model.Model;
import berzerk.model.Soldado;

public class SettingsModel implements Model {

    private final Soldado soldado;

    public SettingsModel(Soldado soldado) {
        this.soldado = soldado;
    }

    public int getPosicaoOpcao(Soldado.Heroi target) {
        Soldado.Heroi[] opcoes = soldado.getHerois();
        for(int i=0; i<opcoes.length; i++){
            if(opcoes[i] == target){
                return i;
            }
        }
        return -1;
    }

    public Soldado.Heroi getSelected() {
        return soldado.getSelected();
    }

    public void setSelected(Soldado.Heroi selected) {
        soldado.setSelected(selected);
    }

    public void nextSelected() {
        if (soldado.getSelected() == Soldado.Heroi.EXPERT){
            soldado.setSelected(Soldado.Heroi.RECRUIT);
        }
        else {
            int i = getPosicaoOpcao(soldado.getSelected());
            i++;
            setSelected(soldado.getHerois()[i]);
        }
    }

    public void previousSelected() {
        if (soldado.getSelected() == Soldado.Heroi.RECRUIT){
            soldado.setSelected( Soldado.Heroi.EXPERT);
        }
        else {
            int i = getPosicaoOpcao(soldado.getSelected());
            i--;
            setSelected(soldado.getHerois()[i]);
        }
    }
}
