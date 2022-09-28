package junitstudy;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    // Test Case

    @DisplayName("Set의 크기는 추가한 요소에서 중복을 제거한 개수를 반환한다.")
    @Test
    void size() {
        final int actual = numbers.size();

        assertThat(actual).isEqualTo(3);
    }

    @ParameterizedTest(name = "Set에 [{0}]을 요소로 포함한다.")
    @ValueSource(ints = {1, 2, 3})
    void contains(int expectedElement) {
        assertThat(numbers.contains(expectedElement)).isTrue();
    }
}
