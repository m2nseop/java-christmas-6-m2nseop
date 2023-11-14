package christmas.Message;

public class OutputMessage {
    public static final String DECEMBER_EVENT_WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String VISIT_DATE_QUESTION = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String MENU_ORDER_QUESTION = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    public static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    public static final String PRE_DISCOUNT_TOTAL_PRICE_MESSAGE = "<할인 전 총주문 금액>";
    public static final String EVENT_GIFT_MENU_MESSAGE = "<증정 메뉴>";
    public static final String RECEIVED_BENEFITS_MESSAGE = "<혜택 내역>";
    public static final String RECEIVED_BENEFITS_TOTAL_AMOUNT_MESSAGE = "<총혜택 금액>";
    public static final String DISCOUNTED_TOTAL_AMOUNT_MESSAGE = "<할인 후 예상 결제 금액>";
    public static final String DECEMBER_EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>";
    public static final String ERROR_MESSAGE = "[ERROR] ";
    public static final String INVALID_ORDER_FORM_ERROR = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String INVALID_MAX_ORDER_QAUNTITY_ERROR = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.";
    public static final String MENU_CONTAIN_ONLY_DRINK_ERROR = "음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.";
    public static final String NOT_EXIST_MENU_ERROR = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String NULL_OR_EMPTY_ERROR = "입력값이 NULL 또는 EMPTY값입니다.";
    public static final String NOT_A_VALID_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private OutputMessage() {
        // 불필요한 인스턴스 생성 방지
    }
}
