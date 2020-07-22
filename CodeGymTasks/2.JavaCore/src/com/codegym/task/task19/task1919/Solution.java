package com.codegym.task.task19.task1919;

/* 
Calculating salaries

*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
        String line, name;
        String[] items;
        Double salary;
        Map<String, Double> salaries = new TreeMap<>();

        while (bufferedReader.ready()) {

            line = bufferedReader.readLine();
            items = line.split(" ");
            name = items[0];
            salary = Double.valueOf(items[1]);
            if (salaries.containsKey(name)) {
                salaries.replace(name, salaries.get(name) + salary);
            } else {
                salaries.put(name, salary);
            }
        }

        for (Map.Entry<String, Double> set : salaries.entrySet()) {
            System.out.println(set.getKey() + " " + set.getValue());
        }

        bufferedReader.close();
    }
}
