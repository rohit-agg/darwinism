package com.codegym.task.task26.task2613;

import com.codegym.task.task26.task2613.exception.InterruptedOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptedOperationException {

        String input = null;
        try {
            input = bis.readLine();
            if (input.equalsIgnoreCase("EXIT")) {
                throw new InterruptedOperationException();
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage(e.getMessage());
        }
        return input;
    }

    public static String requestCurrencyCode() throws InterruptedOperationException {

        String currencyCode = null;
        boolean valid = false;

        do {
            ConsoleHelper.writeMessage(res.getString("choose.currency.code"));
            currencyCode = readString();

            if (currencyCode.length() != 3) {
                ConsoleHelper.writeMessage("Currency code must be of 3 characters");
            } else {
                currencyCode = currencyCode.toUpperCase();
                valid = true;
            }
        } while(!valid);

        return currencyCode;
    }

    public static String[] getTwoValidNumbers(String currencyCode) throws InterruptedOperationException {

        String[] numbers = null;
        boolean valid = false;

        do {
            try {
                ConsoleHelper.writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
                String numbersInput = readString();
                numbers = numbersInput.split(" ");
                if (numbers.length != 2) {
                    ConsoleHelper.writeMessage("Only two numbers must be provided");
                } else {

                    int denomination = Integer.parseInt(numbers[0]);
                    int bankNotes = Integer.parseInt(numbers[1]);
                    if (denomination < 0 || bankNotes < 0) {
                        ConsoleHelper.writeMessage("Both numbers must be positive");
                    }
                    valid = true;
                }

            } catch (NumberFormatException exception) {
                ConsoleHelper.writeMessage(exception.getMessage());
            }
        } while(!valid);

        return numbers;
    }

    public static Operation requestOperation() throws InterruptedOperationException {

        do {
            try {

                ConsoleHelper.writeMessage(res.getString("choose.operation"));
                Integer oper = Integer.parseInt(readString());
                return Operation.getAllowableOperationByOrdinal(oper);

            } catch (NumberFormatException exception) {
                ConsoleHelper.writeMessage(exception.getMessage());
            }

        } while (true);
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage("goodbye");
    }
}
