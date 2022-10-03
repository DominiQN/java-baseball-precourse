package baseball.domain;

import static baseball.domain.digits.DigitsUtil.digitsOf;

import baseball.domain.digits.Digits;

class Always123DigitsGenerator implements SecretNumberGenerator {
    @Override
    public Digits generate(int secretNumberSize) {
        return digitsOf(1, 2, 3);
    }
}
