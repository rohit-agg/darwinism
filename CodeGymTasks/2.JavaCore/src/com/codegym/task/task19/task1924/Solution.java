package com.codegym.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Replacing numbers

*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "zero");
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        map.put(10, "ten");
        map.put(11, "eleven");
        map.put(12, "twelve");
    }

    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();

        BufferedReader bufferedFileReader = new BufferedReader(new FileReader(fileName));
        String line;
        String[] words;
        int number;

        while(bufferedFileReader.ready()) {

            line = bufferedFileReader.readLine();
            words = line.split(" ");
            for (int i = 0; i < words.length; i++) {
                try {

                    number = Integer.parseInt(words[i]);
                    if (map.containsKey(number)) {
                        words[i] = map.get(number);
                    }

                } catch (NumberFormatException exception) {

                }
            }
            System.out.println(String.join(" ", words));
        }

        bufferedFileReader.close();
    }
}
