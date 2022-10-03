package baseball.domain.game;

import static baseball.domain.digits.DigitsUtil.digitsOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertAll;

import baseball.domain.digits.Digits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StartedTest {
    private static final Digits SECRET_NUMBER = digitsOf(1, 2, 3);
    private final GameState started = new Started(SECRET_NUMBER);

    @DisplayName("비밀번호로 생성된다.")
    @Test
    void construct() {
        assertThatNoException()
                .isThrownBy(() -> new Started(SECRET_NUMBER));
    }

    @DisplayName("시작했기에 아직 맞추지 못했다.")
    @Test
    void correct() {
        assertThat(started.isCorrect()).isFalse();
    }

    @DisplayName("시작했기에 스트라이크 개수는 0이다.")
    @Test
    void countStrikes() {
        assertThat(started.countStrikes()).isZero();
    }

    @DisplayName("시작했기에 볼 개수는 0이다.")
    @Test
    void countBalls() {
        assertThat(started.countBalls()).isZero();
    }

    @DisplayName("시작했기에 낫싱이다.")
    @Test
    void isNothing() {
        assertThat(started.isNothing()).isTrue();
    }

    @DisplayName("종료되지 않았다.")
    @Test
    void isFinished() {
        assertThat(started.isFinished()).isFalse();
    }

    @DisplayName("비밀번호와 추측한 숫자들의 길이가 같아야 한다.")
    @Test
    void illegalGuessNumberSize() {
        final Digits guessDigits = digitsOf(1, 2, 3, 4);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> started.guess(guessDigits))
                .withMessage("Guess digits size must be equal to secret number size!");

    }

    @DisplayName("주어진 숫자가 숫자들에 없으면 낫싱, 포함되어 있지만 위치가 다르면 볼, 포함되어 있고 위치도 같으면 스트라이크다.")
    @ParameterizedTest(name = "비밀번호 123 일때, {0}{1}{2}를 입력하면 스트라이크: {3}, 볼: {4}, 낫싱: {5}, 맞춤: {6}이다.")
    @CsvSource({
            "1,2,3, 3,0,false, true",
            "4,5,6, 0,0,true, false",
            "1,3,5, 1,1,false, false"
    })
    void guess(
            int guess1st,
            int guess2nd,
            int guess3rd,
            int expectedStrikes,
            int expectedBalls,
            boolean expectedNothing,
            boolean expectedCorrect
    ) {
        final Digits guessDigits = digitsOf(guess1st, guess2nd, guess3rd);

        final GameState nextState = started.guess(guessDigits);

        assertAll(
                () -> assertThat(nextState.countStrikes()).isEqualTo(expectedStrikes),
                () -> assertThat(nextState.countBalls()).isEqualTo(expectedBalls),
                () -> assertThat(nextState.isNothing()).isEqualTo(expectedNothing),
                () -> assertThat(nextState.isCorrect()).isEqualTo(expectedCorrect)
        );
    }
}
