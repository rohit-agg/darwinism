package com.codegym.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Searching for the right lines

*/

public class Solution {
    public static List<String> words = new ArrayList<>();

    static {
        words.add("file");
        words.add("view");
        words.add("In");
    }

    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();

        BufferedReader bufferedFileReader = new BufferedReader(new FileReader(fileName));
        String line;
        String[] lineWords;
        int count;

        while(bufferedFileReader.ready()) {

            line = bufferedFileReader.readLine();
            lineWords = line.split(" ");
            count = 0;
            for (String lineWord : lineWords) {
                if (words.contains(lineWord)) {
                    count++;
                }
            }
            if (count == 2) {
                System.out.println(line);
            }
        }

        bufferedFileReader.close();
    }
}
