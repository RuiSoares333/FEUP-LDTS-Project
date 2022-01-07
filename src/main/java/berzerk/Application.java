package berzerk;

import berzerk.control.Controller;
import berzerk.control.state.FactoryState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) {
        try
        {
            Controller controller = new Controller(new FactoryState());
            controller.run();
        }
        catch (IOException | InterruptedException | URISyntaxException | FontFormatException e)
        {
            e.printStackTrace();
        }

    }
}