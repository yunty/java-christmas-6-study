package christmas.event;

import christmas.order.Orders;

public class UserDiscount {
    private int totalDiscount;
    private int christmasDdayDiscount;
    private int weekdayDiscount;
    private int weekendDiscount;
    private int specialDiscount;

    public UserDiscount(Orders orders, int day){
        christmasDdayDiscount = ChristhmasDdayDiscount.calculate(day);
        weekdayDiscount = WeekdayDiscount.calculate(orders,day);
        weekendDiscount = WeekendDiscount.calculate(orders, day);
        specialDiscount = SpecialDiscount.calculate(orders, day);
    }





}
