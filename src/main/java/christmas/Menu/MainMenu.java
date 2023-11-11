package christmas.Menu;

public enum MainMenu {
    T_BONE_STEAK("티본스테이크", "55000"),
    BARBECUE_RIBS("바비큐립","54000"),
    SEAFOOD_PASTA("해산물파스타","35000"),
    CHRISTMAS_PASTA("크리스마스파스타","25000");

    private final String foodName;
    private final String foodPrice;

    MainMenu(String foodName, String foodPrice) {
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
