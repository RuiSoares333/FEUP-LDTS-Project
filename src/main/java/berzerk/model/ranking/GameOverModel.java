package berzerk.model.ranking;

import berzerk.model.Model;

public class GameOverModel implements Model {

    int score;
    Boolean isScoreHigh;

    public GameOverModel(int score, boolean isScoreHigh) {
        this.score = score;
        this.isScoreHigh = isScoreHigh;
    }
}
