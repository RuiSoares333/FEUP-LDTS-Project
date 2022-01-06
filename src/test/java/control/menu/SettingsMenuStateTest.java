package control.menu;

import control.MenuCommand;
import control.state.ControllerState;
import control.state.FactoryState;
import control.state.MenuState;
import control.state.SettingsMenuState;
import model.Soldado;
import model.settings.SettingsModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SettingsMenuStateTest {

    SettingsMenuState sms;
    FactoryState factoryState;

    @BeforeEach
    public void initSettingssMenuState(){
        try {
            Soldado soldado = new Soldado();
            factoryState = Mockito.spy(FactoryState.class);
            sms = Mockito.spy(factoryState.genSettingsMenuState(soldado));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyLeft() throws IOException {
        MenuCommand keyMock = Mockito.mock(MenuCommand.class);
        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.LEFT);

        SettingsModel model = sms.getModel();

        sms.processKey(keyMock);
        assertEquals(Soldado.Heroi.EXPERT, model.getSelected());

        sms.processKey(keyMock);
        assertEquals(Soldado.Heroi.TANKY, model.getSelected());
    }

    @Test
    public void processKeyRight() throws IOException {
        MenuCommand keyMock = Mockito.mock(MenuCommand.class);
        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.RIGHT);

        SettingsModel model = sms.getModel();

        sms.processKey(keyMock);
        assertEquals(Soldado.Heroi.TANKY, model.getSelected());

        sms.processKey(keyMock);
        assertEquals(Soldado.Heroi.EXPERT, model.getSelected());
    }

    @Test
    public void processKeySelect() throws IOException {
        MenuCommand keyMock = Mockito.mock(MenuCommand.class);
        Mockito.when(keyMock.getCommandEnum()).thenAnswer(invocation -> MenuCommand.COMMAND.SELECT);
        ControllerState<?> state = sms.processKey(keyMock);

        assertEquals(MenuState.class, state.getClass());
    }


    @Test
    public void getPosition(){
        Assertions.assertEquals(14, sms.getPosition(Soldado.Heroi.RECRUIT));
        Assertions.assertEquals(39, sms.getPosition(Soldado.Heroi.TANKY));
        Assertions.assertEquals(63, sms.getPosition(Soldado.Heroi.EXPERT));
    }
}
