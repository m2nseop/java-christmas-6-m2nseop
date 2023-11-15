package christmas.Domain;

import christmas.Util.OrderMenuValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventPlanner {
    private final List<Menu> orderMenu;

    public EventPlanner(String menu) {
        validateOrderMenuForm(menu);
        List<Menu> orderMenu = createOrderMenuRepository(menu);
        this.orderMenu = orderMenu;
    }

    public int calculatePreDiscountTotalOrderPrice() {
        int totalOrderPrice = 0;
        for (Menu menu : orderMenu) {
            totalOrderPrice += menu.getMenuTotalPrice();
        }
        return totalOrderPrice;
    }

    public Map<String, Integer> calculateCategoryCount() {
        Map<String, Integer> categoryCount = new HashMap<>();
        int count = 0;
        for (Menu menu : orderMenu) {
            String menuCategory = menu.getMenuCategory();
            if (categoryCount.containsKey(menuCategory)) {
                count = categoryCount.get(menuCategory) + menu.getMenuQuantity();
                categoryCount.put(menuCategory, count);
            }
            if (!categoryCount.containsKey(menuCategory)) {
                categoryCount.put(menuCategory, menu.getMenuQuantity());
            }
        }
        return categoryCount;
    }

    private List<Menu> createOrderMenuRepository(String orderMenu) {
        List<Menu> menuList = new ArrayList<>();
        String pattern = "([가-힣]+)-([1-9]\\d*|0*[1-9]\\d+)(?:,|$)";

        // 정규표현식에 맞는 패턴을 생성
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(orderMenu);

        // 매칭된 결과를 출력
        while (matcher.find()) {
            String menuName = matcher.group(1);
            int menuQuantity = Integer.parseInt(matcher.group(2));
            menuList.add(new Menu(menuName, menuQuantity));
        }
        return menuList;
    }

    private void validateOrderMenuForm(String orderedMenu) {
        OrderMenuValidator.checkValidOrderForm(orderedMenu);
        OrderMenuValidator.checkValidOrderQuantity(orderedMenu);
        OrderMenuValidator.checkDuplicateMenu(orderedMenu);
        OrderMenuValidator.checkMaxOrderQuantity(orderedMenu);
        OrderMenuValidator.checkExistingMenu(orderedMenu);
        OrderMenuValidator.checkMenuContainsOnlyDrink(orderedMenu);
    }
}
