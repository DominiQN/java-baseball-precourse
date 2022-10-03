package baseball.domain;

import baseball.domain.digits.Digits;

public interface SecretNumberGenerator {
    Digits generate(int secretNumberSize);
}
