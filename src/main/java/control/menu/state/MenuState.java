package control.menu.state;

import control.MenuCommand;
import model.Menu.MenuModel;
import view.menu.MenuView;

import java.io.IOException;



public class MenuState extends ControllerState<MenuModel> {

    MenuModel model = new MenuModel();
    MenuView view = new MenuView(model);

    public MenuState(FactoryState state) throws IOException {
        super(state);
    }


    public ControllerState<?> run() throws IOException, InterruptedException {

        view.draw(getPosition(model.getSelected()));
        return processKey(model, view.getCommand());

    }


     public ControllerState<?> processKey(MenuModel model, MenuCommand key) throws IOException, InterruptedException {
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
//                         System.out.println(settingsModel.getSelected());
                         // newState = jogo
//                         screen.close();
                     }

                     case RANKS -> newState = state.genRankingMenuState();

//                     case SETT -> newState = state.genSettingsMenuState(settingsModel);

                     case EXIT -> newState = null;

                 }
                 break;
             case QUIT:
                 newState = null;
                 break;
         }
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