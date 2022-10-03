package baseball.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import baseball.domain.digits.Digits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrialResultTest {
    @DisplayName("시도결과는 정답여부, 낫싱여부, 스트라이크 개수, 볼 개수로 생성된다.")
    @Test
    void construct() {
        final boolean isCorrect = true;
        final int strikeCount = 3;
        final int ballCount = 0;
        final boolean isNothing = false;
        final TrialResult result = new TrialResult(isCorrect, strikeCount, ballCount, isNothing);

        assertAll(
                () -> assertThat(result.isCorrect()).isEqualTo(true),
                () -> assertThat(result.getStrikeCount()).isEqualTo(3),
                () -> assertThat(result.getBallCount()).isEqualTo(0),
                () -> assertThat(result.isNothing()).isEqualTo(false)
        );
    }

    @Test
    void equality() {
        final TrialResult one = new TrialResult(false, 0, 0, true);
        final TrialResult another = new TrialResult(false, 0, 0, true);

        assertThat(one).isEqualTo(another);
    }

    @Test
    void createByGameState() {
        final GameState gameState = createGameState(false, 2, 1, false);
        final TrialResult expected = new TrialResult(false, 2, 1, false);

        final TrialResult actual = new TrialResult(gameState);

        assertThat(actual).isEqualTo(expected);
    }

    private GameState createGameState(boolean isCorrect, int strikeCount, int ballCount, boolean isNothing) {
        return new GameState() {
            @Override
            public boolean isCorrect() {
                return isCorrect;
            }

            @Override
            public boolean isNothing() {
                return isNothing;
            }

            @Override
            public int countStrikes() {
                return strikeCount;
            }

            @Override
            public int countBalls() {
                return ballCount;
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public GameState guess(Digits guessDigits) {
                return null;
            }

            @Override
            public GameState finish() {
                return null;
            }

            @Override
            public GameState startNewGame(Digits newSecretNumber) {
                return null;
            }
        };
    }
}
