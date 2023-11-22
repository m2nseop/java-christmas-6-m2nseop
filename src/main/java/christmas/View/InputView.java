package christmas.View;

import camp.nextstep.edu.missionutils.Console;
import christmas.Message.OutputMessage;

public class InputView {

    public static String readVisitDate() {
        System.out.println(OutputMessage.VISIT_DATE_QUESTION);
        String input = Console.readLine();

        validateBlank(input);
        validateNumber(input);
        return input;
    }

    public static String readMenuOrder() {
        System.out.println(OutputMessage.MENU_ORDER_QUESTION);
        String input = Console.readLine();

        validateBlank(input);
        return input;
    }

    public static void validateNumber(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new NumberFormatException(OutputMessage.NOT_A_VALID_DATE);
            }
        }
    }

    public static void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(OutputMessage.NULL_OR_EMPTY_ERROR);
        }
    }
}
