package control.state;

import control.MenuCommand;
import model.Soldado;
import model.ranking.RankingMenuModel;
import view.View;

import java.io.IOException;

public class RankingMenuState extends ControllerState<RankingMenuModel>  {

    RankingMenuModel model;

    public RankingMenuState(FactoryState state, Soldado soldado, View view){
        super(state, soldado, view);
        model = (RankingMenuModel) view.getModel();
    }

    public ControllerState<?> run() throws IOException {
        view.draw(0);
        return processKey(view.getCommand());
    }

    public ControllerState<?> processKey(MenuCommand key) throws IOException {
        return manageCommand(state.genMenuState(soldado));
    }


}
