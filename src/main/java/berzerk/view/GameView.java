package berzerk.view;

import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.entity.Bullet;
import berzerk.model.entity.Wall;
import berzerk.model.entity.enemy.Dementor;
import berzerk.model.entity.enemy.Dragon;
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
    String red = Constants.DEMENTOR_COLOR;
    String white = Constants.GAME_BULLET_COLOR;
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

        for(Wall wall : model.getTerrain().getWalls())
            graphics.putString(new TerminalPosition(wall.getPosition().getX(), wall.getPosition().getY()), "H");
    }

    public void drawMonsters(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(green));

        for(Dragon dragon : model.getEnemies().getDragons())
            graphics.putString(new TerminalPosition(dragon.getPosition().getX(), dragon.getPosition().getY()), "@");

    }

    public void drawDementors(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(red));

        for(Dementor dementor : model.getEnemies().getDementors())
            graphics.putString(new TerminalPosition(dementor.getPosition().getX(), dementor.getPosition().getY()), "@");

    }

    private void drawNivel(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR));
        graphics.putString(new TerminalPosition(91, 1), "NIVEL: "+ model.getTerrain().getLevel());
    }

    private void drawScore(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(black));
        graphics.setForegroundColor(TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR));
        graphics.putString(new TerminalPosition(45,1), "SCORE: " + model.getEnemies().getScore());
    }

    private void drawLives(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(black));
        graphics.setForegroundColor(TextColor.Factory.fromString(heroColor));
        graphics.putString(new TerminalPosition(5,1), "}" + ": " + model.getHero().getHp());
    }

    public void drawBullets(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(black));
        graphics.setForegroundColor(TextColor.Factory.fromString(white));

        for(Bullet bullet : model.getShooter().getBullets()) {
            if (bullet.getOrientation() == 1)
                graphics.putString(new TerminalPosition(bullet.getPosition().getX(), bullet.getPosition().getY()), "(");
            else if(bullet.getOrientation() == 2)
                graphics.putString(new TerminalPosition(bullet.getPosition().getX(), bullet.getPosition().getY()), "-");
            else if(bullet.getOrientation() == 3)
                graphics.putString(new TerminalPosition(bullet.getPosition().getX(), bullet.getPosition().getY()), ")");
            else
                graphics.putString(new TerminalPosition(bullet.getPosition().getX(), bullet.getPosition().getY()), "*");
        }
    }

    public void drawStones(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#b39062"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#6e522d"));

        for (Wall stone : model.getTerrain().getStones())
            graphics.putString(new TerminalPosition(stone.getPosition().getX(), stone.getPosition().getY()), "z");
    }

    @Override
    public void draw(int position) throws IOException {
        getScreen().clear();
        TextGraphics graphics = getGraphics();

        setBackground(graphics);

        drawMonsters(graphics);
        drawDementors(graphics);
        drawHero(graphics, model.getHero());
        drawNivel(graphics);
        drawWalls(graphics);
        drawStones(graphics);

        drawScore(graphics);
        drawLives(graphics);

        drawBullets(graphics);
        getScreen().refresh();
    }

    public void setBackground(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(black));
        graphics.enableModifiers(SGR.BOLD);
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
    }

}
