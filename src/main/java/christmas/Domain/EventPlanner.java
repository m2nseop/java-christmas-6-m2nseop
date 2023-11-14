package christmas.Domain;

import christmas.Util.CommaFormatter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EventPlanner {
    private final List<Menu> orderMenu;
    private final int visitDate;
    public EventPlanner(List<Menu> orderMenu, String visitDate) {
        this.orderMenu = orderMenu;
        this.visitDate = Integer.parseInt(visitDate);
    }
    public int calculatePreDiscountTotalOrderPrice(){
        int totalOrderPrice = 0;
        for(Menu menu : orderMenu){
            totalOrderPrice += menu.getMenuPrice() * menu.getMenuQuantity();
        }
        return totalOrderPrice;
    }

    public Map<String, Integer> calculateCategoryCount(){
        Map<String, Integer> categoryCount = new HashMap<>();
        int count = 0;
        for(Menu menu : orderMenu){
            String menuCategory = menu.getMenuCategory();
            if(categoryCount.containsKey(menuCategory)){
                count = categoryCount.get(menuCategory) + menu.getMenuQuantity();
                categoryCount.put(menuCategory, count);
            }
            if(!categoryCount.containsKey(menuCategory)){
                categoryCount.put(menuCategory, menu.getMenuQuantity());
            }
        }
        return categoryCount;
    }

    public Map<String, Integer> calculateReceivedBenefits(Map<String, Integer> categoryCount, int preDiscountTotalOrderPrice){
        Map<String, Integer> receivedBenefits = new HashMap<>();
        caculateChristmasDDayDiscount(receivedBenefits);
        caculateWeekdayDiscount(receivedBenefits, categoryCount);
        caculateWeekendDiscount(receivedBenefits, categoryCount);
        caculateSpecialDiscount(receivedBenefits);
        checkGiftMenu(receivedBenefits, preDiscountTotalOrderPrice);
        return receivedBenefits;
    }
    public int caculateBenefitsTotalAmount(Map<String, Integer> receivedBenefits){
        int benefitsTotalAmount = 0;
        for (Map.Entry<String, Integer> benefit : receivedBenefits.entrySet()) {
            benefitsTotalAmount += benefit.getValue();
        }
        return benefitsTotalAmount;
    }

    public void caculateSpecialDiscount(Map<String, Integer> receivedBenefits){
        for(SpecialDiscountDay day : SpecialDiscountDay.values()){
            if(day.getSpecialEventDay() == this.visitDate){
                receivedBenefits.put(EventBenefit.SPECIAL_DISCOUNT.getEventType(), EventBenefit.SPECIAL_DISCOUNT.getBenefitAmount());
            }
        }
    }

    public void checkGiftMenu(Map<String, Integer> receivedBenefits, int preDiscountTotalOrderPrice){
        if(preDiscountTotalOrderPrice >= EventOption.MINIMUM_ORDER_PRICE_TO_GET_GIFT_MENU){
            receivedBenefits.put(EventBenefit.GIFT_MENU.getEventType(), EventBenefit.GIFT_MENU.getBenefitAmount());
        }
    }

    public void caculateChristmasDDayDiscount(Map<String, Integer> receivedBenefits){
        int discount = 0;
        if(visitDate <= EventOption.CHRISTMAS_D_DAY_EVENT_END_DATE){
            discount = EventOption.CHRISTMAS_D_DAY_START_DISCOUNT_AMOUNT;
            discount += EventBenefit.CHRISTMAS_D_DAY_DISCOUNT.getBenefitTotalAmount(this.visitDate - 1);
            receivedBenefits.put(EventBenefit.CHRISTMAS_D_DAY_DISCOUNT.getEventType(), discount);
        }
    }

    public void caculateWeekdayDiscount(Map<String, Integer> receivedBenefits, Map<String, Integer> categoryCount) {
        int discount = 0;
        if (!isWeekend()) {
            try {
                int countDessert = categoryCount.get(EventBenefit.WEEKDAY_DISCOUNT.getEventTarget()); // 있는지를 체크
                discount += EventBenefit.WEEKDAY_DISCOUNT.getBenefitTotalAmount(countDessert);
                receivedBenefits.put(EventBenefit.WEEKDAY_DISCOUNT.getEventType(), discount);
            } catch (NullPointerException e) {
            }
        }
    }

    public void caculateWeekendDiscount(Map<String, Integer> receivedBenefits, Map<String, Integer> categoryCount){
        int discount = 0;
        if(isWeekend()) {
            int countMain = categoryCount.get(EventBenefit.WEEKEND_DISCOUNT.getEventTarget()); // 있는지를 체크
            discount += EventBenefit.WEEKEND_DISCOUNT.getBenefitTotalAmount(countMain);
            receivedBenefits.put(EventBenefit.WEEKDAY_DISCOUNT.getEventType(), discount);
        }
    }

    private boolean isWeekend() {
        LocalDate date = LocalDate.of(EventOption.EVENT_YEAR, EventOption.EVENT_MONTH, this.visitDate);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
