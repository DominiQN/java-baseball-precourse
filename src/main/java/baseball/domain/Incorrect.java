package baseball.domain;

import baseball.domain.digits.Digits;
import baseball.domain.digits.GuessAnswer;

public class Incorrect implements GameState {
    private final GuessAnswer previousGuessAnswer;
    private final Digits secretNumber;

    public Incorrect(Digits secretNumber, GuessAnswer previousGuessAnswer) {
        checkIncorrect(previousGuessAnswer);
        this.previousGuessAnswer = previousGuessAnswer;
        this.secretNumber = secretNumber;
    }

    private static void checkIncorrect(GuessAnswer previousGuessAnswer) {
        if (previousGuessAnswer.isCorrect()) {
            throw new IllegalStateException("Previous guess answer is correct!");
        }
    }

    @Override
    public boolean isCorrect() {
        return false;
    }

    @Override
    public boolean isNothing() {
        return previousGuessAnswer.isNothing();
    }

    @Override
    public int countStrikes() {
        return previousGuessAnswer.getStrikeCount();
    }

    @Override
    public int countBalls() {
        return previousGuessAnswer.getBallCount();
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
