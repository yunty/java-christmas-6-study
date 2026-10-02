package christmas.menu;

import christmas.exception.ErrorMessage;
import christmas.exception.InputException;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, MenuCategory.APPETIZER),
    TAPAS("타파스", 5_500, MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, MenuCategory.APPETIZER),

    TBONE_STAKE("티본스테이크", 55_000, MenuCategory.MAIN),
    BBQ_RIB("바비큐립", 54_000, MenuCategory.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MenuCategory.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuCategory.MAIN),

    CHOCO_CAKE("초코케이크", 15_000, MenuCategory.DESERT),
    ICE_CREAM("아이스크림", 5_000, MenuCategory.DESERT),

    ZERO_COKE("제로콜라", 3_000, MenuCategory.DRINK),
    RED_WINE("레드와인", 60_000, MenuCategory.DRINK),
    CHAMPAGNE("샴페인", 25_000, MenuCategory.DRINK);

    final String menuName;
    final int price;
    final MenuCategory menuCategory;

    Menu(String menuName, int price, MenuCategory menuCategory) {
        this.menuName = menuName;
        this.price = price;
        this.menuCategory = menuCategory;
    }

    public int price() {
        return this.price;
    }

    public String menuName() {
        return String.valueOf(menuName);
    }

    public boolean isSameCategory(MenuCategory menuCategory){
        return this.menuCategory.equals(menuCategory);
    }

    public static Menu findByMenuName(String name) {
        return Arrays.stream(values())
                .filter(mainMenu -> mainMenu.isSameMenu(name))
                .findFirst()
                .orElseThrow(() -> new InputException(ErrorMessage.NOT_MATCH_MENU));
    }

    private boolean isSameMenu(String name) {
        return this.menuName.equals(name);
    }
}
