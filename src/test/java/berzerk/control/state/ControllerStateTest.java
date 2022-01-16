package berzerk.control.state;

import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.ranking.RankingModel;
import berzerk.view.menu.RankingView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ControllerStateTest {

    RankingState state;
    RankingModel model;
    RankingView view;
    FactoryState factoryState;
    Soldado soldado;
    Ecra ecra;

    @BeforeEach
    public void initMenuState() throws IOException {
        factoryState = mock(FactoryState.class);
        soldado = mock(Soldado.class);
        model = mock(RankingModel.class);
        ecra = mock(Ecra.class);
        view = mock(RankingView.class);
        state = spy(new RankingState(factoryState, soldado, view));
        doNothing().when(view).draw(anyInt());
    }


    @Test
    public void manageCommandSame(){
        try {
            state.manageCommand(state);
            verify(view, times(0)).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void manageCommandDifferent(){
        try {
            state.manageCommand(any(ControllerState.class));
            verify(view, atLeastOnce()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSoldado(){
        assertEquals(soldado, state.getSoldado());
    }

    @Test
    public void getView(){
        assertEquals(view, state.getView());
    }

    @Test
    public void getState(){
        assertEquals(factoryState, state.getState());
    }
}
