package christmas.event;

import christmas.menu.Menu;
import christmas.menu.MenuCategory;
import christmas.order.Order;
import christmas.order.Orders;

public enum BenefitType {
    CHRISTMAS_DDAY("크리스마스 디데이 할인", true) {
        final int DEFAULT_DISCOUNT_MONEY = 1_000;
        final int MORE_DISCOUNT_EACH_DAY = 100;
        final int EVENT_END_DAY = 25;
        final int EVENT_START_DAY = 1;

        @Override
        int calculateAmount(Orders orders, int day) {
            return MORE_DISCOUNT_EACH_DAY * (day - 1) + DEFAULT_DISCOUNT_MONEY;
        }

        @Override
        boolean supports(Orders orders, int day) {
            return day >= EVENT_START_DAY && day <= EVENT_END_DAY;
        }

    },
    WEEKDAY("평일 할인", true) {
        final int DEFAULT_DISCOUNT_MONEY = 2_023;
        final MenuCategory DISCOUNT_MENU = MenuCategory.DESERT;

        @Override
        int calculateAmount(Orders orders, int day) {
            return orders.countByMenuName(DISCOUNT_MENU) * DEFAULT_DISCOUNT_MONEY;
        }

        @Override
        boolean supports(Orders orders, int day) {
            int getDay = day % 7;
            return getDay > 2 || getDay == 0;
        }
    },
    WEEKEND("주말 할인", true) {
        final int DEFAULT_DISCOUNT_MONEY = 2_023;
        final MenuCategory DISCOUNT_MENU = MenuCategory.MAIN;

        @Override
        int calculateAmount(Orders orders, int day) {
            return orders.countByMenuName(DISCOUNT_MENU) * DEFAULT_DISCOUNT_MONEY;

        }

        @Override
        boolean supports(Orders orders, int day) {
            int getDay = day % 7;
            return getDay < 3 && getDay > 0;
        }

    },
    SPECIAL("특별 할인", true) {
        final int DEFAULT_DISCOUNT_MONEY = 1_000;

        @Override
        int calculateAmount(Orders orders, int day) {
            return DEFAULT_DISCOUNT_MONEY;
        }

        @Override
        boolean supports(Orders orders, int day) {
            int getDay = day % 7;
            return getDay == 3 || day == 25;
        }

    },
    FREE_GIVEN("증정 이벤트", false) {
        final int FREE_GIVEN_MINIMUM_AMOUNT = 120_000;
        final int FREE_GIVEN_DISCOUNT = 25_000;

        @Override
        int calculateAmount(Orders orders, int day) {
            return FREE_GIVEN_DISCOUNT;
        }

        @Override
        boolean supports(Orders orders, int day) {
            return orders.calculateTotalPrice() >= FREE_GIVEN_MINIMUM_AMOUNT;
        }

        @Override
        public Order getFreeGivenOrder() {
            return Order.ofMenuAndCount(Menu.CHAMPAGNE, 1);
        }
    };

    private final String eventName;
    private final boolean paymentDiscount;

    BenefitType(String eventName, boolean paymentDiscount) {
        this.eventName = eventName;
        this.paymentDiscount = paymentDiscount;
    }

    public int calculate(Orders orders, int day) {
        if (orders.calculateTotalPrice() >= 10_000 && supports(orders, day)) {
            return calculateAmount(orders, day);
        }
        return 0;
    }

    public boolean isPaymentDiscount(){
        return this.paymentDiscount;
    }

    public String getEventName() {
        return eventName;
    }
    public boolean isNotPaymentDiscount(){
        return this.paymentDiscount == false;
    }

    public Order getFreeGivenOrder() {
        return Order.ofEmptyOrder();
    }

    abstract int calculateAmount(Orders orders, int day);

    abstract boolean supports(Orders orders, int day);
}
