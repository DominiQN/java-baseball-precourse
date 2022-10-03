package baseball.domain;

import static baseball.domain.digits.DigitsUtil.digitsOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import baseball.domain.digits.Digits;
import baseball.domain.digits.GuessAnswer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameSetTest {
    public static final Always123DigitsGenerator ALWAYS_123_GENERATOR = new Always123DigitsGenerator();
    private static final int SECRET_SIZE = 3;

    @DisplayName("게임세트는 비밀번호 길이와 셔플러로 생성된다.")
    @Test
    void construct() {
        assertThatNoException()
                .isThrownBy(() -> new GameSet(SECRET_SIZE, ALWAYS_123_GENERATOR));
    }

    @DisplayName("생성 시 원했던 비밀번호 길이와 실제 생성된 비밀번호의 길이가 같아야 한다.")
    @Test
    void illegalSecretNumberState() {
        final int secretNumberSize = 4;
        assertThatIllegalStateException()
                .isThrownBy(() -> new GameSet(secretNumberSize, ALWAYS_123_GENERATOR))
                .withMessage("Requested size: 4, Generated size: 3");
    }

    @DisplayName("비밀번호와 추측한 숫자들의 길이가 같아야 한다.")
    @Test
    void illegalGuessNumberSize() {
        final GameSet gameSet = new GameSet(SECRET_SIZE, ALWAYS_123_GENERATOR);
        final Digits guessDigits = digitsOf(1, 2, 3, 4);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> gameSet.guess(guessDigits))
                .withMessage("Guess digits size must be equal to secret number size!");

    }

    @DisplayName("주어진 숫자가 숫자들에 없으면 낫싱, 포함되어 있지만 위치가 다르면 볼, 포함되어 있고 위치도 같으면 스트라이크다.")
    @ParameterizedTest(name = "비밀번호 123 일때, {0}{1}{2}를 입력하면 스트라이크: {3}, 볼: {4}이다.")
    @CsvSource({
            "1,2,3, 3,0",
            "4,5,6, 0,0",
            "1,3,5, 1,1"
    })
    void hint(int guess1st, int guess2nd, int guess3rd, int expectedStrikes, int expectedBalls) {
        final GameSet gameSet = new GameSet(SECRET_SIZE, ALWAYS_123_GENERATOR);
        final Digits guessDigits = digitsOf(guess1st, guess2nd, guess3rd);
        final GuessAnswer expectedAnswer = new GuessAnswer(SECRET_SIZE, expectedStrikes, expectedBalls);
        final GuessAnswer actualAnswer = gameSet.guess(guessDigits);

        assertThat(actualAnswer).isEqualTo(expectedAnswer);
    }
}
