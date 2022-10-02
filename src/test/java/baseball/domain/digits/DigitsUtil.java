package baseball.domain.digits;

import java.util.ArrayList;
import java.util.List;

public class DigitsUtil {
    static Digits digitsOf(int... values) {
        final List<Digit> digitList = new ArrayList<>();
        for (int value : values) {
            final Digit digit = new Digit(value);
            digitList.add(digit);
        }
        return new Digits(digitList);
    }
}
