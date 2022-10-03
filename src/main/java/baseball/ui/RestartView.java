package baseball.ui;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static java.lang.Integer.parseInt;

import baseball.ui.dto.RestartInput;

public class RestartView {
    private static final int RESTART = 1;
    private static final int FINISH = 2;

    public static RestartInput inputRestartOrNot() {
        System.out.print("게임을 새로 시작하려면 " + RESTART + ", 종료하려면 " + FINISH + "를 입력하세요.");
        final String input = readLine();
        final int inputInteger = parseInt(input);
        final boolean isRestarted = parseRestartInput(inputInteger);
        return new RestartInput(isRestarted);
    }

    private static boolean parseRestartInput(int nextInt) {
        return nextInt == RESTART;
    }
}
