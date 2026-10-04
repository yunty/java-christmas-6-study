package christmas.order;

import christmas.exception.ErrorMessage;
import christmas.exception.InputException;
import christmas.menu.MenuCategory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Orders {
    private final Set<Order> orderList = new HashSet<>();

    private Orders(String orders) {
        if (orders == null || orders.isBlank()) {
            throw new InputException(ErrorMessage.INVALID_ORDER);
        }
        String[] splitOrders = orders.split(",", -1);
        addOrder(splitOrders);
        validateOrderCount();
        validateNotOnlyDrinks();
    }

    private void validateOrderCount() {
        long count = orderList.stream().mapToLong(Order::getCount).sum();
        if (count > 20) {
            throw new InputException(ErrorMessage.INVALID_ORDER);
        }
    }

    private void validateNotOnlyDrinks() {
        if (orderList.stream().allMatch(order -> order.isSameCategory(MenuCategory.DRINK))) {
            throw new InputException(ErrorMessage.INVALID_ORDER);
        }
    }

    public static Orders of(String orders) {
        return new Orders(orders);
    }

    public int calculateTotalPrice() {
        return orderList.stream()
                .mapToInt(Order::getTotalPrice)
                .sum();
    }

    public int countByMenuName(MenuCategory menuCategory) {
        return orderList.stream()
                .filter(order -> order.isSameCategory(menuCategory))
                .mapToInt(Order::getCount)
                .sum();
    }
    public Set<Order> getOrderList(){
        return new HashSet<>(orderList);
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
