package christmas.event;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.order.Orders;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class UserDiscountTest {


    @ParameterizedTest
    @MethodSource("orderCases")
        // 메서드를 통해 입력받는다
    void 할인받은_총금액을_확인한다(Orders order, int day, int expected) {
        UserDiscount userDiscount = UserDiscount.applyEvent(order, day);

        int actual = userDiscount.getTotalDiscount();

        assertThat(actual).isEqualTo(expected);
    }

    //리스트 입력할때 유용함
    static Stream<Arguments> orderCases() {
        String order1 = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        String order2 = "타파스-1,제로콜라-1";
        return Stream.of(
                Arguments.of(Orders.of(order1), 3, 31_246),
                Arguments.of(Orders.of(order1), 25, 33_446),
                Arguments.arguments(Orders.of(order2),26,0));
    }

}
