package baseball.domain;

import baseball.domain.digits.Digits;
import baseball.domain.digits.GuessAnswer;

public class Incorrect extends Guessable {
    private final GuessAnswer previousGuessAnswer;

    public Incorrect(Digits secretNumber, GuessAnswer previousGuessAnswer) {
        super(secretNumber);
        checkIncorrect(previousGuessAnswer);
        this.previousGuessAnswer = previousGuessAnswer;
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
}
