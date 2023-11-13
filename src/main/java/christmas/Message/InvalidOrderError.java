package christmas.Message;

public class InvalidOrderError {
    public static final String ERROR_MESSAGE = "[ERROR] ";
    public static final String INVALID_ORDER_FORM_ERROR = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String INVALID_MAX_ORDER_QAUNTITY_ERROR = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.";
    public static final String MENU_CONTAIN_ONLY_DRINK_ERROR = "음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.";
    public static final String NOT_EXIST_MENU_ERROR = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String NULL_OR_EMPTY_ERROR = "입력값이 NULL 또는 EMPTY값입니다.";
    public static final String NOT_A_NUMBER_ERROR = "숫자로 입력하셔야합니다.";
    public static final String NOT_A_VALID_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private InvalidOrderError() {
        // 불필요한 인스턴스 생성 방지
    }
}
