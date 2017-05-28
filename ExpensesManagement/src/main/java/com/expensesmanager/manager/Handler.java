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
        }catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(null, "Wrong date format");
        }    
        
        return inputDate;
    }

    @Override
    public double checkForPrice(String price) {
        double inpPrice = 0;
        try{
            double input=Double.parseDouble(price);
            
//            Check if price corerect typed only with no more than two number
//            after coma
            if(input>0&&Double.compare(Math.round(input*100), input*100)==0){
                inpPrice=input;
            }else{
                throw new NumberFormatException("Price must be greater than 0, and only with two number after comma");
            }
            
        }catch(NumberFormatException e){
//            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return inpPrice;
    }

 
    
}
