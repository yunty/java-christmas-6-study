package christmas.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.exception.InputException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.api.Test;

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
    @ValueSource(strings = {"양송이수프-1,양송이수프-1,초코케이크-1",
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

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "없는메뉴-1", "없음-1", "타파스-1,", ",타파스-1",
            "타파스-1,,제로콜라-1", "타파스-2147483648", "타파스-21",
            "타파스-10,양송이수프-11", "제로콜라-1", "레드와인-1,샴페인-1"})
    void 잘못된_주문은_안내할_수_있는_예외로_거절한다(String input) {
        InputException exception = assertThrows(InputException.class, () -> Orders.of(input));
        assertThat(exception).hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void 주문수량_합계가_20개이면_허용한다() {
        Orders orders = Orders.of("타파스-10,양송이수프-10");
        assertThat(orders.calculateTotalPrice()).isEqualTo(115_000);
    }

}
