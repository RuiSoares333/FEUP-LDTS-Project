package control.menu;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import control.MenuCommand;
import model.Constants;
import model.Menu.MenuModel;
import model.settings.SettingsModel;
import view.menu.MenuView;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


public class MenuController extends Controller{

    SettingsModel settingsModel = new SettingsModel();
    MenuModel model = new MenuModel();

    public MenuController() throws IOException {
        setScreennDerivates(initScreen(), new MenuView(screen.newTextGraphics()));
    }

    protected Screen initScreen() throws IOException {
//        TerminalSize terminalSize = new TerminalSize(Constants.WIDTH, Constants.HEIGHT);
//        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
//        Terminal terminal = terminalFactory.createTerminal();


        try {
//            screen = new TerminalScreen(terminal);
            screen = new TerminalScreen(getTerminal());
        } catch (URISyntaxException | FontFormatException e) {
            e.printStackTrace();
        }
        screen.setCursorPosition(null); // we don't need a cursor

        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
        return screen;
    }

    public void run() throws IOException, InterruptedException {

        KeyStroke key;

        do {

            view.draw(screen, getPosition(model.getSelected()));
            key = screen.readInput();
            processKey(model, command.getCommand(key));

        }while(key.getKeyType() != KeyType.EOF && running);

        screen.close();
    }


     public void processKey(MenuModel model, MenuCommand key) throws IOException, InterruptedException {
         switch (key.getCommandEnum()) {
             case UP:
                 model.previousSelected();
                 break;
             case DOWN:
                 model.nextSelected();
                 break;
             case SELECT:
                 switch (model.getSelected()) {
                     case PLAY -> System.out.println(settingsModel.getSelected());

                     // jogo
                     case RANKS -> {
                         RankingMenuController rankingMenu = new RankingMenuController(screen);
                         rankingMenu.run();
                     }
                     case SETT -> {
                         screen.close();
                         SettingsMenuController settingsMenu = new SettingsMenuController(settingsModel);
                         settingsMenu.run();
                         setScreennDerivates(initScreen(), new MenuView(screen.newTextGraphics()));
                         run();
                     }
                     case EXIT -> running = false;
                 }
                 break;
             case QUIT:
                 running = false;
                 break;
         }
     }

    public int getPosition(MenuModel.Opcao selected){
        return switch (selected) {
            case SETT -> 12;
            case RANKS -> 14;
            case EXIT -> 18;
            default -> 10;
        };


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
