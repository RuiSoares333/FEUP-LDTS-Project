package berzerk.view.menu;

import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.Model;
import berzerk.model.menu.MenuModel;
import berzerk.view.IndicadorView;
import berzerk.view.View;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;

import java.io.IOException;

public class MenuView extends View<MenuModel> implements Model {

    private IndicadorView indicador;

    public MenuView(MenuModel model, Ecra ecra){
        super(model, ecra);
        if(getScreen()!=null) indicador = new IndicadorView(13, 3, getScreen().newTextGraphics());
    }

    public void draw(int position) throws IOException {
        if(getScreen()!=null && getGraphics()!=null) {
            getScreen().clear();

            getGraphics().fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');

            drawGameName();
            drawOptions();
            drawIndicador(indicador, position);

            getScreen().refresh();
        }
    }

    private void drawOptions(){
        getGraphics().putString(new TerminalPosition(48, 11), "PLAY");
        getGraphics().putString(new TerminalPosition(46, 13), "SETTINGS");
        getGraphics().putString(new TerminalPosition(45, 15), "LEADERBOARD");
        getGraphics().putString(new TerminalPosition(48, 19), "EXIT");
    }

    private void drawGameName(){
        for(int y=1; y<=Constants.GAME_NAME.length; y++){
            getGraphics().putString(new TerminalPosition(27, y), Constants.GAME_NAME[y-1]);
        }
    }

    public void drawIndicador(IndicadorView indicador, int position){
        if(indicador!=null)
            indicador.draw(44, position);
    }


}
