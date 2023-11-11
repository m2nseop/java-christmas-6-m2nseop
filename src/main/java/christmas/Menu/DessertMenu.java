package christmas.Menu;

public enum DessertMenu {
    CHOCOLATE_CAKE("초코케이크", "15000"),
    ICE_CREAM("아이스크림","5000");
    private final String foodName;
    private final String foodPrice;

    DessertMenu(String foodName, String foodPrice) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public String getFoodName(){
        return this.foodName;
    }
    public String getFoodPrice() {
        return this.foodPrice;
    }
}
