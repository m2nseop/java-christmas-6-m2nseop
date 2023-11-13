package christmas;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.Util.OrderMenuValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuOrderTest {

//    @BeforeEach
//    void init(){
//
//    }

    @DisplayName("입력형식 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {
            "해물파스타3,티본스테이크2", // -을 안 넣을 경우
            "해물파스타-3!티본스테이크-2", // 구분자를 콤마(,)로 넣지 않았을 경우
            " 해물 파스타 - 3  , 티본 스테이크   -3 ", // 불필요한 공백문자가 들어간 경우
            "past-3,cake-2" // 영어가 들어간 경우
    })
    void checkValidOrderFormTest(String inputs) {
        assertThatThrownBy(() -> OrderMenuValidator.checkValidOrderForm(inputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문한 메뉴의 개수가 1이상인가?")
    @ParameterizedTest
    @ValueSource(strings = {
            "해물파스타-1,티본스테이크-0",
            "해물파스타-0"
    })
    void checkValidOrderQuantityTest(String inputs) {
        assertThatThrownBy(() -> OrderMenuValidator.checkValidOrderQuantity(inputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("중복된 메뉴를 주문했는가?")
    @ParameterizedTest
    @ValueSource(strings = {
            "해물파스타-2,해물파스타-3,레드와인-2",
            "티본스테이크-1,제로콜라-3,티본스테이크-3"
    })
    void checkDuplicateMenuTest(String inputs) {
        assertThatThrownBy(() -> OrderMenuValidator.checkDuplicateMenu(inputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴 개수가 20초과인가?")
    @ParameterizedTest
    @ValueSource(strings = {
            "양송이수프-2,티본스테이크-10,레드와인-9",
            "티본스테이크-1,제로콜라-3,양송이수프-20"
    })
    void checkMaxOrderQuantityTest(String inputs) {
        assertThatThrownBy(() -> OrderMenuValidator.checkMaxOrderQuantity(inputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴가 음료수만 있는가?")
    @ParameterizedTest
    @ValueSource(strings = {
            "레드와인-2,제로콜라-3,샴페인-2",
            "제로콜라-3"
    })
    void checkMenuContainsOnlyDrinkTest(String inputs) {
        assertThatThrownBy(() -> OrderMenuValidator.checkMenuContainsOnlyDrink(inputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴판에 존재하는 메뉴를 주문했는가?")
    @ParameterizedTest
    @ValueSource(strings = {
            "로제파스타-3,안심스테이크-2,제로콜라-3",
            "티본스테이크-3,크림리조또-3,환타-3"
    })
    void checkExistingMenuTest(String inputs) {
        assertThatThrownBy(() -> OrderMenuValidator.checkExistingMenu(inputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
