package baseball.ui.dto;

import baseball.domain.digits.Digits;

public class GuessInput {
    private final Digits guessDigit;

    public GuessInput(Digits digits) {
        this.guessDigit = digits;
    }

    public Digits getGuessDigits() {
        return this.guessDigit;
    }
}
