package berzerk.view.menu;

import berzerk.model.Ecra;
import berzerk.model.menu.MenuModel;
import berzerk.view.IndicadorView;
import berzerk.view.View;

import java.io.IOException;

public class MenuView extends View<MenuModel> {

    private final IndicadorView indicador;

    public MenuView(MenuModel model, Ecra ecra){
        super(model, ecra);
        indicador = new IndicadorView(13, 3, getEcra().getGraphics());
    }

    public void draw(int position) throws IOException {

    }

    private void drawOptions(){
    }

    private void drawGameName(){

    }



}
