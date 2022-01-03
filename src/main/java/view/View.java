package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import control.MenuCommand;
import model.Constants;
import model.Model;

import java.io.IOException;

public abstract class View<T extends Model> {

    protected T model;
    protected Screen screen;
    protected TextGraphics graphics;

    protected View(T model){
        this.model = model;
    }

    public abstract void initScreen() throws IOException;

    public TextGraphics getGraphics() {
        return graphics;
    }

    public MenuCommand getCommand() throws IOException {
        return new MenuCommand().getCommand(screen.readInput());
    }

    public void setModel(T model) {
        this.model = model;
    }

    public void close() throws IOException {
        screen.close();
    }

    protected void clear(int col, int row) {
        screen.clear();
        graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
        graphics.fillRectangle(new TerminalPosition(col, row), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
    }

    protected void refresh() throws IOException {
        screen.refresh();
    }

    public T getModel() {
        return model;
    }

    public abstract void draw(int position) throws IOException;

    protected int getStringLine(int pos) {
        return 10 + pos * 2;
    }

    public Screen getScreen() {
        return screen;
    }

//    //Column to the string be at the center of the screen
//    public int getCol(String s) {
//        return (COLS_MENU - s.length()) / 2;
//    }

//    protected void drawString(String color, int row, String s) {
//        graphics.setForegroundColor(TextColor.Factory.fromString(color));
//        graphics.putString(getCol(s), row, s);
//    }
}
