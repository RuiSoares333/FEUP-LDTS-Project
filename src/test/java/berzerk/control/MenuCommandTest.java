package berzerk.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuCommandTest {
    private MenuCommand command;

    @BeforeEach
    public void initMenuCommand(){
        command = new MenuCommand();
    }

    @Test
    public void defaultCommand(){
        MenuCommand.COMMAND c = MenuCommand.COMMAND.NONE;
        Assertions.assertEquals(c, command.getCommandEnum());
    }

    @Test
    public void nullCommand(){
        KeyStroke key = new KeyStroke(KeyType.Unknown);

        MenuCommand.COMMAND c = MenuCommand.COMMAND.NONE;

        Assertions.assertEquals(c, command.getCommand(key));
    }

    @Test
    public void EOFCommand(){
        KeyStroke key = new KeyStroke(KeyType.EOF);

        MenuCommand.COMMAND c = MenuCommand.COMMAND.QUIT;

        Assertions.assertEquals(c, command.getCommand(key));
    }
    @Test
    public void arrowUpCommand(){
        KeyStroke key = new KeyStroke(KeyType.ArrowUp);

        MenuCommand.COMMAND c = MenuCommand.COMMAND.UP;

        Assertions.assertEquals(c, command.getCommand(key));
    }

    @Test
    public void arrowDownCommand(){
        KeyStroke key = new KeyStroke(KeyType.ArrowDown);

        MenuCommand.COMMAND c = MenuCommand.COMMAND.DOWN;

        Assertions.assertEquals(c, command.getCommand(key));
    }

    @Test
    public void arrowRightCommand(){
        KeyStroke key = new KeyStroke(KeyType.ArrowRight);

        MenuCommand.COMMAND c = MenuCommand.COMMAND.RIGHT;

        Assertions.assertEquals(c, command.getCommand(key));
    }

    @Test
    public void arrowLeftCommand(){
        KeyStroke key = new KeyStroke(KeyType.ArrowLeft);

        MenuCommand.COMMAND c = MenuCommand.COMMAND.LEFT;

        Assertions.assertEquals(c, command.getCommand(key));
    }

    @Test
    public void enterCommand(){
        KeyStroke key = new KeyStroke(KeyType.Enter);

        MenuCommand.COMMAND c = MenuCommand.COMMAND.SELECT;

        Assertions.assertEquals(c, command.getCommand(key));
    }

}
