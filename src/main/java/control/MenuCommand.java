package control;

import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class MenuCommand {
    public enum COMMAND {UP, RIGHT, DOWN, LEFT, SELECT, QUIT, NONE}

    private COMMAND command;
    private Character key;

    public MenuCommand() {
        this.key = ' ';
        this.command = COMMAND.NONE;
    }

    public Character getKey() {
        return key;
    }

    public COMMAND getCommandEnum() {
        return command;
    }


    public MenuCommand getCommand(KeyStroke key){

        if (key == null)
            return this;

        switch (key.getKeyType()) {
            case EOF:
                command = COMMAND.QUIT;
            case ArrowUp:
                command = COMMAND.UP;
                break;
            case ArrowDown:
                command = COMMAND.DOWN;
                break;
            case ArrowRight:
                command = COMMAND.RIGHT;
                break;
            case ArrowLeft:
                command = COMMAND.LEFT;
                break;
            case Enter:
                command = COMMAND.SELECT;
                break;
            case Character:
                this.key = key.getCharacter();
                if (this.key == ' ') {
                    command = COMMAND.SELECT;
                }
        }
        return this;
    }
}
