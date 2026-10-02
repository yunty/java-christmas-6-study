package christmas;

import christmas.event.UserDiscount;
import christmas.order.Orders;

public class User {
    private int totalPrice;
    private Orders orders;
    private UserDiscount totalDiscount;

    private User(Orders orders, int day) {
        this.orders = orders;
        this.totalPrice = orders.calculateTotalPrice();
        totalDiscount = UserDiscount.applyEvent(orders, day);
    }
    public int getTotalDiscount(){
        return totalDiscount.getTotalDiscount();
    }

    public static User of(String userOrders, int day) {
        return new User(Orders.of(userOrders), day);
    }
}
