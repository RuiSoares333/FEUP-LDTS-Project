package berzerk.view.menu;

import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.ranking.GameOverModel;
import berzerk.model.ranking.RankingModel;
import berzerk.view.View;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;

import java.io.IOException;

public class GameOverView extends View<GameOverModel> {
    public GameOverView(GameOverModel model, Ecra ecra) {
        super(model, ecra);
    }

    @Override
    public void draw(int position) throws IOException {
        if(getScreen()!=null && getGraphics()!=null) {
            getScreen().clear();

            getGraphics().fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
            getGraphics().putString(new TerminalPosition(43, 6), "Game Over");


            getScreen().refresh();
        }
    }
}
