package christmas;

import christmas.View.InputView;

public class Application {
    public static void main(String[] args) {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."); // 인삿말
        String visitDate = InputView.readVisitDate();
        System.out.println(visitDate);
    }
}
