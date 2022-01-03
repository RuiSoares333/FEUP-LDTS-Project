package view.menu;

import model.ranking.RankingMenuModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.View;

import java.io.IOException;

public class RankingMenuViewTest {

    View view;
    RankingMenuModel model;

    @BeforeEach
    public void initView() throws IOException {
        model = new RankingMenuModel();
        view = Mockito.spy(new RankingMenuView(model));
    }


    @Test
    public void drawTest() throws IOException {
        view.draw(0);
        Mockito.verify((RankingMenuView) view, Mockito.times(1)).graphicSettings(Mockito.any());
        Mockito.verify((RankingMenuView) view, Mockito.times(1)).drawTopScorers(Mockito.any());
    }

    @Test
    public void numPontos(){
        RankingMenuView vieew = (RankingMenuView) view;

        Assertions.assertEquals("..........", vieew.numPontos(5, 5));
        Assertions.assertEquals("..............", vieew.numPontos(3, 3));
        Assertions.assertEquals("..................", vieew.numPontos(1, 1));
        Assertions.assertEquals(".....", vieew.numPontos(10, 10));
    }


}
