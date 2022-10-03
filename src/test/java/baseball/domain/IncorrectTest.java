package baseball.domain;

import static baseball.domain.digits.DigitsUtil.digitsOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import baseball.domain.digits.Digits;
import baseball.domain.digits.GuessAnswer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IncorrectTest {
    private static final Digits SECRET_NUMBER = digitsOf(1, 2, 3);
    private static final GuessAnswer PREV_GUESS_ANSWER = new GuessAnswer(3, 1, 2);
    private final GameState incorrect = new Incorrect(SECRET_NUMBER, PREV_GUESS_ANSWER);

    @DisplayName("비밀번호와 이전 추측결과로 생성된다.")
    @Test
    void construct() {
        assertThatNoException()
                .isThrownBy(() -> new Incorrect(SECRET_NUMBER, PREV_GUESS_ANSWER));
    }

    @DisplayName("이전 추측결과 맞추지 못했어야 한다.")
    @Test
    void illegalGuessAnswer() {
        final GuessAnswer prevGuessAnswer = new GuessAnswer(3, 3, 0);

        assertThatIllegalStateException()
                .isThrownBy(() -> new Incorrect(SECRET_NUMBER, prevGuessAnswer))
                .withMessage("Previous guess answer is correct!");
    }

    @DisplayName("맞추지 못했다.")
    @Test
    void correct() {
        assertThat(incorrect.isCorrect()).isFalse();
    }

    @DisplayName("스트라이크 개수는 이전 추측결과의 개수이다.")
    @Test
    void countStrikes() {
        assertThat(incorrect.countStrikes()).isEqualTo(1);
    }

    @DisplayName("볼 개수는 이전 추측결과의 개수이다.")
    @Test
    void countBalls() {
        assertThat(incorrect.countBalls()).isEqualTo(2);
    }

    @DisplayName("낫싱은 이전 추측결과로 정해진다.")
    @Test
    void isNothing() {
        assertThat(incorrect.isNothing()).isEqualTo(false);
    }

    @DisplayName("종료되지 않았다.")
    @Test
    void isFinished() {
        assertThat(incorrect.isFinished()).isFalse();
    }
}
