package berzerk.model.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuModelTest {

    MenuModel model;

    @BeforeEach
    public void initModel(){
        model = new MenuModel();
    }

    @Test
    public void getPosicaoOpcao(){
        assertEquals(0, model.getPosicaoOpcao(MenuModel.Opcao.PLAY));
        assertEquals(1, model.getPosicaoOpcao(MenuModel.Opcao.SETT));
        assertEquals(2, model.getPosicaoOpcao(MenuModel.Opcao.RANKS));
        assertEquals(3, model.getPosicaoOpcao(MenuModel.Opcao.EXIT));
        assertEquals(-1, model.getPosicaoOpcao(null));
    }

    @Test
    public void nextSelected(){
        assertEquals(MenuModel.Opcao.PLAY, model.getSelected());
        model.nextSelected();

        assertEquals(MenuModel.Opcao.SETT, model.getSelected());
        model.nextSelected();

        assertEquals(MenuModel.Opcao.RANKS, model.getSelected());
        model.nextSelected();

        assertEquals(MenuModel.Opcao.EXIT, model.getSelected());
        model.nextSelected();

        assertEquals(MenuModel.Opcao.PLAY, model.getSelected());
    }

    @Test
    public void previousSelected(){
        assertEquals(MenuModel.Opcao.PLAY, model.getSelected());
        model.previousSelected();

        assertEquals(MenuModel.Opcao.EXIT, model.getSelected());
        model.previousSelected();

        assertEquals(MenuModel.Opcao.RANKS, model.getSelected());
        model.previousSelected();

        assertEquals(MenuModel.Opcao.SETT, model.getSelected());
        model.previousSelected();

        assertEquals(MenuModel.Opcao.PLAY, model.getSelected());
    }
}
