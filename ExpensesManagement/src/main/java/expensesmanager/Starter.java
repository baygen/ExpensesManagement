/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expensesmanager;

import com.expansesmanager.frame.CommandsFrame;
import com.expensesmanager.manager.CommandsHandler;
import com.expensesmanager.manager.ExpensesManager;
import com.expensesmanger.entity.ExpensesData;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

/**
 *
 * @author Buy
 */
public class Starter {

    CommandsFrame commandsFrame;
    
    public Starter(CommandsFrame frame){
        this.commandsFrame = frame;
        
    }
    
    public static void main(String[] args) {
        
        Starter st = new Starter(new CommandsFrame());
        st.startFrame();
        st.startAPI();
            
    }
    
    private void startAPI() {
        
        ExpensesManager em = new ExpensesManager();
        CommandsHandler handler = new CommandsHandler(em);
        
        JTextArea output = commandsFrame.getOutput();
        
        commandsFrame.getButton().addActionListener((ActionEvent e) -> {
            
            String command = commandsFrame.getCommandField().getText();
            String[] commandsInput = command.split("\\s");
            String res;
            res = handler.doRequest(commandsInput);
//            res = em.getResult();
                    
            output.setText(res);
            
        });
      
    }
    
    private void startFrame() {
        java.awt.EventQueue.invokeLater(() -> {
            commandsFrame.setVisible(true);
        });
    }

    
    
    
    
    
    
}
