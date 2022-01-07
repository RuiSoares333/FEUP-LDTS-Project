package berzerk.control.state;

import berzerk.control.MenuCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class RankingStateTest {

    RankingState rms;
    FactoryState factoryState;

    @BeforeEach
    public void initRankingMenuState(){
        factoryState = Mockito.spy(FactoryState.class);
        rms = Mockito.spy(factoryState.genRankingMenuState(Mockito.any()));
    }

    @Test
    public void run() throws IOException, URISyntaxException, FontFormatException {
        MenuCommand keyMock = Mockito.mock(MenuCommand.class);
        Mockito.when(rms.processKey(keyMock)).thenAnswer(invocation -> MenuState.class);

        Assertions.assertEquals(MenuState.class, rms.run().getClass());
    }

    @Test
    public void processKey() throws IOException, URISyntaxException, FontFormatException {
        MenuCommand keyMock = Mockito.mock(MenuCommand.class);

        Assertions.assertEquals(MenuState.class, rms.processKey(keyMock).getClass());
    }

}
