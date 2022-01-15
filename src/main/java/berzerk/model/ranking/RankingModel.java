package berzerk.model.ranking;

import berzerk.model.Model;
//.javaimport berzerk.model.game.GameModel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RankingModel implements Model {

    Map<String, Integer> jogadores;
//    Scanner in = new Scanner(System.in);


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

//    public void writeScore(GameModel model){
//        try {
//            FileWriter myWriter = new FileWriter("ranking.txt");
//            System.out.println("Write your name: ");
//            String name = in.nextLine();
//            myWriter.write(name + " " + model.calculateTotalScore());
//            myWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
