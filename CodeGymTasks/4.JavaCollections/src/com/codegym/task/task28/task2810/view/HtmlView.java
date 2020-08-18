package com.codegym.task.task28.task2810.view;

import com.codegym.task.task28.task2810.Controller;
import com.codegym.task.task28.task2810.vo.JobPosting;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.List;

public class HtmlView implements View {

    private Controller controller;

    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace(".", "/") + "/jobPostings.html";

    private String getUpdatedFileContents(List<JobPosting> jobPostings) {

        try {

            Document document = getDocument();
            Element element = document.select(".template").first();

            Element baseElement = element.clone();
            baseElement.removeClass("template").removeAttr("style");

            Elements vacancies = document.select(".vacancy").not(".template");
            vacancies.remove();

            for (JobPosting posting : jobPostings) {

                Element jobPost = baseElement.clone();
                jobPost.select(".city").html(posting.getCity());
                jobPost.select(".companyName").html(posting.getCompanyName());
                jobPost.select(".title a").html(posting.getTitle()).attr("href", posting.getUrl());
                element.before(jobPost);
            }

            return document.html();

        } catch (Exception exception) {
            exception.printStackTrace();
            return "Some exception occurred";
        }
    }

    protected Document getDocument() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String content = "";
        while(bufferedReader.ready()) {
            content += bufferedReader.readLine();
        }
        bufferedReader.close();

        Document document = Jsoup.parse(content);
        return document;
    }

    private void updateFile(String content) {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(List<JobPosting> jobPostings) {
        String content = this.getUpdatedFileContents(jobPostings);
        updateFile(content);
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void emulateCitySelection() {
        controller.onCitySelected("Odessa");
    }
}
