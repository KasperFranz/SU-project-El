/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.DBHandler;
import java.sql.SQLException;
import javax.swing.JMenuItem;
import model.Employee;
import model.Worksheet;
import testPackage.CalendarPanelTest;


/**
 *
 * @author Marc
 */
public class MainFrame extends javax.swing.JFrame {

    private Employee activeUser;
    private DBHandler dbhandler;

    /**
     * Creates new form MainFrame
     */
    public MainFrame(Employee user, DBHandler dbhandler) throws SQLException {
        this.dbhandler = dbhandler;
        activeUser = user;

        initComponents();
        initiateFrames();                
    }

    private void initiateFrames() throws SQLException {
        SKS_Sheet sheet = new SKS_Sheet(activeUser, dbhandler);
        mainframeTabbedPane.addTab("SKS skema", sheet);
        
         MaterialeList mi = new MaterialeList(activeUser, dbhandler);
        mainframeTabbedPane.addTab("Materiale liste", mi);
        
        WelcomeTab welcome = new WelcomeTab(activeUser);
        mainframeTabbedPane.addTab("   Forside   ", welcome);
        
        CalendarTab calendar = new CalendarTab(dbhandler);
        mainframeTabbedPane.addTab(" Arbejdskalender ", calendar);

        if (activeUser.getAccessLevel() == 1) {
            StaffTab staff = new StaffTab(dbhandler, false);
            mainframeTabbedPane.addTab("   Personale   ", staff);
            
           CalendarPanelTest tp = new  CalendarPanelTest(dbhandler);
           mainframeTabbedPane.add("Rediger arbejdsseddel", tp);
            CreateWorksheet cw = new CreateWorksheet(dbhandler);
            mainframeTabbedPane.addTab("Opret arbejdsseddel" , cw);
            
            
        } else if (activeUser.getAccessLevel() == 2) {
            StaffTab staff = new StaffTab(dbhandler,true);
            mainframeTabbedPane.addTab("   Personale   ", staff);
           CalendarPanelTest tp = new  CalendarPanelTest(dbhandler);
           mainframeTabbedPane.add("Rediger arbejdsseddel", tp); 
            CreateWorksheet cw = new CreateWorksheet(dbhandler);
            mainframeTabbedPane.addTab("Opret arbejdsseddel" , cw);

        }

  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainframeTabbedPane = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jMenu1.setText("File");

        jMenuItem1.setText("Log ud");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Luk program");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainframeTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainframeTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
      dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainFrame().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JTabbedPane mainframeTabbedPane;
    // End of variables declaration//GEN-END:variables
}
