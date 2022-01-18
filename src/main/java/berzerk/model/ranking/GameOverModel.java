package berzerk.model.ranking;

import berzerk.model.Constants;
import berzerk.model.Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameOverModel implements Model {

    private int score;
    private String name;

    public GameOverModel(int score) {
        this.score = score;
        this.name = "";
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void addCharacter(Character ch){
        if(name.length() < Constants.MAX_NOME_JOGADOR && Character.isAlphabetic(ch)){
            name += Character.toUpperCase(ch);
        }
    }

    public void deleteLastCharacter(){
        if(name.length()>0){
            name = name.substring(0, name.length()-1);
        }
    }

    public void writeInFile(String name, int score){
        File log = new File("src/main/resources/ranking.txt");

        try{
            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(name + " " + score + "\n");
            bufferedWriter.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void saveScores() {
        writeInFile(name, score);
    }
}
