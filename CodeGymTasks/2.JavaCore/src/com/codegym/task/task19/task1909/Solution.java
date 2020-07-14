package com.codegym.task.task19.task1909;

/* 
Changing punctuation marks

*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = bufferedReader.readLine(),
                file2 = bufferedReader.readLine(),
        text;

        BufferedReader bufferedFileReader = new BufferedReader(new FileReader(file1));
        BufferedWriter bufferedFileWriter = new BufferedWriter(new FileWriter(file2));

        while (bufferedFileReader.ready()) {

            text = bufferedFileReader.readLine();
            text = text.replace(".", "!");
            bufferedFileWriter.write(text);
            bufferedFileWriter.newLine();
        }

        bufferedFileWriter.close();
        bufferedFileReader.close();
        bufferedReader.close();
    }
}
