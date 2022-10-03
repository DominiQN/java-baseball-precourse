package baseball.domain.game;

import static baseball.domain.digits.DigitsUtil.digitsOf;

import baseball.domain.SecretNumberGenerator;
import baseball.domain.digits.Digits;

class Always123DigitsGenerator implements SecretNumberGenerator {
    @Override
    public Digits generate(int secretNumberSize) {
        return digitsOf(1, 2, 3);
    }
}
