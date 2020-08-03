package com.codegym.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Tracking changes

*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String fileName1 = bufferedReader.readLine();
        String fileName2 = bufferedReader.readLine();

        bufferedReader.close();

        BufferedReader fileReader1 = new BufferedReader(new FileReader(fileName1));
        BufferedReader fileReader2 = new BufferedReader(new FileReader(fileName2));

        String line1 = fileReader1.readLine(), line2 = fileReader2.readLine();
        String tempLine;

        do {

            if (line1 == null && line2 != null) {
                lines.add(new LineItem(Type.ADDED, line2));
                line2 = fileReader2.readLine();
            } else if (line1 != null && line2 == null) {
                lines.add(new LineItem(Type.REMOVED, line1));
                line1 = fileReader1.readLine();
            } else if (line1.equals(line2)) {

                lines.add(new LineItem(Type.SAME, line1));
                line1 = fileReader1.readLine();
                line2 = fileReader2.readLine();

            } else {

                tempLine = fileReader1.readLine();
                if (tempLine.equals(line2)) {
                    lines.add(new LineItem(Type.REMOVED, line1));
                    line1 = tempLine;
                } else {
                    lines.add(new LineItem(Type.ADDED, line2));
                    line2 = fileReader2.readLine();
                    if (line1.equals(line2)) {
                        lines.add(new LineItem(Type.SAME, line1));
                        line1 = tempLine;
                        line2 = fileReader2.readLine();
                    }
                }
            }

        } while (line1 != null || line2 != null);

        fileReader1.close();
        fileReader2.close();
    }

    public static enum Type {
        ADDED,        // New line added
        REMOVED,      // Line deleted
        SAME          // No change
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
