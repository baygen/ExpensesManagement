/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.entity;

import com.expensesmanager.entity.Expense;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *Interface with main logic for save and load data from file
 * @author Buy
 * not supported
 */
public interface ExpensesList {

    public Map<LocalDate, ArrayList<Expense>> getList();
    
    public void saveListTofile(LinkedHashMap<LocalDate,ArrayList<Expense>> data);
}
