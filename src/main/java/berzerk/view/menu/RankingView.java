package berzerk.view.menu;

import berzerk.model.Ecra;
import berzerk.model.ranking.RankingModel;
import berzerk.view.View;

import java.io.IOException;

public class RankingView extends View<RankingModel> {


    public RankingView(RankingModel model, Ecra ecra){
        super(model, ecra);
    }

    public void draw(int position) throws IOException {

    }

    private void drawTopScorers(){

    }


    private String numPontos(int tamNome, int tamPont){
        return null;
    }

}
