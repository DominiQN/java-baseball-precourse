package baseball.domain.game;

import baseball.domain.digits.Digits;

public interface GameState {
    boolean isCorrect();

    boolean isNothing();

    int countStrikes();

    int countBalls();

    boolean isFinished();

    GameState guess(Digits guessDigits);

    GameState finish();

    GameState startNewGame(Digits newSecretNumber);
}
