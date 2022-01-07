package berzerk.model;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import com.googlecode.lanterna.terminal.Terminal;


public class Ecra {

    private Screen screen;
    private TextGraphics graphics;
    private Terminal terminal;

    public Ecra(){
        terminal = initTerminal();
        screen = initScreen();
        if(screen!=null) graphics = screen.newTextGraphics();
    }

    public Screen getScreen() {
        return screen;
    }

    public TextGraphics getGraphics() {
        return graphics;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public void setGraphics(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    protected Screen initScreen(){
        return null;
    }

    protected Terminal initTerminal(){
        return null;
    }
}
