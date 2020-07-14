package com.codegym.task.task18.task1822;

/* 
Finding data inside a file

*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {

        BufferedReader bufferedConsoleReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bufferedFileReader;
        int searchableId = Integer.parseInt(args[0]);
        String fileName, line;
        String[] lineItems;
        int id;

        try {
            fileName = bufferedConsoleReader.readLine();
            bufferedFileReader = new BufferedReader(new FileReader(fileName));

            while (bufferedFileReader.ready()) {
                line = bufferedFileReader.readLine();
                lineItems = line.split(" ");
                id = Integer.parseInt(lineItems[0]);
                if (id == searchableId) {
                    System.out.println(line);
                    break;
                }
            }

            bufferedFileReader.close();
            bufferedConsoleReader.close();

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
