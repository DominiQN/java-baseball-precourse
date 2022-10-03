package baseball.domain.digits;

import static baseball.domain.digits.DigitsUtil.digitsOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DigitsTest {
    @DisplayName("숫자들 내에 중복된 숫자가 없어야 한다.")
    @Test
    void duplicates() {
        final List<Digit> digitList = Arrays.asList(
                new Digit(1),
                new Digit(1),
                new Digit(2)
        );

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Digits(digitList))
                .withMessage("Duplicate number exists!!");
    }

    @DisplayName("숫자들은 숫자 리스트로 생성된다.")
    @Test
    void create() {
        final List<Digit> digitList = Arrays.asList(
                new Digit(1),
                new Digit(2),
                new Digit(3)
        );
        assertThatNoException()
                .isThrownBy(() -> new Digits(digitList));
    }

    @DisplayName("숫자들에 포함된 숫자 개수를 알 수 있다.")
    @Test
    void size() {
        final List<Digit> digitList = Arrays.asList(
                new Digit(3),
                new Digit(4),
                new Digit(5)
        );
        final Digits digits = digitsOf(3, 4, 5);

        assertThat(digits.size()).isEqualTo(digitList.size());
    }

    @Test
    void equality() {
        final List<Digit> oneList = Arrays.asList(
                new Digit(1),
                new Digit(3),
                new Digit(5)
        );
        final Digits one = new Digits(oneList);
        final List<Digit> anotherList = Arrays.asList(
                new Digit(1),
                new Digit(3),
                new Digit(5)
        );
        final Digits another = new Digits(oneList);

        assertThat(one).isEqualTo(another);
    }

    @DisplayName("숫자들은 비어 있지 않아야 한다.")
    @Test
    void notEmpty() {
        final List<Digit> empty = Collections.emptyList();
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Digits(empty))
                .withMessage("Digits must not be empty!");
    }

    @DisplayName("추측 숫자들은 비밀번호와 길이가 동일해야 한다.")
    @Test
    void illegalGuessDigitsSize() {
        final Digits secretNumber = digitsOf(1, 2, 3);
        final Digits guessDigits = digitsOf(1, 2, 3, 4);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> secretNumber.guess(guessDigits))
                .withMessage("Guess digits size must be equal to secret number size!");
    }

    @DisplayName("주어진 숫자가 숫자들에 없으면 낫싱, 포함되어 있지만 위치가 다르면 볼, 포함되어 있고 위치도 같으면 스트라이크다.")
    @ParameterizedTest(name = "비밀번호 425 일때, {0}{1}{2}를 입력하면 스트라이크: {3}, 볼: {4}이다.")
    @CsvSource({
            "1,2,3, 1,0",
            "4,5,6, 1,1",
            "7,8,9, 0,0"
    })
    void hint(int guess1st, int guess2nd, int guess3rd, int expectedStrikes, int expectedBalls) {
        final Digits secret = digitsOf(4, 2, 5);
        final Digits guessDigits = digitsOf(guess1st, guess2nd, guess3rd);
        final GuessAnswer expectedAnswer = new GuessAnswer(secret.size(), expectedStrikes, expectedBalls);
        final GuessAnswer actualAnswer = secret.guess(guessDigits);

        assertThat(actualAnswer).isEqualTo(expectedAnswer);
    }
}
