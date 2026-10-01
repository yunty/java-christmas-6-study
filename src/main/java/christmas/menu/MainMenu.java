package christmas.menu;

public enum MainMenu implements Menu{
    TBONE_STAKE("티본스테이크",55_000),
    BBQ_RIB("바비큐립",54_000),
    SEAFOOD_PASTA("해산물파스타",35_000),
    CHRISTMAS_PASTA("크리스마스파스타",25_000)
    ;

    final String menuName;
    final int price;

    MainMenu(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }

    @Override
    public int price() {
        return this.price;
    }
}
