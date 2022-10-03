package baseball.domain.game;

import static baseball.domain.digits.DigitsUtil.digitsOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import baseball.domain.SecretNumberGenerator;
import baseball.domain.digits.Digits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BaseballGameTest {
    private static final SecretNumberGenerator ALWAYS_123_GENERATOR = new Always123DigitsGenerator();
    private static final int SECRET_SIZE_3 = 3;

    @ParameterizedTest(name = "비밀번호는 1 ~ 8 사이의 길이만 가능하다. size: {0}")
    @ValueSource(ints = {-1, 0, 9})
    void illegalSecretNumberSize(int illegalSecretNumberSize) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BaseballGame(ALWAYS_123_GENERATOR, illegalSecretNumberSize))
                .withMessage("Secret number must be between 1 and 8!");
    }

    @DisplayName("생성 당시 주어진 비밀번호 길이만큼 비밀번호가 생성되어야 한다.")
    @Test
    void illegalSecretNumberState() {
        final int requestedSize = 4;
        assertThatIllegalStateException()
                .isThrownBy(() -> new BaseballGame(ALWAYS_123_GENERATOR, requestedSize))
                .withMessage("Requested size: 4, Generated size: 3");
    }

    @DisplayName("비밀번호생성기와 비밀번호 크기로 생성된다.")
    @Test
    void construct() {
        assertThatNoException()
                .isThrownBy(() -> new BaseballGame(ALWAYS_123_GENERATOR, SECRET_SIZE_3));
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
        final BaseballGame game = new BaseballGame(ALWAYS_123_GENERATOR, SECRET_SIZE_3);
        final Digits guessDigits = digitsOf(guess1st, guess2nd, guess3rd);
        final TrialResult expected = new TrialResult(
                expectedCorrect,
                expectedStrikes,
                expectedBalls,
                expectedNothing
        );

        final TrialResult actual = game.tryGuess(guessDigits);

        assertThat(actual).isEqualTo(expected);
    }
}
