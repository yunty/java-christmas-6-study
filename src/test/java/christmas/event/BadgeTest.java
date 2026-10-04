package christmas.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {
    @ParameterizedTest
    @CsvSource({"0,NONE", "4999,NONE", "5000,STAR", "9999,STAR",
            "10000,TREE", "19999,TREE", "20000,SANTA", "31246,SANTA"})
    void 총혜택액으로_가장_높은_등급의_배지를_선택한다(int amount, Badge expected) {
        assertThat(Badge.of(amount)).isEqualTo(expected);
    }
}
