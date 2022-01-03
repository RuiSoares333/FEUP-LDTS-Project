package view.menu;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import model.Constants;
import model.Menu.MenuModel;
import model.ranking.RankingMenuModel;
import view.View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RankingMenuView extends View<RankingMenuModel> {


    public RankingMenuView(RankingMenuModel model) throws IOException {
        super(model);
        initScreen();
    }

    public void draw(int position) throws IOException {
        screen.clear();

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

    protected void drawTopScorers(TextGraphics graphics){
        graphics.putString(new TerminalPosition(43, 6), "TOP SCORERS");

        int i=15;
        for (Map.Entry<String, String> jogador: model.getJogadores().entrySet()) {
            String nome = jogador.getKey();
            String pontuacao = jogador.getValue();
            graphics.putString(new TerminalPosition(40, i), nome + numPontos(nome.length(), pontuacao.length()) + pontuacao);
            i+=3;
        }

        graphics.putString(new TerminalPosition(38, 30), "Press Any Key To Continue");

    }


    protected String numPontos(int tamNome, int tamPont){
        int total = 20 - tamNome - tamPont;
        if(total>0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(".".repeat(Math.max(0, total)));
            return stringBuilder.toString();
        }
        return ".....";
    }

}
