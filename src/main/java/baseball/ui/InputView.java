package baseball.ui;

import baseball.ui.dto.GuessRequest;
import baseball.ui.dto.RestartRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private static final int RESTART = 1;
    private static final int FINISH = 2;

    public static GuessRequest inputGuess() {
        System.out.print("숫자를 입력해주세요 :");
        final int nextInt = scanner.nextInt();
        final List<Integer> digits = splitDigits(nextInt);
        return new GuessRequest(digits);
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

    public static RestartRequest inputRestart() {
        System.out.print("게임을 새로 시작하려면 " + RESTART + ", 종료하려면 " + FINISH + "를 입력하세요.");
        final int nextInt = scanner.nextInt();
        final boolean isRestarted = parseRestartInput(nextInt);
        return new RestartRequest(isRestarted);
    }

    private static boolean parseRestartInput(int nextInt) {
        return nextInt == RESTART;
    }
}
