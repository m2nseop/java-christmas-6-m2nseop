package christmas.View;

import christmas.Message.OutputMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutputView {
    public static void printWelcomeMessage() {
        System.out.println(OutputMessage.DECEMBER_EVENT_WELCOME_MESSAGE);
    }

    public static void printException(IllegalArgumentException e) {
        System.out.println(OutputMessage.ERROR_MESSAGE + e.getMessage());
    }

    public static void printOrderedMenu(String orderedMenu) {
        System.out.println("<주문 메뉴>");
        String pattern = "([가-힣]+)-([1-9]\\d*|0*[1-9]\\d+)(?:,|$)";
        // 정규표현식에 맞는 패턴을 생성
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(orderedMenu);

        while (matcher.find()) {
            System.out.println(matcher.group(1) + " " + matcher.group(2) + "개");
        }
    }
}
