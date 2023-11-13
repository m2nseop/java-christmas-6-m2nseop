package christmas.Util;

import static christmas.Domain.EventOption.MAX_ORDER_QUANTITY;
import christmas.Message.OutputMessage;

import christmas.Domain.Menu;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderMenuValidator {
    public static void checkValidOrderForm(String input) {
        String pattern = "^[가-힣]+-[0-9]+(,\\s*[가-힣]+-[0-9]+)*$"; // 메뉴 입력 형식
        if (!input.matches(pattern)) {
            throw new IllegalArgumentException(OutputMessage.INVALID_ORDER_FORM_ERROR);
        }
    }

    public static void checkValidOrderQuantity(String input) { // 개수가 0이상인가?
        String pattern = "^[가-힣]+-([1-9]\\d*|0*[1-9]\\d+)(,\\s*[가-힣]+-([1-9]\\d*|0*[1-9]\\d+))*$";
        if (!input.matches(pattern)) {
            throw new IllegalArgumentException(OutputMessage.INVALID_ORDER_FORM_ERROR);
        }
    }

    public static void checkDuplicateMenu(String input) {
        // 정규표현식 패턴
        String pattern = "([가-힣]+)-([1-9]\\d*|0*[1-9]\\d+)(?:,|$)";

        // 정규표현식에 맞는 패턴을 생성
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(input);

        Set<String> menuSet = new HashSet<>();
        // 매칭된 결과를 출력
        while (matcher.find()) {
            String menuName = matcher.group(1);
            if (!menuSet.add(menuName)) {
                throw new IllegalArgumentException(OutputMessage.INVALID_ORDER_FORM_ERROR);
            }
        }
    }

    public static void checkMaxOrderQuantity(String input) {
        String pattern = "([가-힣]+)-([1-9]\\d*|0*[1-9]\\d+)(?:,|$)";
        // 정규표현식에 맞는 패턴을 생성
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(input);
        int menuQuantity = 0;
        // 매칭된 결과를 출력
        while (matcher.find()) {
            menuQuantity += Integer.parseInt(matcher.group(2));
        }
        if (menuQuantity > MAX_ORDER_QUANTITY) {
            throw new IllegalArgumentException(OutputMessage.INVALID_MAX_ORDER_QAUNTITY_ERROR);
        }
    }

    public static void checkMenuContainsOnlyDrink(String input) {
        String pattern = "([가-힣]+)-([1-9]\\d*|0*[1-9]\\d+)(?:,|$)";
        // 정규표현식에 맞는 패턴을 생성
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(input);
        int menuNum = 0;
        int drinkNum = 0;
        // 매칭된 결과를 출력

        while (matcher.find()) {
            if (isDrinkMenu(matcher.group(1))) {
                drinkNum++;
            }
            menuNum++;
        }
        if (drinkNum == menuNum) {
            throw new IllegalArgumentException(OutputMessage.MENU_CONTAIN_ONLY_DRINK_ERROR);
        }
    }

    public static boolean isDrinkMenu(String orderMenu) {
        for (Menu menu : Menu.values()) {
            if (orderMenu.equals(menu.getMenuName()) && menu.getMenuCategory().equals("음료")) {
                return true;
            }
        }
        return false;
    }

    public static void checkExistingMenu(String input) {
        String pattern = "([가-힣]+)-([1-9]\\d*|0*[1-9]\\d+)(?:,|$)";
        // 정규표현식에 맞는 패턴을 생성
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(input);

        while (matcher.find()) {
            if (!isExistMenu(matcher.group(1))) {
                throw new IllegalArgumentException(OutputMessage.NOT_EXIST_MENU_ERROR);
            }
        }
    }

//    public static boolean isExistMenu(String menu) {
//        if (isAppetizerMenu(menu) || isMainMenu(menu) || isDessertMenu(menu) || isDrinkMenu(menu)) {
//            return true;
//        }
//        return false;
//    }
    public static boolean isExistMenu(String menu) {
        for (Menu main : Menu.values()) {
            if (menu.equals(main.getMenuName())) {
                return true;
            }
        }
        return false;
    }

//    public static boolean isMainMenu(String menu) {
//        for (Menu main : Menu.values()) {
//            if (menu.equals(main.getFoodName())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static boolean isDessertMenu(String menu) {
//        for (Menu dessert : Menu.values()) {
//            if (menu.equals(dessert.getFoodName())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static boolean isAppetizerMenu(String menu) {
//        for (Menu appetizer : Menu.values()) {
//            if (menu.equals(appetizer.getFoodName())) {
//                return true;
//            }
//        }
//        return false;
//    }
}
