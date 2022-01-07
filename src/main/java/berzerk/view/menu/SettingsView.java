package berzerk.view.menu;

import berzerk.model.Ecra;
import berzerk.model.settings.SettingsModel;
import berzerk.view.IndicadorView;
import berzerk.view.View;
import com.googlecode.lanterna.graphics.TextGraphics;

public class SettingsView extends View<SettingsModel> {

    IndicadorView indicador;

    public SettingsView(SettingsModel model, Ecra ecra){
        super(model, ecra);
        if(getEcra().getScreen()!=null) indicador = new IndicadorView(18, 18, getEcra().getScreen().newTextGraphics());
    }

    @Override
    public void draw(int position){

    }

    private void drawHeader(TextGraphics graphics){

    }

    private void drawRecruit(TextGraphics graphics) {

    }

    private void drawTanky(TextGraphics graphics){

    }

    private void drawExpert(TextGraphics graphics){

    }


    private void drawNomeSoldados(TextGraphics graphics) {

    }


}
