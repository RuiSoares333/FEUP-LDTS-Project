package berzerk.control.state;

import berzerk.control.MenuCommand;
import berzerk.model.Game;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import berzerk.view.View;

import java.io.IOException;


public class MenuState extends ControllerState<MenuModel> {

    private final MenuModel model;

    public MenuState(FactoryState state, Soldado soldado, View view){
        super(state, soldado, view);
        model = (MenuModel) view.getModel();
    }


    public ControllerState<?> run() throws IOException {
        if(model!=null) getView().draw(getPosition(model.getSelected()));
        MenuCommand.COMMAND key = getView().getCommand();
        return processKey(key);
    }


     private ControllerState<?> processKey(MenuCommand.COMMAND key) throws IOException{
        if(key != null && model!=null) {
            ControllerState<?> newState = this;
            switch (key) {
                case UP -> model.previousSelected();
                case DOWN -> model.nextSelected();
                case SELECT -> {
                    if (model.getSelected() != null) {
                        switch (model.getSelected()) {
                            case PLAY -> {
                                Game game = new Game();
                                game.run(getView().getScreen());
                                System.out.println(getSoldado().getSelected());
                            }

                            case RANKS -> newState = getState().genRankingMenuState(getSoldado());

                            case SETT -> newState = getState().genSettingsMenuState(getSoldado());

                            case EXIT -> newState = null;
                        }
                    }
                }
                case QUIT -> newState = null;
            }
            manageCommand(newState);
            return newState;
        }

        return null;
     }

     public MenuModel getModel(){
        return model;
     }

    private int getPosition(MenuModel.Opcao selected){
        if(selected != null)
        return switch (selected) {
            case SETT -> 12;
            case RANKS -> 14;
            case EXIT -> 18;
            default -> 10;
        };

        return 0;
    }

}