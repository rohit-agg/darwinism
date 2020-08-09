package com.codegym.task.task37.task3702.female;

import com.codegym.task.task37.task3702.AbstractFactory;
import com.codegym.task.task37.task3702.Human;

public class FemaleFactory implements AbstractFactory {

    @Override
    public Human getPerson(int age) {

        if (age <= KidGirl.MAX_AGE) {
            return new KidGirl();
        } else if (age <= TeenGirl.MAX_AGE) {
            return new TeenGirl();
        } else {
            return new Woman();
        }
    }
}
