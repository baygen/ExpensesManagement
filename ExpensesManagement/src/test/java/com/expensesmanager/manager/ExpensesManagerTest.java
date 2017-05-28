/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.manager;

import com.expensesmanager.entity.Expense;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeMap;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runners.Parameterized;

/**
 *
 * @author Buy
 */
public class ExpensesManagerTest {
    
    static ExpensesManager instance;
    public ExpensesManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        TreeMap<LocalDate,ArrayList<Expense>> data= new TreeMap<>();
        ArrayList<Expense> value=new ArrayList<>();
        value.add(new Expense("apple", "PLN", 40));
        data.put(LocalDate.now(), value);
        data.put(LocalDate.now().minusMonths(1), value);
        instance = new ExpensesManager(data);
    }
    

    @Parameterized.Parameter(0)
    public LocalDate date;
    @Parameterized.Parameter(1)
    public int expResult;
    
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Object[][]date = new Object[][]{
            {LocalDate.now(),-1},
            {LocalDate.now().minusDays(3),0}
        };
        return Arrays.asList(date);
    }
    /**
     * Test of addPurhase method, of class ExpensesManager.
     */
//    @Ignore
    @Test 
    public void testAddPurhase() {
        System.out.println("addPurhase");
        LocalDate dates = LocalDate.now().minusDays(3);
        double price = 30.0;
        String currency = "pln";
        String product = "res9iw";
        int expResults = 1;
        int result = instance.addPurhase(dates, price, currency, product);
        assertEquals(expResults, result);

    }
  

    /**
     * Test of getTotalSpentInCurrency method, of class ExpensesManager.
     */
    @Ignore
    @Test
    public void testGetTotalSpentInCurrency() {
        System.out.println("getTotalSpentInCurrency");
        String currency = "eur";
        double expResults = 10.0;
        double result = instance.getTotalSpentInCurrency(currency);
        assertEquals(expResults, result, 0.0);

    }
     /**
     * Test of removePurchaseByDate method, of class ExpensesManager.
     */
    @Test
    public void testRemovePurchaseByDate() {
        System.out.println("removePurchaseByDate");
        assertEquals(expResult, instance.removePurchaseByDate(date));

    }

    /**
     * Test of getResult method, of class ExpensesManager.
     */
    @Test
    public void testGetResult() {
        System.out.println("getResult");
        String expResults = "2017-04-28"+"\n"+"apple 40 PLN"+"\n"+"\n"
                +"2017-05-28"+"\n"+"apple 40 PLN"+"\n";
        String result = instance.getResult();
        assertEquals(expResults, result);

    }


}
