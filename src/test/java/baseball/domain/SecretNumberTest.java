package baseball.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayList;
import java.util.List;
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

    private Digits digitsOf(int... values) {
        final List<Digit> digitList = new ArrayList<>();
        for (int value : values) {
            final Digit digit = new Digit(value);
            digitList.add(digit);
        }
        return new Digits(digitList);
    }
}
