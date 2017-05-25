/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.manager.interfaces;

import java.time.LocalDate;
import java.util.Map;


/**
 *
 * @author Buy
 */
public interface Manager {
    
    int addPurhase(Object[] data);
    
    void setResultList();
    
    int removePurchaseByDate(LocalDate date);
    
    double getTotalSpentInCurrency(String currency);
    
    String getHelp();
  
}
