package baseball;

import baseball.domain.RandomSecretNumberGenerator;
import baseball.domain.SecretNumberGenerator;
import baseball.domain.game.BaseballGame;
import baseball.domain.game.TrialResult;
import baseball.ui.InputView;
import baseball.ui.OutputView;
import baseball.ui.dto.GuessRequest;
import baseball.ui.dto.GuessResponse;
import baseball.ui.dto.RestartRequest;

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
        final RestartRequest request = InputView.inputRestart();
        if (request.isRestarted()) {
            game.restart();
        } else {
            game.finish();
        }
    }

    private static void playOneSet(BaseballGame game) {
        while (!game.isCorrect()) {
            final GuessRequest request = InputView.inputGuess();
            final TrialResult result = game.tryGuess(request.getGuessDigits());
            OutputView.printGuessResult(new GuessResponse(result), SECRET_NUMBER_SIZE);
        }
    }

    private static BaseballGame runGame() {
        return new BaseballGame(SECRET_NUMBER_GENERATOR, SECRET_NUMBER_SIZE);
    }
}
