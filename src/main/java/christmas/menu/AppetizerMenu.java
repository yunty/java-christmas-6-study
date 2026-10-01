package christmas.menu;

public enum AppetizerMenu implements Menu{
    MUSHROOM_SOUP("양송이수프",6_000),
    TAPAS("타파스",5_500),
    CAESAR_SALAD("시저샐러드",8_000)
    ;
    final String menuName;
    final int price;

    AppetizerMenu(String menuName, int price) {
        this.menuName = menuName;
        this.price = price;
    }
    @Override
    public int price() {
        return this.price;
    }
}
