package baseball.domain;

import java.util.Objects;

public class Digit {
    private final int value;

    public Digit(int value) {
        validateDigitRange(value);

        this.value = value;
    }

    private void validateDigitRange(int value) {
        if (value < 1 || value > 9) {
            throw new IllegalArgumentException("The number must be between 1 and 9!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Digit digit = (Digit) o;
        return value == digit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
