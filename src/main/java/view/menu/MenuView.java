package view.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import model.Constants;
import model.menu.MenuModel;
import view.IndicadorView;
import view.View;

import java.io.IOException;

public class MenuView extends View<MenuModel> {

    IndicadorView indicador;
    public MenuView(MenuModel model) throws IOException {
        super(model);
        initScreen();
        indicador = new IndicadorView(13, 3, screen.newTextGraphics());
    }

    public void draw(int position) throws IOException {
        screen.clear();

        graphicSettings();

        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');

        drawGameName();
        drawOptions();
        indicador.draw(44, position);

        screen.refresh();
    }

    public void graphicSettings(){
        graphics.enableModifiers(SGR.BOLD);

        graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
        graphics.setForegroundColor(TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR));
    }

    public void drawOptions(){
        graphics.putString(new TerminalPosition(48, 11), "PLAY");
        graphics.putString(new TerminalPosition(46, 13), "SETTINGS");
        graphics.putString(new TerminalPosition(45, 15), "LEADERBOARD");
        graphics.putString(new TerminalPosition(48, 19), "EXIT");
    }

    public void drawGameName(){
        for(int y=0; y<Constants.GAME_NAME.length; y++){
            graphics.putString(new TerminalPosition(27, 1+y), Constants.GAME_NAME[y]);
        }
    }



}
