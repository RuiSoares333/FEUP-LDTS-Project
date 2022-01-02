package view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class IndicadorBigView extends Indicador{


    public IndicadorBigView(int width, int height, TextGraphics graphics) {
        super(width,height,graphics);
    }


    protected void drawTop(int colBegin, int lineBegin) {
        for(int i=0; i<5; i++){
            TerminalPosition startLine = new TerminalPosition(colBegin, lineBegin+i);
            TerminalPosition endLine = new TerminalPosition(colBegin + width - 1, lineBegin+i);
            graphics.drawLine(startLine, endLine, '-');
        }
    }

    protected void drawBottom(int colBegin, int lineBegin) {
        for(int i=0; i<5; i++){
            TerminalPosition startLine = new TerminalPosition(colBegin, lineBegin + height - 1 + i);
            TerminalPosition endLine = new TerminalPosition(colBegin + width - 1, lineBegin + height - 1+ i);
            graphics.drawLine(startLine, endLine, '-');
        }
    }

    protected void drawLeft(int colBegin, int lineBegin) {
        TerminalPosition startLine = new TerminalPosition(colBegin, lineBegin);
        TerminalPosition endLine = new TerminalPosition(colBegin, lineBegin + height - 1);
        graphics.drawLine(startLine, endLine, '|');
    }

    protected void drawRight(int colBegin, int lineBegin) {
        TerminalPosition startLine = new TerminalPosition(colBegin + width - 1, lineBegin);
        TerminalPosition endLine = new TerminalPosition(colBegin + width - 1, lineBegin + height - 1);
        graphics.drawLine(startLine, endLine, '|');
    }

    protected void drawCorners(int colBegin, int lineBegin) {
        graphics.putString(colBegin, lineBegin, "+");
        graphics.putString(colBegin, lineBegin + height - 1, "+");
        graphics.putString(colBegin + width - 1, lineBegin, "+");
        graphics.putString(colBegin + width - 1, lineBegin + height - 1, "+");
    }

    public void draw(int colBegin, int lineBegin) {
        drawTop(colBegin, lineBegin);
        drawBottom(colBegin, lineBegin);
        drawRight(colBegin, lineBegin);
        drawLeft(colBegin, lineBegin);
        drawCorners(colBegin, lineBegin);
    }

}