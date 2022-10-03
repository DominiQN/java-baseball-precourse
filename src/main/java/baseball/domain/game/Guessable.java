package baseball.domain.game;

import baseball.domain.digits.Digits;
import baseball.domain.digits.GuessAnswer;

abstract class Guessable implements GameState {
    private final Digits secretNumber;

    public Guessable(Digits secretNumber) {
        this.secretNumber = secretNumber;
    }

    @Override
    public GameState guess(Digits guessDigits) {
        final GuessAnswer guessAnswer = this.secretNumber.guess(guessDigits);

        if (guessAnswer.isCorrect()) {
            return new Correct(guessAnswer);
        }

        return new Incorrect(this.secretNumber, guessAnswer);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
