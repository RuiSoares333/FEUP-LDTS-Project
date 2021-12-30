package view.menu;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public abstract class Menu {
    public abstract void draw(Screen screen, int position) throws IOException;
    abstract void graphicSettings(TextGraphics graphics);
}
