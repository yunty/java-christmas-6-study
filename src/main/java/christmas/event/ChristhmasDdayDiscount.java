package christmas.event;

public final class ChristhmasDdayDiscount {
    private static final int DEFALUT_DICOUNT_MONEY = 1_000;
    private static final int MORE_DISCOUNT_EACH_DAY = 100;
    private static final int EVENT_END_DAY = 25;
    private static final int EVENT_START_DAY = 1;

    private ChristhmasDdayDiscount(){}

    public static int calculate(int day) {
        if (canDiscount(day)) {
            return 0;
        }
        return MORE_DISCOUNT_EACH_DAY * (day-1) + DEFALUT_DICOUNT_MONEY;
    }
    private static boolean canDiscount(int day){
        return day < EVENT_START_DAY || day > EVENT_END_DAY;
    }


}
