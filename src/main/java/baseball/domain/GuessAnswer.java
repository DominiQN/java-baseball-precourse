package baseball.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GuessAnswer {
    private final int strikeCount;
    private final int ballCount;

    public GuessAnswer(int strikeCount, int ballCount) {
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
    }

    public static GuessAnswer countByHint(List<Hint> hints) {
        final Map<Hint, Integer> counting = new EnumMap<>(Hint.class);

        for (Hint hint : hints) {
            counting.merge(hint, 1, Integer::sum);
        }

        final int strikeCount = getHintCount(counting, Hint.STRIKE);
        final int ballCount = getHintCount(counting, Hint.BALL);

        return new GuessAnswer(strikeCount, ballCount);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GuessAnswer that = (GuessAnswer) o;
        return strikeCount == that.strikeCount && ballCount == that.ballCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strikeCount, ballCount);
    }
}
