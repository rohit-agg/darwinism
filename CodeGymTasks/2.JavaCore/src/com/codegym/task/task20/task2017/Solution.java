package com.codegym.task.task20.task2017;

import java.io.ObjectInputStream;
import java.io.Serializable;

/* 
Deserialization

*/
public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) {

        try {
            A object = (A) objectStream.readObject();
            return object;

        } catch (Exception exception) {
            return null;
        }
    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {

    }
}
