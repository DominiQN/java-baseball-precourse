package baseball.ui;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static java.lang.Integer.parseInt;

import baseball.domain.digits.Digits;
import baseball.ui.dto.GuessInput;
import baseball.ui.dto.GuessOutput;

public class GuessView {
    public static GuessInput inputGuess() {
        System.out.print("숫자를 입력해주세요 :");
        final String input = readLine();
        final int inputInteger = parseInt(input);
        final Digits digits = DigitsSplitter.split(inputInteger);
        return new GuessInput(digits);
    }

    public static void printGuessResult(GuessOutput guessOutput, int secretNumberSize) {
        printBallCount(guessOutput.getBallCount());
        printStrikeCount(guessOutput.getStrikeCount());
        printNothing(guessOutput.isNothing());
        System.out.println();
        printCorrect(guessOutput.isCorrect(), secretNumberSize);
    }

    private static void printBallCount(int ballCount) {
        if (ballCount > 0) {
            System.out.print(ballCount + "볼 ");
        }
    }

    private static void printStrikeCount(int strikeCount) {
        if (strikeCount > 0) {
            System.out.print(strikeCount + "스트라이크 ");
        }
    }

    private static void printNothing(boolean isNothing) {
        if (isNothing) {
            System.out.print("낫싱");
        }
    }

    private static void printCorrect(boolean isCorrect, int secretNumberSize) {
        if (isCorrect) {
            System.out.println(secretNumberSize + "개의 숫자를 모두 맞히셨습니다! 게임 종료");
        }
    }
}
