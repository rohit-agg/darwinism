package com.codegym.task.task26.task2613.command;

import com.codegym.task.task26.task2613.CashMachine;
import com.codegym.task.task26.task2613.ConsoleHelper;
import com.codegym.task.task26.task2613.CurrencyManipulator;
import com.codegym.task.task26.task2613.CurrencyManipulatorFactory;
import com.codegym.task.task26.task2613.exception.InsufficientFundsException;
import com.codegym.task.task26.task2613.exception.InterruptedOperationException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptedOperationException {

        String currencyCode = ConsoleHelper.requestCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        boolean valid = false, withdrew = false;
        int amount = 0;

        ConsoleHelper.writeMessage(res.getString("before"));
        do {
            try {

                do {
                    try {
                        ConsoleHelper.writeMessage(res.getString("specify.amount"));
                        String input = ConsoleHelper.readString();
                        amount = Integer.parseInt(input);

                        if (currencyManipulator.isAmountAvailable(amount)) {
                            valid = true;
                        } else {
                            ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                        }
                    } catch (NumberFormatException exception) {
                        ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    }
                } while (!valid);

                Map<Integer, Integer> withdrewCurrency = currencyManipulator.withdrawAmount(amount);
                for (Map.Entry<Integer, Integer> entry : withdrewCurrency.entrySet()) {
                    ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                }
                withdrew = true;

            } catch (InsufficientFundsException exception) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        } while(!withdrew);
    }
}
