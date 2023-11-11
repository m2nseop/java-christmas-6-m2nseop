package christmas.Menu;

public enum DrinkMenu {
    ZERO_COLA("제로콜라", "3000"),
    RED_WINE("레드와인","60000"),
    CHAMPAGNE("샴페인","25000");

    private final String foodName;
    private final String foodPrice;

    DrinkMenu(String foodName, String foodPrice) {
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
