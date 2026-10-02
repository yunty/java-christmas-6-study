package christmas.event;

import christmas.order.Orders;

public class UserDiscount {
    private final int totalDiscount;
    private final int christmasDdayDiscount;
    private final int weekdayDiscount;
    private final int weekendDiscount;
    private final int specialDiscount;
    private final int freeGivenEvent;

    private UserDiscount(Orders orders, int day) {
        christmasDdayDiscount = ChristhmasDdayDiscount.calculate(day);
        weekdayDiscount = WeekdayDiscount.calculate(orders, day);
        weekendDiscount = WeekendDiscount.calculate(orders, day);
        specialDiscount = SpecialDiscount.calculate(day);
        freeGivenEvent = FreeGivenEvent.canGetFreeGiven(orders);
        totalDiscount = sumDiscountAmount();
    }

    public static UserDiscount applyEvent(Orders orders, int day) {
        return new UserDiscount(orders, day);
    }
    public int getTotalDiscount(){
        return totalDiscount;
    }

    private int sumDiscountAmount() {
        return christmasDdayDiscount + weekdayDiscount + weekendDiscount + specialDiscount
                + freeGivenEvent;
    }


}
