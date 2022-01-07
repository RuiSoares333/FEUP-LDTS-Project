package berzerk.control.state;

import berzerk.control.MenuCommand;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class MenuStateTest {

    MenuState ms;
    FactoryState factoryState;
    Soldado soldado;

    @BeforeEach
    public void initMenuState(){
        factoryState = mock(FactoryState.class);
        soldado = mock(Soldado.class);
        ms = factoryState.genMenuState(soldado);
    }

    @Test
    public void processKeyExit() throws IOException, URISyntaxException, FontFormatException {
        MenuCommand keyMock = mock(MenuCommand.class);
        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.QUIT);

        assertNull(ms.processKey(keyMock));
    }

    @Test
    public void processKeyUp() throws IOException, URISyntaxException, FontFormatException {
        MenuCommand keyMock = mock(MenuCommand.class);
        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.UP);

        MenuModel model = ms.getModel();

        ms.processKey(keyMock);
        assertEquals(MenuModel.Opcao.EXIT, model.getSelected());

        ms.processKey(keyMock);
        assertEquals(MenuModel.Opcao.RANKS, model.getSelected());
    }

    @Test
    public void processKeyDown() throws IOException, URISyntaxException, FontFormatException {
        MenuCommand keyMock = mock(MenuCommand.class);
        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.DOWN);

        MenuModel model = ms.getModel();

        ms.processKey(keyMock);
        assertEquals(MenuModel.Opcao.SETT, model.getSelected());
    }

    @Test
    public void selectSettings() throws IOException, URISyntaxException, FontFormatException {
        MenuCommand keyMock = mock(MenuCommand.class);
        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.DOWN);
        ms.processKey(keyMock);

        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.SELECT);
        ControllerState<?> state = ms.processKey(keyMock);

        assertEquals(SettingsState.class, state.getClass());
    }

    @Test
    public void selectRanking() throws IOException, URISyntaxException, FontFormatException {
        MenuCommand keyMock = mock(MenuCommand.class);
        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.DOWN);
        ms.processKey(keyMock);
        ms.processKey(keyMock);

        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.SELECT);
        ControllerState<?> state = ms.processKey(keyMock);

        assertEquals(RankingState.class, state.getClass());
    }

    @Test
    public void selectExit() throws IOException, URISyntaxException, FontFormatException {
        MenuCommand keyMock = mock(MenuCommand.class);
        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.UP);
        ms.processKey(keyMock);

        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.SELECT);

        assertNull(ms.processKey(keyMock));
    }

    @Test
    public void getPosition(){
        Assertions.assertEquals(10, ms.getPosition(MenuModel.Opcao.PLAY));
        Assertions.assertEquals(12, ms.getPosition(MenuModel.Opcao.SETT));
        Assertions.assertEquals(14, ms.getPosition(MenuModel.Opcao.RANKS));
        Assertions.assertEquals(18, ms.getPosition(MenuModel.Opcao.EXIT));
    }
}
