package christmas.Controller;

import christmas.Message.OutputMessage;
import christmas.Util.OrderMenuValidator;
import christmas.View.InputView;
import christmas.View.OutputView;

public class EventController {
    public void evenStart() {
        takeVisitDate();

        takeOrder();
    }

    public void takeVisitDate() {
        try {
            OutputView.printWelcomeMessage();
            String visitDate = InputView.readVisitDate();
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            takeVisitDate();
        }
    }

    public void takeOrder() {
        try {
            String orderedMenu = InputView.readMenuOrder();
            validateOrderedMenuForm(orderedMenu);
            OutputView.printOrderedMenu(orderedMenu);
        } catch (NumberFormatException e) {
            OutputView.printException(e);
            takeOrder();
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            takeOrder();
        }
    }

    public void validateOrderedMenuForm(String orderedMenu) {
        OrderMenuValidator.checkValidOrderForm(orderedMenu);
        OrderMenuValidator.checkValidOrderQuantity(orderedMenu);
        OrderMenuValidator.checkDuplicateMenu(orderedMenu);
        OrderMenuValidator.checkMaxOrderQuantity(orderedMenu);
        OrderMenuValidator.checkExistingMenu(orderedMenu);
        OrderMenuValidator.checkMenuContainsOnlyDrink(orderedMenu);
    }
}
