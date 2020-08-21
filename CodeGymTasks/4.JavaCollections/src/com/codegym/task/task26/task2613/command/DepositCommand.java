package com.codegym.task.task26.task2613.command;

import com.codegym.task.task26.task2613.CashMachine;
import com.codegym.task.task26.task2613.ConsoleHelper;
import com.codegym.task.task26.task2613.CurrencyManipulator;
import com.codegym.task.task26.task2613.CurrencyManipulatorFactory;
import com.codegym.task.task26.task2613.exception.InterruptedOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptedOperationException {

        String currencyCode = ConsoleHelper.requestCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        int denomination = 0, bankNotes = 0;
        boolean valid = false;

        do {
            try {
                String[] numbers = ConsoleHelper.getTwoValidNumbers(currencyCode);
                denomination = Integer.parseInt(numbers[0]);
                bankNotes = Integer.parseInt(numbers[1]);
                valid = true;

            } catch (Exception exception) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }

        } while (!valid);

        ConsoleHelper.writeMessage(res.getString("before"));
        currencyManipulator.addAmount(denomination, bankNotes);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), denomination * bankNotes, currencyCode));
    }
}
