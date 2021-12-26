import java.io.IOException;
import control.Game;

public class Application {
    public static void main(String[] args) {
        try
        {
            Game game = new Game();
            game.run();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}