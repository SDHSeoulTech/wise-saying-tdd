import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        test2();
    }

    public static void test2() {

    }

    public static void test1() {
        //System.in --> 표준 입력

        // 1. 입력을 봇에게 맡길 수 있다.
        Scanner scan = new Scanner("등록\n현재를 사랑하라.\n작자미상\n");

        String val1 = scan.nextLine();
        System.out.println(val1);

        String val2 = scan.nextLine();
        System.out.println(val2);

        String val3 = scan.nextLine();
        System.out.println(val3);

        //테스트 봇 만들기
        TestApp app = new TestApp();
        app.run();
    }

}
