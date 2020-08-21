package com.codegym.task.task28.task2810.model;

import com.codegym.task.task28.task2810.vo.JobPosting;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IndeedStrategy implements Strategy {

    static private final String URL_FORMAT = "https://www.indeed.com/jobs?q=java+%s&start=%d";

    protected Document getDocument(String searchString, int page) throws IOException {

        String url = String.format(URL_FORMAT, searchString, page);
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36")
                .referrer("https://codegym.cc/")
                .timeout(5000).get();
        return document;
    }

    @Override
    public List<JobPosting> getJobPostings(String searchString) {

        List<JobPosting> jobPostings = new ArrayList<>();
        int page = 0;
        try {
            while(true) {

                Document document = getDocument(searchString, page);
                page += 10;
                Elements jobPosts = document.getElementsByClass("jobsearch-SerpJobCard");
                if (jobPosts.isEmpty()) {
                    break;
                }

                for (Element element : jobPosts) {

                    JobPosting jobPosting = new JobPosting();
                    jobPosting.setTitle(element.select(".turnstileLink").attr("title"));
                    // jobPosting.setCity(element.select(".companyInfoWrapper").first().child(1).attr("data-rc-loc"));
                    jobPosting.setCity(element.select(".location").text());
                    jobPosting.setCompanyName(element.select(".company").text());
                    jobPosting.setUrl(element.select(".turnstileLink").attr("href"));
                    jobPosting.setWebsiteName(URL_FORMAT);

                    jobPostings.add(jobPosting);

                }
//                break;
            }
        } catch (IOException e) {

        }
        return jobPostings;
    }
}
