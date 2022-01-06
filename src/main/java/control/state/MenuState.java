package control.state;

import control.MenuCommand;
import model.Game;
import model.Soldado;
import model.menu.MenuModel;
import view.View;

import java.io.IOException;



public class MenuState extends ControllerState<MenuModel> {

    MenuModel model;

    public MenuState(FactoryState state, Soldado soldado, View view){
        super(state, soldado, view);
        model = (MenuModel) view.getModel();
    }


    public ControllerState<?> run() throws IOException{

        view.draw(getPosition(model.getSelected()));
        return processKey(view.getCommand());

    }


     public ControllerState<?> processKey(MenuCommand key) throws IOException{
        ControllerState<?> newState = this;
        switch (key.getCommandEnum()) {
             case UP -> model.previousSelected();
             case DOWN -> model.nextSelected();
             case SELECT -> {
                 switch (model.getSelected()) {
                     case PLAY -> {
                         Game game = new Game();
                         game.run(view.getScreen());
                         System.out.println(soldado.getSelected());
                     }

                     case RANKS -> newState = state.genRankingMenuState(soldado);

                     case SETT -> newState = state.genSettingsMenuState(soldado);

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