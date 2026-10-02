package christmas.event;

public class SpecialDiscount {
    private static final int DEFAULT_DISCOUNT_MONEY = 1_000;

    private SpecialDiscount(){}

    public static int calculate(int day) {
        if (canDiscount(day)) {
            return DEFAULT_DISCOUNT_MONEY;
        }
        return 0;
    }
    private static boolean canDiscount(int day){
        int getDay = day % 7;
        return getDay == 3 || day == 25 ;
    }
}
