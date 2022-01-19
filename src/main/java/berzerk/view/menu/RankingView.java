package berzerk.view.menu;

import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.ranking.RankingModel;
import berzerk.view.View;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.Map;

public class RankingView extends View<RankingModel> {


    public RankingView(RankingModel model, Ecra ecra){
        super(model, ecra);
    }

    public void draw(int position) throws IOException {
        if(getScreen()!=null && getGraphics()!=null) {
            getScreen().clear();

            getGraphics().fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
            drawTopScorers(getGraphics());

            getScreen().refresh();
        }
    }

    private void drawTopScorers(TextGraphics graphics){
        graphics.putString(new TerminalPosition(43, 6), "TOP SCORERS");

        int i=15;
        for (Map.Entry<String, Integer> jogador: getModel().getJogadores().entrySet()) {
            if(i==30){
                break;
            }
            String nome = jogador.getKey();
            String pontuacao = String.valueOf(jogador.getValue());
            graphics.putString(new TerminalPosition(40, i), nome + numPontos(nome.length(), pontuacao.length()) + pontuacao);
            i+=3;
        }

        graphics.putString(new TerminalPosition(38, 30), "Press Any Key To Continue");

    }


    public String numPontos(int tamNome, int tamPont){
        int total = 20 - tamNome - tamPont;
        if(total>0) {
            return ".".repeat(total);
        }
        else return ".....";
    }

}
