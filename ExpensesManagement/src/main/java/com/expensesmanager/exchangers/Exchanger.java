/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.exchangers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JOptionPane;

/**
 *
 * @author Buy
 */
public final class Exchanger implements Exchange{
    
        
    private JsonObject jsonObject;

    public Exchanger() {
    
    }
    
    public Exchanger(String mainCurrency) {
        setRatesArray (mainCurrency);
    }

    
    
    @Override
    public Double getExchangeRate(String currencyTo) {

        String currency = currencyTo.toUpperCase();

        double rate;
        try{
        rate=1/Double.valueOf(this.jsonObject.get(currency).toString());
        }catch(NullPointerException e){
            rate=0;
        }

        return rate;
    }

    /**
     *
     * @param mainCurrency main currency for which we set exchange rates
     *  in JSONObject value
     */
    @Override
    public void setRatesArray(String mainCurrency) {
        
        try {
            String currencyURL = "http://api.fixer.io/latest?base="+mainCurrency.toUpperCase();
            URL con;
            
            con = new URL(currencyURL);
        
            try (InputStream is = con.openStream()) {
                JsonReader jsr = Json.createReader(is);
                JsonObject jobj = jsr.readObject();
                JsonObject res = jobj.getJsonObject("rates");
                if(res!=null){
                    this.jsonObject=res;               
                }else{
                    throw new IllegalArgumentException(mainCurrency+" is wrong format");
                }
            }
        } catch (MalformedURLException ex) {
        } catch (IOException ex) {
        }
        
    }
    
    /**
     *
     * @param currency which must be checked
     * @return true if rate for this  currency exists
     */
    public static boolean checkCurrency(String currency){
        boolean check=false;
        InputStream is=null;
        String currencyURL = "http://api.fixer.io/latest?base="+currency.toUpperCase();
        try{
            URL con = new URL(currencyURL);
            is = con.openStream();
            JsonReader jsr = Json.createReader(is);
            JsonObject jobj = jsr.readObject();
            JsonObject res = jobj.getJsonObject("rates");
            if(res!=null){
             check = true;   
            }
        } catch (MalformedURLException ex) {
        } catch (IOException ex) {
        }finally{
            try {
                is.close();
            } catch (IOException|NullPointerException ex) {
            }
        }
        
        return check;
    }
}
