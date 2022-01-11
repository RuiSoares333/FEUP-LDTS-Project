package berzerk.view;

import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.entity.Monster;
import berzerk.model.entity.Wall;
import berzerk.model.entity.hero.Hero;
import berzerk.model.game.GameModel;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class GameView extends View<GameModel> {

    String black = Constants.GAME_BACKGROUND_COLOR;
    String blue = Constants.GAME_WALL_COLOR;
    String green = Constants.MONSTER_COLOR;
    String heroColor;

    public GameView(GameModel model, Ecra ecra){
        super(model, ecra);
        heroColor = getHeroColor();
    }

    public String getHeroColor(){
        String color = "";
        if(heroColor==null) {
            switch (this.getModel().getHero().getClass().getSimpleName()) {
                case "Recruit" -> color = Constants.RECRUIT_COLOR;
                case "Tanky" -> color = Constants.TANKY_COLOR;
                case "Expert" -> color = Constants.EXPERT_COLOR;
                default -> color = "#FFFF33";
            }
        }
        return color;
    }

    public void drawHero(TextGraphics graphics, Hero hero){
        graphics.setForegroundColor(TextColor.Factory.fromString(heroColor));
        graphics.putString(new TerminalPosition(hero.getPosition().getX(), hero.getPosition().getY()), "}");
    }

    public void drawWalls(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(blue));
        graphics.setForegroundColor(TextColor.Factory.fromString(blue));

        for(Wall wall : model.getWalls())
            graphics.putString(new TerminalPosition(wall.getPosition().getX(), wall.getPosition().getY()), "H");
    }

    public void drawMonsters(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(green));

        for(Monster monster : model.getMonsters())
            graphics.putString(new TerminalPosition(monster.getPosition().getX(), monster.getPosition().getY()), "@");

    }

    private void drawNivel(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR));
        graphics.putString(new TerminalPosition(92, 0), "NIVEL: "+ model.getNivel());
    }



    @Override
    public void draw(int position) throws IOException {
        getScreen().clear();
        TextGraphics graphics = getGraphics();

        setBackground(graphics);

        drawMonsters(graphics);
        drawHero(graphics, model.getHero());
        drawNivel(graphics);
        drawWalls(graphics);

        getScreen().refresh();
    }

    public void setBackground(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(black));
        graphics.enableModifiers(SGR.BOLD);
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
    }

}
