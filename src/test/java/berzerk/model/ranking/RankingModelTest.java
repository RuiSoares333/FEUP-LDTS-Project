package berzerk.model.ranking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RankingModelTest {

    RankingModel model;

    @BeforeEach
    public void initModel(){
        model = new RankingModel();
    }

    @Test
    public void getReader() {
        assertNotNull(model.getReader());
    }

    @Test
    public void getOrderedMap() {
        Map<String, Integer> map = new HashMap<>();
        assertNotNull(model.getOrderedMap(map));
        assertEquals(0, model.getOrderedMap(map).size());


        map.put("A", 500);
        map.put("B", 1000);
        map.put("C", 100);
        map.put("D", 1500);
        assertNotNull(model.getOrderedMap(map));
        map = model.getOrderedMap(map);
        assertEquals(4, map.size());
    }

    @Test
    public void getJogadores(){
        assertNotNull(model.getJogadores());
    }
}
