package com.codegym.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy  implements StorageStrategy{

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    int size = 0;
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;

    public int hash(Long k) {
        return k.hashCode();
    }

    public int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    public Entry getEntry(Long key) {

        if (size == 0) {
            return null;
        }

        int hash = hash(key);
        Entry entry = table[indexFor(hash, table.length)];
        if (entry == null) {
            return null;
        }

        do {

            if (entry.hash == hash && entry.key.equals(key)) {
                return entry;
            }
            entry = entry.next;

        } while (entry != null);

        return null;
    }

    public void resize(int newCapacity) {

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) Math.min(newCapacity * loadFactor, Integer.MAX_VALUE);
    }

    public void transfer(Entry[] newTable) {

        int newSize = newTable.length;
        for (Entry entry : table) {
            while (entry != null) {

                Entry next = entry.next;
                int index = indexFor(entry.hash, newSize);
                entry.next = newTable[index];
                newTable[index] = entry;
                entry = next;
            }
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex) {

        if (size > threshold) {
            resize(2 * table.length);
            hash = hash(key);
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {

        Entry next = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, next);
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        for (Entry entry : table) {
            while (entry != null) {
                if (entry.value.equals(value)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        addEntry(hash(key), key, value, indexFor(hash(key), table.length));
    }

    @Override
    public Long getKey(String value) {
        for (Entry entry : table) {
            while (entry != null) {
                if (entry.value.equals(value)) {
                    return entry.key;
                }
                entry = entry.next;
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        return getEntry(key).value;
    }
}
