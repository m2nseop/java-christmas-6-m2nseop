package christmas.Domain;

public class EventOption {
    public static final int EVENT_YEAR = 2023;
    public static final int EVENT_MONTH = 12;
    public static final int EVENT_START_DATE = 1;
    public static final int CHRISTMAS_D_DAY_EVENT_END_DATE = 25;
    public static final int EVENT_END_DATE = 31;
    public static final int MAX_ORDER_QUANTITY = 20;
    public static final int GIFT_MENU_QUANTITY = 1;
    public static final String GIFT_MENU = "샴페인";
    public static final int MINIMUM_ORDER_PRICE_TO_GET_GIFT_MENU = 120000;
    public static final int CHRISTMAS_D_DAY_START_DISCOUNT_AMOUNT = 1000;
    private EventOption() {
        // 불필요한 인스턴스 생성 방지
    }
}
