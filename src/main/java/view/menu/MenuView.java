package view.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.Constants;
import view.Indicador;
import view.IndicadorView;

import java.io.IOException;

public class MenuView extends Menu{

    Indicador indicador;
    public MenuView(TextGraphics graphics){
        indicador = new IndicadorView(13, 3, graphics);
    }

    @Override
    public void draw(Screen screen, int position) throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphicSettings(graphics);


        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
        drawGameName(graphics);
        drawOptions(graphics);

        indicador.draw(44, position);
        screen.refresh();
    }

    public void graphicSettings(TextGraphics graphics){
        graphics.enableModifiers(SGR.BOLD);

        graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
        graphics.setForegroundColor(TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR));
    }

    public void drawOptions(TextGraphics graphics){
        graphics.putString(new TerminalPosition(48, 11), "PLAY");
        graphics.putString(new TerminalPosition(46, 13), "SETTINGS");
        graphics.putString(new TerminalPosition(45, 15), "LEADERBOARD");
        graphics.putString(new TerminalPosition(48, 19), "EXIT");
    }

    public void drawGameName(TextGraphics graphics){
        for(int y=0; y<Constants.GAME_NAME.length; y++){
            graphics.putString(new TerminalPosition(27, 1+y), Constants.GAME_NAME[y]);
        }
    }


}
