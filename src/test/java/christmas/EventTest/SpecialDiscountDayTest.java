package christmas.EventTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.Event.EventBadge;
import christmas.Event.SpecialDiscountDay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpecialDiscountDayTest {
    @DisplayName("특별 할인 날짜 확인 테스트")
    @Test
    void isSpecialDayTest() {
        assertThat(SpecialDiscountDay.isSpecialDay(3)).isEqualTo(true);
        assertThat(SpecialDiscountDay.isSpecialDay(10)).isEqualTo(true);
        assertThat(SpecialDiscountDay.isSpecialDay(17)).isEqualTo(true);
        assertThat(SpecialDiscountDay.isSpecialDay(24)).isEqualTo(true);
        assertThat(SpecialDiscountDay.isSpecialDay(25)).isEqualTo(true);
        assertThat(SpecialDiscountDay.isSpecialDay(31)).isEqualTo(true);
        assertThat(SpecialDiscountDay.isSpecialDay(11)).isEqualTo(false);
    }
}
