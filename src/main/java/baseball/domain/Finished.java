package baseball.domain;

import baseball.domain.digits.Digits;

public class Finished implements GameState {
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isCorrect() {
        throw error();
    }

    @Override
    public boolean isNothing() {
        throw error();
    }

    @Override
    public int countStrikes() {
        throw error();
    }

    @Override
    public int countBalls() {
        throw error();
    }

    @Override
    public GameState guess(Digits guessDigits) {
        throw error();
    }

    private IllegalStateException error() {
        return new IllegalStateException("The game is finished.");
    }
}
