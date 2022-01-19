package berzerk.view.menu;

import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.entity.Wall;
import berzerk.model.entity.hero.Hero;
import berzerk.model.game.GameModel;
import berzerk.model.game.Terrain;
import berzerk.view.View;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static berzerk.model.Constants.getHeroColor;

public class LastLevelView extends View<GameModel>{

    TextColor black = TextColor.Factory.fromString(Constants.GAME_BACKGROUND_COLOR);
    TextColor blue = TextColor.Factory.fromString(Constants.GAME_WALL_COLOR);
    TextColor orange = TextColor.Factory.fromString("#a66600");
    TextColor medium = TextColor.Factory.fromString("#ed9200");
    TextColor yellow = TextColor.Factory.fromString("#edae00");
    TextColor dbrown = TextColor.Factory.fromString("#6e522d");
    TextColor lbrown = TextColor.Factory.fromString("#b39062");
    TextColor heroColor;

    public LastLevelView(GameModel model, Ecra ecra, String heroType) {
        super(model, ecra);
        model.getEnemies().setDementors(new ArrayList<>());
        model.getEnemies().setDragons(new ArrayList<>());
        heroColor = getHeroColor(heroType);
    }

    public void drawHero(TextGraphics graphics, Hero hero){
        graphics.setForegroundColor(heroColor);
        graphics.putString(new TerminalPosition(hero.getPosition().getX(), hero.getPosition().getY()), "}");
    }

    public void drawTrophy(TextGraphics graphics){
        for (int y = 0; y < Constants.TROPHY.length; y++) {
            char[] blocos = Constants.TROPHY[y].toCharArray();
            for (int x = 0; x < blocos.length; x++) {
                switch (blocos[x]) {
                    case 'o' -> graphics.setBackgroundColor(orange);
                    case 'm' -> graphics.setBackgroundColor(medium);
                    case 'a' -> graphics.setBackgroundColor(yellow);
                    default -> graphics.setBackgroundColor(black);
                }
                graphics.fillRectangle(new TerminalPosition(x+42, y+12), new TerminalSize(1, 1), ' ');
            }
        }
    }

    public void drawTerrain(TextGraphics graphics, Terrain terrain){
        drawWalls(graphics, terrain.getWalls());
        drawStones(graphics, terrain.getStones());
    }

    public void drawWalls(TextGraphics graphics, List<Wall> walls){
        graphics.setBackgroundColor(blue);
        graphics.setForegroundColor(blue);

        for(Wall wall : walls)
            graphics.putString(new TerminalPosition(wall.getPosition().getX(), wall.getPosition().getY()), "H");
    }

    public void drawStones(TextGraphics graphics, List<Wall> stones) {
        graphics.setForegroundColor(lbrown);
        graphics.setBackgroundColor(dbrown);

        for (Wall stone : stones)
            graphics.putString(new TerminalPosition(stone.getPosition().getX(), stone.getPosition().getY()), "z");
    }

    @Override
    public void draw(int position) throws IOException {
        getScreen().clear();

        TextGraphics graphics = getGraphics();
        drawHero(graphics, model.getHero());
        drawTerrain(graphics, model.getTerrain());
        drawTrophy(graphics);

        getScreen().refresh();
    }
}
