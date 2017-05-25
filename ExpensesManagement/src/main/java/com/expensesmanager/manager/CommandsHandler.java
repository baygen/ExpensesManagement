/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.manager;

import com.expensesmanager.manager.interfaces.IncomingCommandsHandler;
import com.expensesmanager.exchangers.Exchanger;
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
public final class CommandsHandler implements IncomingCommandsHandler{

    private Object[] data;
    private final ExpensesManager em;
    private String res;
    private boolean accept;

    public CommandsHandler(ExpensesManager manager) {
        this.em = manager;
    }
   
    @Override
    public String doRequest(String[] requested) {
        
        String reqcommand = requested[0].toLowerCase();
        
        switch(reqcommand){
            case("add"):
                if(checkForCommandAdd(requested)){
                    em.addPurhase(data);
                    break;
                }
                break;
            case("list"):
                if(checkForCommandList(requested)){
                    em.setResultList();
                    break;
                }
                break;
            case("clear"):
                if(checkForCommandClear(requested)){
                    em.removePurchaseByDate((LocalDate)data[0]);
                    break;
                }
                break;
            case("total"):
                if(checkForCommandTotal(requested)){
                    em.getTotalSpentInCurrency((String)data[0]);
                }
                break;
            case("help"):
                if(checkForCommandHelp(requested)){
                    em.getHelpCommand();
                    break;
                }
                break;
            default:
                JOptionPane.showMessageDialog(null,"Wrong command format, Please type 'help' for list of commands.");
        }
        this.res = em.getResult();
             return res;
    }

    private boolean checkForCommandAdd(String[] requested) {
        accept=false;
        int size = requested.length;
        
    //We checked if users input acceptance our criteria
        if(size>4){
            if(checkForCurrency(requested[3])
            &&checkForDate(requested[1])
            &&checkForPrice(requested[2])){

                LocalDate date = LocalDate.parse(requested[1]);
                double price = Double.parseDouble(requested[2]);
                String currency = requested[3];
                String product="";
                    for(int i=4;i<size;i++){
                        product = product + " "+requested[i];
                    }
                data = new Object[]{date, price,currency , product};
                accept = true;
            }
        }else{
            JOptionPane.showMessageDialog(null,"Wrong command or value, "
                    + "type help for available commands.");
        } 
        return accept;
    }
    

    private boolean checkForCommandList(String[] requested) {
        
        accept=false;
        if(requested.length==1){
            accept = true;
        }else{
            JOptionPane.showMessageDialog(null, "This command don't have arguments");
        }
        
        return accept;
    }

    private boolean checkForCommandClear(String[] requested) {
            
        accept=false;
        if(requested.length==2&&checkForDate(requested[1])){
            accept=true;
            LocalDate date = LocalDate.parse(requested[1]);
            data = new Object[]{date};
        }else{
            JOptionPane.showMessageDialog(null, "Wrong value for command");
        }
    return accept;
    }

    private boolean checkForCommandTotal(String[] requested) {
        
        accept=false;
        if(requested.length==2){
            if(Exchanger.checkCurrency(requested[1])){
                accept = true;
                
                data=new Object[]{requested[1]};
            }else{
                JOptionPane.showMessageDialog(null, "Can't convert to "+requested[1]);
                accept=false;
            }
        }else{
        }
        
        return accept;
    }

    private boolean checkForCommandHelp(String[] requested) {
        accept = false;
            if(requested.length==1){
                accept = true;
            }
        return accept;
    }

    private boolean checkForCurrency(String currency) {
        
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

    private boolean checkForDate(String dateString) {
        
        boolean passed = false;
        try{
            LocalDate.parse(dateString);
            passed=true;
        }catch(DateTimeParseException e){
            JOptionPane.showMessageDialog(null, "Wrong date format");
        }    
        
        return passed;
    }

    private boolean checkForPrice(String price) {
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
