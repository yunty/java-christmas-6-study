package christmas.event;

import christmas.menu.MenuCategory;
import christmas.order.Orders;

public class WeekendDiscount {
    private static final int DEFAULT_DISCOUNT_MONEY = 2_023;
    private static final MenuCategory DISCOUNT_MENU = MenuCategory.MAIN;

    private WeekendDiscount(){}

    public static int calculate(Orders orders, int day) {
        if (canNotDiscount(day)) {
            return 0;
        }
        return countCanDiscountMenu(orders) * DEFAULT_DISCOUNT_MONEY;
    }
    private static int countCanDiscountMenu(Orders orders){
        return orders.countByMenuName(DISCOUNT_MENU);
    }
    private static boolean canNotDiscount(int day){
        int getDay = day % 7;
        return getDay >2 || getDay == 0;
    }
}
