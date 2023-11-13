package christmas.Controller;

import christmas.Domain.OrderMenu;
import christmas.Util.OrderMenuValidator;
import christmas.View.InputView;
import christmas.View.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventPlannerController {
    public void evenStart() {
        String visitDate = takeVisitDate();

        String orderMenu = takeOrder();
        List<OrderMenu> orderMenuList = makeOrderMenuList(orderMenu); // 누구의 책임으로 줄 것인가?
        OutputView.printOrderMenu(orderMenu);

    }
    public List<OrderMenu> makeOrderMenuList(String orderMenu){
        List<OrderMenu> menuList = new ArrayList<>();
        String pattern = "([가-힣]+)-([1-9]\\d*|0*[1-9]\\d+)(?:,|$)";

        // 정규표현식에 맞는 패턴을 생성
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(orderMenu);

        // 매칭된 결과를 출력
        while (matcher.find()) {
            String menuName = matcher.group(1);
            int menuQuantity = Integer.parseInt(matcher.group(2));
            menuList.add(new OrderMenu(menuName, menuQuantity));
        }
        return menuList;
    }

    public String takeVisitDate() {
        try {
            OutputView.printWelcomeMessage();
            String visitDate = InputView.readVisitDate();
            return visitDate;
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return takeVisitDate();
        }
    }

    public String takeOrder() {
        try {
            String orderMenu = InputView.readMenuOrder();
            validateOrderMenuForm(orderMenu);
            return orderMenu;
        } catch (NumberFormatException e) {
            OutputView.printException(e);
            return takeOrder();
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return takeOrder();
        }
    }

    public void validateOrderMenuForm(String orderedMenu) {
        OrderMenuValidator.checkValidOrderForm(orderedMenu);
        OrderMenuValidator.checkValidOrderQuantity(orderedMenu);
        OrderMenuValidator.checkDuplicateMenu(orderedMenu);
        OrderMenuValidator.checkMaxOrderQuantity(orderedMenu);
        OrderMenuValidator.checkExistingMenu(orderedMenu);
        OrderMenuValidator.checkMenuContainsOnlyDrink(orderedMenu);
    }
}
