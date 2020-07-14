package com.codegym.task.task19.task1910;

/* 
Punctuation

*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = bufferedReader.readLine(),
                file2 = bufferedReader.readLine();
        int text;

        BufferedReader bufferedFileReader = new BufferedReader(new FileReader(file1));
        BufferedWriter bufferedFileWriter = new BufferedWriter(new FileWriter(file2));

        while (bufferedFileReader.ready()) {

            text = bufferedFileReader.read();
            if ((text >= 'a' && text <= 'z') || (text >= 'A' && text <= 'Z') || (text >= '0' && text <= '9') || text == ' ') {
                bufferedFileWriter.write((char) text);
            }
        }

        bufferedFileWriter.close();
        bufferedFileReader.close();
        bufferedReader.close();
    }
}
