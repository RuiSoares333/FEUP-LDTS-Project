package control.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import control.MenuCommand;
import model.Menu.MenuModel;
import view.menu.Menu;

import java.io.IOException;

public abstract class Controller {

    protected Screen screen;
    protected TextGraphics graphics;

    protected boolean running = true;

    protected Menu view;
    protected MenuCommand command = new MenuCommand();


    public void setScreennDerivates(Screen screen, Menu view){
        this.screen = screen;
        graphics = this.screen.newTextGraphics();
        this.view = view;
    }

    abstract void run() throws IOException, InterruptedException;
    abstract void processKey(MenuModel model, MenuCommand key) throws IOException, InterruptedException;
    abstract Screen initScreen() throws IOException;
}
