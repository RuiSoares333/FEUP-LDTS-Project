package berzerk.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.googlecode.lanterna.input.KeyType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class CommandTest {
    private Command command;

    @BeforeEach
    public void initMenuCommand(){
        command = new Command();
    }

    @Test
    public void defaultCommand(){
        Command.COMMAND c = Command.COMMAND.NONE;
        assertEquals(c, command.getCommand());
    }

    KeyType[] FS= {F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15, F16, F17, F18, F19};

    @Test
    public void nullCommand(){
        KeyStroke key = new KeyStroke(KeyType.Unknown);
        Command.COMMAND c = Command.COMMAND.NONE;
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

        Command.COMMAND c = Command.COMMAND.QUIT;

        assertEquals(c, command.getCommand(key));
    }
    @Test
    public void arrowUpCommand(){
        KeyStroke key = new KeyStroke(KeyType.ArrowUp);

        Command.COMMAND c = Command.COMMAND.UP;

        assertEquals(c, command.getCommand(key));
    }

    @Test
    public void arrowDownCommand(){
        KeyStroke key = new KeyStroke(KeyType.ArrowDown);

        Command.COMMAND c = Command.COMMAND.DOWN;

        assertEquals(c, command.getCommand(key));
    }

    @Test
    public void arrowRightCommand(){
        KeyStroke key = new KeyStroke(KeyType.ArrowRight);

        Command.COMMAND c = Command.COMMAND.RIGHT;

        assertEquals(c, command.getCommand(key));
    }

    @Test
    public void arrowLeftCommand(){
        KeyStroke key = new KeyStroke(KeyType.ArrowLeft);

        Command.COMMAND c = Command.COMMAND.LEFT;

        assertEquals(c, command.getCommand(key));
    }

    @Test
    public void enterCommand(){
        KeyStroke key = new KeyStroke(KeyType.Enter);

        Command.COMMAND c = Command.COMMAND.SELECT;

        assertEquals(c, command.getCommand(key));
    }

    @Test
    public void enterSpaceBar(){
        KeyStroke key = mock(KeyStroke.class);

        doReturn(KeyType.Character).when(key).getKeyType();
        doReturn(' ').when(key).getCharacter();

        Command.COMMAND c = Command.COMMAND.SPACE;

        assertEquals(c, command.getCommand(key));
    }

    @Test
    public void enterX(){
        KeyStroke key = mock(KeyStroke.class);
        doReturn(KeyType.Character).when(key).getKeyType();
        doReturn('x').when(key).getCharacter();

        Command.COMMAND c = Command.COMMAND.CONSTRUCT;

        assertEquals(c, command.getCommand(key));
    }

}
