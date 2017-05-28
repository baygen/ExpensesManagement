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
import org.junit.rules.ExpectedException;

/**
 *
 * @author Buy
 */
public class HandlerTest {
    
    public HandlerTest() {
    }
    static Handler instance ;
    String[] data;
    @BeforeClass
    public static void setUpClass() {
        instance=new Handler(new ExpensesManager());
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        data = new String[]{"add","2016-05-14","50","USD","juice"};

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
        String expResult = data[3];
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
        String price = "-3.765";
//        Handler instance = null;
//        double expResult = 50.0;
//        double result = 
        ExpectedException exception =ExpectedException.none();
        exception.expect(NumberFormatException.class);
                instance.checkForPrice(price);
//        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    @Test
    public void testDoRequest(){
        System.out.println("checkDoRequst");
        
        
        String expResult = data[1]+System.lineSeparator()+ data[4]+" "+ data[2]+" "+data[3]+System.lineSeparator();
        String result = instance.doRequest(data);
        assertEquals("Add comand", expResult, result);
    }
}
