package baseball.domain;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IndexedDigitIterator implements Iterator<IndexedDigit> {
    private final ListIterator<Digit> listIterator;

    public IndexedDigitIterator(List<Digit> digits) {
        this.listIterator = digits.listIterator();
    }

    @Override
    public boolean hasNext() {
        return listIterator.hasNext();
    }

    @Override
    public IndexedDigit next() {
        final int index = listIterator.nextIndex();
        final Digit digit = listIterator.next();
        return new IndexedDigit(digit, index);
    }
}
