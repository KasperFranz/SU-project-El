/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testPackage;

import control.DBHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Worksheet;
import model.Employee;

/**
 *
 * @author Nikolaj & Kasper
 */
public class CalendarPanelTest extends JPanel {

    private DBHandler dbHandler;
    private DefaultListModel worksheetListModel;

    /**
     * Creates new form CalendarPanelTest
     */
    public CalendarPanelTest(DBHandler dbHandler) throws SQLException {
        worksheetListModel = new DefaultListModel();
        this.dbHandler = dbHandler;
        initComponents();
        fillEmployeeCombo();
    }

    /**
     * Clear and load the worksheets!
     *
     * @param worksheets the new worksheets we gonna add to the worksheet list.
     */
    private void LoadWorksheets(ArrayList<Worksheet> worksheets) {
        int oldSelection = -1;
        try {
            oldSelection = worksheetList.getSelectedIndex();
            worksheetList.clearSelection();
        } catch (NullPointerException ex) {
            // there are comming a exception first time this method is calling because we cant get a selected item when there are none in the list.
            System.out.println("Vi ved der kommer en null point exception ved CalendarItem første gang den kører.");
        }
        // clearing the worksheetList Model!
        worksheetListModel.clear();
        // For each worksheet we add it to the worksheet list!
        for (int i = 0; i < worksheets.size(); i++) {
            worksheetListModel.addElement(worksheets.get(i));
        }
        // if the oldSelection is 0 or larger we gonna set it as the selected index.
        if (oldSelection >= 0) {
            System.out.println("3!");
            worksheetList.setSelectedIndex(oldSelection);
        }
    }

    /**
     * Fill the employee combo box with all employees from the database.
     *
     * @throws SQLException throws a SQLException.
     */
    private void fillEmployeeCombo() throws SQLException {
        // First we gonna remove all items from the combobox (so we allways got all the employees)
        employeeComboBox.setSelectedIndex(-1);
        employeeComboBox.removeAllItems();
        // first we gonna add a all item (there gonna show all the worksheets.
        employeeComboBox.addItem("Alle");
        // we gonna grab all the employees from the database and add it in the combobox.
        ArrayList<Employee> employees = dbHandler.retrieveAllEmployees();
        for (int i = 0; i < employees.size(); i++) {
            employeeComboBox.addItem(employees.get(i));
        }
        employeeComboBox.setSelectedIndex(0);
    }

    /**
     * Fill the combobox with all the assigned Employees.
     *
     * @param worksheet the worksheet you gonna take the employees from
     */
    private void fillAssignedEmployeeCombo(Worksheet worksheet) {
        //First we gonna remove all the items (so we allways got fresh data)
        assignedEmployee.setSelectedIndex(-1);
        assignedEmployee.removeAllItems();
        // foreach employee we gonna add the employee to the assigned employees combobox
        for (int i = 0; i < worksheet.getEmployees().size(); i++) {
            assignedEmployee.addItem(worksheet.getEmployees().get(i));
        }

        // IF there are no employees added to the worksheet we gonna add a text to say this.
        if (worksheet.getEmployees().isEmpty()) {
            assignedEmployee.addItem("Ingen tilknyttet");
        }
    }

    /**
     * Gonna change the button/text fields to disable or enabled
     *
     * @param StateNow the state you want it changed to.
     */
    private void changeEmployeeButtonState(boolean StateNow) {
        dateTextField.setEditable(StateNow);
        dateTextField.setEnabled(StateNow);
        descriptionTextArea.setEditable(StateNow);
        descriptionTextArea.setEnabled(StateNow);
        customerAddressTextField.setEnabled(StateNow);
        customerAddressTextField.setEditable(StateNow);
        customerNameTextField.setEditable(StateNow);
        customerNameTextField.setEnabled(StateNow);
        customerPhoneTextField.setEditable(StateNow);
        customerPhoneTextField.setEnabled(StateNow);
        assignedEmployee.setEnabled(StateNow);
        descriptionTextArea.setEnabled(StateNow);
        descriptionTextArea.setEditable(StateNow);
        commentTextArea.setEditable(StateNow);
        commentTextArea.setEnabled(StateNow);
    }

