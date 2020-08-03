package com.codegym.task.task19.task1907;

/* 
Counting words

*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();

        BufferedReader bufferedFileReader = new BufferedReader(new FileReader(fileName));
        String line;
        String[] words;
        int count = 0;

        while (bufferedFileReader.ready()) {
            line = bufferedFileReader.readLine();
            words = line.split("[^a-zA-Z]");
            for (String word : words) {
                if (word.equals("world")) {
                    count++;
                }
            }
        }

        System.out.println(count);

        bufferedFileReader.close();
        bufferedReader.close();
    }
}
