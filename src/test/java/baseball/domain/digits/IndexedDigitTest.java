package baseball.domain.digits;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IndexedDigitTest {
    @DisplayName("숫자와 위치가 같으면 동일하다.")
    @Test
    void equality() {
        final IndexedDigit one = new IndexedDigit(new Digit(1), 2);
        final IndexedDigit another = new IndexedDigit(new Digit(1), 2);

        assertThat(one).isEqualTo(another);
    }
}
