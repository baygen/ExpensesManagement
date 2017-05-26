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
public interface CheckInputValues {
    
    /**
     * Checked if value suitable for currency criteria 
     * @param currency value to check
     * @return currency if value accepted or null if not
     */
    String checkForCurrency(String currency);
    
    /**
     * Checked if date value is correct
     * @param date string to be checked
     * @return date if it correct or null if not
     */
    LocalDate checkForDate(String date);
    
    /**
     *
     * @param price values to be checked
     * @return price in number format if correct or 0 if not
     */
    double checkForPrice(String price);
}
