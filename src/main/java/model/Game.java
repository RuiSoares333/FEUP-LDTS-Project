package model;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private Screen screen;

    private boolean flag = true;

    private final int width = 100;
    private final int height = 22;

    private final Arena arena = new Arena(width, height);


    public void run(Screen screen) throws IOException{

//        TerminalSize terminalSize = new TerminalSize(width, height);
//        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
//        Terminal terminal = terminalFactory.createTerminal();
//
//        screen = new TerminalScreen(terminal);
//        screen.setCursorPosition(null); // we don't need a cursor
//        screen.startScreen(); // screens must be started
//        screen.doResizeIfNecessary(); // resize screen if necessary

        KeyStroke key;

        arena.moveMonsters(screen, arena);
        do {

            draw(screen);
            key = screen.readInput();
//            arena.moveMonsters();
            processKey(key);

        }while (key.getKeyType() != KeyType.EOF && flag);

        screen.close();
    }

    private void draw(Screen screen) throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(KeyStroke key) {
        flag = arena.processKey(key);
    }


}
