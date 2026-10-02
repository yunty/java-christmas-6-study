package christmas.event;

import christmas.order.Orders;

public class FreeGivenEvent {
    private static final int FREE_GIVEN_MINIMUM_AMOUNT = 120_000;
    private static final int FREE_GIVEN_DISCOUNT = 25_000;

    public static int canGetFreeGiven(Orders orders) {
        if (orders.calculateTotalPrice() >= FREE_GIVEN_MINIMUM_AMOUNT) {
            return FREE_GIVEN_DISCOUNT;
        }
        return 0;
    }
}
