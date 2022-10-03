package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartedTest {
    public static final Always123DigitsGenerator ALWAYS_123_GENERATOR = new Always123DigitsGenerator();
    private static final int SECRET_SIZE = 3;
    private final GameState started = new Started(SECRET_SIZE, ALWAYS_123_GENERATOR);

    @DisplayName("비밀번호 길이와 셔플러로 생성된다.")
    @Test
    void construct() {
        assertThatNoException()
                .isThrownBy(() -> new Started(SECRET_SIZE, ALWAYS_123_GENERATOR));
    }

    @DisplayName("생성 시 원했던 비밀번호 길이와 실제 생성된 비밀번호의 길이가 같아야 한다.")
    @Test
    void illegalSecretNumberState() {
        final int secretNumberSize = 4;
        assertThatIllegalStateException()
                .isThrownBy(() -> new Started(secretNumberSize, ALWAYS_123_GENERATOR))
                .withMessage("Requested size: 4, Generated size: 3");
    }

    @DisplayName("시작했기에 아직 맞추지 못했다.")
    @Test
    void correct() {
        assertThat(started.isCorrect()).isFalse();
    }

    @DisplayName("시작했기에 스트라이크 개수는 0이다.")
    @Test
    void countStrikes() {
        assertThat(started.countStrikes()).isZero();
    }

    @DisplayName("시작했기에 볼 개수는 0이다.")
    @Test
    void countBalls() {
        assertThat(started.countBalls()).isZero();
    }

    @DisplayName("시작했기에 낫싱이다.")
    @Test
    void isNothing() {
        assertThat(started.isNothing()).isTrue();
    }

    @DisplayName("종료되지 않았다.")
    @Test
    void isFinished() {
        assertThat(started.isFinished()).isFalse();
    }
}
