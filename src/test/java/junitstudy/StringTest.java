package junitstudy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {
    @DisplayName("문자열을 콤마(,)로 분리하면, 콤마를 기준으로 문자열이 분리되어 배열로 반환된다.")
    @Test
    void splitStringByComma() {
        final String targetString = "1,2";

        final String[] splitStrings = targetString.split(",");

        assertThat(splitStrings).containsExactly("1", "2");
    }
}
