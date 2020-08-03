package com.codegym.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Introducing properties

*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        // Implement this method

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();

        FileInputStream fileInputStream = new FileInputStream(fileName);
        load(fileInputStream);
        fileInputStream.close();
    }

    public void save(OutputStream outputStream) throws Exception {
        // Implement this method

        Properties propertiesList = new Properties();

        for (Map.Entry<String, String> property : properties.entrySet()) {
            propertiesList.put(property.getKey(), property.getValue());
        }

        propertiesList.store(outputStream, null);
    }

    public void load(InputStream inputStream) throws Exception {
        // Implement this method

        Properties propertiesList = new Properties();
        propertiesList.load(inputStream);

        for (String name : propertiesList.stringPropertyNames()) {
            properties.put(name, propertiesList.getProperty(name));
        }
    }

    public static void main(String[] args) {

    }
}
