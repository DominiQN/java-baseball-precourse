package baseball.domain.digits;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Digits {
    private final List<Digit> elements;

    public Digits(List<Digit> elements) {
        validateDuplicates(elements);
        requireNotEmpty(elements);
        this.elements = elements;
    }

    private static void validateDuplicates(List<Digit> elements) {
        final Set<Digit> distinctElements = new HashSet<>(elements);
        if (elements.size() != distinctElements.size()) {
            throw new IllegalArgumentException("Duplicate number exists!!");
        }
    }

    private void requireNotEmpty(List<Digit> elements) {
        if (elements.isEmpty()) {
            throw new IllegalArgumentException("Digits must not be empty!");
        }
    }

    public int size() {
        return elements.size();
    }

    public GuessAnswer guess(Digits guessDigits) {
        final List<Digit> guessElements = guessDigits.elements;
        validateGuessDigitsSize(guessElements);

        final List<Hint> hints = hintByGuessDigits(guessElements);
        return GuessAnswer.countByHint(hints);
    }

    private List<Hint> hintByGuessDigits(List<Digit> guessDigits) {
        final IndexedDigitIterator iterator = new IndexedDigitIterator(guessDigits);
        final List<Hint> hints = new ArrayList<>();

        while (iterator.hasNext()) {
            IndexedDigit guessing = iterator.next();
            final Hint hint = hintByGuessing(guessing);
            hints.add(hint);
        }
        return hints;
    }

    private Hint hintByGuessing(IndexedDigit guessing) {
        final int guessIndex = guessing.getIndex();
        final Digit guessDigit = guessing.getDigit();
        final int realIndex = this.elements.indexOf(guessDigit);

        return Hint.byIndexes(guessIndex, realIndex);
    }

    private void validateGuessDigitsSize(List<Digit> guessDigits) {
        if (guessDigits.size() != elements.size()) {
            throw new IllegalArgumentException("Guess digits size must be equal to secret number size!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Digits digits = (Digits) o;
        return elements.equals(digits.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }
}
