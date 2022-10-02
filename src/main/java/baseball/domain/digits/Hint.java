package baseball.domain.digits;

public enum Hint {
    STRIKE,
    BALL,
    FOUL;

    public static Hint byIndexes(int guessDigitIndex, int realDigitIndex) {
        validateGuessDigitIndex(guessDigitIndex);

        if (realDigitIndex == guessDigitIndex) {
            return Hint.STRIKE;
        }

        if (realDigitIndex >= 0) {
            return Hint.BALL;
        }

        return Hint.FOUL;
    }

    private static void validateGuessDigitIndex(int guessDigitIndex) {
        if (guessDigitIndex < 0) {
            throw new IllegalArgumentException("Guess digit index must not be negative!");
        }
    }
}
