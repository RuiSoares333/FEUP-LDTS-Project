package berzerk.view.menu;

import berzerk.model.Ecra;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import berzerk.model.Constants;
import berzerk.model.menu.MenuModel;
import berzerk.view.IndicadorView;
import berzerk.view.View;

import java.io.IOException;

public class MenuView extends View<MenuModel> {

    private final IndicadorView indicador;

    public MenuView(MenuModel model, Ecra ecra){
        super(model, ecra);
        indicador = new IndicadorView(13, 3, getEcra().getScreen().newTextGraphics());
    }

    public void draw(int position) throws IOException {
        if(getEcra().getScreen()!= null && getEcra().getGraphics() != null) {
            getEcra().getScreen().clear();

            getEcra().getGraphics().fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');

            drawGameName();
            drawOptions();
            indicador.draw(44, position);

            getEcra().getScreen().refresh();
        }
    }

    private void drawOptions(){
        getEcra().getGraphics().putString(new TerminalPosition(48, 11), "PLAY");
        getEcra().getGraphics().putString(new TerminalPosition(46, 13), "SETTINGS");
        getEcra().getGraphics().putString(new TerminalPosition(45, 15), "LEADERBOARD");
        getEcra().getGraphics().putString(new TerminalPosition(48, 19), "EXIT");
    }

    private void drawGameName(){
        for(int y=0; y<Constants.GAME_NAME.length; y++){
            getEcra().getGraphics().putString(new TerminalPosition(27, 1+y), Constants.GAME_NAME[y]);
        }
    }



}
