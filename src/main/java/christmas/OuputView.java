package christmas;

import christmas.event.Badge;
import christmas.event.BenefitType;
import christmas.menu.MenuCategory;
import christmas.order.Order;
import christmas.order.Orders;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class OuputView {
    public void printStartComment() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printGetDayComment() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void printGetMenuComment() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public void printShowEventComment(int day) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", day);
    }

    public void printUserInputMenu(Orders orders) {
        printTitle("주문 메뉴");
        orders.getOrderList()
                .stream()
                .forEach(this::printMenu);
    }

    public void printTotalPrice(int totalPrice) {
        printTitle("할인 전 총주문 금액");
        printAmount(totalPrice);
    }

    public void printFreeGiven(List<Order> orders) {
        printTitle("증정 메뉴");
        if (orders.isEmpty()) {
            System.out.println("없음");
            return;
        }
        orders.forEach(this::printMenu);
    }

    public void printBenefits(Map<BenefitType, Integer> benefits) {
        printTitle("혜택 내역");
        if (benefits.isEmpty()) {
            System.out.println("없음");
            return;
        }
        benefits.forEach((type, amount) ->
                System.out.printf(Locale.KOREA, "%s: -%,d원%n", type.getEventName(), amount));
    }

    public void printBenefitAmount(int amount) {
        printTitle("총혜택 금액");
        printAmount(-amount);
    }

    public void printExpectedPayment(int amount) {
        printTitle("할인 후 예상 결제 금액");
        printAmount(amount);
    }

    public void printBadge(Badge badge) {
        printTitle("12월 이벤트 배지");
        System.out.println(badge.getBadgeType());
    }

    public void printError(String message) {
        System.out.println(message);
    }

    private void printTitle(String title) {
        System.out.printf("%n<%s>%n", title);
    }

    private void printAmount(int amount) {
        System.out.printf(Locale.KOREA, "%,d원%n", amount);
    }

    private void printMenu(Order order) {
        if(order.isSameCategory(MenuCategory.NONE)){
            System.out.println("없음");
            return;
        }
        System.out.printf("%s %s개%n", order.showMyMenuName(), order.getCount());
    }

}
