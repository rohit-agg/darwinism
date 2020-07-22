package com.codegym.task.task18.task1828;

/* 
Prices 2

*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();

        if  (args.length == 0) {
            return;
        }

        String symbol = args[0];
        Integer productId = Integer.valueOf(args[1]);
        String productName = "";
        Double price = 0.0;
        Integer quantity = 0;

        if (symbol.equals("-u")) {
            productName = args[2];
            price = Double.valueOf(args[3]);
            quantity = Integer.valueOf(args[4]);
        }

        Product product;
        List<Product> productList = new ArrayList<>();
        String temp;

        char[] idChar = new char[8],
                productNameChar = new char[30],
                priceChar = new char[8],
                quantityChar = new char[4],
                newlineChar = new char[2];

        BufferedReader bufferedFileReader = new BufferedReader(new FileReader(fileName));
        while (bufferedFileReader.ready()) {

            product = new Product();

            bufferedFileReader.read(idChar, 0, 8);
            temp = new String(idChar).trim();
            if (temp.length() == 0) {
                break;
            }
            product.id = Integer.parseInt(temp);

            bufferedFileReader.read(productNameChar, 0, 30);
            product.productName = new String(productNameChar).trim();

            bufferedFileReader.read(priceChar, 0, 8);
            product.price = Double.parseDouble(new String(priceChar).trim());

            bufferedFileReader.read(quantityChar, 0, 4);
            product.quantity = Integer.parseInt(new String(quantityChar).trim());

            bufferedFileReader.read(newlineChar, 0, 1);

            if (symbol.equals("-u") && productId == product.id) {

                product.productName = productName;
                product.price = price;
                product.quantity = quantity;
                productList.add(product);

            } else if (symbol.equals("-d") && productId == product.id) {

                continue;

            } else {
                productList.add(product);
            }
        }

        BufferedWriter bufferedFileWriter = new BufferedWriter(new FileWriter(fileName));
        for (Product p : productList) {
            bufferedFileWriter.write(String.format("%-8d%-30s%-8.2f%-4d\n", p.id, p.productName, p.price, p.quantity));
        }

        bufferedFileReader.close();
        bufferedReader.close();
        bufferedFileWriter.close();
    }

    public static class Product {
        public int id;
        public String productName;
        public Double price;
        public int quantity;
    }
}
