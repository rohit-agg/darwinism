package com.codegym.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size = 0;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize;

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

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
        FileBucket file = table[indexFor(hash, table.length)];
        if (file == null) {
            return null;
        }

        Entry entry = file.getEntry();
        do {
            if (entry.hash == hash && entry.key.equals(key)) {
                return entry;
            }
            entry = entry.next;
        } while (entry != null);

        return null;
    }

    public void resize(int newCapacity) {

        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    public void transfer(FileBucket[] newTable) {

        int newSize = newTable.length;
        for (FileBucket file : table) {

            Entry entry = file.getEntry();
            Entry newEntry = newTable[indexFor(entry.hash, newSize)].getEntry();
            while (entry != null) {

                Entry next = entry.next;
                int index = indexFor(entry.hash, newSize);
                entry.next = newEntry;
                newEntry = entry;
                entry = next;
            }
            file.putEntry(newEntry);
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex) {

        FileBucket bucket = table[bucketIndex];
        if (bucket != null && size > bucket.getFileSize()) {
            resize(table.length);
            hash = hash(key);
            bucketIndex = indexFor(hash, table.length);
            bucketSizeLimit *= 2;
        }

        createEntry(hash, key, value, bucketIndex);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {

        FileBucket bucket = table[bucketIndex];
        if (bucket == null) {
            bucket = new FileBucket();
        }
        Entry next = bucket.getEntry();
        bucket.putEntry(new Entry(hash, key, value, next));
        table[bucketIndex] = bucket;
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        for (FileBucket bucket : table) {
            if (bucket == null) {
                return false;
            }
            Entry entry = bucket.getEntry();
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
        for (FileBucket bucket : table) {
            Entry entry = bucket.getEntry();
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
