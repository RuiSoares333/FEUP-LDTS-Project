package view.menu;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import model.Constants;
import model.settings.SettingsModel;
import view.IndicadorView;
import view.View;

import java.io.IOException;

public class SettingsMenuView extends View<SettingsModel> {

    IndicadorView indicador;

    public SettingsMenuView(SettingsModel model) throws IOException {
        super(model);
        initScreen();
        indicador = new IndicadorView(18, 18, graphics);
    }


    public void graphicSettings(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH, Constants.HEIGHT), ' ');
    }

    @Override
    public void draw(int position) throws IOException {
        screen.clear();

        graphicSettings(graphics);
        drawHeader();
        drawNomeSoldados();
        drawRecruit();
        drawTanky();
        drawExpert();
        indicador.draw(position, 7);

        screen.refresh();
    }

    protected void drawHeader(){
        graphics.putString(40, 1, "CHOOSE YOUR HERO");
    }

    protected void drawRecruit() {
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
                graphics.fillRectangle(new TerminalPosition(x+15, y+8), new TerminalSize(1, 1), ' ');

            }
        }
    }

    protected void drawTanky(){

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
                graphics.fillRectangle(new TerminalPosition(x+40, y+8), new TerminalSize(1, 1), ' ');
            }
        }
    }

    protected void drawExpert(){

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
                graphics.fillRectangle(new TerminalPosition(x+64, y+8), new TerminalSize(1, 1), ' ');
            }
        }
    }


    protected void drawNomeSoldados() {
        graphics.putString(20, 30, "RECRUIT");
        graphics.putString(45, 30, "TANKY");
        graphics.putString(68, 30, "EXPERT");
    }



}
