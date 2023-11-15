package christmas.EventTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.Domain.MenuBoard;
import christmas.Event.EventBadge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventBadgeTest {
    @DisplayName("금액에 맞는 배지를 가져오는지 확인")
    @Test
    void findMyEventBadgeTypeTest() {
        assertThat(EventBadge.findMyEventBadgeType(3000)).isEqualTo(EventBadge.NOTHING.getEventBadgeType());
        assertThat(EventBadge.findMyEventBadgeType(6000)).isEqualTo(EventBadge.STAR.getEventBadgeType());
        assertThat(EventBadge.findMyEventBadgeType(10000)).isEqualTo(EventBadge.TREE.getEventBadgeType());
        assertThat(EventBadge.findMyEventBadgeType(22000)).isEqualTo(EventBadge.SANTA.getEventBadgeType());
    }
}
