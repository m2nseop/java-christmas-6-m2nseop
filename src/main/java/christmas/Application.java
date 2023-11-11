package christmas;

import christmas.Menu.AppetizerMenu;
import christmas.Menu.DessertMenu;
import christmas.Menu.DrinkMenu;
import christmas.Menu.MainMenu;
import christmas.View.InputView;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static final String ERROR_MESSAGE = "[ERROR] ";
    public static final String INVALID_ORDER_FORM_ERROR = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String INVALID_MAX_ORDER_QAUNTITY_ERROR = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요";
    public static final String MENU_CONTAIN_ONLY_DRINK_ERROR = "음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.";
    public static final String NOT_EXIST_MENU_ERROR = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final int MAX_ORDER_QUANTITY = 20;

    public static void main(String[] args) {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."); // 인삿말
        String visitDate = InputView.readVisitDate();
        proccessOrder();
    }

    public static void proccessOrder() {
        try {
            String orderedMenu = InputView.readMenuOrder();
            checkValidOrderForm(orderedMenu);
            checkValidOrderQuantity(orderedMenu);
            checkDuplicateMenu(orderedMenu);
            checkMaxOrderQuantity(orderedMenu);
            checkExistingMenu(orderedMenu);
            checkMenuContainsOnlyDrink(orderedMenu);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            proccessOrder();
        }
    }

    public static void checkValidOrderForm(String input) {
        String pattern = "^[가-힣]+-[0-9]+(,\\s*[가-힣]+-[0-9]+)*$"; // 메뉴 입력 형식
        if (!input.matches(pattern)) {
            throw new IllegalArgumentException(ERROR_MESSAGE + INVALID_ORDER_FORM_ERROR);
        }
    }

    public static void checkValidOrderQuantity(String input) { // 개수가 0이상인가?
        String pattern = "^[가-힣]+-([1-9]\\d*|0*[1-9]\\d+)(,\\s*[가-힣]+-([1-9]\\d*|0*[1-9]\\d+))*$";
        if (!input.matches(pattern)) {
            throw new IllegalArgumentException(ERROR_MESSAGE + INVALID_ORDER_FORM_ERROR);
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
                throw new IllegalArgumentException(ERROR_MESSAGE + INVALID_ORDER_FORM_ERROR);
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
            throw new IllegalArgumentException(ERROR_MESSAGE + INVALID_MAX_ORDER_QAUNTITY_ERROR);
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
            if(isDrinkMenu(matcher.group(1))){
                drinkNum++;
            }
            menuNum++;
        }
        if (drinkNum == menuNum) {
            throw new IllegalArgumentException(ERROR_MESSAGE + MENU_CONTAIN_ONLY_DRINK_ERROR);
        }
    }

    public static boolean isDrinkMenu(String menu){
        for (DrinkMenu drink : DrinkMenu.values()) {
            if(menu.equals(drink.getFoodName())){
                return true;
            }
        }
        return false;
    }

    public static void checkExistingMenu(String input){
        String pattern = "([가-힣]+)-([1-9]\\d*|0*[1-9]\\d+)(?:,|$)";
        // 정규표현식에 맞는 패턴을 생성
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(input);

        while (matcher.find()) {
            if(!isExistMenu(matcher.group(1))) {
                throw new IllegalArgumentException(ERROR_MESSAGE + NOT_EXIST_MENU_ERROR);
            }
        }
    }

    public static boolean isExistMenu(String menu){
        if( isAppetizerMenu(menu) || isMainMenu(menu) || isDessertMenu(menu) || isDrinkMenu(menu)){
            return true;
        }
        return false;
    }

    public static boolean isMainMenu(String menu){
        for (MainMenu main : MainMenu.values()) {
            System.out.println(menu);
            System.out.println(menu.equals(main.getFoodName()));
            if(menu.equals(main.getFoodName())){
                return true;
            }
        }
        return false;
    }

    public static boolean isDessertMenu(String menu){
        for (DessertMenu dessert : DessertMenu.values()) {
            if(menu.equals(dessert.getFoodName())){
                return true;
            }
        }
        return false;
    }

    public static boolean isAppetizerMenu(String menu){
        for (AppetizerMenu appetizer : AppetizerMenu.values()) {
            if(menu.equals(appetizer.getFoodName())){
                return true;
            }
        }
        return false;
    }
}