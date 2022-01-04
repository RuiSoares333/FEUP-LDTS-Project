package view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import control.MenuCommand;
import model.Constants;
import model.Model;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class View<T extends Model> {

    protected T model;
    protected Screen screen;
    protected TextGraphics graphics;

    protected View(T model){
        this.model = model;
    }

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


    public T getModel() {
        return model;
    }

    public abstract void draw(int position) throws IOException;

    public Screen getScreen() {
        return screen;
    }


    public void initScreen() throws IOException {

        try {
            screen = new TerminalScreen(getTerminal());
        } catch (URISyntaxException | FontFormatException e) {
            e.printStackTrace();
        }
        screen.setCursorPosition(null); // we don't need a cursor

        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary

        graphics = screen.newTextGraphics();
    }


    public Terminal getTerminal() throws IOException, URISyntaxException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource("Font Berzerk.ttf");
        assert resource != null;
        File fontFile = new File(resource.toURI());
        Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        DefaultTerminalFactory factory = new DefaultTerminalFactory();

        Font loadedFont = font.deriveFont(Font.PLAIN, 15);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        factory.setTerminalEmulatorFontConfiguration(fontConfig);
        factory.setForceAWTOverSwing(true);
        factory.setInitialTerminalSize(new TerminalSize(Constants.WIDTH, Constants.HEIGHT));

        Terminal terminal = factory.createTerminal();
        ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
            }
        });
        return terminal;
    }

}
