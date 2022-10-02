package baseball.domain.digits;

import java.util.Objects;

public class IndexedDigit {
    private final Digit digit;
    private final int index;

    public IndexedDigit(Digit digit, int index) {
        this.digit = digit;
        this.index = index;
    }

    public Digit getDigit() {
        return digit;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IndexedDigit that = (IndexedDigit) o;
        return index == that.index && digit.equals(that.digit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(digit, index);
    }
}
