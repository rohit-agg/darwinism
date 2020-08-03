package com.codegym.task.task20.task2025;

import java.util.*;

/* 
Number algorithms

*/
public class Solution {

    public static long[] getNumbers(long N) {

        long[] result = null;
        List<Long> numbers = new ArrayList<>();
        long num, temp, sum;
        List<Long> digits;
        int count = 1, counter, digitCount = 0;

        int power;
        Map<Integer, Long> powers = new HashMap<>();

        temp = N;
        while(temp != 0) {
            temp /= 10;
            digitCount++;
        }

        for (int i = 1; i <= digitCount; i++) {
            for (int j = 0; j <= 9; j++) {
                power = (i * 10) + j;
                powers.put(power, (long) Math.pow(j, i));
            }
        }

        for (num = 1; num < N; num++) {

            sum = 0;
            count = 0;
            temp = num;
            digits = new ArrayList<>();

            while(temp != 0) {
                digits.add(temp % 10);
                temp /= 10;
                count++;
            }

            for (Long digit : digits) {
                power = (int) ((count * 10) + digit);
                sum += powers.get(power);
            }

            if (sum == num) {
                numbers.add(num);
            }
        }

        result = new long[numbers.size()];
        for (counter = 0; counter < numbers.size(); counter++) {
            result[counter] = numbers.get(counter);
        }

        return result;
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(100000000)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
    }
}
