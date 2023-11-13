package christmas.View;

import static christmas.Domain.EventOption.*;
import static christmas.Message.OutputMessage.*;

import camp.nextstep.edu.missionutils.Console;
import christmas.Message.OutputMessage;

public class InputView {

    public static String readVisitDate() {
        System.out.println(OutputMessage.VISIT_DATE_QUESTION);
        String input = Console.readLine();

        validateBlank(input);
        validateNumber(input);
        isValidDate(input); // 분리할 것인지 고민
        return input;
    }

    public static String readMenuOrder() {
        System.out.println(OutputMessage.MENU_ORDER_QUESTION);
        String input = Console.readLine();

        validateBlank(input);
        return input;
    }

    public static boolean isValidDate(String input) {
        int number = Integer.parseInt(input);
        if (number < EVENT_START_DATE || number > EVENT_END_DATE) {
            throw new IllegalArgumentException(NOT_A_VALID_DATE);
        }
        return true;
    }

    public static void validateNumber(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new NumberFormatException(NOT_A_NUMBER_ERROR);
            }
        }
    }

    public static void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(NULL_OR_EMPTY_ERROR);
        }
    }
}
