package com.blueocean;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {

            String paragraph = "If you're hungry, visit the kitchen.";
            StringMatcher matcher = new StringMatcher();
            List<String> keywords = matcher.search(paragraph);

            System.out.println("Keywords found:");
            for (String word : keywords) {
                System.out.println(word);
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
