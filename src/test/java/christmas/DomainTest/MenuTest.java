package christmas.DomainTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.Domain.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuTest {
    @DisplayName("존재하지 않는 메뉴 이름일 경우 예외처리 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"토마토파스타", "스테이크", "포도와인", "사이다"})
    void checkExistingMenu(String menuName) {
        assertThatThrownBy(() -> new Menu(menuName, "1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴 개수가 숫자가 아닌 경우 예외처리 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"a", "박민서", "1-a", "ㅁ", "1a"})
    void validateNumberTest(String menuQuantity) {
        assertThatThrownBy(() -> new Menu("티본스테이크", menuQuantity))
                .isInstanceOf(NumberFormatException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴 개수가 0인경우 예외처리 테스트")
    @Test
    void validateMenuQuantityZeroTest() {
        assertThatThrownBy(() -> new Menu("티본스테이크", "0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴 개수가 0인경우 예외처리 테스트")
    @Test
    void getMenuTotalPriceTest() {
        Menu menu = new Menu("티본스테이크", "3");
        int expected = 165000;
        int result = menu.getMenuTotalPrice();
        assertThat(expected).isEqualTo(result);
    }
}
