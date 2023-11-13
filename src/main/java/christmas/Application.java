package christmas;

import christmas.Controller.EventPlannerController;

public class Application {

    public static void main(String[] args) {
        EventPlannerController eventPlannerController = new EventPlannerController();
        eventPlannerController.evenStart();
    }
}