package berzerk.model.ranking;

import berzerk.model.Model;

public class GameOverModel implements Model {

    private int score;

    public GameOverModel(int score) {
        this.score = score;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
