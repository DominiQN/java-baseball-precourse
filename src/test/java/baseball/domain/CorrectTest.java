package baseball.domain;

import static baseball.domain.digits.DigitsUtil.digitsOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import baseball.domain.digits.Digits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CorrectTest {
    private static final Digits SECRET_NUMBER = digitsOf(1, 2, 3);
    private final GameState correct = new Correct(SECRET_NUMBER);

    @DisplayName("비밀번호로 생성된다.")
    @Test
    void construct() {
        final Digits secretNumber = digitsOf(1, 2, 3);
        assertThatNoException()
                .isThrownBy(() -> new Correct(secretNumber));
    }

    @DisplayName("맞췄다.")
    @Test
    void correct() {
        assertThat(correct.isCorrect()).isTrue();
    }

    @DisplayName("맞췄기에 비밀번호 길이만큼 스트라이크이다.")
    @Test
    void countStrikes() {
        assertThat(correct.countStrikes()).isEqualTo(3);
    }

    @DisplayName("맞췄기에 볼 개수는 0이다.")
    @Test
    void countBalls() {
        assertThat(correct.countBalls()).isZero();
    }

    @DisplayName("맞췄기에 낫싱이 아니다.")
    @Test
    void isNothing() {
        assertThat(correct.isNothing()).isFalse();
    }

    @DisplayName("종료되지 않았다.")
    @Test
    void isFinished() {
        assertThat(correct.isFinished()).isFalse();
    }

    @DisplayName("맞췄기에 더 이상 추측할 수 없다.")
    @Test
    void guess() {
        final Digits guessDigits = digitsOf(1, 2, 3);
        assertThatIllegalStateException()
                .isThrownBy(() -> correct.guess(guessDigits))
                .withMessage("The secret number is successfully guessed!");
    }
}
