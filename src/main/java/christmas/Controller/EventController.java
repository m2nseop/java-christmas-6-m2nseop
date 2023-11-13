package christmas.Controller;

import christmas.Message.EventMessage;
import christmas.Util.OrderMenuValidator;
import christmas.View.InputView;
import christmas.View.OutputView;

public class EventController {
    public void run(){
        System.out.println(EventMessage.DECEMBER_EVENT_MESSAGE); // 인삿말
        String visitDate = InputView.readVisitDate();
        proccessOrder();
    }

    public static void proccessOrder() {
        try {
            String orderedMenu = InputView.readMenuOrder();
            OrderMenuValidator.checkValidOrderForm(orderedMenu);
            OrderMenuValidator.checkValidOrderQuantity(orderedMenu);
            OrderMenuValidator.checkDuplicateMenu(orderedMenu);
            OrderMenuValidator.checkMaxOrderQuantity(orderedMenu);
            OrderMenuValidator.checkExistingMenu(orderedMenu);
            OrderMenuValidator.checkMenuContainsOnlyDrink(orderedMenu);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            proccessOrder();
        }

    }
}
