package com.codegym.task.task33.task3310.tests;

import com.codegym.task.task33.task3310.Helper;
import com.codegym.task.task33.task3310.Shortener;
import com.codegym.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {

    public void testStorage(Shortener shortener) {

        String one = Helper.generateRandomString();
        String two = Helper.generateRandomString();
        String three = one;

        Long idOne = shortener.getId(one);
        Long idTwo = shortener.getId(two);
        Long idThree = shortener.getId(three);

        Assert.assertNotEquals(idOne, idTwo);
        Assert.assertNotEquals(idThree, idTwo);
        Assert.assertEquals(idOne, idThree);

        String stringOne = shortener.getString(idOne);
        String stringTwo = shortener.getString(idTwo);
        String stringThree = shortener.getString(idThree);

        Assert.assertEquals(stringOne, one);
        Assert.assertEquals(stringTwo, two);
        Assert.assertEquals(stringThree, three);
    }

    @Test
    public void testHashMapStorageStrategy() {

        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {

        Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {

        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {

        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {

        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {

        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }
}
