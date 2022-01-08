package berzerk.view;

import berzerk.model.Arena;
import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.Model;
import berzerk.model.entity.Monster;
import berzerk.model.entity.Wall;
import berzerk.model.entity.hero.Hero;
import berzerk.model.menu.MenuModel;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class GameView extends View implements Model {

    Arena arena;


    public GameView(Ecra ecra, Arena arena){
        super(new MenuModel(), ecra);
        this.arena = arena;
    }

    public void drawHero(TextGraphics graphics, Hero hero){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(hero.getPosition().getX(), hero.getPosition().getY()), "}");
    }

    public void drawWall(TextGraphics graphics, Wall wall){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#5C62F7"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#5C62F7"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(wall.getPosition().getX(), wall.getPosition().getY()), "H");
    }

    public void drawMonster(TextGraphics graphics, Monster monster) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#24A120"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(monster.getPosition().getX(), monster.getPosition().getY()), "@");
    }



    @Override
    public void draw(int position) throws IOException {
        getScreen().clear();
        TextGraphics graphics = getGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');

        drawHero(graphics, arena.getHero());            // draw the mighty hero


        for (Wall wall : arena.getWalls())         // draw the imposing walls
            drawWall(graphics, wall);
        for(Monster monster: arena.getMonsters())  // draw the fierce monsters
            drawMonster(graphics, monster);

        getScreen().refresh();
    }


}
