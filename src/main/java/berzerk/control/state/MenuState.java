package berzerk.control.state;

import berzerk.control.MenuCommand;
import berzerk.model.Game;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import berzerk.view.View;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class MenuState extends ControllerState<MenuModel> {

    MenuModel model;

    public MenuState(FactoryState state, Soldado soldado, View view){
        super(state, soldado, view);
        model = (MenuModel) view.getModel();
    }


    public ControllerState<?> run() throws IOException, URISyntaxException, FontFormatException {

        getView().draw(getPosition(model.getSelected()));
        return processKey(getView().getCommand());

    }


     public ControllerState<?> processKey(MenuCommand key) throws IOException, URISyntaxException, FontFormatException {
        ControllerState<?> newState = this;
        switch (key.getCommandEnum()) {
             case UP -> model.previousSelected();
             case DOWN -> model.nextSelected();
             case SELECT -> {
                 switch (model.getSelected()) {
                     case PLAY -> {
                         Game game = new Game();
                         game.run(getView().getEcra().getScreen());
                         System.out.println(getSoldado().getSelected());
                     }

                     case RANKS -> newState = getState().genRankingMenuState(getSoldado());

                     case SETT -> newState = getState().genSettingsMenuState(getSoldado());

                     case EXIT -> newState = null;

                 }
             }
             case QUIT -> newState = null;
         }
         manageCommand(newState);
         return newState;
     }

     public MenuModel getModel(){
        return model;
     }

    public int getPosition(MenuModel.Opcao selected){
        return switch (selected) {
            case SETT -> 12;
            case RANKS -> 14;
            case EXIT -> 18;
            default -> 10;
        };
    }

}