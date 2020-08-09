package com.codegym.task.task33.task3310;

import com.codegym.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {

        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {

        Set<Long> ids = new HashSet<>();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {

        Set<String> values = new HashSet<>();
        for (Long key : keys) {
            values.add(shortener.getString(key));
        }
        return values;
    }

    public static void testStrategy(StorageStrategy storageStrategy, long elementsNumber) {

        Helper.printMessage(storageStrategy.getClass().toString());

        Shortener shortener = new Shortener(storageStrategy);
        Set<String> strings = new HashSet<>();
        Date startTime, endTime;

        for (long i = 0; i < elementsNumber; i++) {
            strings.add(Helper.generateRandomString());
        }

        startTime = new Date();
        Set<Long> ids = getIds(shortener, strings);
        endTime = new Date();
        Helper.printMessage("IDs generated in " + (endTime.getTime() - startTime.getTime()));

        startTime = new Date();
        Set<String> finalStrings = getStrings(shortener, ids);
        endTime = new Date();
        Helper.printMessage("Strings retrieved in " + (endTime.getTime() - startTime.getTime()));

        if (strings.equals(finalStrings)) {
            Helper.printMessage("The test passed.");
        } else {
            Helper.printMessage("The test failed.");
        }
    }
}
