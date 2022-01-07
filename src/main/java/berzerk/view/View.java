package berzerk.view;

import berzerk.control.MenuCommand;
import berzerk.model.Constants;
import berzerk.model.Ecra;
import berzerk.model.Model;
import com.googlecode.lanterna.TextColor;

import java.io.IOException;

public abstract class View<T extends Model> {

    private T model;
    private Ecra ecra;


    public View(T model, Ecra ecra){
        this.model = model;
        this.ecra = ecra;
        this.ecra.getGraphics().setBackgroundColor(TextColor.Factory.fromString(Constants.MENU_BACKGROUND_COLOR));
        this.ecra.getGraphics().setForegroundColor(TextColor.Factory.fromString(Constants.MENU_LETTER_COLOR));
    }

    public MenuCommand getCommand() throws IOException {
        return new MenuCommand().getCommand(ecra.getScreen().readInput());
    }

    public void setModel(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public void setEcra(Ecra ecra){
        this.ecra = ecra;
    }

    public Ecra getEcra(){
        return ecra;
    }

    public void close() throws IOException {
        ecra.getScreen().close();
    }

    public abstract void draw(int position) throws IOException;

}