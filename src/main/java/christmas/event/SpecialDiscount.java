package christmas.event;

import christmas.menu.MenuCategory;
import christmas.order.Orders;

public class SpecialDiscount {
    private static final int DEFALUT_DICOUNT_MONEY = 1_000;

    private SpecialDiscount(){}

    public static int calculate(Orders orders, int day) {
        if (canNotDiscount(day)) {
            return 0;
        }
        return DEFALUT_DICOUNT_MONEY;
    }
    private static boolean canNotDiscount(int day){
        int getDay = day % 7;
        return getDay == 3 || day == 25 ;
    }
}
