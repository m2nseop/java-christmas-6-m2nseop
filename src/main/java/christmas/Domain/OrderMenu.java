package christmas.Domain;

public class OrderMenu {
    private String menuCategory;
    private String menuName;
    private int menuPrice;
    private int menuQuantity;

    public OrderMenu(String menuName, int menuQuantity){
        this.menuName = menuName;
        this.menuQuantity = menuQuantity;
        this.menuPrice = getMenuPrice(menuName);
        this.menuCategory = getMenuCategory(menuName);
    }

    private String getMenuCategory(String menuName){
        String menuCategory = "";
        for(Menu menu : Menu.values()){
            if(menu.getMenuName().equals(menuName)){
                menuCategory = menu.getMenuCategory();
            }
        }
        return menuCategory;
    }
    private int getMenuPrice(String menuName){
        int menuPrice = 0;
        for(Menu menu : Menu.values()){
            if(menu.getMenuName().equals(menuName)){
                menuPrice =  menu.getMenuPrice();
            }
        }
        return menuPrice;
    }
}
