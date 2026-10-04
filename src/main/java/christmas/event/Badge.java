package christmas.event;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private String badgeType;
    private int minimumAmount;

    Badge(String badgeType, int minimumAmount) {
        this.badgeType = badgeType;
        this.minimumAmount = minimumAmount;
    }

    public static Badge of(int amount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.support(amount))
                .max(Comparator.comparingInt(badge -> badge.minimumAmount))
                .orElse(NONE);
    }

    public boolean support(int amount) {
        return amount >= this.minimumAmount;
    }

    public String getBadgeType() {
        return badgeType;
    }
}
