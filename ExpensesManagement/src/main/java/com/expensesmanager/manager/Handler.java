/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.manager;

import com.expensesmanager.exchangers.Exchanger;
import com.expensesmanager.manager.interfaces.Manager;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

/**
 *
 * @author Buy
 */
public final class Handler extends CommandsHandler {


    public Handler(Manager manager) {
        super(manager);
    }
   

    @Override
    public String checkForCurrency(String currency) {
        String warning="This currency: "+currency.toUpperCase()
                        +", doesn't exist at fixer.io. "+System.lineSeparator()
                        +"If you continue, this currency will not be taken into "
                        + "the calculation of total spents"+System.lineSeparator()
                        + " Do you want to continue? ";
        String currencyInput = "not currency value";
        if(currency.length()==3&&Exchanger.checkCurrency(currency)){
         
            currencyInput = currency;
        }else if(currency.length()==3){
            if(JOptionPane.showConfirmDialog(null, warning)==JOptionPane.YES_OPTION)
                currencyInput = currency;
        }
        return currencyInput;
    }

    @Override
    public LocalDate checkForDate(String dateString) {
        
        LocalDate inputDate;
        
        try{
            inputDate=LocalDate.parse(dateString);
        }catch(DateTimeParseException e){
            inputDate = LocalDate.now();
            JOptionPane.showMessageDialog(null, "Wrong date format");
        }    
        
        return inputDate;
    }

    @Override
    public double checkForPrice(String price) {
        double inpPrice = 0.0;
        try{
            double temp=Double.parseDouble(price);
            
//            Check if price corerect typed only with no more than two number
//            after coma
            if(temp>0&&Double.compare(Math.round(temp*100), temp*100)==0)
                inpPrice=temp;
            
            
        }catch(NumberFormatException e){
//            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return inpPrice;
    }

 
    
}
