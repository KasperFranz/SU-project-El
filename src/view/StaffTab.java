/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.DBHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Employee;

/**
 *
 * @author Marc
 */
public class StaffTab extends javax.swing.JPanel {

    private DBHandler dbHandler;
    private DefaultListModel employeeListModel;
    private final String UPDATEUSERBUTTONTEXT = "Opdater bruger";
    private final String SAVEUSERBUTTONTEXT = "Gem bruger";

    /**
     * Creates new form Settings
     */
    public StaffTab(DBHandler dbHandler) throws SQLException {
        this.dbHandler = dbHandler;
        employeeListModel = new DefaultListModel();

        loadEmployee();

        initComponents();
    }

    private void loadEmployee() throws SQLException {
        int oldSelection = -1;
        try {
            oldSelection = employeeList.getSelectedIndex();
            employeeList.clearSelection();

        } catch (NullPointerException ex) {
            System.out.println("Vi ved der kommer en null point exception ved EmployeeList");
        }
        employeeListModel.clear();
        ArrayList<Employee> employees = dbHandler.retrieveAllEmployees();
        for (int i = 0; i < employees.size(); i++) {
            employeeListModel.addElement(employees.get(i));
        }
        if (oldSelection >= 0) {
            employeeList.setSelectedIndex(oldSelection);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeList = new javax.swing.JList(employeeListModel);
        jLabel4 = new javax.swing.JLabel();
        oldEmployeeName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        oldEmployeeUsername = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        updateUserDetails = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        oldEmployeeAccesslevelBox = new javax.swing.JComboBox();
        oldEmployeePassword = new javax.swing.JPasswordField();
        newUserPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        newEmployeeName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        newEmployeeUsername = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        newEmployeePassword = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        newEmployeeAccesslevelBox = new javax.swing.JComboBox();

        jLabel1.setText("BorderTestLabel");

        jLabel2.setText("BorderTestLabel");

        jLabel3.setText("BorderTestLabel");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Eksisterende Brugere"));

        employeeList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                employeeListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(employeeList);

        jLabel4.setText("Navn:");

        oldEmployeeName.setEnabled(false);

        jLabel5.setText("Brugernavn:");

        oldEmployeeUsername.setEnabled(false);

        jLabel6.setText("Kodeord:");

        updateUserDetails.setText(UPDATEUSERBUTTONTEXT);
        updateUserDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUserDetailsActionPerformed(evt);
            }
        });

        jLabel10.setText("Adgangsniveau:");

        oldEmployeeAccesslevelBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ansat", "Overmontør", "Admin" }));
        oldEmployeeAccesslevelBox.setEnabled(false);

        oldEmployeePassword.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(oldEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(oldEmployeeAccesslevelBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(oldEmployeePassword, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(updateUserDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(oldEmployeeUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(oldEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(oldEmployeeUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(oldEmployeePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(oldEmployeeAccesslevelBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(updateUserDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))))
        );

        newUserPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Opret ny bruger"));

        jLabel7.setText("Navn:");

        jLabel8.setText("Brugernavn:");

        jLabel9.setText("Kodeord:");

        jButton2.setText("Registrer bruger");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setText("Adgangsniveau:");

        newEmployeeAccesslevelBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ansat", "Overmontør", "Admin" }));

        javax.swing.GroupLayout newUserPanelLayout = new javax.swing.GroupLayout(newUserPanel);
        newUserPanel.setLayout(newUserPanelLayout);
        newUserPanelLayout.setHorizontalGroup(
            newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newUserPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(newEmployeePassword, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(newEmployeeUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11)
                    .addComponent(newEmployeeAccesslevelBox, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(167, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newUserPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        newUserPanelLayout.setVerticalGroup(
            newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newUserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newEmployeeUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newEmployeePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(newEmployeeAccesslevelBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newUserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newUserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void employeeListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_employeeListValueChanged
        if (employeeList.getSelectedIndex() >= 0) {
            Employee g = (Employee) employeeList.getSelectedValue();
            oldEmployeeName.setText(g.getName());
            oldEmployeePassword.setText(g.getPassword());
            oldEmployeeUsername.setText(g.getUsername());
            oldEmployeeAccesslevelBox.setSelectedIndex(g.getAccessLevel());
            if (!updateUserDetails.getText().equals(UPDATEUSERBUTTONTEXT)) {
                updateUserDetails.setText(UPDATEUSERBUTTONTEXT);
                changeEmployeeButtonState(false);
            }
        }
    }//GEN-LAST:event_employeeListValueChanged

    private void updateUserDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUserDetailsActionPerformed
        boolean currentState;
        if (updateUserDetails.getText().equals(UPDATEUSERBUTTONTEXT)) {
            currentState = true;
            updateUserDetails.setText(SAVEUSERBUTTONTEXT);
        } else {
            currentState = false;
            updateUserDetails.setText(UPDATEUSERBUTTONTEXT);
        }
        updateUserDetails.setText(SAVEUSERBUTTONTEXT);

        changeEmployeeButtonState(currentState);
    }//GEN-LAST:event_updateUserDetailsActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        insertUser();
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList employeeList;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox newEmployeeAccesslevelBox;
    private javax.swing.JTextField newEmployeeName;
    private javax.swing.JTextField newEmployeePassword;
    private javax.swing.JTextField newEmployeeUsername;
    private javax.swing.JPanel newUserPanel;
    private javax.swing.JComboBox oldEmployeeAccesslevelBox;
    private javax.swing.JTextField oldEmployeeName;
    private javax.swing.JPasswordField oldEmployeePassword;
    private javax.swing.JTextField oldEmployeeUsername;
    private javax.swing.JButton updateUserDetails;
    // End of variables declaration//GEN-END:variables

    private void changeEmployeeButtonState(boolean StateNow) {


        System.out.println("Changes to" + !StateNow);
        oldEmployeeName.setEditable(StateNow);
        oldEmployeeName.setEnabled(StateNow);
        oldEmployeePassword.setEditable(StateNow);
        oldEmployeePassword.setEnabled(StateNow);
        oldEmployeeUsername.setEnabled(StateNow);
        oldEmployeeUsername.setEditable(StateNow);
        oldEmployeeAccesslevelBox.setEnabled(StateNow);
        if (!StateNow) {
            UpdateUser();
        }
    }

    private void UpdateUser() {
        if (oldEmployeeName.getText().isEmpty() || oldEmployeePassword.getText().isEmpty() || oldEmployeeUsername.getText().isEmpty() || oldEmployeeUsername.getText().isEmpty()) {
            System.out.println("UDFYLD ALLE FELTER!");
        } else {
            try {
                Employee oldEmployee = (Employee) employeeList.getSelectedValue();
                oldEmployee.setPassword(oldEmployeePassword.getText());
                oldEmployee.setName(oldEmployeeName.getText());
                oldEmployee.setAccessLevel(oldEmployeeAccesslevelBox.getSelectedIndex());
                oldEmployee.setUsername(oldEmployeeUsername.getText());
                dbHandler.updateEmployee(oldEmployee);
                loadEmployee();
            } catch (SQLException ex) {
                System.out.println("WE GOT A ERROR ON THE UPDATE SCRIPT\n" + ex);
            }
        }
    }

    private void insertUser() {
        if (newEmployeePassword.getText().isEmpty() || newEmployeeName.getText().isEmpty() || newEmployeeUsername.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Udfyld alle felterne!");
        } else {
            try {
                Employee newEmployee = new Employee(newEmployeeUsername.getText(), newEmployeeName.getText(), newEmployeePassword.getText(), newEmployeeAccesslevelBox.getSelectedIndex());
                dbHandler.insertEmployee(newEmployee);
                loadEmployee();
                clearNewEmployee();
            } catch (SQLException ex) {
                System.out.println("WE GOT A ERROR ON THE INSERT SCRIPT\n" + ex);
            }
        }
    }

    private void clearNewEmployee() {
        newEmployeeAccesslevelBox.setSelectedIndex(0);
        newEmployeeName.setText("");
        newEmployeePassword.setText("");
        newEmployeeUsername.setText("");
    }
}
