import java.io.IOException;
import control.Game;
import control.MenuController;

public class Application {
    public static void main(String[] args) {
        try
        {
//            MenuController menuController = new MenuController();
//            menuController.execute();
            Game game = new Game();
            game.run();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}