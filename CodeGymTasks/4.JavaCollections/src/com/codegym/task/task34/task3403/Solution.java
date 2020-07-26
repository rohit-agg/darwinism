package com.codegym.task.task34.task3403;

/* 
Factorization using recursion

*/
public class Solution {
    public void recurse(int n) {

        if (n == 1) {
            return;
        }

        int i = 2, j;
        while (true) {

            if (n % i == 0) {
                System.out.print(i + " ");
                break;
            }

            i++;
            for (j = 2; j < i / 2;) {
                if (j % i == 0) {
                    i++;
                } else {
                    j++;
                }
            }
        }

        recurse(n / i);
    }
}