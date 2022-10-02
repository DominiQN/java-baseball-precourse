package baseball.domain;

public class SecretNumber {
    private final Digits secretDigits;

    public SecretNumber(Digits secretDigits) {
        this.secretDigits = secretDigits;
    }

    public void guess(Digits guessDigits) {
        validateDigitsSize(guessDigits);
    }

    private void validateDigitsSize(Digits guessDigits) {
        if (guessDigits.size() != secretDigits.size()) {
            throw new IllegalArgumentException("Guess digits size must be equal to secret number size!");
        }
    }
}
