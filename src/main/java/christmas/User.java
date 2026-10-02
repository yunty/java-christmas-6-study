package christmas;

import christmas.order.Orders;

public class User {
    private int totalPrice;
    private Orders orders;

    private User(Orders orders){
        this.orders = orders;
        this.totalPrice = orders.calculateTotalPrice();
    }
    public static User of(String userOrders){
        return new User(Orders.of(userOrders));
    }
}
