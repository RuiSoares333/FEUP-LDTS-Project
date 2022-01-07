package berzerk.view;

import berzerk.model.Constants;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class IndicadorView {

    protected int width;
    protected int height;
    protected TextGraphics graphics;

    public IndicadorView(int width, int height, TextGraphics graphics){
        if(graphics==null){return;}
        this.width = width;
        this.height = height;
        this.graphics = graphics;
        graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
        graphics.setForegroundColor(TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR));

    }

    private void drawTop(int colBegin, int lineBegin) {

    }

    private void drawBottom(int colBegin, int lineBegin) {

    }


    public void draw(int colBegin, int lineBegin) {

    }

}
