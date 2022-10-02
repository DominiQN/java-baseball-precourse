package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;

class IndexedDigitIteratorTest {
    @Test
    void iterator() {
        final List<Digit> digits = Arrays.asList(
                new Digit(2),
                new Digit(1),
                new Digit(4)
        );
        final Iterator<IndexedDigit> digitIterator = new IndexedDigitIterator(digits);

        assertAll(
                () -> assertThat(digitIterator).hasNext().isNotNull(),
                () -> assertThat(digitIterator).toIterable()
                        .containsExactly(
                                new IndexedDigit(new Digit(2), 0),
                                new IndexedDigit(new Digit(1), 1),
                                new IndexedDigit(new Digit(4), 2)
                        ),
                () -> assertThat(digitIterator).isExhausted()
        );
    }
}
