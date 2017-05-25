/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.exchangers;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author Buy
 */
public interface Exchange {
    
    /**
     *
     * @param currencyTo it is currency in which we done outlay
     * @return rate beetwen currencies
     */
    Double getExchangeRate( String currencyTo);
    
    void setRatesArray(String mainCurrency)throws MalformedURLException, IOException;
}
