package berzerk.control;

import com.googlecode.lanterna.input.KeyStroke;

public class Command {
    public enum COMMAND {UP, RIGHT, DOWN, LEFT, SELECT, QUIT, NONE, SPACE}

    private COMMAND command;

    public Command() {
        this.command = COMMAND.NONE;
    }

    public COMMAND getCommand() {
        return command;
    }


    public Command.COMMAND getCommand(KeyStroke key){

        switch (key.getKeyType()) {
            case EOF -> command = COMMAND.QUIT;
            case ArrowUp -> command = COMMAND.UP;
            case ArrowDown -> command = COMMAND.DOWN;
            case ArrowRight -> command = COMMAND.RIGHT;
            case ArrowLeft -> command = COMMAND.LEFT;
            case Enter -> command = COMMAND.SELECT;
            case Character -> {
                if(key.getCharacter() == ' '){
                    command = COMMAND.SPACE;
                }
            }
            default -> command = COMMAND.NONE;
        }
        return command;
    }

}
