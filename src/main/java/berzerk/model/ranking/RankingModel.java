package berzerk.model.ranking;

import berzerk.model.Model;
//.javaimport berzerk.model.game.GameModel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
        try {
            FileReader fileWriter = new FileReader("src/main/resources/ranking.txt");
            return new BufferedReader(fileWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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
