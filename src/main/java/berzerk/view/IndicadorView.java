package berzerk.view;

import berzerk.model.Constants;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
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
        graphics.enableModifiers(SGR.BLINK, SGR.BOLD);
    }

    public void drawTop(TerminalPosition startLine, TerminalPosition endLine) {
        graphics.drawLine(startLine, endLine, '-');
    }

    public void drawBottom(TerminalPosition startLine, TerminalPosition endLine) {
        graphics.drawLine(startLine, endLine, '-');
    }


    public void draw(int colBegin, int lineBegin) {
        if(graphics!=null) {
            drawTop(topLeft(colBegin, lineBegin), topRight(colBegin, lineBegin));
            drawBottom(botLeft(colBegin, lineBegin), botRight(colBegin, lineBegin));
        }
    }

    public TerminalPosition topLeft(int colBegin, int lineBegin){
        return new TerminalPosition(colBegin, lineBegin);
    }

    public TerminalPosition topRight(int colBegin, int lineBegin){
        return new TerminalPosition(colBegin + width - 1, lineBegin);
    }

    public TerminalPosition botLeft(int colBegin, int lineBegin){
        return new TerminalPosition(colBegin, lineBegin + height - 1);
    }

    public TerminalPosition botRight(int colBegin, int lineBegin){
        return new TerminalPosition(colBegin + width - 1, lineBegin + height - 1);
    }



}
