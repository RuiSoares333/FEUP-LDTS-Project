package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Constants;

public class IndicadorView {

    protected int width;
    protected int height;
    protected TextGraphics graphics;

    public IndicadorView(int width, int height, TextGraphics graphics){
        assert graphics!=null;
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

    protected void drawTop(int colBegin, int lineBegin) {
        TerminalPosition startLine = new TerminalPosition(colBegin, lineBegin);
        TerminalPosition endLine = new TerminalPosition(colBegin + width - 1, lineBegin);
        if(graphics != null){
            graphics.drawLine(startLine, endLine, '-');
            graphics.putString(colBegin, lineBegin, "[");
            graphics.putString(colBegin + width - 1, lineBegin, "@");
        }

    }

    protected void drawBottom(int colBegin, int lineBegin) {
        TerminalPosition startLine = new TerminalPosition(colBegin, lineBegin + height - 1);
        TerminalPosition endLine = new TerminalPosition(colBegin + width - 1, lineBegin + height - 1);
        if(graphics != null){
            graphics.drawLine(startLine, endLine, '-');
            graphics.putString(colBegin, lineBegin + height - 1, "[");
            graphics.putString(colBegin + width - 1, lineBegin+ height -1, "@");
        }
    }


    public void draw(int colBegin, int lineBegin) {
        graphics.enableModifiers(SGR.BLINK, SGR.BOLD);
        drawTop(colBegin, lineBegin);
        drawBottom(colBegin, lineBegin);
    }

}
