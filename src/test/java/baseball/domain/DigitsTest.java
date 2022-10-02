package baseball.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class DigitsTest {
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
}
