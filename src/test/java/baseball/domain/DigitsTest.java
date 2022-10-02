package baseball.domain;

import static baseball.domain.DigitsUtil.digitsOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
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

    @DisplayName("추측하는 위치가 숫자들 길이를 벗어나지 않아야 한다.")
    @Test
    void illegalHintIndex() {
        final Digits digits = digitsOf(1, 2, 3);

        assertThatThrownBy(() -> digits.hintBy(5, new Digit(1)))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageMatching("Given digit index is out of bound: +\\w");
    }

    @DisplayName("주어진 숫자가 숫자들에 없으면 낫싱, 포함되어 있지만 위치가 다르면 볼, 포함되어 있고 위치도 같으면 스트라이크다.")
    @ParameterizedTest(name = "index {0}에 숫자 {1}이 있는지 추측하면, {2}를 힌트로 받는다.")
    @CsvSource({
            "0,1,STRIKE",
            "1,2,STRIKE",
            "2,3,STRIKE",
            "0,2,BALL",
            "0,4,FOUL"
    })
    void hint(int givenDigitIndex, int givenDigitValue, Hint expectedHint) {
        final Digits digits = digitsOf(1, 2, 3);
        final Digit givenDigit = new Digit(givenDigitValue);

        final Hint actualHint = digits.hintBy(givenDigitIndex, givenDigit);

        assertThat(actualHint).isEqualTo(expectedHint);
    }
}
