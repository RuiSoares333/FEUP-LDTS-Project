package control.menu.state;

import control.MenuCommand;
import model.Menu.MenuModel;
import model.Soldado;
import view.View;

import java.io.IOException;



public class MenuState extends ControllerState<MenuModel> {

    MenuModel model;

    public MenuState(FactoryState state, Soldado soldado, View view){
        super(state, soldado, view);
        model = (MenuModel) view.getModel();
    }


    public ControllerState<?> run() throws IOException, InterruptedException {

        view.draw(getPosition(model.getSelected()));
        return processKey(model, view.getCommand());

    }


     public ControllerState<?> processKey(MenuModel model, MenuCommand key) throws IOException{
        ControllerState<?> newState = this;
        switch (key.getCommandEnum()) {
             case UP:
                 model.previousSelected();
                 break;
             case DOWN:
                 model.nextSelected();
                 break;
             case SELECT:
                 switch (model.getSelected()) {
                     case PLAY -> {
                         // newState = jogo
                         System.out.println(soldado.getSelected());
                     }

                     case RANKS -> newState = state.genRankingMenuState(soldado);

                     case SETT -> newState = state.genSettingsMenuState(soldado);

                     case EXIT -> newState = null;

                 }
                 break;
             case QUIT:
                 newState = null;
                 break;
         }
         manageCommand(newState);
         return newState;
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