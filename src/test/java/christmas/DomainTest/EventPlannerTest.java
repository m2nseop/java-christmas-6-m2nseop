package christmas.DomainTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.Domain.EventPlanner;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventPlannerTest {

    @DisplayName("입력형식 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {
            "해물파스타3,티본스테이크2", // -을 안 넣을 경우
            "해물파스타-3!티본스테이크-2", // 구분자를 콤마(,)로 넣지 않았을 경우
            " 해물 파스타 - 3  , 티본 스테이크   -3 ", // 불필요한 공백문자가 들어간 경우
            "past-3,cake-2", // 영어가 들어간 경우
            ",,", // 아무것도 입력하지 않았을 때
            ",",
            ""
    })
    void checkValidOrderFormTest(String orderMenu) {
        assertThatThrownBy(() -> new EventPlanner(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("중복된 메뉴를 주문했는가?")
    @ParameterizedTest
    @ValueSource(strings = {
            "해물파스타-2,해물파스타-3,레드와인-2",
            "티본스테이크-1,제로콜라-3,티본스테이크-3"
    })
    void checkDuplicateMenuTest(String orderMenu) {
        assertThatThrownBy(() -> new EventPlanner(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴 개수가 20초과인가?")
    @ParameterizedTest
    @ValueSource(strings = {
            "양송이수프-2,티본스테이크-10,레드와인-9",
            "티본스테이크-1,제로콜라-3,양송이수프-20"
    })
    void checkMaxOrderQuantityTest(String orderMenu) {
        assertThatThrownBy(() -> new EventPlanner(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴가 음료수만 있는가?")
    @ParameterizedTest
    @ValueSource(strings = {
            "레드와인-2,제로콜라-3,샴페인-2",
            "제로콜라-3"
    })
    void checkMenuContainsOnlyDrinkTest(String orderMenu) {
        assertThatThrownBy(() -> new EventPlanner(orderMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.");
    }

    @DisplayName("할인전 주문 메뉴 총 가격 계산 함수 테스트")
    @Test
    void calculatePreDiscountTotalOrderPriceTest() {
        EventPlanner eventPlanner = new EventPlanner("티본스테이크-2,제로콜라-3,샴페인-2");
        assertThat(eventPlanner.calculatePreDiscountTotalOrderPrice()).isEqualTo(169000);
    }

    @DisplayName("카테고리별 메뉴 개수 계산 함수 테스트")
    @Test
    void calculateCategoryCountTest() {
        EventPlanner eventPlanner = new EventPlanner("초코케이크-2,티본스테이크-2,크리스마스파스타-1,제로콜라-3,타파스-2,샴페인-2");
        Map<String, Integer> categoryCount = new HashMap<>();
        categoryCount.put("디저트", 2);
        categoryCount.put("메인", 3);
        categoryCount.put("음료", 5);
        categoryCount.put("에피타이저", 2);
        assertThat(eventPlanner.calculateCategoryCount()).isEqualTo(categoryCount);
    }
}
