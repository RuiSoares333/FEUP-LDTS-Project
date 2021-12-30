package control.menu;

import control.MenuCommand;
import model.Menu.MenuModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class MenuControllerTest {

    MenuController mc;

    @BeforeEach
    public void initMenuController() throws IOException, InterruptedException {
        try {
            mc = new MenuController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyExit() throws IOException, InterruptedException {
        MenuModel modelMock = Mockito.mock(MenuModel.class);
        MenuCommand keyMock = Mockito.mock(MenuCommand.class);

        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.QUIT);
        mc.processKey(modelMock, keyMock);

        Assertions.assertFalse(mc.running);
    }

    @Test
    public void processKeyUp() throws IOException, InterruptedException {
        MenuCommand keyMock = Mockito.mock(MenuCommand.class);

        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.UP);
        mc.processKey(mc.model, keyMock);

        Assertions.assertTrue(mc.running);
        Assertions.assertEquals(MenuModel.Opcao.EXIT, mc.model.getSelected());
    }

    @Test
    public void processKeyDown() throws IOException, InterruptedException {
        MenuCommand keyMock = Mockito.mock(MenuCommand.class);

        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.DOWN);
        mc.processKey(mc.model, keyMock);

        Assertions.assertTrue(mc.running);
        Assertions.assertEquals(MenuModel.Opcao.SETT, mc.model.getSelected());
    }

    @Test
    public void getPosition(){
        Assertions.assertEquals(10, mc.getPosition(MenuModel.Opcao.PLAY));
        Assertions.assertEquals(12, mc.getPosition(MenuModel.Opcao.SETT));
        Assertions.assertEquals(14, mc.getPosition(MenuModel.Opcao.RANKS));
        Assertions.assertEquals(18, mc.getPosition(MenuModel.Opcao.EXIT));

    }
}
