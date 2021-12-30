package view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Constants;

public abstract class Indicador {

    protected int width;
    protected int height;
    protected TextGraphics graphics;

    public Indicador(int width, int height, TextGraphics graphics){
        this.width = width;
        this.height = height;
        this.graphics = graphics;
        if(width>100){
            graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR));
        } else {
            graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
        }
        graphics.setForegroundColor(TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR));
    }

    abstract void drawTop(int colBegin, int lineBegin);
    abstract void drawBottom(int colBegin, int lineBegin);
    abstract void drawLeft(int colBegin, int lineBegin);
    abstract void drawRight(int colBegin, int lineBegin);
    abstract void drawCorners(int colBegin, int lineBegin);
    public abstract void draw(int colBegin, int lineBegin);

}
