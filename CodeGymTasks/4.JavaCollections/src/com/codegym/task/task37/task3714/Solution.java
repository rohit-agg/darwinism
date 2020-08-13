package com.codegym.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Ancient Rome

*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman numeral to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result: " + romanToInteger(romanString));
    }

    private static int value(char c) {

        switch (c) {
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X':
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
            default:
                return 0;
        }
    }

    public static int romanToInteger(String s) {

        int number = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i),
            next = (i < s.length() - 1) ? s.charAt(i + 1) : ' ';

            if (value(cur) >= value(next)) {
                number += value(cur);
            } else {
                number -= value(cur);
            }
        }
        return number;
    }
}
