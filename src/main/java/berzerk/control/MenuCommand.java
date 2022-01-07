package berzerk.control;

import com.googlecode.lanterna.input.KeyStroke;

public class MenuCommand {
    public enum COMMAND {UP, RIGHT, DOWN, LEFT, SELECT, QUIT, NONE}

    private COMMAND command;

    public MenuCommand() {
        this.command = COMMAND.NONE;
    }

    public COMMAND getCommandEnum() {
        return command;
    }


    public MenuCommand.COMMAND getCommand(KeyStroke key){

        switch (key.getKeyType()) {
            case EOF -> command = COMMAND.QUIT;
            case ArrowUp -> command = COMMAND.UP;
            case ArrowDown -> command = COMMAND.DOWN;
            case ArrowRight -> command = COMMAND.RIGHT;
            case ArrowLeft -> command = COMMAND.LEFT;
            case Enter -> command = COMMAND.SELECT;
        }
        return command;
    }

}
