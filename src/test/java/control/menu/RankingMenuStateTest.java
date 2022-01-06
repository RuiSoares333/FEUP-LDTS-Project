package control.menu;

import control.MenuCommand;
import control.state.FactoryState;
import control.state.MenuState;
import control.state.RankingMenuState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;


public class RankingMenuStateTest {

    RankingMenuState rms;
    FactoryState factoryState;

    @BeforeEach
    public void initRankingMenuState(){
        try {
            factoryState = Mockito.spy(FactoryState.class);
            rms = Mockito.spy(factoryState.genRankingMenuState(Mockito.any()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void run() throws IOException {
        MenuCommand keyMock = Mockito.mock(MenuCommand.class);
        Mockito.when(rms.processKey(keyMock)).thenAnswer(invocation -> MenuState.class);

        Assertions.assertEquals(MenuState.class, rms.run().getClass());
    }

    @Test
    public void processKey() throws IOException {
        MenuCommand keyMock = Mockito.mock(MenuCommand.class);

        Assertions.assertEquals(MenuState.class, rms.processKey(keyMock).getClass());
    }

}
