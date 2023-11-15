package christmas.Domain;

import christmas.Event.EventBenefit;
import christmas.Event.EventOption;
import christmas.Event.SpecialDiscountDay;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class EventBenefitSettler {
    private final int visitDate;

    public EventBenefitSettler(String visitDate) {
        this.visitDate = Integer.parseInt(visitDate);
    }

    public Map<String, Integer> calculateReceivedBenefits(Map<String, Integer> categoryCount,
                                                          int preDiscountTotalOrderPrice) {
        Map<String, Integer> receivedBenefits = new HashMap<>();
        if (preDiscountTotalOrderPrice >= EventOption.MINIMUM_ORDER_PRICE_TO_GET_DISCOUNT) {
            caculateChristmasDDayDiscount(receivedBenefits);
            caculateWeekdayDiscount(receivedBenefits, categoryCount);
            caculateWeekendDiscount(receivedBenefits, categoryCount);
            caculateSpecialDiscount(receivedBenefits);
            checkGiftMenu(receivedBenefits, preDiscountTotalOrderPrice);
        }
        return receivedBenefits;
    }

    public int caculateBenefitsTotalAmount(Map<String, Integer> receivedBenefits) {
        int benefitsTotalAmount = 0;
        for (Map.Entry<String, Integer> benefit : receivedBenefits.entrySet()) {
            benefitsTotalAmount += benefit.getValue();
        }
        return benefitsTotalAmount;
    }

    public int calculateDiscountedTotalAmount(Map<String, Integer> receivedBenefits, int preDiscountTotalOrderPrice) {
        int discountedTotalAmount = preDiscountTotalOrderPrice;
        for (Map.Entry<String, Integer> benefit : receivedBenefits.entrySet()) {
            if (benefit.getKey() != EventBenefit.GIFT_MENU.getEventType()) {
                discountedTotalAmount -= benefit.getValue();
            }
        }
        return discountedTotalAmount;
    }

    private void caculateSpecialDiscount(Map<String, Integer> receivedBenefits) {
        if (SpecialDiscountDay.isSpecialDay(this.visitDate)) {
            receivedBenefits.put(EventBenefit.SPECIAL_DISCOUNT.getEventType(),
                    EventBenefit.SPECIAL_DISCOUNT.getBenefitAmount());
        }
    }

    private void checkGiftMenu(Map<String, Integer> receivedBenefits, int preDiscountTotalOrderPrice) {
        if (preDiscountTotalOrderPrice >= EventOption.MINIMUM_ORDER_PRICE_TO_GET_GIFT_MENU) {
            receivedBenefits.put(EventBenefit.GIFT_MENU.getEventType(), EventBenefit.GIFT_MENU.getBenefitAmount());
        }
    }

    private void caculateChristmasDDayDiscount(Map<String, Integer> receivedBenefits) {
        int discount = 0;
        if (this.visitDate <= EventOption.CHRISTMAS_D_DAY_EVENT_END_DATE) {
            discount = EventOption.CHRISTMAS_D_DAY_START_DISCOUNT_AMOUNT;
            discount += EventBenefit.CHRISTMAS_D_DAY_DISCOUNT.getBenefitTotalAmount(this.visitDate - 1);
            receivedBenefits.put(EventBenefit.CHRISTMAS_D_DAY_DISCOUNT.getEventType(), discount);
        }
    }

    private void caculateWeekdayDiscount(Map<String, Integer> receivedBenefits, Map<String, Integer> categoryCount) {
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

    private void caculateWeekendDiscount(Map<String, Integer> receivedBenefits, Map<String, Integer> categoryCount) {
        int discount = 0;
        if (isWeekend()) {
            try {
                int countMain = categoryCount.get(EventBenefit.WEEKEND_DISCOUNT.getEventTarget()); // 있는지를 체크
                discount += EventBenefit.WEEKEND_DISCOUNT.getBenefitTotalAmount(countMain);
                receivedBenefits.put(EventBenefit.WEEKDAY_DISCOUNT.getEventType(), discount);
            } catch (NullPointerException e) {
            }
        }
    }

    private boolean isWeekend() {
        LocalDate date = LocalDate.of(EventOption.EVENT_YEAR, EventOption.EVENT_MONTH, this.visitDate);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
