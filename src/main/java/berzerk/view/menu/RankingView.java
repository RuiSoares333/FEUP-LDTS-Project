package berzerk.view.menu;

import berzerk.model.Ecra;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import berzerk.model.Constants;
import berzerk.model.ranking.RankingModel;
import berzerk.view.View;

import java.io.IOException;
import java.util.Map;

public class RankingView extends View<RankingModel> {


    public RankingView(RankingModel model, Ecra ecra){
        super(model, ecra);
    }

    public void draw(int position) throws IOException {
        getEcra().getScreen().clear();

        getEcra().getGraphics().fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
        drawTopScorers();

        getEcra().getScreen().refresh();
    }

    private void drawTopScorers(){
        TextGraphics graphics = getEcra().getGraphics();
        graphics.putString(new TerminalPosition(43, 6), "TOP SCORERS");

        int i=15;
        for (Map.Entry<String, String> jogador: getModel().getJogadores().entrySet()) {
            String nome = jogador.getKey();
            String pontuacao = jogador.getValue();
            graphics.putString(new TerminalPosition(40, i), nome + numPontos(nome.length(), pontuacao.length()) + pontuacao);
            i+=3;
        }

        graphics.putString(new TerminalPosition(38, 30), "Press Any Key To Continue");

    }


    private String numPontos(int tamNome, int tamPont){
        int total = 20 - tamNome - tamPont;
        if(total>0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(".".repeat(Math.max(0, total)));
            return stringBuilder.toString();
        }
        return ".....";
    }

}
