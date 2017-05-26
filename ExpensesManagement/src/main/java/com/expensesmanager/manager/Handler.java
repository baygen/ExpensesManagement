/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.manager;

import com.expensesmanager.exchangers.Exchanger;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    public String checkForCurrency(String currency) {
        
        String currencyInput = null;
        if(currency.length()==3){
            
                if(Exchanger.checkCurrency(currency)){
                   currencyInput = currency;
                    
                }else{
                
                String warning="This currency: "+currency.toUpperCase()
                        +", doesn't exist at fixer.io. "+System.lineSeparator()
                        +"If you continue, this currency will not be taken into "
                        + "the calculation of total spents"+System.lineSeparator()
                        + " Do you want to continue? ";
            if(JOptionPane.showConfirmDialog(null, warning)==JOptionPane.YES_OPTION)
                currencyInput = currency;
                }
            
        }else if(currency.length()!=3){
            JOptionPane.showMessageDialog(null, currency+" is not currency format");
        }
        return currencyInput;
    }

    @Override
    public LocalDate checkForDate(String dateString) {
        
        LocalDate inputDate;
        inputDate = null;
        try{
            inputDate=LocalDate.parse(dateString);
//            super.
        }catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(null, "Wrong date format");
        }    
        
        return inputDate;
    }

    @Override
    public double checkForPrice(String price) {
        double inpPrice = 0;
        try{
            if(Double.parseDouble(price)>0)
                inpPrice = Double.parseDouble(price);
            
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, price+" is wrong value for price");
        }
        return inpPrice;
    }

 
    
}
