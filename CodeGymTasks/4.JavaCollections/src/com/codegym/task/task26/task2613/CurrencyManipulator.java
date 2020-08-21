package com.codegym.task.task26.task2613;

import com.codegym.task.task26.task2613.exception.InsufficientFundsException;

import java.util.*;

public class CurrencyManipulator {

    private String currencyCode;
    private TreeMap<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        this.denominations = new TreeMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }

    public void addAmount(int denomination, int count) {

        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {

        int totalAmount = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            totalAmount += (entry.getKey() * entry.getValue());
        }
        return totalAmount;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws InsufficientFundsException {

        TreeMap<Integer, Integer> withdrew = new TreeMap<>();
        int requiredNotes;

        for (Map.Entry<Integer, Integer> entry : denominations.descendingMap().entrySet()) {

            if (expectedAmount < entry.getKey()) {
                continue;
            }

            requiredNotes = expectedAmount / entry.getKey();
            if (requiredNotes > entry.getValue()) {
                requiredNotes = entry.getValue();
            }

            withdrew.put(entry.getKey(), requiredNotes);
            expectedAmount -= (entry.getKey() * requiredNotes);

            if (expectedAmount == 0) {
                break;
            }
        }

        if (expectedAmount != 0) {
            throw new InsufficientFundsException();
        }

        for (Map.Entry<Integer, Integer> entry : withdrew.entrySet()) {
            denominations.put(entry.getKey(), denominations.get(entry.getKey()) - entry.getValue());
        }

        return withdrew.descendingMap();
    }
}
