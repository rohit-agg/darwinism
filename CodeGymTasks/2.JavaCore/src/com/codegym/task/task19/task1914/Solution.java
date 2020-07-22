package com.codegym.task.task19.task1914;

/* 
Problem solving

*/

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {

        PrintStream consoleStream = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);

        testString.printSomething();
        System.setOut(consoleStream);

        String expression = byteArrayOutputStream.toString();
        String exp = "";
        String[] parts = expression.split("=");
        int num1 = 0, num2 = 0, result = 0;

        if (parts[0].contains("+")) {

            parts = parts[0].split("\\+");
            num1 = Integer.parseInt(parts[0].trim());
            num2 = Integer.parseInt(parts[1].trim());
            exp = "+";
            result = num1 + num2;

        } else if (parts[0].contains("-")) {

            parts = parts[0].split("-");
            num1 = Integer.parseInt(parts[0].trim());
            num2 = Integer.parseInt(parts[1].trim());
            exp = "-";
            result = num1 - num2;

        } else if (parts[0].contains("*")) {

            parts = parts[0].split("\\*");
            num1 = Integer.parseInt(parts[0].trim());
            num2 = Integer.parseInt(parts[1].trim());
            exp = "*";
            result = num1 * num2;
        }

        System.out.println(num1 + " " + exp + " " + num2 + " = " + result);
        byteArrayOutputStream.close();
        printStream.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

