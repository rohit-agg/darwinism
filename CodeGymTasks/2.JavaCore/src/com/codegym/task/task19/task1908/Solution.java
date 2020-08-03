package com.codegym.task.task19.task1908;

/* 
Picking out numbers

*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String fileName1 = bufferedReader.readLine();
        String fileName2 = bufferedReader.readLine();

        BufferedReader bufferedFileReader = new BufferedReader(new FileReader(fileName1));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName2));

        String line;
        String[] words;

        while (bufferedFileReader.ready()) {

            line = bufferedFileReader.readLine();
            words = line.split(" ");
            for (String word : words) {

                try {

                    Integer number = Integer.parseInt(word);
                    bufferedWriter.write(number.toString() + " ");

                } catch (NumberFormatException exception) {
                }
            }
        }

        bufferedFileReader.close();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
