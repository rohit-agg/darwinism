package com.codegym.task.task26.task2613.command;

import com.codegym.task.task26.task2613.CashMachine;
import com.codegym.task.task26.task2613.ConsoleHelper;
import com.codegym.task.task26.task2613.exception.InterruptedOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptedOperationException {

        boolean loggedIn = false;
        String card, pin;

        ConsoleHelper.writeMessage(res.getString("before"));
        do {

            ConsoleHelper.writeMessage(res.getString("specify.data"));
            card = ConsoleHelper.readString();
            pin = ConsoleHelper.readString();

            if (card.length() != 12 || pin.length() != 4) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            } else {

                if (validCreditCards.containsKey(card) && validCreditCards.getString(card).equals(pin)) {
                    loggedIn = true;
                } else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), card));
                }
            }

        } while(!loggedIn);

        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), card));
    }
}
