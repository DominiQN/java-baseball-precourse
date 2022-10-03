package baseball.domain.game;

import java.util.Objects;

public class TrialResult {
    private final boolean isCorrect;
    private final int strikeCount;
    private final int ballCount;
    private final boolean isNothing;

    public TrialResult(boolean isCorrect, int strikeCount, int ballCount, boolean isNothing) {
        this.isCorrect = isCorrect;
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
        this.isNothing = isNothing;
    }

    public TrialResult(GameState state) {
        this(state.isCorrect(), state.countStrikes(), state.countBalls(), state.isNothing());
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public boolean isNothing() {
        return isNothing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrialResult that = (TrialResult) o;
        return isCorrect == that.isCorrect && strikeCount == that.strikeCount && ballCount == that.ballCount
                && isNothing == that.isNothing;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isCorrect, strikeCount, ballCount, isNothing);
    }
}
