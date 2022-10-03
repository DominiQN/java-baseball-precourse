package baseball.ui.dto;

import baseball.domain.digits.Digit;
import baseball.domain.digits.Digits;
import java.util.ArrayList;
import java.util.List;

public class GuessInput {
    private final Digits guessDigit;

    public GuessInput(List<Integer> digitIntegers) {
        final List<Digit> digitList = parseDigits(digitIntegers);

        this.guessDigit = new Digits(digitList);
    }

    private static List<Digit> parseDigits(List<Integer> digitIntegers) {
        final List<Digit> digitList = new ArrayList<>();
        for (int digitValue : digitIntegers) {
            final Digit digit = new Digit(digitValue);
            digitList.add(digit);
        }
        return digitList;
    }

    public Digits getGuessDigits() {
        return this.guessDigit;
    }
}
