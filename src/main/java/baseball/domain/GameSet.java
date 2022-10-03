package baseball.domain;

import baseball.domain.digits.Digits;

public class GameSet {
    private final Digits secretNumber;

    public GameSet(int secretNumberSize, SecretNumberGenerator secretNumberGenerator) {
        final Digits generatedSecretNumber = secretNumberGenerator.generate(secretNumberSize);

        validateSecretNumberSize(secretNumberSize, generatedSecretNumber.size());

        this.secretNumber = generatedSecretNumber;
    }

    private void validateSecretNumberSize(int requestedSize, int generatedSize) {
        if (generatedSize != requestedSize) {
            throw new IllegalStateException("Requested size: " + requestedSize + ", Generated size: " + generatedSize);
        }
    }
}
