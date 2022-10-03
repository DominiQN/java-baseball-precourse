package baseball.domain;

import static baseball.domain.digits.DigitsUtil.digitsOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import baseball.domain.digits.Digits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {
    private final GameState finished = new Finished();

    @DisplayName("종료되었기에 맞췄는지 조회할 수 없다.")
    @Test
    void correct() {
        assertThatIllegalStateException()
                .isThrownBy(() -> finished.isCorrect())
                .withMessage("The game is finished.");
    }

    @DisplayName("종료되었기에 스트라이크 개수를 조회할 수 없다.")
    @Test
    void countStrikes() {
        assertThatIllegalStateException()
                .isThrownBy(() -> finished.countStrikes())
                .withMessage("The game is finished.");
    }

    @DisplayName("종료되었기에 볼 개수를 조회할 수 없다.")
    @Test
    void countBalls() {
        assertThatIllegalStateException()
                .isThrownBy(() -> finished.countBalls())
                .withMessage("The game is finished.");
    }

    @DisplayName("종료되었기에 낫싱 여부를 조회할 수 없다.")
    @Test
    void isNothing() {
        assertThatIllegalStateException()
                .isThrownBy(() -> finished.isNothing())
                .withMessage("The game is finished.");
    }

    @DisplayName("종료되었기에 추측할 수 없다.")
    @Test
    void guess() {
        final Digits guessDigits = digitsOf(1, 2, 3);
        assertThatIllegalStateException()
                .isThrownBy(() -> finished.guess(guessDigits))
                .withMessage("The game is finished.");
    }

    @DisplayName("종료되었다.")
    @Test
    void isFinished() {
        assertThat(finished.isFinished()).isTrue();
    }
}
