package control.menu;

import com.googlecode.lanterna.screen.Screen;
import control.MenuCommand;
import model.Menu.MenuModel;
import view.menu.RankingMenuView;

import java.io.IOException;

public class RankingMenuController extends Controller{

    private final Screen screen;
    private final RankingMenuView rankingMenuView = new RankingMenuView();

    public RankingMenuController(Screen screen){
        this.screen = screen;
    }

    public void run() throws IOException {
        rankingMenuView.draw(screen,0);
        processKey(null, command.getCommand(screen.readInput()));
    }

    @Override
    void processKey(MenuModel model, MenuCommand key){

    }

    @Override
    Screen initScreen(){
        return null;
    }

}
