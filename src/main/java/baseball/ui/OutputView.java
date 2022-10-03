package baseball.ui;

import baseball.ui.dto.GuessResponse;

public class OutputView {
    public static void printGuessResult(GuessResponse guessResponse, int secretNumberSize) {
        printBallCount(guessResponse.getBallCount());
        printStrikeCount(guessResponse.getStrikeCount());
        printNothing(guessResponse.isNothing());
        System.out.println();
        printCorrect(guessResponse.isCorrect(), secretNumberSize);
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
