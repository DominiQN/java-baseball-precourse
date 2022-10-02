package baseball.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Digits {
    private final List<Digit> elements;

    public Digits(List<Digit> elements) {
        validateDuplicates(elements);

        this.elements = elements;
    }

    private static void validateDuplicates(List<Digit> elements) {
        final Set<Digit> distinctElements = new HashSet<>(elements);
        if (elements.size() != distinctElements.size()) {
            throw new IllegalArgumentException("Duplicate number exists!!");
        }
    }
}
