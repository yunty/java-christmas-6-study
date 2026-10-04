package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 혜택이_없는_예시의_금액과_메뉴를_출력한다() {
        run("26", "타파스-1,제로콜라-1");
        assertThat(output()).contains(
                "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!",
                "타파스 1개", "제로콜라 1개",
                "<할인 전 총주문 금액>" + LINE_SEPARATOR + "8,500원",
                "<증정 메뉴>" + LINE_SEPARATOR + "없음",
                "<총혜택 금액>" + LINE_SEPARATOR + "0원",
                "<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "8,500원",
                "<12월 이벤트 배지>" + LINE_SEPARATOR + "없음");
        assertThat(output()).doesNotContain("%n");
    }

    @Test
    void 증정품이_있는_예시의_혜택과_결제금액을_출력한다() {
        run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        assertThat(output()).contains(
                "<할인 전 총주문 금액>" + LINE_SEPARATOR + "142,000원",
                "<증정 메뉴>" + LINE_SEPARATOR + "샴페인 1개",
                "크리스마스 디데이 할인: -1,200원", "평일 할인: -4,046원",
                "특별 할인: -1,000원", "증정 이벤트: -25,000원",
                "<총혜택 금액>" + LINE_SEPARATOR + "-31,246원",
                "<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "135,754원",
                "<12월 이벤트 배지>" + LINE_SEPARATOR + "산타");
        assertThat(output()).doesNotContain("주말 할인:");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "0", "32", "-1", "1.5", "2147483648"})
    void 잘못된_날짜는_다시_입력받는다(String invalidDate) {
        run(invalidDate, "26", "타파스-1,제로콜라-1");
        assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        assertThat(output()).contains("<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "8,500원");
        assertThat(output()).containsOnlyOnce("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "31"})
    void 방문일_양끝_날짜를_입력할_수_있다(String day) {
        run(day, "타파스-1,제로콜라-1");
        assertThat(output()).doesNotContain("[ERROR]");
        assertThat(output()).contains("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    @Test
    void 잘못된_주문은_날짜를_유지하고_주문만_다시_입력받는다() {
        run("26", "제로콜라-1", "타파스-1,제로콜라-1");
        assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        assertThat(output()).containsOnlyOnce("12월 중 식당 예상 방문 날짜는 언제인가요?");
        assertThat(output()).contains("<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "8,500원");
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
