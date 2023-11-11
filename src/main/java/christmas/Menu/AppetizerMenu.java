package christmas.Menu;

public enum AppetizerMenu {
    SHIITAKE_MUSHROOM_SOUP("양송이수프", "6000"),
    TAPAS("타파스","5500"),
    CAESAR_SALAD("시저샐러드","8000");

    private final String foodName;
    private final String foodPrice;

    AppetizerMenu(String foodName, String foodPrice) {
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
