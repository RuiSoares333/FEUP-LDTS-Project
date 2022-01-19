package berzerk.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoldadoTest {

    @Test
    public void toStringTest(){
        Soldado soldado = new Soldado();
        assertEquals("Recruit", soldado.toString());

        soldado.setSelected(Soldado.Heroi.TANKY);
        assertEquals("Tanky", soldado.toString());

        soldado.setSelected(Soldado.Heroi.EXPERT);
        assertEquals("Expert", soldado.toString());
    }
}
