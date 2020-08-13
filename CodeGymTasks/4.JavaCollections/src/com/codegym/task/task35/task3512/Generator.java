package com.codegym.task.task35.task3512;

public class Generator<T> {

    private Class<T> obj;

    public Generator(Class<T> clazz) {
        obj = clazz;
    }

    T newInstance() throws IllegalAccessException, InstantiationException {
        return (T) obj.newInstance();
    }
}
