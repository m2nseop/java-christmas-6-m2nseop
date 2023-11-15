package christmas.Event;

public enum EventBenefit {
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인", "총 주문 금액", 100),
    WEEKDAY_DISCOUNT("평일 할인", "디저트", 2023),
    WEEKEND_DISCOUNT("주말 할인", "메인",2023),
    SPECIAL_DISCOUNT("특별 할인", "총 주문 금액", 1000),
    GIFT_MENU("증정 이벤트", "샴페인", 25000); // 증정 메뉴의 개수는 option에 넣을 것

    private final String eventType;
    private final String eventTarget;
    private final int benefitAmount;

    EventBenefit(String eventType, String eventTarget, int benefitAmount) {
        this.eventType = eventType;
        this.eventTarget = eventTarget;
        this.benefitAmount = benefitAmount;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventTarget() {
        return eventTarget;
    }

    public int getBenefitAmount() {
        return benefitAmount;
    }

    public int getBenefitTotalAmount(int quanitity) {
        return this.benefitAmount * quanitity;
    }
}
