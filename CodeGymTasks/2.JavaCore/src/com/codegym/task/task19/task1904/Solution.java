package com.codegym.task.task19.task1904;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/* 
Yet another adapter

*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {

        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner scanner) {
            fileScanner = scanner;
        }

        public Person read() {

            String firstName = fileScanner.next();
            String middleName = fileScanner.next();
            String lastName = fileScanner.next();

            int month = fileScanner.nextInt();
            int day = fileScanner.nextInt();
            int year = fileScanner.nextInt();

            return new Person(lastName, firstName, middleName, new Date(year - 1900, month - 1, day));
        }

        public void close() {
            fileScanner.close();
        }
    }
}
