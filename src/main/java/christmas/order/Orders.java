package christmas.order;

import christmas.exception.ErrorMessage;
import christmas.exception.InputException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Orders {
    private final Set<Order> orderList = new HashSet<Order>();

    public Orders(String orders) {
        String[] splitOrders = orders.split(",");
        addOrder(splitOrders);

    }

    private void addOrder(String[] orders) {
        Arrays.stream(orders)
                .map(Order::new)
                .map(this::isDuplicate)
                .forEach(orderList::add);
    }

    private Order isDuplicate(Order order) {
        if (orderList.contains(order)) {
            throw new InputException(ErrorMessage.DUPLICATE_MENU);
        }
        return order;
    }

}
