package com.codegym.task.task26.task2613.command;

import com.codegym.task.task26.task2613.CashMachine;
import com.codegym.task.task26.task2613.ConsoleHelper;
import com.codegym.task.task26.task2613.CurrencyManipulator;
import com.codegym.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command {

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");

    @Override
    public void execute() {

        Collection<CurrencyManipulator> currencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (currencyManipulators.isEmpty()) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
            return;
        }

        for (CurrencyManipulator manipulator : currencyManipulators) {
            if (!manipulator.hasMoney()) {
                ConsoleHelper.writeMessage(res.getString("no.money"));
            } else {
                ConsoleHelper.writeMessage(res.getString("before") + manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());
            }
        }
    }
}
