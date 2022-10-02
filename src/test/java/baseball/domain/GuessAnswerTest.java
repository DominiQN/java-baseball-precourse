package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
}
