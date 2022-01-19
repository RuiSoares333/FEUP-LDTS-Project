package berzerk.control;

import berzerk.control.state.FactoryState;
import berzerk.control.state.MenuState;
import berzerk.model.Soldado;
import berzerk.view.View;
import berzerk.view.menu.MenuView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ControllerTest {

    Controller controller;
    FactoryState factoryState;
    MenuState menuState;

    @BeforeEach
    public void initController(){
        factoryState = mock(FactoryState.class);
        menuState = new MenuState(factoryState, mock(Soldado.class), mock(MenuView.class));
        doReturn(menuState).when(factoryState).genMenuState(any(Soldado.class), any(MenuView.class));
        controller = spy(new Controller(factoryState));
    }

    @Test
    public void attributes(){
        assertNotNull(controller.soldado);
        assertNotNull(controller.state);
        assertEquals(MenuState.class, controller.state.getClass());
    }

    @Test
    public void isNull(){
        assertFalse(controller.isNull(new MenuState(mock(FactoryState.class), mock(Soldado.class), mock(View.class))));
        assertTrue(controller.isNull(null));
    }


    @Test
    public void run(){
        try {
            factoryState = mock(FactoryState.class);
            menuState = spy(new MenuState(factoryState, mock(Soldado.class), mock(MenuView.class)));
            doReturn(menuState).when(factoryState).genMenuState(any(Soldado.class), any(MenuView.class));
            doReturn(null).when(menuState).run();
            controller = spy(new Controller(factoryState));

            controller.run();

            assertNull(controller.state);
        } catch (IOException | URISyntaxException | FontFormatException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
