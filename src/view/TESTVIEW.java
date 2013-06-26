/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.SQLException;

/**
 *
 * @author Kasper
 */
public class TESTVIEW {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        SKS_Sheet sheet = new SKS_Sheet(null, null);
        sheet.setVisible(true);
    }
}
