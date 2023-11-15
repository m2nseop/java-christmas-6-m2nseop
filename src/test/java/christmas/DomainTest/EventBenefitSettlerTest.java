package christmas.DomainTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.Domain.EventBenefitSettler;
import christmas.Domain.EventPlanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventBenefitSettlerTest {
    @DisplayName("올바른 범위의 방문날짜인가?")
    @ParameterizedTest
    @ValueSource(strings = {"0", "33", "-31"})
    void checkDuplicateMenuTest(String visitDate) {
        assertThatThrownBy(() -> new EventBenefitSettler(visitDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
