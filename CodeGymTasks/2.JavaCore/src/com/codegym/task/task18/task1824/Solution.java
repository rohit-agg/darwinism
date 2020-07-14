package com.codegym.task.task18.task1824;

/* 
Files and exceptions

*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {

        try {

            BufferedReader bufferedConsoleReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName;
            boolean status;

            do {
                fileName = bufferedConsoleReader.readLine();
                status = checkFile(fileName);

            } while(status);

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static boolean checkFile(String fileName) {

        try {

            FileReader fileReader = new FileReader(fileName);
            fileReader.close();
            return true;

        } catch (FileNotFoundException exception) {
            System.out.println(fileName);
            return false;
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return true;
        }
    }
}
