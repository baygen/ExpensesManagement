/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.manager;

import com.expensesmanager.exchangers.Exchanger;
import com.expensesmanager.manager.interfaces.CheckInputValues;
import com.expensesmanager.manager.interfaces.IncomingCommandsHandler;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Buy
 */
public abstract class CommandsHandler implements CheckInputValues, IncomingCommandsHandler {
    
    protected Object[] data;
    protected final ExpensesManager em;
    protected String res;
    protected boolean accept;
    
    public CommandsHandler(ExpensesManager manager) {
        this.em=manager;
    }

    @Override
    public String doRequest(String[] requested) {
        String reqcommand = requested[0].toLowerCase();
        switch (reqcommand) {
            case "add":
                if (commandAdd(requested)) {
                    em.addPurhase(data);
                    break;
                }
                break;
            case "list":
                if (commandList(requested)) {
                    em.setResultList();
                    break;
                }
                break;
            case "clear":
                if (commandClear(requested)) {
                    em.removePurchaseByDate((LocalDate) data[0]);
                    break;
                }
                break;
            case "total":
                if (commandTotal(requested)) {
                    em.getTotalSpentInCurrency((String) data[0]);
                }
                break;
            case "help":
                if (commandHelp(requested)) {
                    em.getHelpCommand();
                    break;
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Wrong command format, Please type 'help' for list of commands.");
        }
        this.res = em.getResult();
        return res;
    }

    private boolean commandAdd(String[] requested) {
        accept = false;
        int size = requested.length;
        //We checked if users input acceptance our criteria
        if (size > 4) {
            if (checkForCurrency(requested[3]) && checkForDate(requested[1]) && checkForPrice(requested[2])) {
                LocalDate date = LocalDate.parse(requested[1]);
                double price = Double.parseDouble(requested[2]);
                String currency = requested[3];
                String product = "";
                for (int i = 4; i < size; i++) {
                    product = product + " " + requested[i];
                }
                data = new Object[]{date, price, currency, product};
                accept = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Wrong command or value, " + "type help for available commands.");
        }
        return accept;
    }

    private boolean commandList(String[] requested) {
        accept = false;
        if (requested.length == 1) {
            accept = true;
        } else {
            JOptionPane.showMessageDialog(null, "This command don't have arguments");
        }
        return accept;
    }

    private boolean commandClear(String[] requested) {
        accept = false;
        if (requested.length == 2 && checkForDate(requested[1])) {
            accept = true;
            LocalDate date = LocalDate.parse(requested[1]);
            data = new Object[]{date};
        } else {
            JOptionPane.showMessageDialog(null, "Wrong value for command");
        }
        return accept;
    }

    private boolean commandTotal(String[] requested) {
        accept = false;
        if (requested.length == 2) {
            if (Exchanger.checkCurrency(requested[1])) {
                accept = true;
                data = new Object[]{requested[1]};
            } else {
                JOptionPane.showMessageDialog(null, "Can't convert to " + requested[1]);
                accept = false;
            }
        } else {
        }
        return accept;
    }

    private boolean commandHelp(String[] requested) {
        accept = false;
        if (requested.length == 1) {
            accept = true;
        }
        return accept;
    }

    @Override
    public abstract boolean checkForCurrency(String currency);

    @Override
    public abstract boolean checkForDate(String dateString);

    @Override
    public abstract boolean checkForPrice(String price);
    
}
