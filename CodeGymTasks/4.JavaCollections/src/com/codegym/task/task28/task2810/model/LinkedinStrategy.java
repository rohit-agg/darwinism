package com.codegym.task.task28.task2810.model;

import com.codegym.task.task28.task2810.vo.JobPosting;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LinkedinStrategy implements Strategy {

    private static final String URL_FORMAT = "https://www.linkedin.com/jobs/search?keywords=Java+%s&start=%d";

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

                /**


                  JobPosting jobPosting = new JobPosting();
                  jobPosting.setTitle("Hello");
                  jobPosting.setCity("Hello 2");
                  jobPosting.setCompanyName("Hello 3");
                  jobPosting.setUrl("Hello 4");
                  jobPosting.setWebsiteName(URL_FORMAT);

                  if (!jobPostings.contains(jobPosting)) {
                      jobPostings.add(jobPosting);
                  }

                 */

                Document document = getDocument(searchString, page++);
                Elements jobPosts = document.select(".jobs-search-result-item");
                if (jobPosts.isEmpty()) {
                    break;
                }

                for (Element element : jobPosts) {

                    JobPosting jobPosting = new JobPosting();
                    jobPosting.setTitle(element.select(".listed-job-posting__title").text());
                    jobPosting.setCity(element.select(".listed-job-posting__location").text());
                    jobPosting.setCompanyName(element.select(".listed-job-posting__company").text());
                    jobPosting.setUrl(element.select("meta[itemprop=url]").first().attr("content"));
                    jobPosting.setWebsiteName(URL_FORMAT);

                    if (!jobPostings.contains(jobPosting)) {
                        jobPostings.add(jobPosting);
                    }
                }
            }
        } catch (IOException e) {

        }
        return jobPostings;
    }
}
