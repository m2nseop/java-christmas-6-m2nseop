package christmas.Domain;

import christmas.Message.OutputMessage;

public class Menu {
    private final String menuCategory;
    private final String menuName;
    private final int menuPrice;
    private final int menuQuantity;

    public Menu(String menuName, String menuQuantity) {
        validateNumber(menuQuantity);
        validateMenuQuantityZero(menuQuantity);
        checkExistingMenu(menuName);
        this.menuName = menuName;
        this.menuQuantity = Integer.parseInt(menuQuantity);
        this.menuPrice = getMenuPrice(menuName);
        this.menuCategory = getMenuCategory(menuName);
    }

    public static void validateNumber(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new NumberFormatException(OutputMessage.INVALID_ORDER_FORM_ERROR);
            }
        }
    }

    private void validateMenuQuantityZero(String input) { // 개수가 0이상인가?
        if (Integer.parseInt(input) == 0) {
            throw new IllegalArgumentException(OutputMessage.INVALID_ORDER_FORM_ERROR);
        }
    }

    private void checkExistingMenu(String menuName) {
        if(!MenuBoard.isExistingMenu(menuName)){
            throw new IllegalArgumentException(OutputMessage.NOT_EXIST_MENU_ERROR);
        }
    }

    private String getMenuCategory(String menuName) {
        String menuCategory = "";
        for (MenuBoard menu : MenuBoard.values()) {
            if (menu.getMenuName().equals(menuName)) {
                menuCategory = menu.getMenuCategory();
            }
        }
        return menuCategory;
    }

    private int getMenuPrice(String menuName) {
        int menuPrice = 0;
        for (MenuBoard menu : MenuBoard.values()) {
            if (menu.getMenuName().equals(menuName)) {
                menuPrice = menu.getMenuPrice();
            }
        }
        return menuPrice;
    }

    public int getMenuTotalPrice() {
        return this.menuPrice * this.menuQuantity;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public int getMenuQuantity() {
        return this.menuQuantity;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public String getMenuCategory() {
        return this.menuCategory;
    }
}
