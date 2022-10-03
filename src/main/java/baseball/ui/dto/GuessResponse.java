package baseball.ui.dto;

import baseball.domain.game.TrialResult;

public class GuessResponse {
    private final TrialResult trialResult;

    public GuessResponse(TrialResult result) {
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
