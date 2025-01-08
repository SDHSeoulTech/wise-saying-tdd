package app.standard;

import app.App;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TestBot {
    // 자바 시간에 객체는 static 지양.

    public static String run(String input) {
        Scanner sc = new Scanner(input + "종료\n");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        App app = new App(sc);
        app.run();

        out.toString();

        return out.toString();

    }

    public static void makeSample(int cnt) {
        App app = new App(null);
        app.makeSampleData(cnt); // file 저장
    }
}
