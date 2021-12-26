import java.io.IOException;
import Application.Game;

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