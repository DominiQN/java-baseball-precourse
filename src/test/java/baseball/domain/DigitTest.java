package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DigitTest {
    @DisplayName("숫자는 값이 같으면 동일하다.")
    @Test
    void equals() {
        final Digit one = new Digit(1);
        final Digit another = new Digit(1);
        assertThat(one).isEqualTo(another);
    }

    @ParameterizedTest(name = "{0}은 1에서 9사이의 숫자다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void only1To9(int value) {
        assertThatNoException()
                .isThrownBy(() -> new Digit(value));
    }

    @ParameterizedTest(name = "{0}은 1에서 9사이의 숫자가 아니다.")
    @ValueSource(ints = {0, 10, -1})
    void invalidNumber(int value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Digit(value))
                .withMessage("The number must be between 1 and 9!");
    }
}