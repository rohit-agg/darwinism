package com.codegym.task.task33.task3310.strategy;

import java.io.Serializable;

public class Entry implements Serializable {

     Long key;
     String value;
     Entry next;
     int hash;

    public Entry(int hash, Long key, String value, Entry next) {

        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public Long getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Entry entry = (Entry) obj;
        if (key.equals(entry.key) && value.equals(entry.value)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
