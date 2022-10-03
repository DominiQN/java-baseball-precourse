package baseball.domain.game;

import baseball.domain.digits.Digits;
import baseball.domain.digits.GuessAnswer;

public class Correct implements GameState {
    private final GuessAnswer previousGuessAnswer;

    public Correct(GuessAnswer previousGuessAnswer) {
        checkCorrect(previousGuessAnswer);
        this.previousGuessAnswer = previousGuessAnswer;
    }

    private void checkCorrect(GuessAnswer previousGuessAnswer) {
        if (!previousGuessAnswer.isCorrect()) {
            throw new IllegalStateException("Previous guess answer is not correct!");
        }
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
        return previousGuessAnswer.getStrikeCount();
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

    @Override
    public GameState finish() {
        return new Finished();
    }
}
