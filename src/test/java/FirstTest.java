import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class FirstTest {

    @Test
    void t1() {

        int rst = 1;
        assertThat(rst).isEqualTo(1);
    }

    @Test
    void t2() {
//        App app = new App();
//        app.run();

        // aaa가 출력되는가?
//        assertThat(result).isEqualTo("aaa");
    }

    @Test
    void t3() {
        // 테스트봇 선입력
//        Scanner sc = new Scanner("종료\n");
//
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        TestApp app = new TestApp();
//        app.run();
        String out = TestBot.run("");

        assertThat(out)
                .contains("명령 )")
                .contains("명언앱을 종료합니다.");
        // 출력값 체크
    }

    @Test
    @DisplayName("명령을 여러번 입력할 수 있다.")
    void t4() {
        String out = TestBot.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                현재를 사랑하라.
                작자미상
                종료
                """);

        // 명령 ) 횟수를 세서 검증해야 됨.
        long count = out.split("명령 \\)").length - 1;

        //검증
        assertThat(count).isEqualTo(3);
    }

    @Test
    @DisplayName("앱 시작시 '== 명언 앱 ==' 출력")
    void t5() {
        // 테스트봇 선입력
        String out = TestBot.run("");

        // 테스트 코드 작성이 어려움.
        // assertJ를 사용법을 잘 모름.
        assertThat(out)
                .containsSubsequence("== 명언 앱 ==", "명언앱을 종료합니다.");
        // 출력값 체크
    }

    @Test
    @DisplayName("등록 - 명언 1개 입력")
    void t6() {
        String out = TestBot.run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(out)
                .containsSubsequence("명언 : ", "작가 : ");
        // 출력값 체크
    }

    @Test
    @DisplayName("등록 - 명언 1개 입력, 명언 번호 출력")
    void t7() {
        String out = TestBot.run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("등록 - 명언 2개 입력, 명언 번호가 증가")
    void t8() {
        String out = TestBot.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(out)
                .contains("1번 명언이 등록되었습니다.")
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("등록 - 명언 2개 입력, 명언 번호가 증가")
    void t9() {
        String out = TestBot.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                """);

        assertThat(out)
                .contains("번호 / 작가 / 명언")
                .contains("-----------------")
                .containsSubsequence("2 / 작자미상 / 과거에 집착하지 마라.", "1 / 작자미상 / 현재를 사랑하라.");
    }

}
