package christmas.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.order.Orders;
import christmas.order.Order;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class UserBenefitTest {


    @ParameterizedTest
    @MethodSource("orderCases")
        // 메서드를 통해 입력받는다
    void 실제_할인액과_총혜택액을_구분한다(Orders order, int day,
                                int expectedDiscount, int expectedBenefit) {
        UserBenefit userDiscount = UserBenefit.applyEvent(order, day);

        assertThat(userDiscount.getDiscountAmount()).isEqualTo(expectedDiscount);
        assertThat(userDiscount.getBenefitAmount()).isEqualTo(expectedBenefit);

        if (expectedBenefit > expectedDiscount) {
            assertThat(userDiscount.getFreeGivenOrder())
                    .extracting(Order::showMyMenuName)
                    .containsExactly("샴페인");
            assertThat(userDiscount.getFreeGivenOrder().get(0).getCount()).isEqualTo(1);
        } else {
            assertThat(userDiscount.getFreeGivenOrder()).isEmpty();
        }
    }

    //리스트 입력할때 유용함
    static Stream<Arguments> orderCases() {
        String order1 = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        String order2 = "타파스-1,제로콜라-1";
        return Stream.of(
                Arguments.of(Orders.of(order1), 3, 6_246, 31_246),
                Arguments.of(Orders.of(order1), 25, 8_446, 33_446),
                Arguments.of(Orders.of(order2), 26, 0, 0),
                Arguments.of(Orders.of("아이스크림-1"), 3, 0, 0),
                Arguments.of(Orders.of("아이스크림-2"), 3, 6_246, 6_246),
                Arguments.of(Orders.of("티본스테이크-2,아이스크림-2"),
                        26, 4_046, 29_046));
    }

}
