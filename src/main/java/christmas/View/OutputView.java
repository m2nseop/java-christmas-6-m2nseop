package christmas.View;

import christmas.Message.InvalidOrderError;

public class OutputView {
    public static void printException(IllegalArgumentException e) {
        System.out.println(InvalidOrderError.ERROR_MESSAGE + e.getMessage());
    }
}
