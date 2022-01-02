package control.menu.state;

import control.MenuCommand;
import model.ranking.RankingMenuModel;
import view.menu.RankingMenuView;

import java.io.IOException;

public class RankingMenuControllerState extends ControllerState<RankingMenuModel>  {

    RankingMenuModel model = new RankingMenuModel();
    RankingMenuView view = new RankingMenuView(model);

    public RankingMenuControllerState(FactoryState state) throws IOException {
        super(state);
    }

    public ControllerState<?> run() throws IOException {
        view.draw(0);
        return processKey(view.getCommand());
    }

    ControllerState<?> processKey(MenuCommand key) throws IOException {
        return state.genMenuState();
    }


}
