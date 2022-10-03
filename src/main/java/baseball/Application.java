package baseball;

import baseball.domain.RandomSecretNumberGenerator;
import baseball.domain.SecretNumberGenerator;
import baseball.domain.game.BaseballGame;
import baseball.domain.game.TrialResult;
import baseball.ui.GuessView;
import baseball.ui.RestartView;
import baseball.ui.dto.GuessInput;
import baseball.ui.dto.GuessOutput;
import baseball.ui.dto.RestartInput;

public class Application {
    public static final int SECRET_NUMBER_SIZE = 3;
    public static final SecretNumberGenerator SECRET_NUMBER_GENERATOR = new RandomSecretNumberGenerator();

    public static void main(String[] args) {
        final BaseballGame game = runGame();

        while (!game.isFinished()) {
            playOneSet(game);
            restartOrFinish(game);
        }
    }

    private static void restartOrFinish(BaseballGame game) {
        final RestartInput request = RestartView.inputRestartOrNot();
        if (request.isRestarted()) {
            game.restart();
            return;
        }
        game.finish();
    }

    private static void playOneSet(BaseballGame game) {
        while (!game.isCorrect()) {
            final GuessInput request = GuessView.inputGuess();
            final TrialResult result = game.tryGuess(request.getGuessDigits());
            GuessView.printGuessResult(new GuessOutput(result), SECRET_NUMBER_SIZE);
        }
    }

    private static BaseballGame runGame() {
        return new BaseballGame(SECRET_NUMBER_GENERATOR, SECRET_NUMBER_SIZE);
    }
}
