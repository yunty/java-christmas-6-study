package christmas;

import christmas.event.Badge;
import christmas.event.UserBenefit;
import christmas.order.Orders;

public class User {
    private int totalPrice;
    private Orders orders;
    private UserBenefit totalBenefit;
    private Badge badge;

    private User(Orders orders, int day) {
        this.orders = orders;
        this.totalPrice = orders.calculateTotalPrice();
        this.totalBenefit = UserBenefit.applyEvent(orders, day);
        this.badge = Badge.of(totalBenefit.getDiscountAmount());
    }
    public int getTotalDiscount(){
        return totalBenefit.getDiscountAmount();
    }

    public static User of(String userOrders, int day) {
        return new User(Orders.of(userOrders), day);
    }
}
