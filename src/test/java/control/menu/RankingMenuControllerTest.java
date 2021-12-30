package control.menu;

import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RankingMenuControllerTest {

    RankingMenuController rmc;

    @BeforeEach
    public void initRankingMenuController() {
        rmc = new RankingMenuController(Mockito.mock(Screen.class));
    }

    @Test
    public void initScreen(){
        Assertions.assertNull(rmc.initScreen());
    }

}
