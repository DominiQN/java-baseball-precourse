package baseball.domain.game;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import baseball.domain.SecretNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BaseballGameTest {
    private static final SecretNumberGenerator ALWAYS_123_GENERATOR = new Always123DigitsGenerator();
    private static final int SECRET_SIZE_3 = 3;

    @ParameterizedTest(name = "비밀번호는 1 ~ 8 사이의 길이만 가능하다. size: {0}")
    @ValueSource(ints = {-1, 0, 9})
    void illegalSecretNumberSize(int illegalSecretNumberSize) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BaseballGame(ALWAYS_123_GENERATOR, illegalSecretNumberSize))
                .withMessage("Secret number must be between 1 and 8!");
    }

    @DisplayName("생성 당시 주어진 비밀번호 길이만큼 비밀번호가 생성되어야 한다.")
    @Test
    void illegalSecretNumberState() {
        final int requestedSize = 4;
        assertThatIllegalStateException()
                .isThrownBy(() -> new BaseballGame(ALWAYS_123_GENERATOR, requestedSize))
                .withMessage("Requested size: 4, Generated size: 3");
    }

    @DisplayName("비밀번호생성기와 비밀번호 크기로 생성된다.")
    @Test
    void construct() {
        assertThatNoException()
                .isThrownBy(() -> new BaseballGame(ALWAYS_123_GENERATOR, SECRET_SIZE_3));
    }

}
