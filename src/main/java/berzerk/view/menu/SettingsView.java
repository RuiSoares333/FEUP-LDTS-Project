package berzerk.view.menu;

import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.settings.SettingsModel;
import berzerk.view.IndicadorView;
import berzerk.view.View;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class SettingsView extends View<SettingsModel> {

    IndicadorView indicador;

    public SettingsView(SettingsModel model, Ecra ecra){
        super(model, ecra);
        if(getScreen()!=null) indicador = new IndicadorView(18, 18, getScreen().newTextGraphics());
    }

    @Override
    public void draw(int position) throws IOException {
        if(getScreen()!=null && getGraphics()!=null) {
            getScreen().clear();

            TextGraphics graphics = getGraphics();

            getGraphics().fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');

            drawHeader(graphics);
            drawNomeSoldados(graphics);
            drawRecruit(graphics);
            drawTanky(graphics);
            drawExpert(graphics);
            if (indicador != null) indicador.draw(position, 7);

            getScreen().refresh();
        }
    }

    private void drawHeader(TextGraphics graphics){
        graphics.putString(40, 2, "CHOOSE YOUR HERO");
    }

    private void drawRecruit(TextGraphics graphics) {
        for (int y = 0; y < Constants.RECRUTA_SIM.length; y++) {
            char[] blocos = Constants.RECRUTA_SIM[y].toCharArray();
            for (int x = 0; x < blocos.length; x++) {
                switch (blocos[x]) {
                    case 'p' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                    case 'g' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#689f38"));
                    case 'v' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#558b2f"));
                    case 'V' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#33691e"));
                    case 'h' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#8d6e63"));
                    case 's' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#ffab91"));
                    case 'c' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#4e342e"));
                    case 'b' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
                    default -> graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
                }
                graphics.fillRectangle(getTerminalPositionRecruit(x, y), new TerminalSize(1, 1), ' ');
            }
        }
    }

    private void drawTanky(TextGraphics graphics){

        for(int y=0; y<Constants.TANKY_SIM.length; y++){
            char [] blocos = Constants.TANKY_SIM[y].toCharArray();
            for(int x=0; x<blocos.length; x++){
                switch (blocos[x]) {
                    case 'p' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                    case 'v' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#b02121"));
                    case 'V' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#781212"));
                    case 'a' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#48a7f0"));
                    case 'A' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#4b5bab"));
                    case 'c' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#5ecad6"));
                    default -> graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
                }
                graphics.fillRectangle(getTerminalPositionTanky(x, y), new TerminalSize(1, 1), ' ');
            }
        }
    }

    private void drawExpert(TextGraphics graphics){

        for(int y=0; y<Constants.EXPERT_SIM.length; y++){
            char [] blocos = Constants.EXPERT_SIM[y].toCharArray();
            for(int x=0; x<blocos.length; x++){
                switch (blocos[x]) {
                    case 'p' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                    case 'c' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#616161"));
                    case 'v' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#F44236"));
                    case 'l' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#FF5722"));
                    case 'r' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#E91E63"));
                    default -> graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
                }
                graphics.fillRectangle(getTerminalPositionExpert(x, y), new TerminalSize(1, 1), ' ');
            }
        }
    }


    private void drawNomeSoldados(TextGraphics graphics) {
        graphics.putString(20, 30, "RECRUIT");
        graphics.putString(45, 30, "TANKY");
        graphics.putString(68, 30, "EXPERT");
    }

    public TerminalPosition getTerminalPositionRecruit(int x, int y){
        return new TerminalPosition(x+15, y+8);
    }

    public TerminalPosition getTerminalPositionTanky(int x, int y){
        return new TerminalPosition(x+40, y+8);
    }

    public TerminalPosition getTerminalPositionExpert(int x, int y){
        return new TerminalPosition(x+64, y+8);
    }

}
