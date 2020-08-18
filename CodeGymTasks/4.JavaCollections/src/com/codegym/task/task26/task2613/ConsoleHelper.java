package com.codegym.task.task26.task2613;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {

        String input = null;
        try {
            input = bis.readLine();
        } catch (Exception e) {
        }
        return input;
    }
}
