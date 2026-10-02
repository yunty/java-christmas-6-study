package christmas.order;

import christmas.exception.ErrorMessage;
import christmas.exception.InputException;
import christmas.menu.Menu;
import christmas.menu.MenuCategory;
import java.util.Objects;

public class Order {
    private final Menu menu;
    private final int count;

    public Order(String order) {
        String[] splitOrder = splitInputOrder(order);

        this.menu = getMenuFromOrder(splitOrder);
        this.count = getCountFromOrder(splitOrder);
    }

    public boolean isSameCategory(MenuCategory menuCategory){
        return menu.isSameCategory(menuCategory);
    }
    public int getCount(){
        return count;
    }

    public String showMyMenuName() {
        return this.menu.menuName();
    }

    public int getTotalPrice() {
        return menu.price() * count;
    }

    private static Menu getMenuFromOrder(String[] splitOrder) {
        return Menu.findByMenuName(splitOrder[0]);
    }

    private static int getCountFromOrder(String[] splitOrder) {
        return Integer.parseInt(splitOrder[1]);
    }

    private String[] splitInputOrder(String inputOrder) {
        inputValidate(inputOrder);
        return inputOrder.split("-");
    }

    private void inputValidate(String inputOrder) {
        String inputRegex = "[가-힣]+-[1-9]\\d*";
        if (inputOrder.matches(inputRegex)) {
            return;
        }
        throw new InputException(ErrorMessage.NOT_MATCH_REGEX);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return menu == order.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(menu);
    }
}
