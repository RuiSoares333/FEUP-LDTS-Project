package berzerk.model;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.Test;

import static berzerk.model.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantsTest {


    @Test
    public void gameName(){
        assertEquals(7, GAME_NAME.length);
    }

    @Test
    public void recruit(){
        assertEquals(16, RECRUTA_SIM.length);
        for(String s: RECRUTA_SIM){
            char[] caracteres = s.toCharArray();
            assertEquals(16, caracteres.length);
        }
    }

    @Test
    public void tanky(){
        assertEquals(16, TANKY_SIM.length);
        for(String s: TANKY_SIM){
            char[] caracteres = s.toCharArray();
            assertEquals(16, caracteres.length);
        }
    }

    @Test
    public void expert(){
        assertEquals(16, EXPERT_SIM.length);
        for(String s: EXPERT_SIM){
            char[] caracteres = s.toCharArray();
            assertEquals(16, caracteres.length);
        }
    }

    @Test
    public void trophy(){
        assertEquals(16, TROPHY.length);
        for(String s: TROPHY){
            char[] caracteres = s.toCharArray();
            assertEquals(16, caracteres.length);
        }
    }

    @Test
    public void getHeroColorTest(){
        assertEquals(TextColor.Factory.fromString(RECRUIT_COLOR), getHeroColor("Recruit"));
        assertEquals(TextColor.Factory.fromString(TANKY_COLOR), getHeroColor("Tanky"));
        assertEquals(TextColor.Factory.fromString(EXPERT_COLOR), getHeroColor("Expert"));
        assertEquals(TextColor.Factory.fromString("#FFFF33"), getHeroColor("kekw"));
        assertEquals(TextColor.Factory.fromString("#FFFF33"), getHeroColor(null));

    }

}
