package com.codegym.task.task31.task3102;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 
Find all the files

*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {

        List<String> filePaths = new ArrayList<>();
        Queue<String> directories = new LinkedList<>();
        directories.add(root);
        File directory;

        while (directories.size() > 0) {

            directory = new File(directories.remove());
            for (String file : directory.list()) {

                File dirFile = new File(directory.getAbsoluteFile() + File.separator + file);
                if (dirFile.isDirectory()) {
                    directories.add(dirFile.getAbsolutePath());
                } else {
                    filePaths.add(dirFile.getAbsolutePath());
                }
            }
        }

        return filePaths;
    }

    public static void main(String[] args) throws IOException {

        System.out.println(getFileTree("/Users/rohit-agg/my-work/darwinism/CodeGymTasks/4.JavaCollections/src/com/codegym/task/task31"));
    }
}