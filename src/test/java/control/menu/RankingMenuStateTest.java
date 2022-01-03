package control.menu;

import control.MenuCommand;
import control.menu.state.FactoryState;
import control.menu.state.MenuState;
import control.menu.state.RankingMenuState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;


public class RankingMenuStateTest {

    RankingMenuState ms;
    FactoryState factoryState;

    @BeforeEach
    public void initMenuController(){
        try {
            factoryState = Mockito.spy(FactoryState.class);
            ms = Mockito.spy(factoryState.genRankingMenuState(Mockito.any()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void run() throws IOException {
        MenuCommand keyMock = Mockito.mock(MenuCommand.class);
        Mockito.when(ms.processKey(keyMock)).thenAnswer(invocation -> MenuState.class);

        Assertions.assertEquals(MenuState.class, ms.run().getClass());
    }

    @Test
    public void processKey() throws IOException {
        MenuCommand keyMock = Mockito.mock(MenuCommand.class);

        Assertions.assertEquals(MenuState.class, ms.processKey(keyMock).getClass());
    }

}
