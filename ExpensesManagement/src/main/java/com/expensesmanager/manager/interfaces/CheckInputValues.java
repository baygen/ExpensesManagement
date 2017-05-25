/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.manager.interfaces;

/**
 *
 * @author Buy
 */
public interface CheckInputValues {
    
    /**
     * Checked if value suitable for currency criteria 
     * @param currency value to check
     * @return true if value accepted
     */
    boolean checkForCurrency(String currency);
    
    /**
     * Checked if date value is correct
     * @param date string to be checked
     * @return true if correct
     */
    boolean checkForDate(String date);
    
    /**
     *
     * @param price values to be checked
     * @return true if price is number
     */
    boolean checkForPrice(String price);
}
