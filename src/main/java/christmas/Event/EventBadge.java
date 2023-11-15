package christmas.Event;

public enum EventBadge {
    NOTHING("없음", 0),
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);
    private final String eventBadgeType;
    private final int eventBadgeStandardAmount;

    EventBadge(String eventBadgeType, int eventBadgeStandardAmount) {
        this.eventBadgeType = eventBadgeType;
        this.eventBadgeStandardAmount = eventBadgeStandardAmount;
    }

    public String getEventBadgeType() {
        return eventBadgeType;
    }

    public int getEventBadgeStandardAmount() {
        return eventBadgeStandardAmount;
    }

    public static String findMyEventBadgeType(int benefitsTotalAmount) {
        String eventBadgeType = "";
        for (EventBadge badge : EventBadge.values()) {
            if (badge.getEventBadgeStandardAmount() <= benefitsTotalAmount) {
                eventBadgeType = badge.getEventBadgeType();
            }
        }
        return eventBadgeType;
    }
}
