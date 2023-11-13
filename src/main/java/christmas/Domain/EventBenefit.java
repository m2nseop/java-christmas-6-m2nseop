package christmas.Domain;

public enum EventBenefit {
    CHRISTMAS_D_DAY_DISCOUNT ("크리스마스 디데이 할인",100),
    WEEKDAY_DISCOUNT ("평일 할인",2023),
    WEEKEND_DISCOUNT ("주말 할인",2023),
    SPECIAL_DISCOUNT ("특별 할인",1000);
//    GIFT_MENU ("증정 이벤트", "샴페인"); // 증정 메뉴의 개수는 option에 넣을 것

    private final String eventType;
    private final int benefitType;

    EventBenefit(String eventType, int benefitType){
        this.eventType = eventType;
        this.benefitType = benefitType;
    }
    public String getEventType() {
        return eventType;
    }
    public int getBenefitType() {
        return benefitType;
    }
}
