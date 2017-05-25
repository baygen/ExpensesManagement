/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expensesmanager;

import com.expensesmanager.frame.CommandsFrame;
import com.expensesmanager.manager.CommandsHandler;
import com.expensesmanager.manager.ExpensesManager;
import com.expensesmanager.entity.ExpensesData;
import com.expensesmanager.manager.Handler;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

/**
 *
 * @author Buy
 */
public class Main {

    CommandsFrame commandsFrame;
    
    public Main(CommandsFrame frame){
        this.commandsFrame = frame;
        
    }
    
    public static void main(String[] args) {
        
        Main st = new Main(new CommandsFrame());
        st.startFrame();
        st.startAPI();
            
    }
    
    private void startAPI() {
        
        ExpensesManager em = new ExpensesManager();
        Handler handler = new Handler(em);
        
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
