package Application;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {

        try {

            System.out.println("Hello, World!");

            Game game = new Game();
            game.run();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}