package christmas.Domain;

public enum EventBadge {
    NOTHING("없음", 0),
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);
    private final String eventBadgeType;
    private final int eventBadgeStandard;

    EventBadge(String eventBadgeType, int eventBadgeStandard) {
        this.eventBadgeType = eventBadgeType;
        this.eventBadgeStandard = eventBadgeStandard;
    }

    public String getEventBadgeType() {
        return eventBadgeType;
    }

    public int getEventBadgeStandard() {
        return eventBadgeStandard;
    }
}
