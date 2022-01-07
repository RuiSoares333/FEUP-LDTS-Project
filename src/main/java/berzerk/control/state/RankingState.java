package berzerk.control.state;

import berzerk.control.MenuCommand;
import berzerk.model.Soldado;
import berzerk.model.ranking.RankingModel;
import berzerk.view.View;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class RankingState extends ControllerState<RankingModel>  {

    RankingModel model;

    public RankingState(FactoryState state, Soldado soldado, View view){
        super(state, soldado, view);
        model = (RankingModel) view.getModel();
    }

    public ControllerState<?> run() throws IOException, URISyntaxException, FontFormatException {
        getView().draw(0);
        return processKey(getView().getCommand());
    }

    public ControllerState<?> processKey(MenuCommand key) throws IOException, URISyntaxException, FontFormatException {
        return manageCommand(getState().genMenuState(getSoldado()));
    }


}
