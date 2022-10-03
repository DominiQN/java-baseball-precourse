package baseball.ui.dto;

import baseball.domain.game.TrialResult;

public class GuessOutput {
    private final TrialResult trialResult;

    public GuessOutput(TrialResult result) {
        this.trialResult = result;
    }

    public int getStrikeCount() {
        return trialResult.getStrikeCount();
    }

    public int getBallCount() {
        return trialResult.getBallCount();
    }

    public boolean isNothing() {
        return trialResult.isNothing();
    }

    public boolean isCorrect() {
        return trialResult.isCorrect();
    }
}
