/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.expensesmanager.entity;

import com.expensesmanager.entity.Expense;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 *if needed, this class should save and load existed data from file 
 * into TreeMap, that may be added into expenses manager.
 * 
 * not supported 
 * @author Buy
 */
public class ExpensesData implements ExpensesList{

    private final String file;
    private TreeMap<LocalDate, ArrayList<Expense>> list;
    private FileInputStream fis;

    public ExpensesData() {
       file = "data.dat";
       checkFile();
       setListFromFile();
    }
    
    @Override
    public TreeMap<LocalDate, ArrayList<Expense>> getList() {
        return list;
    }

    
    @SuppressWarnings("unchecked")
    private void setListFromFile(){
        try{
            fis = new FileInputStream(file);
            ObjectInputStream os = new ObjectInputStream(fis);
            if(os.readObject()!=null){
                this.list=(TreeMap<LocalDate, ArrayList<Expense>>)os.readObject();
            }else{
                this.list=new TreeMap<>();
            }
            
        }catch(ClassNotFoundException| FileNotFoundException e){
            saveListTofile(new LinkedHashMap<>());
        } catch (IOException ex) {
            
        }finally{
            try {
                fis.close();
            } catch (IOException ex) {
            }
        }
        
    }

    @Override
    public void saveListTofile(LinkedHashMap<LocalDate, ArrayList<Expense>> data) {
        
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(data);
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            
        } finally {
            try {
                fs.close();
            } catch (IOException ex) {
            }
        }
        
    }

    private void checkFile() {
        if(Files.notExists(Paths.get(file)))
            try {
                Files.createFile(Paths.get(file));
        } catch (IOException ex) {
            
        }
    }
}
