/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.manager;

import com.expensesmanager.manager.interfaces.Manager;
import com.expensesmanager.exchangers.Exchanger;
import com.expensesmanager.entity.Expense;
import com.expensesmanager.entity.ExpensesData;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JOptionPane;


/**
 *
 * @author Buy
 */
public class ExpensesManager implements Manager{

    private final TreeMap<LocalDate,ArrayList<Expense>> list;
    private double spentSum;
    private String result="";
    
    public ExpensesManager() {
        this.list = new TreeMap<>();
    }

    public ExpensesManager(ExpensesData list) {
        this.list= list.getList();
    }
   
    @Override
    public int addPurhase(LocalDate date,double price,String currency,String product) {
        
        Expense expense = new Expense(product, currency, price);
        ArrayList<Expense> value;
        
//      Checking if date is exist in map
        if(list.keySet().contains(date)){
            list.get(date).add(expense);
        }else{
            value = new ArrayList<>();
            value.add(expense);
            list.put(date,value);
        }
        setResultList();
        return list.get(date).size();
    }

    @Override
    public void setResultList() {
        
        if(getList().size()>0){
        
            this.result="";
        for (Map.Entry<LocalDate, ArrayList<Expense>> entry : getList().entrySet()) {
            
            result = result + entry.getKey().toString()+System.lineSeparator();
            
            entry.getValue().forEach((expense) -> {
                result = result+ expense.toString()+System.lineSeparator();
            });
            result = result + System.lineSeparator();
        }
        }else{
            JOptionPane.showMessageDialog(null, "Your list is empty.");
        }
    }

    /**
     *
     * @param date
     * @return -1 when key deleted
     */
    @Override
    public int removePurchaseByDate(LocalDate date) {
        
        int newsize=list.keySet().size();
        if(list.containsKey(date)){
            list.remove(date);
            newsize = list.keySet().size() - newsize;
        }else{
            JOptionPane.showMessageDialog(null,"Can't remove "+date + 
                     "purchases becouse 'Yoy didn't add purchases for that date.'");
        }
        setResultList();
        
        return newsize;
    }

    @Override
    public double getTotalSpentInCurrency(String currency) {
        
        spentSum=0;
        try{
        Exchanger exchanger = new Exchanger(currency);
                
        for (Map.Entry<LocalDate, ArrayList<Expense>> entry : list.entrySet()) {

            ArrayList<Expense> value = entry.getValue();
                
            value.forEach((Expense expanse)->{
                double price = expanse.getPrice();
                if(expanse.getCurrency().equalsIgnoreCase(currency)){
                   spentSum = spentSum+price;
                }else{
                    double currencyRate=exchanger.getExchangeRate(expanse.getCurrency());
                   spentSum = spentSum + price*currencyRate;
                }                
            });
        }
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"Can't calculate total spent because: Your list is empty.");
        }
        result = displayTotal(spentSum,currency);
        return spentSum;
    }
    
    
    public TreeMap<LocalDate, ArrayList<Expense>> getList(){
         return list;
    }

    private String displayTotal(Double sum, String currency) {
        result = "";
        return String.format(Locale.US,"%.2f", sum)+" "+ currency.toUpperCase();
    }

    public String getResult() {
        
        return result;
    }

    @Override
    public String getHelp() {
        final String newLine=System.lineSeparator();
        String helpMessage =
                  "Commands list:"+ newLine
                + "     'add 2017-05-25 10.45 USD Apple' - adds expense entry to the list"+ newLine
                +newLine
                + "     where:"+newLine
                + "         2017-05-25 - is the data when expense occured "+ newLine
                + "         10.5 - is an amount of money spent"+newLine
                + "         USD - is the name of product purchased"+ newLine
                +newLine
                +"      'list' - shows the list of all expenses sorted by date"+ newLine
                +newLine
                +"      'clear 2017-04-20' - removes all expenses for specified date"+ newLine
                +newLine
                +"      where:"+newLine
                +"      2017-04-20 - is the date for which all expenses should be removed "+ newLine
                +newLine
                +"      'total PLN' - calculate total amount of meney spent in specified currency"+newLine
                +"      PLN - is the currency in which total amount of expenses should be presented"
                ;
        
        return helpMessage;
    }

    void getHelpCommand() {
        result = "";
        result = getHelp();
    }
}
