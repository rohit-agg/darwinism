package com.codegym.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 
Threads and bytes

*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {

        BufferedReader bufferedConsoleReader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        try {

            while (!(line = bufferedConsoleReader.readLine()).equals("exit")) {
                ReadThread readThread = new ReadThread(line);
                readThread.start();
            }

            bufferedConsoleReader.close();

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static class ReadThread extends Thread {

        String fileName;


        public ReadThread(String fileName) throws IOException {
            // Implement constructor body
            this.fileName = fileName;
        }

        @Override
        public void run() {

            int[] bytes = new int[255];
            int i, b, max, maxB;

            for (i = 0; i < 255; i++) {
                bytes[i] = 0;
            }

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
                while (bufferedReader.ready()) {
                    b = bufferedReader.read();
                    bytes[b]++;
                }

                maxB = 0;
                max = bytes[maxB];
                for (i = 1; i < 255; i++) {
                    if (bytes[i] > max) {
                        maxB = i;
                        max = bytes[i];
                    }
                }
                resultMap.put(fileName, maxB);

                bufferedReader.close();

            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
        // Implement file reading here
    }
}
