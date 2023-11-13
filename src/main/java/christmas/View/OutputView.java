package christmas.View;

import christmas.Domain.EventOption;
import christmas.Message.OutputMessage;
import christmas.Util.CommaFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutputView {
    public static void printWelcomeMessage() {
        System.out.println(OutputMessage.DECEMBER_EVENT_WELCOME_MESSAGE);
    }

    public static void printException(IllegalArgumentException e) {
        System.out.println(OutputMessage.ERROR_MESSAGE + e.getMessage());
    }

    public static void printOrderMenu(String orderedMenu) {
        System.out.println(OutputMessage.ORDER_MENU_MESSAGE);
        String pattern = "([가-힣]+)-([1-9]\\d*|0*[1-9]\\d+)(?:,|$)";
        // 정규표현식에 맞는 패턴을 생성
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(orderedMenu);

        while (matcher.find()) {
            System.out.println(matcher.group(1) + " " + matcher.group(2) + "개");
        }
    }
    public static void printPreDicountTotalOrderPrice(int preDicountTotalOrderPrice){
        System.out.println(OutputMessage.PRE_DISCOUNT_TOTAL_PRICE_MESSAGE);
        String price = CommaFormatter.formatWithComma(preDicountTotalOrderPrice);
        System.out.println(price + "원");
    }
    public static void printGiftMenu(int preDiscountTotalOrderPrice){
        System.out.println(OutputMessage.EVENT_GIFT_MENU_MESSAGE);
        if(preDiscountTotalOrderPrice >= 120000){
            System.out.println(EventOption.GIFT_MENU + " " + EventOption.GIFT_MENU_QUANTITY + "개");
        }
        if(preDiscountTotalOrderPrice < 120000){
            System.out.println("없음");
        }
    }
}
