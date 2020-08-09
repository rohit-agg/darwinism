package com.codegym.task.task33.task3310.tests;

import com.codegym.task.task33.task3310.Helper;
import com.codegym.task.task33.task3310.Shortener;
import com.codegym.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.codegym.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {

        Date startTime = new Date();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }
        Date endTime = new Date();
        return (endTime.getTime() - startTime.getTime());
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {

        Date startTime = new Date();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        Date endTime = new Date();
        return (endTime.getTime() - startTime.getTime());
    }

    public void testHashMapStorage() {

        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids = new HashSet<>();
        Set<String> strings = new HashSet<>();

        long time1 = getTimeToGetIds(shortener1, origStrings, ids);
        long time2 = getTimeToGetIds(shortener2, origStrings, ids);

        Assert.assertTrue(time1 > time2);

        time1 = getTimeToGetStrings(shortener1, ids, strings);
        time2 = getTimeToGetStrings(shortener2, ids, strings);

        Assert.assertEquals(time1, time2, 30);
    }
}