    /**
     * Removing all the text from the fields and assigning assignedEmployees to
     * no worksheet.
     */
    private void clearFields() {
        assignedEmployee.setSelectedIndex(-1);
        assignedEmployee.removeAllItems();
        commentTextArea.setText("");
        customerAddressTextField.setText("");
        customerNameTextField.setText("");
        customerPhoneTextField.setText("");
        descriptionTextArea.setText("");
        assignedEmployee.addItem("Ingen arbejdsseddel");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateTextField = new javax.swing.JTextField();
        dateLabel = new javax.swing.JLabel();
        jobDescriptionLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        assignedEmployeesLabel = new javax.swing.JLabel();
        customerNameLabel = new javax.swing.JLabel();
        customerNameTextField = new javax.swing.JTextField();
        customerAddressTextField = new javax.swing.JTextField();
        customerPhoneTextField = new javax.swing.JTextField();
        CustomerAddressLabel = new javax.swing.JLabel();
        CustomerPhoneLabel = new javax.swing.JLabel();
        CommentLabel = new javax.swing.JLabel();
        CommentScroll = new javax.swing.JScrollPane();
        commentTextArea = new javax.swing.JTextArea();
        updateButton = new javax.swing.JButton();
        assignedEmployee = new javax.swing.JComboBox();
        employeeList = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        employeeComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        worksheetList = new javax.swing.JList(worksheetListModel);
        deleteWorksheet = new javax.swing.JButton();

        dateLabel.setText("Dato/tid:");

        jobDescriptionLabel.setText("Beskrivelse:");

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        jScrollPane2.setViewportView(descriptionTextArea);

        assignedEmployeesLabel.setText("Medarbejdere:");

        customerNameLabel.setText("Kundens navn:");

        CustomerAddressLabel.setText("Kundens Adresse:");

        CustomerPhoneLabel.setText("Kundens telefon nummer:");

        CommentLabel.setText("Kommentar:");

        commentTextArea.setColumns(20);
        commentTextArea.setRows(5);
        CommentScroll.setViewportView(commentTextArea);

        updateButton.setText("Opdater arbejdsseddel");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        employeeList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Vælg medarbejder:");

        employeeComboBox.setModel(new javax.swing.DefaultComboBoxModel());
        employeeComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                employeeComboBoxItemStateChanged(evt);
            }
        });

        worksheetList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                worksheetListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(worksheetList);

        javax.swing.GroupLayout employeeListLayout = new javax.swing.GroupLayout(employeeList);
        employeeList.setLayout(employeeListLayout);
        employeeListLayout.setHorizontalGroup(
            employeeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
            .addGroup(employeeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(employeeListLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(employeeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addComponent(employeeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        employeeListLayout.setVerticalGroup(
            employeeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(employeeListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(employeeListLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(employeeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        deleteWorksheet.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        deleteWorksheet.setForeground(new java.awt.Color(255, 0, 0));
        deleteWorksheet.setText("Slet");
        deleteWorksheet.setToolTipText("");
        deleteWorksheet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteWorksheetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(employeeList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateLabel)
                    .addComponent(jobDescriptionLabel)
                    .addComponent(assignedEmployeesLabel)
                    .addComponent(jScrollPane2)
                    .addComponent(dateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(assignedEmployee, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CustomerAddressLabel)
                    .addComponent(customerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CommentLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(CustomerPhoneLabel)
                        .addComponent(customerNameLabel)
                        .addComponent(customerAddressTextField)
                        .addComponent(customerPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(deleteWorksheet)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updateButton))
                        .addComponent(CommentScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addComponent(customerNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(customerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CustomerAddressLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CustomerPhoneLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CommentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CommentScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateButton)
                            .addComponent(deleteWorksheet))
                        .addGap(9, 9, 9))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jobDescriptionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(assignedEmployeesLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(assignedEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(employeeList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void worksheetListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_worksheetListValueChanged
        if (worksheetList.getSelectedIndex() >= 0 && worksheetList.getSelectedValue() instanceof Worksheet) {
            Worksheet ci = (Worksheet) worksheetList.getSelectedValue();

            fillAssignedEmployeeCombo(ci);
            changeEmployeeButtonState(true);
            // TODO here we gonna set the calendar/time picker
            descriptionTextArea.setText(ci.getJobDescription());
            customerNameTextField.setText(ci.getCustomerName());
            customerAddressTextField.setText(ci.getCustomerAdress());
            customerPhoneTextField.setText(ci.getCustomerPhone());
            commentTextArea.setText(ci.getComment());
        }
    }//GEN-LAST:event_worksheetListValueChanged

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        try {
            Worksheet worksheet = (Worksheet) worksheetList.getSelectedValue();
            worksheet.setComment(commentTextArea.getText());
            worksheet.setCustomerAdress(customerAddressTextField.getText());
            worksheet.setCustomerName(customerNameTextField.getText());
            worksheet.setCustomerPhone(customerPhoneTextField.getText());
            worksheet.setJobDescription(descriptionTextArea.getText());
            clearFields();
            changeEmployeeButtonState(false);
//          TODO  Date date = SetTimeOfJob HERE!!!!!!
//            worksheet.setTimeOfJob(date);
            dbHandler.updateWorksheet(worksheet);
            fillEmployeeCombo();
        } catch (SQLException ex) {
            System.out.println("ERROR WITH SAVING" + ex);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void employeeComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_employeeComboBoxItemStateChanged
        if (employeeComboBox.getSelectedIndex() >= 0) {
            worksheetListModel.clear();

            clearFields();
            changeEmployeeButtonState(false);
            try {
                if (employeeComboBox.getSelectedItem().equals("Alle")) {

                    LoadWorksheets(dbHandler.retrieveAllWorksheets());

                } else {

                    LoadWorksheets(dbHandler.retrieveWorksheets((Employee) employeeComboBox.getSelectedItem()));
                }
            } catch (SQLException ex) {
                System.out.println("We got a retrieve worksheet ERROR");
            }
        }
    }//GEN-LAST:event_employeeComboBoxItemStateChanged

    private void deleteWorksheetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteWorksheetActionPerformed
        try {
            int delete = JOptionPane.showConfirmDialog(this, "Er du sikker på du vil slette denne arbejdsseddel?");


            if (delete == JOptionPane.YES_OPTION) {
                Worksheet worksheet = (Worksheet) worksheetList.getSelectedValue();
                if (worksheet != null) {
                    dbHandler.deleteWorksheet(worksheet);
                    fillEmployeeCombo();
                    clearFields();
                    changeEmployeeButtonState(false);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_deleteWorksheetActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CommentLabel;
    private javax.swing.JScrollPane CommentScroll;
    private javax.swing.JLabel CustomerAddressLabel;
    private javax.swing.JLabel CustomerPhoneLabel;
    private javax.swing.JComboBox assignedEmployee;
    private javax.swing.JLabel assignedEmployeesLabel;
    private javax.swing.JTextArea commentTextArea;
    private javax.swing.JTextField customerAddressTextField;
    private javax.swing.JLabel customerNameLabel;
    private javax.swing.JTextField customerNameTextField;
    private javax.swing.JTextField customerPhoneTextField;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JButton deleteWorksheet;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JComboBox employeeComboBox;
    private javax.swing.JPanel employeeList;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jobDescriptionLabel;
    private javax.swing.JButton updateButton;
    private javax.swing.JList worksheetList;
    // End of variables declaration//GEN-END:variables
}
