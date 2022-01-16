package berzerk.control.state;

import berzerk.model.Soldado;
import berzerk.view.GameView;
import berzerk.view.menu.GameOverView;
import berzerk.view.menu.MenuView;
import berzerk.view.menu.RankingView;
import berzerk.view.menu.SettingsView;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class FactoryStateTest {

    FactoryState factoryState = new FactoryState();

    @Test
    public void genMenuState(){
        assertNotNull(factoryState.genMenuState(mock(Soldado.class), mock(MenuView.class)).getClass());
        assertEquals(MenuState.class, factoryState.genMenuState(mock(Soldado.class), mock(MenuView.class)).getClass());
    }

    @Test
    public void genRankingState(){
        assertNotNull(factoryState.genRankingMenuState(mock(Soldado.class), mock(RankingView.class)).getClass());
        assertEquals(RankingState.class, factoryState.genRankingMenuState(mock(Soldado.class), mock(RankingView.class)).getClass());
    }

    @Test
    public void genSettingsState(){
        assertNotNull(factoryState.genSettingsMenuState(mock(Soldado.class), mock(SettingsView.class)).getClass());
        assertEquals(SettingsState.class, factoryState.genSettingsMenuState(mock(Soldado.class), mock(SettingsView.class)).getClass());
    }

    @Test
    public void genGameState() {
        assertNotNull(factoryState.genGameState(mock(Soldado.class), mock(GameView.class)).getClass());
        assertEquals(GameState.class, factoryState.genGameState(mock(Soldado.class), mock(GameView.class)).getClass());
    }

    @Test
    public void genGameOverState() {
        assertNotNull(factoryState.genGameOverState(mock(Soldado.class), mock(GameOverView.class)).getClass());
        assertEquals(GameOverState.class, factoryState.genGameOverState(mock(Soldado.class), mock(GameOverView.class)).getClass());
    }
}
