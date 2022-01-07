package berzerk.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.googlecode.lanterna.input.KeyType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuCommandTest {
    private MenuCommand command;

    @BeforeEach
    public void initMenuCommand(){
        command = new MenuCommand();
    }

    @Test
    public void defaultCommand(){
        MenuCommand.COMMAND c = MenuCommand.COMMAND.NONE;
        assertEquals(c, command.getCommand());
    }

    KeyType[] FS= {F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15, F16, F17, F18, F19};

    @Test
    public void nullCommand(){
        KeyStroke key = new KeyStroke(KeyType.Unknown);
        MenuCommand.COMMAND c = MenuCommand.COMMAND.NONE;
        assertEquals(c, command.getCommand(key));

        key = new KeyStroke(KeyType.Backspace);
        assertEquals(c, command.getCommand(key));

        key = new KeyStroke(KeyType.Tab);
        assertEquals(c, command.getCommand(key));

        key = new KeyStroke(KeyType.Insert);
        assertEquals(c, command.getCommand(key));

        key = new KeyStroke(KeyType.Delete);
        assertEquals(c, command.getCommand(key));

        key = new KeyStroke(KeyType.PageDown);
        assertEquals(c, command.getCommand(key));

        key = new KeyStroke(KeyType.PageUp);
        assertEquals(c, command.getCommand(key));

        for (KeyType kt: FS) {
            key = new KeyStroke(KeyType.PageUp);
            assertEquals(c, command.getCommand(key));
        }
    }

    @Test
    public void EOFCommand(){
        KeyStroke key = new KeyStroke(KeyType.EOF);

        MenuCommand.COMMAND c = MenuCommand.COMMAND.QUIT;

        assertEquals(c, command.getCommand(key));
    }
    @Test
    public void arrowUpCommand(){
        KeyStroke key = new KeyStroke(KeyType.ArrowUp);

        MenuCommand.COMMAND c = MenuCommand.COMMAND.UP;

        assertEquals(c, command.getCommand(key));
    }

    @Test
    public void arrowDownCommand(){
        KeyStroke key = new KeyStroke(KeyType.ArrowDown);

        MenuCommand.COMMAND c = MenuCommand.COMMAND.DOWN;

        assertEquals(c, command.getCommand(key));
    }

    @Test
    public void arrowRightCommand(){
        KeyStroke key = new KeyStroke(KeyType.ArrowRight);

        MenuCommand.COMMAND c = MenuCommand.COMMAND.RIGHT;

        assertEquals(c, command.getCommand(key));
    }

    @Test
    public void arrowLeftCommand(){
        KeyStroke key = new KeyStroke(KeyType.ArrowLeft);

        MenuCommand.COMMAND c = MenuCommand.COMMAND.LEFT;

        assertEquals(c, command.getCommand(key));
    }

    @Test
    public void enterCommand(){
        KeyStroke key = new KeyStroke(KeyType.Enter);

        MenuCommand.COMMAND c = MenuCommand.COMMAND.SELECT;

        assertEquals(c, command.getCommand(key));
    }

}
