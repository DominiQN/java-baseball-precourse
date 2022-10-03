package baseball.domain;

import static baseball.domain.digits.DigitsUtil.digitsOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import baseball.domain.digits.Digits;
import baseball.domain.digits.GuessAnswer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CorrectTest {
    private static final GuessAnswer PREV_GUESS_ANSWER = new GuessAnswer(3, 3, 0);
    private final GameState correct = new Correct(PREV_GUESS_ANSWER);

    @DisplayName("이전 추측결과 맞췄어야 한다.")
    @Test
    void illegalGuessAnswer() {
        final GuessAnswer prevGuessAnswer = new GuessAnswer(3, 2, 1);

        assertThatIllegalStateException()
                .isThrownBy(() -> new Correct(prevGuessAnswer))
                .withMessage("Previous guess answer is not correct!");
    }

    @DisplayName("추측결과로 생성된다.")
    @Test
    void construct() {
        final GuessAnswer prevGuessAnswer = new GuessAnswer(3, 3, 0);
        assertThatNoException()
                .isThrownBy(() -> new Correct(prevGuessAnswer));
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
