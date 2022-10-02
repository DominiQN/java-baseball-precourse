package baseball.domain;

import static baseball.domain.DigitsUtil.digitsOf;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SecretNumberTest {
    @DisplayName("비밀번호는 숫자들로 생성된다.")
    @Test
    void constructor() {
        final Digits digits = digitsOf(1, 2, 3);

        assertThatNoException()
                .isThrownBy(() -> new SecretNumber(digits));
    }

    @DisplayName("추측 숫자들은 비밀번호와 길이가 동일해야 한다.")
    @Test
    void illegalGuessDigitsSize() {
        final Digits secretDigits = digitsOf(1, 2, 3);
        final SecretNumber secretNumber = new SecretNumber(secretDigits);
        final Digits guessDigits = digitsOf(1, 2, 3, 4);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> secretNumber.guess(guessDigits))
                .withMessage("Guess digits size must be equal to secret number size!");
    }
}
