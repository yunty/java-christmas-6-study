package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.event.Badge;
import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    void 증정품은_배지_선정에_포함하고_결제금액에서는_차감하지_않는다() {
        User user = User.of("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", 3);

        assertThat(user.getTotalPrice()).isEqualTo(142_000);
        assertThat(user.getExpectedPayment()).isEqualTo(135_754);
        assertThat(user.getBadge()).isEqualTo(Badge.SANTA);
    }
}
