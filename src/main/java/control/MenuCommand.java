package control;

import com.googlecode.lanterna.input.KeyStroke;

public class MenuCommand {
    public enum COMMAND {UP, RIGHT, DOWN, LEFT, SELECT, QUIT, NONE}

    private COMMAND command;
    private Character key;

    public MenuCommand() {
        this.key = ' ';
        this.command = COMMAND.NONE;
    }

    public COMMAND getCommandEnum() {
        return command;
    }


    public MenuCommand getCommand(KeyStroke key){

        switch (key.getKeyType()) {
            case EOF -> command = COMMAND.QUIT;
            case ArrowUp -> command = COMMAND.UP;
            case ArrowDown -> command = COMMAND.DOWN;
            case ArrowRight -> command = COMMAND.RIGHT;
            case ArrowLeft -> command = COMMAND.LEFT;
            case Enter -> command = COMMAND.SELECT;
            case Character -> {
                this.key = key.getCharacter();
                if (this.key == ' ') {
                    command = COMMAND.SELECT;
                }
            }
        }
        return this;
    }
}
