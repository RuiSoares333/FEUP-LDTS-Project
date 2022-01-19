package berzerk.view;

import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.entity.Bullet;
import berzerk.model.entity.Wall;
import berzerk.model.entity.enemy.Dementor;
import berzerk.model.entity.enemy.Dragon;
import berzerk.model.entity.hero.Hero;
import berzerk.model.game.Enemies;
import berzerk.model.game.GameModel;
import berzerk.model.game.Shooter;
import berzerk.model.game.Terrain;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.List;

import static berzerk.model.Constants.getHeroColor;

public class GameView extends View<GameModel> {

    TextColor black = TextColor.Factory.fromString(Constants.GAME_BACKGROUND_COLOR);
    TextColor blue = TextColor.Factory.fromString(Constants.GAME_WALL_COLOR);
    TextColor green = TextColor.Factory.fromString(Constants.MONSTER_COLOR);
    TextColor red = TextColor.Factory.fromString(Constants.DEMENTOR_COLOR);
    TextColor white = TextColor.Factory.fromString(Constants.GAME_BULLET_COLOR);
    TextColor orange = TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR);
    TextColor dbrown = TextColor.Factory.fromString("#6e522d");
    TextColor lbrown = TextColor.Factory.fromString("#b39062");

    TextColor heroColor;

    public GameView(GameModel model, Ecra ecra, String heroType){
        super(model, ecra);
        heroColor = getHeroColor(heroType);
    }

    public void drawHero(TextGraphics graphics, Hero hero){
        graphics.setForegroundColor(heroColor);
        graphics.putString(new TerminalPosition(hero.getPosition().getX(), hero.getPosition().getY()), "}");
    }


    // ---------------------------------- TERRAIN -----------------------------------------------------

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

    // ----------------------------- ENEMIES -----------------------------------------------------------

    public void drawEnemies(TextGraphics graphics, Enemies enemies){
        graphics.setBackgroundColor(black);

        drawMonsters(graphics, enemies.getDragons());
        drawDementors(graphics, enemies.getDementors());
    }

    public void drawMonsters(TextGraphics graphics, List<Dragon> dragons) {
        graphics.setForegroundColor(green);

        for(Dragon dragon : dragons)
            graphics.putString(new TerminalPosition(dragon.getPosition().getX(), dragon.getPosition().getY()), "@");

    }

    public void drawDementors(TextGraphics graphics, List<Dementor> dementors) {
        graphics.setForegroundColor(red);

        for(Dementor dementor : dementors)
            graphics.putString(new TerminalPosition(dementor.getPosition().getX(), dementor.getPosition().getY()), "@");

    }

    // ---------------------------------- HUD --------------------------------------------------------

    public void drawHUD(TextGraphics graphics, GameModel model){
        graphics.setBackgroundColor(black);
        graphics.setForegroundColor(orange);

        drawNivel(graphics, model.getTerrain().getLevel());
        drawScore(graphics, model.getEnemies().getScore());
        drawLives(graphics, model.getHero().getHp());
    }

    private void drawNivel(TextGraphics graphics, int nivel){
        graphics.putString(new TerminalPosition(91, 1), "NIVEL: "+ nivel);
    }

    private void drawScore(TextGraphics graphics, int score){
        graphics.putString(new TerminalPosition(45,1), "SCORE: " + score);
    }

    private void drawLives(TextGraphics graphics, int hp){
        graphics.setForegroundColor(heroColor);
        graphics.putString(new TerminalPosition(5,1), "}" + ": " + hp);
    }

    // --------------------------- SHOOTER ------------------------------------------------------------

    public void drawBullets(TextGraphics graphics, Shooter shooter) {
        graphics.setBackgroundColor(black);
        graphics.setForegroundColor(white);

        List<Bullet> bullets = shooter.getBullets();
        for(Bullet bullet : bullets) {
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


    @Override
    public void draw(int position) throws IOException {
        getScreen().clear();
        TextGraphics graphics = getGraphics();

        setBackground(graphics);

        drawEnemies(graphics, model.getEnemies());
        drawBullets(graphics, model.getShooter());
        drawTerrain(graphics, model.getTerrain());
        drawHUD(graphics, model);
        drawHero(graphics, model.getHero());

        getScreen().refresh();
    }

    public void setBackground(TextGraphics graphics){
        graphics.setBackgroundColor(black);
        graphics.enableModifiers(SGR.BOLD);
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
    }

}
