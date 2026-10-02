package christmas.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.exception.InputException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class OrdersTest {
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-1,티본스테이크-1,초코케이크-1",
    "아이스크림-1,레드와인-1"})
    void 올바른_값이_들어가면_객체_생성(String input){
        Orders orders = Orders.of(input);
        assertThat(orders).isNotNull();
    }
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프/1,티본스테이크-1,초코케이크-1",
            "아이스크림1,레드와인-1"})
    void 잘못된_형식이_들어가면_예외_발생(String input){
        assertThrows(InputException.class,
                () -> Orders.of(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-1,양송이스프-1,초코케이크-1",
            "레드와인-1,레드와인-1"})
    void 중복된_값이_들어가면_예외_발생(String input){
        assertThrows(InputException.class,
                () -> Orders.of(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프-2,티본스테이크-0,초코케이크-1",
            "아이스크림1,레드와인-a"})
    void 개수가_1이상의_정수가_아니면_예외_발생(String input){
        assertThrows(InputException.class,
                () -> Orders.of(input));
    }

}
