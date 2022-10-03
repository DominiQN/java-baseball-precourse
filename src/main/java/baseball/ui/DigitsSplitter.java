package baseball.ui;

import baseball.domain.digits.Digit;
import baseball.domain.digits.Digits;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DigitsSplitter {
    public static Digits split(int value) {
        requirePositive(value);
        final List<Integer> digitIntegers = splitDigits(value);
        final List<Digit> digits = mapToDigit(digitIntegers);
        return new Digits(digits);
    }

    private static void requirePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("The value must be positive!");
        }
    }

    private static List<Integer> splitDigits(int integer) {
        final List<Integer> digits = new ArrayList<>();
        while (integer > 0) {
            digits.add(integer % 10);
            integer /= 10;
        }
        Collections.reverse(digits);
        return digits;
    }

    private static List<Digit> mapToDigit(List<Integer> digitIntegers) {
        final List<Digit> result = new ArrayList<>();
        for (int digitInteger : digitIntegers) {
            final Digit digit = new Digit(digitInteger);
            result.add(digit);
        }
        return result;
    }
}
