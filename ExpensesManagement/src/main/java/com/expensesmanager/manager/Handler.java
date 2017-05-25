/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.manager;

import com.expensesmanager.manager.interfaces.IncomingCommandsHandler;
import com.expensesmanager.exchangers.Exchanger;
import com.expensesmanager.manager.interfaces.CheckInputValues;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Buy
 */
public final class Handler extends CommandsHandler {


    public Handler(ExpensesManager manager) {
        super(manager);
    }
   

    @Override
    public boolean checkForCurrency(String currency) {
        
        boolean passed;
        passed = false;
        if(currency.length()==3){
            
                if(Exchanger.checkCurrency(currency)){
                    passed = true;
                }else{
                System.out.println("com.expensesmanager.manager.CommandsHandler.checkForCurrency()");
                
                String warning="This currency: "+currency.toUpperCase()
                        +", doesn't exist at fixer.io. "+System.lineSeparator()
                        +"If you continue, this currency will not be taken into "
                        + "the calculation of total spents"+System.lineSeparator()
                        + " Do you want to continue? ";
            if(JOptionPane.showConfirmDialog(null, warning)==JOptionPane.YES_OPTION)
                passed = true;
                }
            
        }else if(currency.length()!=3){
            JOptionPane.showMessageDialog(null, currency+" is not currency format");
        }
        return passed;
    }

    @Override
    public boolean checkForDate(String dateString) {
        
        boolean passed = false;
        try{
            LocalDate.parse(dateString);
            passed=true;
        }catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(null, "Wrong date format");
        }    
        
        return passed;
    }

    @Override
    public boolean checkForPrice(String price) {
        boolean passed = false;
        try{
            Double.parseDouble(price);
            passed = true;
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, price+" is wrong value for price");
        }
        return passed;
    }

 
    
}
