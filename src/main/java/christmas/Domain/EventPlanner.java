package christmas.Domain;

import java.util.List;

public class EventPlanner {
    private final List<Menu> orderMenu;
    private final String visitDate;
    public EventPlanner(List<Menu> orderMenu, String visitDate) {
        this.orderMenu = orderMenu;
        this.visitDate = visitDate;
    }
    public int calculatePreDiscountTotalOrderPrice(){
        int totalOrderPrice = 0;
        for(Menu menu : orderMenu){
            totalOrderPrice += menu.getMenuPrice() * menu.getMenuQuantity();
        }
        return totalOrderPrice;
    }
}
