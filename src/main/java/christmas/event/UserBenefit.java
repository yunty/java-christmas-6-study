package christmas.event;

import christmas.order.Orders;
import java.util.EnumMap;
import java.util.Map;

public class UserBenefit {

    private final Map<BenefitType, Integer> benefits = new EnumMap<>(BenefitType.class);
    private final int discountAmount;
    private final int benefitAmount;

    public static UserBenefit applyEvent(Orders orders, int day) {
        return new UserBenefit(orders, day);
    }

    private UserBenefit(Orders orders, int day) {
        applyBenefit(orders, day);
        discountAmount = sumDiscountAmount();
        benefitAmount = sumBenefitAmount();
    }

    private void applyBenefit(Orders orders, int day) {
        for (BenefitType type : BenefitType.values()) {
            addBenefit(type, type.calculate(orders, day));
        }
    }

    private void addBenefit(BenefitType type, int amount) {
        if (amount > 0) {
            benefits.put(type, amount);
        }
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public int getBenefitAmount() {
        return benefitAmount;
    }

    private int sumBenefitAmount() {
        return benefits.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int sumDiscountAmount() {
        return benefits.entrySet().stream()
                .filter(entry -> entry.getKey().isPaymentDiscount())
                .mapToInt(entry -> entry.getValue())
                .sum();
    }
}
