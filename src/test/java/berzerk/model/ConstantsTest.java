package berzerk.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantsTest {


    @Test
    public void gameName(){
        assertEquals(7, Constants.GAME_NAME.length);
    }

    @Test
    public void recruit(){
        assertEquals(16, Constants.RECRUTA_SIM.length);
        for(String s: Constants.RECRUTA_SIM){
            char[] caracteres = s.toCharArray();
            assertEquals(16, caracteres.length);
        }
    }

    @Test
    public void tanky(){
        assertEquals(16, Constants.TANKY_SIM.length);
        for(String s: Constants.TANKY_SIM){
            char[] caracteres = s.toCharArray();
            assertEquals(16, caracteres.length);
        }
    }

    @Test
    public void expert(){
        assertEquals(16, Constants.EXPERT_SIM.length);
        for(String s: Constants.EXPERT_SIM){
            char[] caracteres = s.toCharArray();
            assertEquals(16, caracteres.length);
        }
    }

    @Test
    public void trophy(){
        assertEquals(16, Constants.TROPHY.length);
        for(String s: Constants.TROPHY){
            char[] caracteres = s.toCharArray();
            assertEquals(16, caracteres.length);
        }
    }

}
