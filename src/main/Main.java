/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.SQLException;
import view.LoginFrame;
import view.MainFrame;

/**
 *
 * @author Marc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        LoginFrame login = new LoginFrame();
        login.setVisible(true);
        
    }
}
