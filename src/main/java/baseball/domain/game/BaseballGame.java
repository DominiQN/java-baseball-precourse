package baseball.domain.game;

import baseball.domain.SecretNumberGenerator;
import baseball.domain.digits.Digits;

public class BaseballGame {
    public static final int MIN_SECRET_NUMBER_SIZE = 1;
    public static final int MAX_SECRET_NUMBER_SIZE = 8;
    private final SecretNumberGenerator secretNumberGenerator;
    private final int secretNumberSize;
    private GameState state;

    public BaseballGame(SecretNumberGenerator secretNumberGenerator, int secretNumberSize) {
        validateSecretNumberSize(secretNumberSize);

        this.secretNumberGenerator = secretNumberGenerator;
        this.secretNumberSize = secretNumberSize;
        this.state = new Started(generateSecretNumber());
    }

    private void validateSecretNumberSize(int secretNumberSize) {
        if (secretNumberSize < MIN_SECRET_NUMBER_SIZE || secretNumberSize > MAX_SECRET_NUMBER_SIZE) {
            throw new IllegalArgumentException("Secret number must be between 1 and 8!");
        }
    }

    private Digits generateSecretNumber() {
        final Digits secretNumber = secretNumberGenerator.generate(this.secretNumberSize);
        checkSecretNumber(secretNumber);
        return secretNumber;
    }

    private void checkSecretNumber(Digits secretNumber) {
        int generatedSize = secretNumber.size();
        if (generatedSize != this.secretNumberSize) {
            throw new IllegalStateException(
                    "Requested size: " + this.secretNumberSize + ", Generated size: " + generatedSize
            );
        }
    }

    public TrialResult tryGuess(Digits guessDigits) {
        this.state = state.guess(guessDigits);
        return new TrialResult(state);
    }
}
