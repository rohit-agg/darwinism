package com.codegym.task.task18.task1821;

/* 
Symbol frequency

*/

import java.io.FileReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {

        try {

            FileReader fileReader = new FileReader(args[0]);
            int[] allSymbols = new int[255];
            int symbol, i;

            for (i = 0; i<255; i++) {
                allSymbols[i] = 0;
            }

            while (fileReader.ready()) {
                symbol = fileReader.read();
                allSymbols[symbol]++;
            }

            for (i = 0; i < 255; i++) {
                if (allSymbols[i] > 0) {
                    System.out.format("%c %d\n", (char) i, allSymbols[i]);
                }
            }

            fileReader.close();

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
