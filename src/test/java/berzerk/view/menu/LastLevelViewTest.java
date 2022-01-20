package berzerk.view.menu;

import berzerk.model.Ecra;
import berzerk.model.entity.Bullet;
import berzerk.model.entity.Wall;
import berzerk.model.entity.enemy.Dementor;
import berzerk.model.entity.enemy.Dragon;
import berzerk.model.entity.properties.Position;
import berzerk.model.game.Enemies;
import berzerk.model.game.GameModel;
import berzerk.model.game.Shooter;
import berzerk.model.game.Terrain;
import berzerk.view.GameView;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

public class LastLevelViewTest {

    GameModel model;
    Enemies enemies;
    Terrain terrain;

    List<Dementor> dementors = new ArrayList<>();
    List<Dragon> dragons = new ArrayList<>();

    Shooter shooter;

    LastLevelView view;
    Ecra ecra;
    Screen screen;
    TextGraphics graphics;

    @BeforeEach
    public void initView() throws IOException {
        terrain = new Terrain(2);
        enemies = mock(Enemies.class);
        shooter = mock(Shooter.class);
        model = mock(GameModel.class);
        ecra = mock(Ecra.class);

        doReturn(enemies).when(model).getEnemies();
        doNothing().when(enemies).setDementors(any());
        doNothing().when(enemies).setDragons(any());

        view = spy(new LastLevelView(model, ecra, "kekw"));


        screen = mock(Screen.class);
        graphics = mock(TextGraphics.class);

        doReturn(screen).when(view).getScreen();
        doReturn(graphics).when(view).getGraphics();

        doReturn(shooter).when(model).getShooter();


        doNothing().when(screen).clear();
        doNothing().when(screen).refresh();


    }

    @Test
    public void drawStones(){
        try{
            List<Bullet> bullets = new ArrayList<>();
            bullets.add(new Bullet(10,11,1));
            List<Wall> walls = new ArrayList<>();
            walls.add(new Wall(20, 20));

            view.drawStones(graphics, walls);

            verify(graphics, atLeastOnce()).putString(any(TerminalPosition.class), anyString());
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
