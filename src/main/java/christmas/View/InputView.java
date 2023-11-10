package christmas.View;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static final String ERROR_MESSAGE = "[ERROR] ";
    public static final String VISIT_DATE_QUESTION = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String NULL_OR_EMPTY_ERROR = "입력값이 NULL 또는 EMPTY값입니다.";
    public static final String NOT_A_NUMBER_ERROR = "숫자로 입력하셔야합니다.";
    public static final String NOT_A_VALID_DATE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final int EVENT_START_DATE = 1;
    public static final int EVENT_END_DATE = 31;
    public static String readVisitDate() {
        System.out.println(VISIT_DATE_QUESTION);

        String input ="";
        try {
            input = Console.readLine();

            isEmpty(input);
            isNumber(input);
            isValidDate(input);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return readVisitDate();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readVisitDate();
        }
        return input;
    }

    public static boolean isValidDate(String input){
        int number = Integer.parseInt(input);
        if(number < EVENT_START_DATE || number > EVENT_END_DATE){
            throw new IllegalArgumentException(ERROR_MESSAGE + NOT_A_VALID_DATE);
        }
        return true;
    }
    public static boolean isNumber(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new NumberFormatException(ERROR_MESSAGE + NOT_A_NUMBER_ERROR);
            }
        }
        return true;
    }

    public static boolean isEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE + NULL_OR_EMPTY_ERROR);
        }
        return true;
    }
}
