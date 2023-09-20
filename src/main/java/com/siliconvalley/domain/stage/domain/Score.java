package com.siliconvalley.domain.stage.domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Score {
    HIGH(3, score -> score != null && score >= 0.8f),
    MEDIUM(2, score -> score != null && score < 0.8f),
    LOW(1, score -> score == null);

    private final int scoreValue;
    private final Predicate<Float> scoreCondition;

    Score(int scoreValue, Predicate<Float> scoreCondition) {
        this.scoreValue = scoreValue;
        this.scoreCondition = scoreCondition;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public static Score determineScore(Float detectionScore) {
        return Arrays.stream(Score.values())
                .filter(detectionScoreEnum -> detectionScoreEnum.scoreCondition.test(detectionScore))
                .findFirst()
                .orElse(LOW);
    }
}
