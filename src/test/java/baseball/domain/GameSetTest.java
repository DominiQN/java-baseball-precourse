package baseball.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameSetTest {
    public static final Always123DigitsGenerator ALWAYS_123_GENERATOR = new Always123DigitsGenerator();
    private static final int SECRET_SIZE = 3;

    @DisplayName("게임세트는 비밀번호 길이와 셔플러로 생성된다.")
    @Test
    void construct() {
        assertThatNoException()
                .isThrownBy(() -> new GameSet(SECRET_SIZE, ALWAYS_123_GENERATOR));
    }

    @DisplayName("생성 시 원했던 비밀번호 길이와 실제 생성된 비밀번호의 길이가 같아야 한다.")
    @Test
    void illegalSecretNumberState() {
        final int secretNumberSize = 4;
        assertThatIllegalStateException()
                .isThrownBy(() -> new GameSet(secretNumberSize, ALWAYS_123_GENERATOR))
                .withMessage("Requested size: 4, Generated size: 3");
    }
}
