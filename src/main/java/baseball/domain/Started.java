package baseball.domain;

import baseball.domain.digits.Digits;

public class Started extends Guessable {
    public Started(Digits secretNumber) {
        super(secretNumber);
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
}
