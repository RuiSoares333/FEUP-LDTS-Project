package berzerk.control.state;

import berzerk.control.Command;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import berzerk.model.ranking.RankingModel;
import berzerk.view.View;
import berzerk.view.menu.MenuView;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class RankingState extends ControllerState<RankingModel>  {

    protected final RankingModel model;

    public RankingState(FactoryState state, Soldado soldado, View<RankingModel> view){
        super(state, soldado, view);
        model = view.getModel();
    }

    @Override
    public ControllerState<?> run() throws IOException {
        view.draw(0);
        manageCommand(processKey(view.getCommand(new Command())));
        return getState().genMenuState(soldado, new MenuView(new MenuModel(), new Ecra()));
    }

    @Override
    public ControllerState<?> processKey(Command command) {
        return null;
    }


}
