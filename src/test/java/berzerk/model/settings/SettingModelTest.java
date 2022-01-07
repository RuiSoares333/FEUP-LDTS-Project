package berzerk.model.settings;

import berzerk.model.Soldado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SettingModelTest {

    Soldado soldado;
    SettingsModel model;

    @BeforeEach
    public void initModel(){
        soldado = new Soldado();
        model = new SettingsModel(soldado);
    }

    @Test
    public void nextSelected(){
        assertEquals(Soldado.Heroi.RECRUIT, model.getSelected());

        model.nextSelected();
        assertEquals(Soldado.Heroi.TANKY, model.getSelected());

        model.nextSelected();
        assertEquals(Soldado.Heroi.EXPERT, model.getSelected());

        model.nextSelected();
        assertEquals(Soldado.Heroi.RECRUIT, model.getSelected());
    }

    @Test
    public void previousSelected(){
        assertEquals(Soldado.Heroi.RECRUIT, model.getSelected());

        model.previousSelected();
        assertEquals(Soldado.Heroi.EXPERT, model.getSelected());

        model.previousSelected();
        assertEquals(Soldado.Heroi.TANKY, model.getSelected());

        model.previousSelected();
        assertEquals(Soldado.Heroi.RECRUIT, model.getSelected());
    }
}
