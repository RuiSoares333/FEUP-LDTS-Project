package view.menu;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.Constants;
import view.Indicador;
import view.IndicadorBigView;

import java.io.IOException;

public class SettingsMenuView extends Menu{

    Indicador indicador;

    public SettingsMenuView(TextGraphics graphics){
        indicador = new IndicadorBigView(116, 100, graphics);
    }

    @Override
    public void draw(Screen screen, int position) throws IOException {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();

        graphicSettings(graphics);
        drawAll(graphics);

        indicador.draw(position, 38);
        screen.refresh();
    }


    public void graphicSettings(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(Constants.WIDTH*8, Constants.HEIGHT*8), ' ');
    }

    private void drawAll(TextGraphics graphics){
        drawHeader(graphics);
        drawExpertNome(graphics);
        drawTankyNome(graphics);
        drawRecruitNome(graphics);
        drawRecruit(graphics);
        drawTanky(graphics);
        drawExpert(graphics);
    }

    private void drawHeader(TextGraphics graphics){
        for (int y = 0; y < Constants.CHOOSE_YOUR_HERO.length; y++) {
            char[] blocos = Constants.CHOOSE_YOUR_HERO[y].toCharArray();
            for (int x = 0; x < blocos.length; x++) {
                if (blocos[x] == 'C') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#943167"));
                } else {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
                }
                graphics.fillRectangle(new TerminalPosition((x+28)*7+1, (y+1)*6), new TerminalSize(8, 5), ' ');
            }
        }
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
                graphics.fillRectangle(new TerminalPosition((x+16)*7+1, (y+7)*6), new TerminalSize(12, 6), ' ');

            }
        }
    }

    private void drawTanky(TextGraphics graphics){

        for(int y=0; y<Constants.TANKY_SIM.length; y++){
            char [] blocos = Constants.TANKY_SIM[y].toCharArray();
            for(int x=0; x<blocos.length; x++){
                switch (blocos[x]) {
                    case 'p' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                    case 'c' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#757575"));
                    case 'h' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#795548"));
                    case 's' -> graphics.setBackgroundColor(TextColor.Factory.fromString("#FFAB91"));
                    default -> graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
                }
                graphics.fillRectangle(new TerminalPosition((x+48)*7+1, (y+7)*6), new TerminalSize(12, 6), ' ');
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
                graphics.fillRectangle(new TerminalPosition((x+80)*7+1, (y+7)*6), new TerminalSize(12, 6), ' ');
            }
        }
    }


    private void drawRecruitNome(TextGraphics graphics) {
        for (int y = 0; y < Constants.RECRUTA_NOME.length; y++) {
            char[] blocos = Constants.RECRUTA_NOME[y].toCharArray();
            for (int x = 0; x < blocos.length; x++) {
                if (blocos[x] == 'r') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#943167"));
                } else {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
                }
                graphics.fillRectangle(new TerminalPosition((x+10)*7+1, (y+24)*6), new TerminalSize(8, 5), ' ');
            }
        }
    }

    private void drawTankyNome(TextGraphics graphics) {
        for (int y = 0; y < Constants.TANKY_NOME.length; y++) {
            char[] blocos = Constants.TANKY_NOME[y].toCharArray();
            for (int x = 0; x < blocos.length; x++) {
                if (blocos[x] == 'T') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#943167"));
                } else {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
                }
                graphics.fillRectangle(new TerminalPosition((x+47)*7+1, (y+24)*6), new TerminalSize(8, 5), ' ');
            }
        }
    }


    private void drawExpertNome(TextGraphics graphics) {
        for (int y = 0; y < Constants.EXPERT_NOME.length; y++) {
            char[] blocos = Constants.EXPERT_NOME[y].toCharArray();
            for (int x = 0; x < blocos.length; x++) {
                if (blocos[x] == 'E') {
                    graphics.setBackgroundColor(TextColor.Factory.fromString("#943167"));
                } else {
                    graphics.setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
                }
                graphics.fillRectangle(new TerminalPosition((x+79)*7+1, (y+24)*6), new TerminalSize(8, 5), ' ');
            }
        }
    }
}
