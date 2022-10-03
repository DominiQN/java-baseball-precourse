package baseball.domain;

import static camp.nextstep.edu.missionutils.Randoms.shuffle;

import baseball.domain.digits.Digit;
import baseball.domain.digits.Digits;
import java.util.Arrays;
import java.util.List;

public class RandomSecretNumberGenerator implements SecretNumberGenerator {
    public static final List<Digit> ALL_DIGITS = Arrays.asList(
            new Digit(1),
            new Digit(2),
            new Digit(3),
            new Digit(4),
            new Digit(5),
            new Digit(6),
            new Digit(7),
            new Digit(8),
            new Digit(9)
    );

    @Override
    public Digits generate(int secretNumberSize) {
        final List<Digit> shuffledDigits = shuffle(ALL_DIGITS);
        final List<Digit> secretDigits = take(shuffledDigits, secretNumberSize);
        return new Digits(secretDigits);
    }

    private List<Digit> take(List<Digit> list, int size) {
        return list.subList(0, size);
    }
}
