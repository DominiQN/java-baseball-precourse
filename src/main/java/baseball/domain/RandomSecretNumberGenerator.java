package baseball.domain;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

import baseball.domain.digits.Digit;
import baseball.domain.digits.Digits;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class RandomSecretNumberGenerator implements SecretNumberGenerator {
    @Override
    public Digits generate(int secretNumberSize) {
        final List<Digit> secretDigits = take(secretNumberSize);
        return new Digits(secretDigits);
    }

    private List<Digit> take(int size) {
        final LinkedHashSet<Digit> set = new LinkedHashSet<>();
        while (set.size() < size) {
            final int pickedNumber = pickNumberInRange(1, 9);
            final Digit pickedDigit = new Digit(pickedNumber);
            set.add(pickedDigit);
        }
        return new ArrayList<>(set);
    }
}
