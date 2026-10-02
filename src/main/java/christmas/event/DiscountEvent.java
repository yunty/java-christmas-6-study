package christmas.event;

public interface DiscountEvent {
    int calculate(int day);
    void checkCanDiscount(int day);
}
