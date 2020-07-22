package com.codegym.task.task19.task1923;

/* 
Words with numbers

*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Solution {
    public static void main(String[] args) throws Exception {

        String file1 = args[0],
                file2 = args[1];

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file1));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2));
        String line;
        String[] items;

        while(bufferedReader.ready()) {

            line = bufferedReader.readLine();
            items = line.split(" ");
            for (String item : items) {
                for (char c : item.toCharArray()) {
                    if (c >= '0' && c <= '9') {
                        bufferedWriter.write(item + " ");
                        break;
                    }
                }
            }
        }

        bufferedWriter.close();
        bufferedReader.close();
    }
}
