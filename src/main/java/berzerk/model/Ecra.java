package berzerk.model;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


public class Ecra {

    private final Screen screen;
    private final Terminal terminal;

    public Ecra(){
        terminal = initTerminal();
        screen = initScreen();
    }

    public Ecra(Terminal terminal, Screen screen){
        this.terminal = terminal;
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }


    public Terminal getTerminal() {
        return terminal;
    }


    protected Screen initScreen(){

        try{
            Screen screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary

            return screen;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected Terminal initTerminal(){

        try {
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

        } catch (URISyntaxException | IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
