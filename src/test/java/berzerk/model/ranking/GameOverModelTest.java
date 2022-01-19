package berzerk.model.ranking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameOverModelTest {

    GameOverModel model;
    int score;

    @BeforeEach
    public void initGameOverModel(){
        score = 150;
        model = new GameOverModel(score);
    }

    @Test
    public void getScoreTest(){
        score = model.getScore();
        assertEquals(150, score);
    }

    @Test
    public void addCharacterTest(){
        Character ch = 'M';
        String expected;

        model.addCharacter(ch);
        expected = "M";

        assertEquals(expected, model.getName());

        ch = 'L';

        model.addCharacter(ch);
        expected = "ML";

        assertEquals(expected, model.getName());

        ch = 'A';

        model.addCharacter(ch);
        expected = "MLA";

        assertEquals(expected, model.getName());

        ch = 'K';

        model.addCharacter(ch);
        expected = "MLA";

        assertEquals(expected, model.getName());


    }

    @Test
    public void deleteLastCharacter(){
        String expected = "ML";
        String name = "MLA";

        model.setName(name);
        model.deleteLastCharacter();

        assertEquals(expected, model.getName());
    }

}
