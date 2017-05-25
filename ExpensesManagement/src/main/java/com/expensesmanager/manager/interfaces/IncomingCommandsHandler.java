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
public interface IncomingCommandsHandler {
    
    /**
     *
     * @param request is input data array from user with command
     * this method have to choose command that arrives, and pass it into manager.
     * @return the result of command executed 
     */
    String doRequest(String[] request);
    
}
