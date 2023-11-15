package christmas.Domain;

public class Menu {
    private final String menuCategory;
    private final String menuName;
    private final int menuPrice;
    private final int menuQuantity;

    public Menu(String menuName, int menuQuantity) {
        this.menuName = menuName;
        this.menuQuantity = menuQuantity;
        this.menuPrice = getMenuPrice(menuName);
        this.menuCategory = getMenuCategory(menuName);
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
