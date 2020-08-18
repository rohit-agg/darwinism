package com.codegym.task.task28.task2810;

import com.codegym.task.task28.task2810.model.Model;
import com.codegym.task.task28.task2810.model.Provider;
import com.codegym.task.task28.task2810.vo.JobPosting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

//    private Provider[] providers;

    private Model model;

    public Controller(Model model) {

        if (model == null) {
            throw new IllegalArgumentException();
        }

        this.model = model;
    }

    public void onCitySelected(String cityName) {
        model.selectCity(cityName);
    }

//    public Controller(Provider... providers) throws IllegalArgumentException {
//
//        if (providers.length == 0) {
//            throw new IllegalArgumentException();
//        }
//        this.providers =  providers;
//    }

//    @Override
//    public String toString() {
//        return "Controller{" +
//                "providers=" + Arrays.toString(providers) +
//                '}';
//    }

//    public void scan() {
//
//        List<JobPosting> jobPostingList = new ArrayList<>();
//        for (Provider provider : providers) {
//            jobPostingList.addAll(provider.getJavaJobPostings(""));
//        }
//        System.out.println(jobPostingList.size());
//    }
}
