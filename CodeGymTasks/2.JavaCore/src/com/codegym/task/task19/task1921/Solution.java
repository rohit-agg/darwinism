package com.codegym.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* 
John Johnson

*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        String fileName = args[0];
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line, name;
        String[] words;
        int day, month, year;

        while(bufferedReader.ready()) {

            line = bufferedReader.readLine();
            words = line.split(" ");

            year = Integer.parseInt(words[words.length - 1]);
            day = Integer.parseInt(words[words.length - 2]);
            month = Integer.parseInt(words[words.length - 3]);
            words = Arrays.asList(words).subList(0, words.length - 3).toArray(new String[words.length - 3]);

            Person person = new Person(String.join(" ", words), new Date(year - 1900, month - 1, day));
            PEOPLE.add(person);
        }

        bufferedReader.close();
    }
}
