package baseball.domain.digits;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GuessAnswer {
    private final int secretNumberSize;
    private final int strikeCount;
    private final int ballCount;

    public GuessAnswer(int secretNumberSize, int strikeCount, int ballCount) {
        if (strikeCount + ballCount > secretNumberSize) {
            throw new IllegalStateException("Hint counts cannot be larger than secret number size!");
        }
        this.secretNumberSize = secretNumberSize;
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
    }

    public static GuessAnswer countByHint(List<Hint> hints) {
        final int secretNumberSize = hints.size();

        final Map<Hint, Integer> counting = new EnumMap<>(Hint.class);

        for (Hint hint : hints) {
            counting.merge(hint, 1, Integer::sum);
        }

        final int strikeCount = getHintCount(counting, Hint.STRIKE);
        final int ballCount = getHintCount(counting, Hint.BALL);

        return new GuessAnswer(secretNumberSize, strikeCount, ballCount);
    }

    private static int getHintCount(Map<Hint, Integer> counting, Hint hint) {
        return counting.getOrDefault(hint, 0);
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public boolean isNothing() {
        return strikeCount == 0 && ballCount == 0;
    }

    public boolean isCorrect() {
        return this.secretNumberSize == this.strikeCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GuessAnswer that = (GuessAnswer) o;
        return secretNumberSize == that.secretNumberSize
                && strikeCount == that.strikeCount
                && ballCount == that.ballCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(secretNumberSize, strikeCount, ballCount);
    }
}
