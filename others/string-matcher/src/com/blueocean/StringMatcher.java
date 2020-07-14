package com.blueocean;

import org.tartarus.snowball.ext.EnglishStemmer;

import java.io.*;
import java.util.*;

public class StringMatcher {

    private Set<String> keywords;

    private Map<String, Set<String>> synonyms;

    public List<String> search(String paragraph) {

        this.loadKeyWords();
        this.loadSynonyms();

        EnglishStemmer stemmer = new EnglishStemmer();
        List<String> foundWords = new ArrayList<>();

        StringTokenizer inputTokenizer = new StringTokenizer(paragraph, " \t\n\r\f,;:.\"'");
        while (inputTokenizer.hasMoreElements()) {

            String token = inputTokenizer.nextToken().toLowerCase();
            if (keywords.contains(token)) {
                foundWords.add(token);
            } else if (synonyms.containsKey(token)) {
                foundWords.add(synonyms.get(token).toArray()[0].toString());
            } else {

                stemmer.setCurrent(token);
                stemmer.stem();
                token = stemmer.getCurrent();

                if (keywords.contains(token)) {
                    foundWords.add(token);
                } else if (synonyms.containsKey(token)) {
                    foundWords.add(synonyms.get(token).toArray()[0].toString());
                }
            }
        }

        return foundWords;
    }

    private void loadKeyWords() {

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader("keywords.txt"));
            keywords = new TreeSet<>();

            while(bufferedReader.ready()) {
                keywords.add(bufferedReader.readLine());
            }

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void loadSynonyms() {

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader("synonyms.txt"));
            synonyms = new HashMap<>();

            while(bufferedReader.ready()) {

                String[] words = bufferedReader.readLine().split(",");
                for (int i = 1; i < words.length; i++) {

                    if (words[i].length() == 0) {
                        continue;
                    }

                    if (synonyms.containsKey(words[i])) {
                        synonyms.get(words[i]).add(words[0]);
                    } else {
                        synonyms.put(words[i], new TreeSet<>(Arrays.asList(words[0])));
                    }
                }
            }

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
