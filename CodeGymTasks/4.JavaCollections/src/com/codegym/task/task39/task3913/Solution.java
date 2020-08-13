package com.codegym.task.task39.task3913;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;

public class Solution {
    public static void main(String[] args)  {
        LogParser logParser = new LogParser(Paths.get("/Users/rohit-agg/my-work/darwinism/CodeGymTasks/4.JavaCollections/src/com/codegym/task/task39/task3913/logs"));
        System.out.println(logParser.execute("get date for status = \"ERROR\""));

    }
}