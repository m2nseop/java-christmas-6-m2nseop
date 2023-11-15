package christmas.Controller;

import christmas.Domain.EventBenefitSettler;
import christmas.Domain.EventPlanner;
import christmas.Domain.Menu;
import christmas.Event.EventBadge;
import christmas.Util.OrderMenuValidator;
import christmas.View.InputView;
import christmas.View.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventController {
    public EventPlanner eventPlanner;
    public EventBenefitSettler eventBenefitSettler;

    public void eventStart() {
        takeVisitDate(); // 방문일 접수

        takeOrder(); // 주문 받기

        int preDiscountTotalOrderPrice = handleTotalOrderPriceBeforeDiscount();

        handleBenefit(preDiscountTotalOrderPrice);
    }

    public void takeVisitDate() {
        try {
            OutputView.printWelcomeMessage();
            String visitDate = InputView.readVisitDate();
            OrderMenuValidator.isValidDate(visitDate); // 분리할 것인지 고민
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
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            takeOrder();
        }
    }

    public void handleBenefit(int preDiscountTotalOrderPrice) {
        Map<String, Integer> categoryCount = eventPlanner.calculateCategoryCount();

        // receivedBenefits, benefitsTotalAmount
        Map<String, Integer> receivedBenefits = eventBenefitSettler.calculateReceivedBenefits(categoryCount,
                preDiscountTotalOrderPrice);
        int benefitsTotalAmount = eventBenefitSettler.caculateBenefitsTotalAmount(receivedBenefits);
        OutputView.printReceivedBenefits(receivedBenefits);
        OutputView.printBenefitsTotalAmount(benefitsTotalAmount);

        // receivedBenefits, preDiscountTotalOrderPrice
        int discountedTotalAmount = eventBenefitSettler.calculateDiscountedTotalAmount(receivedBenefits,
                preDiscountTotalOrderPrice);
        OutputView.printDiscountedTotalAmount(discountedTotalAmount);

        // benefitsTotalAmount
        String eventBadgeType = EventBadge.findMyEventBadgeType(benefitsTotalAmount);
        OutputView.printEventBadgeType(eventBadgeType);
    }

    public int handleTotalOrderPriceBeforeDiscount() {
        int preDiscountTotalOrderPrice = eventPlanner.calculatePreDiscountTotalOrderPrice();
        OutputView.printPreDicountTotalOrderPrice(preDiscountTotalOrderPrice);
        OutputView.printGiftMenu(preDiscountTotalOrderPrice);
        return preDiscountTotalOrderPrice;
    }
}
