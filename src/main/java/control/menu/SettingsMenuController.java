package control.menu;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import control.MenuCommand;
import model.Constants;
import model.Menu.MenuModel;
import model.settings.SettingsModel;
import view.menu.SettingsMenuView;

import java.awt.*;
import java.io.IOException;

public class SettingsMenuController extends Controller{

    protected SettingsModel settingsModel = new SettingsModel();

    public SettingsMenuController(SettingsModel settingsModel) throws IOException {
        this.settingsModel = settingsModel;
        setScreennDerivates(initScreen(), new SettingsMenuView(screen.newTextGraphics()));
    }

    public void run() throws IOException {

        SettingsMenuView settingsMenuView = new SettingsMenuView(screen.newTextGraphics());
        MenuCommand mc = new MenuCommand();
        do {

            settingsMenuView.draw(screen, getPosition(settingsModel.getSelected()));
            processKey(settingsModel, mc.getCommand(screen.readInput()));

        }while (running);
        screen.close();
    }

    @Override
    void processKey(MenuModel model, MenuCommand key) {

    }

    @Override
    Screen initScreen() throws IOException {
        AWTTerminalFontConfiguration cfg = new SwingTerminalFontConfiguration(true,
                AWTTerminalFontConfiguration.BoldMode.NOTHING, new Font(Font.MONOSPACED,Font.PLAIN, 2));
        Terminal terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(Constants.WIDTH*8, Constants.HEIGHT*8))
                .setTerminalEmulatorFontConfiguration(cfg)
                .createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
        return screen;
    }

    public void processKey(SettingsModel model, MenuCommand key){
        switch (key.getCommandEnum()) {
            case LEFT -> model.previousSelected();
            case RIGHT -> model.nextSelected();
            case SELECT, QUIT -> running = false;
        }
    }

    public int getPosition(SettingsModel.Heroi selected){
        return switch (selected) {
            case TANKY -> 336;
            case EXPERT -> 560;
            default -> 112;
        };
    }
}
