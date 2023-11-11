package christmas.Controller;

import static christmas.Util.OrderMenuValidator.*;

import christmas.View.InputView;

public class EventController {
    static final String DECEMBER_EVENT_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public void run(){
        System.out.println(DECEMBER_EVENT_MESSAGE); // 인삿말
        String visitDate = InputView.readVisitDate();
        proccessOrder();
    }

    public static void proccessOrder() {
        try {
            String orderedMenu = InputView.readMenuOrder();
            checkValidOrderForm(orderedMenu);
            checkValidOrderQuantity(orderedMenu);
            checkDuplicateMenu(orderedMenu);
            checkMaxOrderQuantity(orderedMenu);
            checkExistingMenu(orderedMenu);
            checkMenuContainsOnlyDrink(orderedMenu);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            proccessOrder();
        }
    }
}
