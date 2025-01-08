package app.domain.wiseSaying;

import app.domain.wisesaying.repository.WiseSayingFileRepository;
import app.global.AppConfig;
import app.standard.TestBot;
import app.standard.Util;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {

    @BeforeAll
    static void beforeAll() {
        AppConfig.setTestMode();
    }

    @BeforeEach
    void before() {
        Util.File.deleteForce(AppConfig.getDbPath());
    }

    @AfterEach
    void after() {
        Util.File.deleteForce(AppConfig.getDbPath());
    }

    @Test
    void t1() {

        int rst = 1;
        assertThat(rst).isEqualTo(1);
    }

    @Test
    void t2() {
//        app.App app = new app.App();
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

    @Test
    @DisplayName("삭제 - id를 이용해서 해당 id의 명언을 삭제할 수 있다. 입력 : 삭제?id=1")
    void t10() {
        String out = TestBot.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=1
                목록
                """);

        assertThat(out)
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("삭제 예외 처리 - 없는 id로 삭제를 시도하면 예외처리 메시지가 나온다.")
    void t11() {
        String out = TestBot.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=1
                삭제?id=1
                목록
                """);

        assertThat(out)
                .contains("1번 명언은 존재하지 않습니다.")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("수정 - id를 이용해서 해당 id의 명언을 수정할 수 있다. 이때 기존의 명언과 작가가 나와야 함. 입력값 - 수정?id=1")
    void t12() {
        String out = TestBot.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                수정?id=1
                새 명언 내용
                새 작가
                목록
                """);

        assertThat(out)
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.")
                .contains("1 / 새 작가 / 새 명언 내용");
    }

    @Test
    @DisplayName("목록 - 명언이 하나도 등록되지 않았을 때")
    void t13() {
        String out = TestBot.run("""
                목록
                """);

        assertThat(out)
                .contains("등록된 명언이 없습니다.");
    }

    @Test
    @DisplayName("빌드")
    void t14() {
        String out = TestBot.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                빌드
                """);

        boolean rst = Util.File.exists(WiseSayingFileRepository.getBuildPath());
        assertThat(rst).isTrue();
    }

    @Test
    @DisplayName("검색 - 검색 타입과 키워드를 입력받아 키워드를 포함하는 명언을 출력한다. ")
    void t15() {
        String out = TestBot.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록?keywordType=content&keyword=과거
                """);

        assertThat(out)
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.");
    }

}
