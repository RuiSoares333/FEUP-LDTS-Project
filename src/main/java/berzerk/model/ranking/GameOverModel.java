package berzerk.model.ranking;

import berzerk.model.Model;

public class GameOverModel implements Model {

    private int score;
    Boolean isScoreHigh;

    public GameOverModel(int score, boolean isScoreHigh) {
        this.score = score;
        this.isScoreHigh = isScoreHigh;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
