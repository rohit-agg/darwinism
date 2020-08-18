package com.codegym.task.task38.task3812;

/* 
Processing annotations

*/

import java.lang.annotation.Annotation;

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {

        if (!c.isAnnotationPresent(PrepareMyTest.class)) {
            return false;
        }

        PrepareMyTest prepareMyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
        for (String fullyQualifiedName : prepareMyTest.fullyQualifiedNames()) {
            System.out.println(fullyQualifiedName);
        }

        return true;
    }

    public static boolean printValues(Class c) {

        if (!c.isAnnotationPresent(PrepareMyTest.class)) {
            return false;
        }

        PrepareMyTest prepareMyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
        for (Class clazz : prepareMyTest.value()) {
            System.out.println(clazz.getSimpleName());
        }
        return true;
    }
}
