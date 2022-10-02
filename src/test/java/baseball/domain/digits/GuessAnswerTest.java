package baseball.domain.digits;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GuessAnswerTest {
    @DisplayName("같은 값을 가지면 추측결과는 동일하다.")
    @Test
    void equality() {
        final GuessAnswer one = new GuessAnswer(1, 0);
        final GuessAnswer other = new GuessAnswer(1, 0);

        assertThat(one).isEqualTo(other);
    }

    @DisplayName("추측결과 스트라이크, 볼 개수를 알 수 있다.")
    @Test
    void properties() {
        final GuessAnswer answer = new GuessAnswer(2, 0);

        assertAll(
                () -> assertThat(answer.getStrikeCount()).isEqualTo(2),
                () -> assertThat(answer.getBallCount()).isEqualTo(0)
        );
    }

    @ParameterizedTest()
    @CsvSource({
            "0, 1, false",
            "1, 0, false",
            "0, 0, true",
    })
    void isNothing(int strikeCount, int ballCount, boolean expected) {
        final GuessAnswer answer = new GuessAnswer(strikeCount, ballCount);

        boolean actual = answer.isNothing();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}, {1}, {2}이면, 스트라이크: {3}, 볼: {4}이다.")
    @CsvSource({
            "BALL, BALL, STRIKE, 1, 2",
            "STRIKE, BALL, BALL, 1, 2",
            "FOUL, STRIKE, BALL, 1, 1",
            "FOUL, FOUL, FOUL, 0, 0"
    })
    void countByHint(Hint hint1, Hint hint2, Hint hint3, int expectedStrikes, int expectedBalls) {
        final List<Hint> hints = Arrays.asList(hint1, hint2, hint3);
        final GuessAnswer expectedAnswer = new GuessAnswer(expectedStrikes, expectedBalls);
        final GuessAnswer actualAnswer = GuessAnswer.countByHint(hints);

        assertThat(actualAnswer).isEqualTo(expectedAnswer);
    }
}
