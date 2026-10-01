package christmas.menu;

public enum DrinkMenu implements Menu {
    ZERO_COKE("제로콜라",3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인",25_000)
    ;
    final String menuName;
    final int price;

    DrinkMenu(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }

    @Override
    public int price() {
        return this.price;
    }
}
