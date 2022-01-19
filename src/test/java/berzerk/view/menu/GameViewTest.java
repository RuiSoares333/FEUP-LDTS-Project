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
import berzerk.view.View;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class GameViewTest {

    GameModel model;
    Enemies enemies;
    Terrain terrain;

    Shooter shooter;

    GameView view;
    Ecra ecra;
    Screen screen;
    TextGraphics graphics;

    @BeforeEach
    public void initView() throws IOException {
        shooter = mock(Shooter.class);
        model = mock(GameModel.class);
        ecra = mock(Ecra.class);
        view = spy(new GameView(model, ecra, "kekw"));
        screen = mock(Screen.class);
        graphics = mock(TextGraphics.class);

        doReturn(screen).when(view).getScreen();
        doReturn(graphics).when(view).getGraphics();

        doReturn(shooter).when(model).getShooter();


        doNothing().when(screen).clear();
        doNothing().when(screen).refresh();



    }

    @Test
    public void drawBullets() throws IOException {
        List<Dementor> dementors = new ArrayList<>();
        dementors.add(new Dementor(new Position(5, 5)));
        List<Dragon> dragons = new ArrayList<>();
        dragons.add(new Dragon(10, 10));
        List<Wall> walls = new ArrayList<>();
        walls.add(new Wall(20, 20));
        List<Bullet> bullets = new ArrayList<>();
        bullets.add(new Bullet(10,11,1));
        bullets.add(new Bullet(20,10,2));
        bullets.add(new Bullet(20,15,3));
        bullets.add(new Bullet(25,12,4));

//        doReturn(dementors).when(model).getEnemies();

        doReturn(bullets).when(shooter).getBullets();

        view.drawBullets(graphics, shooter);

        verify(graphics, atLeastOnce()).putString(any(TerminalPosition.class), anyString());

//        verify(screen, atLeastOnce()).clear();
//        verify(screen, atLeastOnce()).refresh();
    }

    @Test
    public void drawStones(){
        List<Wall> walls = new ArrayList<>();
        walls.add(new Wall(20, 20));
        view.drawStones(graphics, walls);
        verify(graphics, atLeastOnce()).putString(any(TerminalPosition.class), anyString());

    }
}
