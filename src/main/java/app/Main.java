package app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        App app = new App(new Scanner(System.in));

        app.run();
    }
}
