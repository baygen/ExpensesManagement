/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.manager.interfaces;

import java.time.LocalDate;


/**
 *
 * @author Buy
 */
public interface Manager {
    
    int addPurhase(LocalDate date, double price, String currency, String product);
    
    void setResultList();
    
    int removePurchaseByDate(LocalDate date);
    
    double getTotalSpentInCurrency(String currency);
    
    String getHelp();
  
}
