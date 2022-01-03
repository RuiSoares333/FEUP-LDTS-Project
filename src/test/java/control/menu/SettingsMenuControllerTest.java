//package control.menu;
//
//import control.MenuCommand;
//import model.settings.SettingsModel;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.io.IOException;
//
//public class SettingsMenuControllerTest {
//
//    SettingsMenuController smc;
//
//    @BeforeEach
//    public void initSMC() throws IOException {
//        smc = new SettingsMenuController(new SettingsModel());
//    }
//
//    @Test
//    public void processKeyLeft(){
//        MenuCommand keyMock = Mockito.mock(MenuCommand.class);
//
//        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.LEFT);
//        smc.processKey(smc.settingsModel, keyMock);
//
//        Assertions.assertTrue(smc.running);
//        Assertions.assertEquals(SettingsModel.Heroi.EXPERT, smc.settingsModel.getSelected());
//    }
//
//    @Test
//    public void processKeyRight(){
//        MenuCommand keyMock = Mockito.mock(MenuCommand.class);
//
//        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.RIGHT);
//        smc.processKey(smc.settingsModel, keyMock);
//
//        Assertions.assertTrue(smc.running);
//        Assertions.assertEquals(SettingsModel.Heroi.TANKY, smc.settingsModel.getSelected());
//    }
//
//    @Test
//    public void processKeySelect(){
//        MenuCommand keyMock = Mockito.mock(MenuCommand.class);
//
//        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.SELECT);
//        smc.processKey(smc.settingsModel, keyMock);
//
//        Assertions.assertFalse(smc.running);
//        Assertions.assertEquals(SettingsModel.Heroi.RECRUIT, smc.settingsModel.getSelected());
//    }
//
//    @Test
//    public void processKeyExit(){
//        MenuCommand keyMock = Mockito.mock(MenuCommand.class);
//
//        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.QUIT);
//        smc.processKey(smc.settingsModel, keyMock);
//
//        Assertions.assertFalse(smc.running);
//        Assertions.assertEquals(SettingsModel.Heroi.RECRUIT, smc.settingsModel.getSelected());
//    }
//
//    @Test
//    public void getPosition(){
//        Assertions.assertEquals(smc.getPosition(SettingsModel.Heroi.RECRUIT), 112);
//        Assertions.assertEquals(smc.getPosition(SettingsModel.Heroi.TANKY), 336);
//        Assertions.assertEquals(smc.getPosition(SettingsModel.Heroi.EXPERT), 560);
//    }
//}
