package baseball.domain;

import baseball.domain.digits.Digits;

public class Correct implements GameState {
    private final Digits secretNumber;

    public Correct(Digits secretNumber) {
        this.secretNumber = secretNumber;
    }

    @Override
    public boolean isCorrect() {
        return true;
    }

    @Override
    public boolean isNothing() {
        return false;
    }

    @Override
    public int countStrikes() {
        return secretNumber.size();
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
        throw new IllegalStateException("The secret number is successfully guessed!");
    }
}
