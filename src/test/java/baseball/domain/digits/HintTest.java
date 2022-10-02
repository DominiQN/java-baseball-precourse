package baseball.domain.digits;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class HintTest {
    @DisplayName("추측 위치는 0 이상이어야 한다.")
    @ParameterizedTest(name = "추측 위치: {0}은 음수이므로 추측 위치 범위가 될 수 없다.")
    @ValueSource(ints = {-1, -10})
    void illegalGuessIndex(int guessDigitIndex) {
        final int indexInDigits = 0;
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Hint.byIndexes(guessDigitIndex, indexInDigits))
                .withMessage("Guess digit index must not be negative!");
    }

    @DisplayName("포함되지 않으면(실제위치: -1) 낫싱, 추측 위치와 실제 위치가 같으면 스트라이크, 다르면 볼이다.")
    @ParameterizedTest(name = "추측 위치: {0}, 실제 위치: {1} = 힌트: {2}")
    @CsvSource({
            "0,0,STRIKE",
            "1,1,STRIKE",
            "0,2,BALL",
            "1,2,BALL",
            "0,-1,FOUL",
            "1,-1,FOUL"
    })
    void hintByIndexes(int guessIndex, int realIndex, Hint expectedHint) {
        final Hint actualHint = Hint.byIndexes(guessIndex, realIndex);
        assertThat(actualHint).isEqualTo(expectedHint);
    }
}
