package berzerk.view;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.menu.MenuModel;
import berzerk.view.menu.MenuView;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ViewTest {

    MenuModel model;
    Ecra ecra;
    Screen screen;
    TextGraphics graphics;
    Command command;
    View<MenuModel> view;

    @BeforeEach
    public void initView(){
        model = mock(MenuModel.class);
        ecra = mock(Ecra.class);
        screen = mock(Screen.class);
        graphics = mock(TextGraphics.class);
        command = mock(Command.class);
        when(ecra.getScreen()).thenReturn(screen);
        when(screen.newTextGraphics()).thenReturn(graphics);
        view = spy(new MenuView(model, ecra));
    }

    @Test
    public void graphicSettings(){
        verify(screen, atLeastOnce()).newTextGraphics();
        verify(graphics, atLeastOnce()).setBackgroundColor(any(TextColor.class));
        verify(graphics, atLeastOnce()).setForegroundColor(any(TextColor.class));
    }

    @Test
    public void getCommand(){
        try {
            KeyStroke a = mock(KeyStroke.class);
            when(screen.readInput()).thenReturn(a);
            doReturn(Command.COMMAND.DOWN).when(command).getCommand(a);

            assertEquals(Command.COMMAND.DOWN, view.getCommand(command));

            verify(command, atLeastOnce()).getCommand(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void attributes(){

        try {
            assertEquals(screen, view.getScreen());
            assertEquals(graphics, view.getGraphics());

            view.close();
            verify(ecra, atLeastOnce()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
