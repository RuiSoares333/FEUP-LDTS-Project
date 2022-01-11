package berzerk.view;

import berzerk.control.Command;
import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.Model;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public abstract class View<T extends Model> {

    protected T model;

    private final Ecra ecra;
    private Screen screen;
    private TextGraphics graphics;

    public View(T model, Ecra ecra) {
        this.model = model;
        this.ecra = ecra;

        screen = ecra.getScreen();
        if (screen != null) {
            graphics = screen.newTextGraphics();

            graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
            graphics.setForegroundColor(TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR));
        }
    }

    public Command.COMMAND getCommand() throws IOException {
        if(screen != null) return new Command().getCommand(screen.readInput());
        return Command.COMMAND.NONE;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setGraphics(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public TextGraphics getGraphics() {
        return graphics;
    }

    public void close() throws IOException {
        if(ecra!=null && ecra.getScreen()!=null) ecra.getScreen().close();
    }

    public abstract void draw(int position) throws IOException;

}
