package com.codegym.task.task40.task4009;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* 
Buon Compleanno!

*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getWeekdayOfBirthday("30.05.1984", "2015"));
    }

    public static String getWeekdayOfBirthday(String birthday, String year) {
        //write your code here

        Locale.setDefault(Locale.ITALIAN);

        LocalDate birthdayIn = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("d.M.yyyy"));
        Year yearIn = Year.parse(year);

        return birthdayIn.withYear(yearIn.getValue()).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALIAN);
    }
}