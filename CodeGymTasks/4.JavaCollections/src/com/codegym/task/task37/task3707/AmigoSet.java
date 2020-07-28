package com.codegym.task.task37.task3707;

import java.io.*;
import java.util.*;

public class AmigoSet<T> extends AbstractSet<T> implements Serializable, Cloneable, Set<T> {

    private static final Object PRESENT = new Object();

    private transient HashMap<T, Object> map;

    private Set<T> set;
    private int capacity;
    private float loadFactor;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends T> data) {
        map = new HashMap<>(Math.max(16, (int) Math.ceil(data.size() / 0.75f)));
        this.addAll(data);
    }

    @Override
    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {

        for (T item : c) {
            this.add(item);
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean add(T elem) {

        if (!map.containsKey(elem)) {
            map.put(elem, PRESENT);
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {
        map.remove(o);
        return true;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Object clone() throws InternalError {
        try {
            AmigoSet<T> cloneSet = (AmigoSet<T>) super.clone();
            cloneSet.map = (HashMap) map.clone();
            return cloneSet;
        } catch (Exception e) {
            throw new InternalError(e.getMessage());
        }

    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {

        capacity = HashMapReflectionHelper.callHiddenMethod(this.map, "capacity");
        loadFactor = HashMapReflectionHelper.callHiddenMethod(this.map, "loadFactor");
        this.set = new HashSet<>();
        this.set.addAll(this.map.keySet());

        objectOutputStream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {

        objectInputStream.defaultReadObject();
        this.map = new HashMap<>(capacity, loadFactor);
        for (T item : set) {
            this.map.put(item, PRESENT);
        }
    }
}
