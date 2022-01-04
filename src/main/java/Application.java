import control.Controller;
import control.menu.state.FactoryState;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try
        {
            Controller controller = new Controller(new FactoryState());
            controller.run();
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }

    }
}