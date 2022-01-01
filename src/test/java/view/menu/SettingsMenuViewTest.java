package view.menu;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class SettingsMenuViewTest {

    SettingsMenuView view;
    Screen screen;
    TextGraphics graphics;

    @BeforeEach
    public void initView() throws IOException {
        TerminalSize terminalSize = new TerminalSize(Constants.WIDTH, Constants.HEIGHT);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();

        screen = new TerminalScreen(terminal);
        graphics = screen.newTextGraphics();
        view = Mockito.spy(new SettingsMenuView(graphics));
    }

    @Test
    public void drawTest() throws IOException {
        view.draw(screen, 10);
        Mockito.verify(view, Mockito.times(1)).graphicSettings(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).drawHeader(Mockito.any());

        Mockito.verify(view, Mockito.times(1)).drawRecruit(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).drawRecruitNome(Mockito.any());

        Mockito.verify(view, Mockito.times(1)).drawTanky(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).drawTankyNome(Mockito.any());

        Mockito.verify(view, Mockito.times(1)).drawExpert(Mockito.any());
        Mockito.verify(view, Mockito.times(1)).drawExpertNome(Mockito.any());

        Mockito.verify(view, Mockito.times(1)).drawAll(Mockito.any());
    }
}
