package berzerk.control;

import com.googlecode.lanterna.input.KeyStroke;

public class Command {
    public enum COMMAND {UP, RIGHT, DOWN, LEFT, SELECT, QUIT, NONE, SPACE, CONSTRUCT, DELETE, TYPE}

    private COMMAND command;
    private Character key;

    public Command() {
        this.command = COMMAND.NONE;
        key = ' ';
    }

    public COMMAND getCommand() {
        return command;
    }

    public Character getKey(){
        return key;
    }

    public Command getCommand(KeyStroke key){
        switch (key.getKeyType()) {
            case EOF -> command = COMMAND.QUIT;
            case ArrowUp -> command = COMMAND.UP;
            case ArrowDown -> command = COMMAND.DOWN;
            case ArrowRight -> command = COMMAND.RIGHT;
            case ArrowLeft -> command = COMMAND.LEFT;
            case Enter -> command = COMMAND.SELECT;
            case Backspace -> command = COMMAND.DELETE;
            case Character -> {
                this.key = key.getCharacter();
                command = COMMAND.TYPE;
                if(key.getCharacter() == ' '){
                    command = COMMAND.SPACE;
                }
                else if(key.getCharacter() == 'x'){
                    command = COMMAND.CONSTRUCT;
                }
            }
            default -> command = COMMAND.NONE;
        }
        return this;
    }

}
