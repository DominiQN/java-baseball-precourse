package baseball.ui;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static java.lang.Integer.parseInt;

import baseball.ui.dto.GuessInput;
import baseball.ui.dto.GuessOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuessView {
    public static GuessInput inputGuess() {
        System.out.print("숫자를 입력해주세요 :");
        final String input = readLine();
        final int inputInteger = parseInt(input);
        final List<Integer> digits = splitDigits(inputInteger);
        return new GuessInput(digits);
    }

    private static List<Integer> splitDigits(int integer) {
        final List<Integer> digits = new ArrayList<>();
        while (integer > 0) {
            digits.add(integer % 10);
            integer /= 10;
        }
        Collections.reverse(digits);
        return digits;
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
