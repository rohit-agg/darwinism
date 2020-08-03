package com.codegym.task.task19.task1927;

/* 
Contextual advertising

*/

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {

        PrintStream printStream = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream1 = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream1);

        testString.printSomething();

        String output = byteArrayOutputStream.toString();
        printStream1.close();
        System.setOut(printStream);

        String[] lines = output.split("\n");
        int i = 0;

        for (String line : lines) {
            System.out.println(line);
            i++;
            if (i == 2) {
                System.out.println("CodeGym - online Java courses");
                i = 0;
            }
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
