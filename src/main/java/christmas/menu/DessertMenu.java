package christmas.menu;

public enum DessertMenu implements Menu {
    CHOCO_CAKE("초코케이크",15_000),
    ICE_CREAM("아이스크림", 5_000)
    ;
    final String menuName;
    final int price;

    DessertMenu(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }

    @Override
    public int price() {
        return this.price;
    }
}
