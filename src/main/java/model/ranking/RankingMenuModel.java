package model.ranking;

import model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RankingMenuModel implements Model {

    Map<String, String> jogadores;

    public RankingMenuModel(){
        jogadores = populateMap();
    }

    protected Map<String, String> populateMap(){
        Map<String, String> novosJogadores = new HashMap<>();
        InputStream is = ClassLoader.getSystemResourceAsStream("ranking.txt");
        assert is != null;
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        try {
            for (String line; (line = reader.readLine()) != null;) {
                String[] scorer = line.split(" ");
                novosJogadores.put(scorer[0], scorer[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return novosJogadores;
    }

    public Map<String, String> getJogadores() {
        return jogadores;
    }
}
