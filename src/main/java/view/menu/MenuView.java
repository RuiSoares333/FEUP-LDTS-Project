package view.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import model.Constants;
import model.Menu.MenuModel;
import view.Indicador;
import view.View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class MenuView extends View<MenuModel> {

    Indicador indicador;
    public MenuView(MenuModel model) throws IOException {
        super(model);
        initScreen();
        indicador = new Indicador(13, 3, graphics);
    }

    public void draw(int position) throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphicSettings(graphics);


        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
        drawGameName(graphics);
        drawOptions(graphics);

        indicador.draw(44, position);
        screen.refresh();
    }

    public void graphicSettings(TextGraphics graphics){
        graphics.enableModifiers(SGR.BOLD);

        graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
        graphics.setForegroundColor(TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR));
    }

    public void drawOptions(TextGraphics graphics){
        graphics.putString(new TerminalPosition(48, 11), "PLAY");
        graphics.putString(new TerminalPosition(46, 13), "SETTINGS");
        graphics.putString(new TerminalPosition(45, 15), "LEADERBOARD");
        graphics.putString(new TerminalPosition(48, 19), "EXIT");
    }

    public void drawGameName(TextGraphics graphics){
        for(int y=0; y<Constants.GAME_NAME.length; y++){
            graphics.putString(new TerminalPosition(27, 1+y), Constants.GAME_NAME[y]);
        }
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
