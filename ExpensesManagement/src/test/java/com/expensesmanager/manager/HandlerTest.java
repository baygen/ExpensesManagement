/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.manager;

import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Buy
 */
public class HandlerTest {
    
    public HandlerTest() {
    }
     Handler instance ;
     TestExpenses exp;
    String[] data;
    
    @Ignore
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @Ignore
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        data = new String[]{"add","2016-05-14","50","USD","juice"};
        exp = new TestExpenses();
        instance=new Handler(exp);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkForCurrency method, of class Handler.
     */
    @Test
    public void testCheckForCurrency() {
        System.out.println("checkForCurrency");
        String currency = data[3];
//        Handler instance = new Handler(new ExpensesManager());
        String expResult = data[3].toUpperCase();
        String result = instance.checkForCurrency(currency);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of checkForDate method, of class Handler.
     */
    @Test
    public void testCheckForDate() {
        System.out.println("checkForDate");
        String dateString = data[1];
//        Handler instance = null;
        LocalDate expResult = LocalDate.parse(data[1]);
//                LocalDate.parse(dateString);
        LocalDate result = instance.checkForDate(dateString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of checkForPrice method, of class Handler.
     */
    @Test
//        (expected = NumberFormatException.class)
    public void testCheckForPrice() {
        System.out.println("checkForPrice");
        
//        Handler instance = null;
        double expResult = 50.0;
        double result = Double.parseDouble(data[2]);
//        ExpectedException exception =ExpectedException.none();
//        exception.expect(NumberFormatException.class);
          instance.checkForPrice(data[2]);
        assertEquals(expResult, result, 0.0);

    }
    
    @Test
    public void testDoRequest(){
        System.out.println("checkDoRequst");
        
        
//        String expResult = data[1]+System.lineSeparator()+ data[4]+" "+ data[2]+" "+data[3]+System.lineSeparator();
            instance.doRequest(data);

        LocalDate expDate= LocalDate.parse(data[1]);
        LocalDate testDate=exp.testDate;
        double expDouble=Double.parseDouble(data[2]);
        double resultDouble=exp.testPrice;
//        assertEquals("Add comand", expDate  , testDate);
        assertEquals("Check for price value:",expDouble, resultDouble,0.0);
        assertEquals("Check for Date value:",expDate, testDate);
    }
    
    @Test
    public void testCheckAddCommand(){
        System.out.println("checkAddCommand");
        boolean result=instance.commandAdd(data);
        boolean expResult = true;
        assertEquals(expResult, result);
    }
}
