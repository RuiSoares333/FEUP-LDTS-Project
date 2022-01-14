package berzerk.model.ranking;

import berzerk.model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RankingModel implements Model {

    Map<String, Integer> jogadores;

    public RankingModel(){
        jogadores = populateMap();
    }

    public Map<String, Integer> populateMap(){
        Map<String, Integer> novosJogadores = new HashMap<>();

        BufferedReader reader = getReader();
        try {
            for (String line; (line = reader.readLine()) != null;) {
                String[] scorer = line.split(" ");
                novosJogadores.put(scorer[0], Integer.valueOf(scorer[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getOrderedMap(novosJogadores);
    }

    protected BufferedReader getReader(){
        InputStream is = ClassLoader.getSystemResourceAsStream("ranking.txt");
        assert is != null;
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(streamReader);
    }

    public LinkedHashMap<String, Integer> getOrderedMap(Map<String, Integer> novosJogadores){
        LinkedHashMap<String, Integer> jogadoresOrdenados = new LinkedHashMap<>();
        novosJogadores.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> jogadoresOrdenados.put(x.getKey(), x.getValue()));
        return jogadoresOrdenados;
    }

    public Map<String, Integer> getJogadores() {
        return jogadores;
    }
}
