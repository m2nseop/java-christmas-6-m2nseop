package christmas.DomainTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.Domain.Menu;
import christmas.Domain.MenuBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuBoardTest {
    @DisplayName("있는 메뉴인지 확인하는 함수 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타", "제로콜라", "레드와인", "샴페인",
            "초코케이크", "아이스크림"})
    void isExistingMenuReturnTrue(String menuName) {
        assertThat(MenuBoard.isExistingMenu(menuName)).isEqualTo(true);
    }

    @DisplayName("없는 메뉴인지 확인하는 함수 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"채송이수프", "간바스", "리코타치즈샐러드", "찹스테이크"})
    void isExistingMenuReturnFalse(String menuName) {
        assertThat(MenuBoard.isExistingMenu(menuName)).isEqualTo(false);
    }
}
