package baseball.domain;

import baseball.domain.digits.Digits;

public class Started implements GameState {
    private final Digits secretNumber;

    public Started(int secretNumberSize, SecretNumberGenerator secretNumberGenerator) {
        final Digits generatedSecretNumber = secretNumberGenerator.generate(secretNumberSize);

        validateSecretNumberSize(secretNumberSize, generatedSecretNumber.size());

        this.secretNumber = generatedSecretNumber;
    }

    private void validateSecretNumberSize(int requestedSize, int generatedSize) {
        if (generatedSize != requestedSize) {
            throw new IllegalStateException("Requested size: " + requestedSize + ", Generated size: " + generatedSize);
        }
    }

    @Override
    public boolean isCorrect() {
        return false;
    }

    @Override
    public boolean isNothing() {
        return true;
    }

    @Override
    public int countStrikes() {
        return 0;
    }

    @Override
    public int countBalls() {
        return 0;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public GameState guess(Digits guessDigits) {
        return null;
    }
}
