/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.manager;

import com.expensesmanager.exchangers.Exchanger;
import com.expensesmanager.manager.interfaces.CheckInputValues;
import com.expensesmanager.manager.interfaces.IncomingCommandsHandler;
import com.expensesmanager.manager.interfaces.Manager;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Buy
 */
public abstract class CommandsHandler implements CheckInputValues, IncomingCommandsHandler {
    
//    protected Object[] data;
    protected final Manager em;
    protected String res;
    protected boolean accept;
    protected LocalDate date;
    protected String currency;
    protected double price ;
    protected String product;
    
    public CommandsHandler(Manager manager) {
        this.em=manager;
    }

    @Override
    public String doRequest(String[] requested) {
        String reqcommand = requested[0].toLowerCase();
        switch (reqcommand) {
            case "add":
                if (commandAdd(requested)) {
                    em.addPurhase(date,price,currency,product);
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
                    em.removePurchaseByDate(date);
                    break;
                }
                break;
            case "total":
                if (commandTotal(requested)) {
                    em.getTotalSpentInCurrency(currency);
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

    public boolean commandAdd(String[] requested) {
        accept = false;
        int size = requested.length;
        //We checked if users input acceptance our criteria
        if (size > 4) {
            
            LocalDate dateInp = checkForDate(requested[1]);
            double priceInp = checkForPrice(requested[2]);
            String currencyInp = checkForCurrency(requested[3]);
            
            if ((!(dateInp == null))&&priceInp!=0&&currencyInp!=null) {
                this.date=dateInp;
                this.currency = currencyInp;
                this.price = priceInp;
                String products = requested[4];
                for (int i = 5; i < size; i++) {
                    products = products + " " + requested[i];
                }
                this.product=products;
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
        if (requested.length == 2){
                   
            LocalDate dateExpense = checkForDate(requested[1]);
            if(dateExpense!=null){
            this.date = dateExpense;
            accept = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Wrong value for command");
        }
        return accept;
    }

    private boolean commandTotal(String[] requested) {
        accept = false;
        if (requested.length == 2) {
            if(checkForCurrency(requested[1])!=null){ 
                accept = true;
                this.currency=requested[1];
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
    public abstract String checkForCurrency(String currency);

    @Override
    public abstract LocalDate checkForDate(String dateString);

    @Override
    public abstract double checkForPrice(String price);
    
}
