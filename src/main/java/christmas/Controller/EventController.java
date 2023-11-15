package christmas.Controller;

import christmas.Domain.EventBenefitSettler;
import christmas.Domain.EventPlanner;
import christmas.Event.EventBadge;
import christmas.View.InputView;
import christmas.View.OutputView;
import java.util.Map;

public class EventController {
    public EventPlanner eventPlanner;
    public EventBenefitSettler eventBenefitSettler;

    public void eventStart() {
        OutputView.printWelcomeMessage();
        takeVisitDate(); // 방문일 접수

        takeOrder(); // 주문 받기

        int preDiscountTotalOrderPrice = handlePreDiscountTotalOrderPrice();

        handleBenefit(preDiscountTotalOrderPrice);
    }

    public void takeVisitDate() {
        try {
            String visitDate = InputView.readVisitDate();// 분리할 것인지 고민
            eventBenefitSettler = new EventBenefitSettler(visitDate);
        } catch (NumberFormatException e) {
            OutputView.printException(e);
            takeVisitDate();
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            takeVisitDate();
        }
    }

    public void takeOrder() {
        try {
            String orderMenu = InputView.readMenuOrder();
            eventPlanner = new EventPlanner(orderMenu);
            OutputView.printOrderMenu(orderMenu);
        } catch (NumberFormatException e) {
            OutputView.printException(e);
            takeOrder();
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            takeOrder();
        }
    }

    public void handleBenefit(int preDiscountTotalOrderPrice) {
        Map<String, Integer> categoryCount = eventPlanner.calculateCategoryCount();

        Map<String, Integer> receivedBenefits = handleReceivedBenefits(categoryCount, preDiscountTotalOrderPrice);
        int benefitsTotalAmount = handlebenefitsTotalAmount(receivedBenefits);
        handleDiscountedTotalAmount(receivedBenefits, preDiscountTotalOrderPrice);
        handleEventBadge(benefitsTotalAmount);
    }

    public Map<String, Integer> handleReceivedBenefits(Map<String, Integer> categoryCount, int preDiscountTotalOrderPrice){
        Map<String, Integer> receivedBenefits = eventBenefitSettler.calculateReceivedBenefits(categoryCount,
                preDiscountTotalOrderPrice);
        OutputView.printReceivedBenefits(receivedBenefits);
        return receivedBenefits;
    }

    public int handlebenefitsTotalAmount(Map<String, Integer> receivedBenefits){
        int benefitsTotalAmount = eventBenefitSettler.caculateBenefitsTotalAmount(receivedBenefits);
        OutputView.printBenefitsTotalAmount(benefitsTotalAmount);
        return benefitsTotalAmount;
    }

    public void handleDiscountedTotalAmount(Map<String, Integer> receivedBenefits, int preDiscountTotalOrderPrice){
        int discountedTotalAmount = eventBenefitSettler.calculateDiscountedTotalAmount(receivedBenefits,
                preDiscountTotalOrderPrice);
        OutputView.printDiscountedTotalAmount(discountedTotalAmount);
    }

    public void handleEventBadge(int benefitsTotalAmount){
        String eventBadgeType = EventBadge.findMyEventBadgeType(benefitsTotalAmount);
        OutputView.printEventBadgeType(eventBadgeType);
    }

    public int handlePreDiscountTotalOrderPrice() {
        int preDiscountTotalOrderPrice = eventPlanner.calculatePreDiscountTotalOrderPrice();
        OutputView.printPreDicountTotalOrderPrice(preDiscountTotalOrderPrice);
        OutputView.printGiftMenu(preDiscountTotalOrderPrice);
        return preDiscountTotalOrderPrice;
    }
}
