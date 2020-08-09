package com.codegym.task.task33.task3310;

import com.codegym.task.task33.task3310.strategy.StorageStrategy;
import com.sun.org.apache.bcel.internal.generic.FSTORE;

import javax.swing.plaf.synth.SynthRootPaneUI;

public class Shortener {

    private Long lastId = 0L;
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    synchronized public Long getId(String string) {

        if (storageStrategy.containsValue(string)) {
            return storageStrategy.getKey(string);
        }

        lastId++;
        storageStrategy.put(lastId, string);
        return lastId;
    }

    public String getString(Long id) {
        return storageStrategy.getValue(id);
    }
}
