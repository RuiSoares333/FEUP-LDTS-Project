package berzerk.view.menu;

import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.ranking.GameOverModel;
import berzerk.view.View;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class GameOverView extends View<GameOverModel> {

    public GameOverView(GameOverModel model, Ecra ecra) {
        super(model, ecra);
    }

    public void drawHUD(TextGraphics graphics, GameOverModel model){
        graphics.putString(new TerminalPosition(46, 10), "GAME OVER!");
        graphics.putString(new TerminalPosition(45, 15), "YOUR SCORE: " + model.getScore());
        graphics.putString(new TerminalPosition(40, 25), "ENTER YOUR NAME:");
        graphics.putString(new TerminalPosition(40, 30), "PRESS ENTER TO CONTINUE");
    }

    public void drawName(TextGraphics graphics, GameOverModel model){
        String name = model.getName();
        StringBuilder toPrint = new StringBuilder(name);
        for (int i = name.length(); i < Constants.MAX_NOME_JOGADOR; i++)
            toPrint.append("_");
        graphics.putString(new TerminalPosition(58, 25), toPrint.toString());
    }

    @Override
    public void draw(int position) throws IOException {
        if(getScreen()!=null && getGraphics()!=null) {
            getScreen().clear();

            TextGraphics graphics = getGraphics();
            getGraphics().fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
            drawHUD(graphics, model);
            drawName(graphics, model);

            getScreen().refresh();
        }
    }
}
