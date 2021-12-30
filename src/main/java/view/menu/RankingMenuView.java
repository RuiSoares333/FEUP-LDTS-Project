package view.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RankingMenuView extends Menu{
    @Override
    public void draw(Screen screen, int position) throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        graphicSettings(graphics);
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
        drawTopScorers(graphics);

        screen.refresh();
    }

    public void graphicSettings(TextGraphics graphics){
        graphics.enableModifiers(SGR.BOLD);

        graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
        graphics.setForegroundColor(TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR));
    }

    private void drawTopScorers(TextGraphics graphics){
        graphics.putString(new TerminalPosition(46, 6), "TOP SCORERS");

        InputStream is = ClassLoader.getSystemResourceAsStream("ranking.txt");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        try {
            int i = 8;
            for (String line; (line = reader.readLine()) != null; i+=1) {
                String[] scorer = line.split(" ");
                graphics.putString(new TerminalPosition(45, i), scorer[0] + "......" + scorer[1]);
            }
            graphics.putString(new TerminalPosition(40, 15), "Press Any Key To Continue");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
