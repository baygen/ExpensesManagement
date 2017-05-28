/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Buy
 */
public class Expense implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String product;
    private String currency;
    private double price;

    public Expense() {
    }

    public Expense(String product, String currency, double price) {
        this.product = product;
        this.currency = currency.toUpperCase();
        this.price = price;
    }
    
    public void setProduct(String product){
        this.product=product;
    }
    
    public String getProduct(){
        return product;
    }
    
    public void setCurrency(String currency){
        this.currency = currency.toUpperCase();
    }
    
    public String getCurrency(){
        return currency;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    
    private String getPriceForToString(){
        
        return ((int)price/price==1)?(""+(int)price):""+price;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.product);
        hash = 83 * hash + Objects.hashCode(this.currency);
        hash = 79 * hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public String toString() {
        String res = product + " "+ getPriceForToString()+" "+currency; 
        return res;
    }
    
    
}
