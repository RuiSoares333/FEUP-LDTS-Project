package view.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import model.Constants;
import model.Menu.MenuModel;
import model.ranking.RankingMenuModel;
import view.View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RankingMenuView extends View<RankingMenuModel> {


    public RankingMenuView(RankingMenuModel model) throws IOException {
        super(model);
        initScreen();
    }

    public void draw(int position) throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        graphicSettings(graphics);
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
        drawTopScorers(graphics);

        screen.refresh();
    }

    public void graphicSettings(TextGraphics graphics){
        graphics.enableModifiers(SGR.BOLD);

        graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
        graphics.setForegroundColor(TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR));
    }

    protected void drawTopScorers(TextGraphics graphics){
        graphics.putString(new TerminalPosition(46, 6), "TOP SCORERS");

        InputStream is = ClassLoader.getSystemResourceAsStream("ranking.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        try {
            int i = 8;
            for (String line; (line = reader.readLine()) != null; i+=1) {
                String[] scorer = line.split(" ");
                graphics.putString(new TerminalPosition(45, i), scorer[0] + "......" + scorer[1]);
            }
            graphics.putString(new TerminalPosition(40, 15), "Press Any Key To Continue");
        } catch (IOException e) {
            e.printStackTrace();
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
