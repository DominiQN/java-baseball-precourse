package baseball.ui;

import static baseball.domain.digits.DigitsUtil.digitsOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import baseball.domain.digits.Digits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DigitsSplitterTest {
    @DisplayName("양수여야 한다.")
    @ParameterizedTest(name = "{0}은 양수가 아니다.")
    @ValueSource(ints = {0, -1, -312})
    void positive(int value) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> DigitsSplitter.split(value))
                .withMessage("The value must be positive!");
    }

    @DisplayName("자리를 기준으로 분리된다.")
    @Test
    void split() {
        final Digits result = DigitsSplitter.split(134);
        assertThat(result).isEqualTo(digitsOf(1, 3, 4));
    }
}
