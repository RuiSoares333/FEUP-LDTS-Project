import java.io.IOException;

import control.menu.MenuController;

public class Application {
    public static void main(String[] args) {
        try
        {
            MenuController menuController = new MenuController();
            menuController.run();
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }

    }
}