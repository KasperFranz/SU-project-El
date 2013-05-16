/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Kasper
 */
public class Worksheet {
    private int orderId;
    private Date timeOfJob;
    private String customerName, customerAdress, customerPhone, jobDescription,comment;
    private ArrayList<Employee> employee;

    public Worksheet(int orderId,Date timeOfJob, String customerName, String customerAdress, String customerPhone, String jobDescription, ArrayList<Employee> employee, String comment) {
        this.orderId = orderId;
        this.timeOfJob = timeOfJob;
        this.customerName = customerName;
        this.customerAdress = customerAdress;
        this.customerPhone = customerPhone;
        this.jobDescription = jobDescription;
        this.employee = employee;
        this.comment = comment;
    }

    public Worksheet(int orderId, Date timeOfJob, String customerName, String customerAdress, String customerPhone, String jobDescription, String comment) {
        this.orderId = orderId;
        this.timeOfJob = timeOfJob;
        this.customerName = customerName;
        this.customerAdress = customerAdress;
        this.customerPhone = customerPhone;
        this.jobDescription = jobDescription;
        this.comment = comment;
        employee = new ArrayList<>();
    }

    public Worksheet(Date timeOfJob, String customerName, String customerAdress, String customerPhone, String jobDescription, String comment, ArrayList<Employee> employee) {
        this.timeOfJob = timeOfJob;
        this.customerName = customerName;
        this.customerAdress = customerAdress;
        this.customerPhone = customerPhone;
        this.jobDescription = jobDescription;
        this.comment = comment;
        this.employee = employee;
    }
   
    private String getTimeTxt() {

        String dateFormat = "dd/MM-yyyy h:mm";
        Calendar cal = Calendar.getInstance();
        cal.setTime(timeOfJob);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        
        return sdf.format(cal.getTime());
    }

    public void setEmployee(ArrayList<Employee> employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        String tempEmployee;
        if (employee.isEmpty()) {
            tempEmployee = "[Ingen medarbejdere]";
        } else {
            tempEmployee = employee.toString();
        }
        return getTimeTxt() + " " + jobDescription + " - " + tempEmployee;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getTimeOfJob() {
        return timeOfJob;
    }

    public void setTimeOfJob(Date timeOfJob) {
        this.timeOfJob = timeOfJob;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAdress() {
        return customerAdress;
    }

    public void setCustomerAdress(String customerAdress) {
        this.customerAdress = customerAdress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }


    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public ArrayList<Employee> getEmployees() {
        return employee;
    }
    
    public Employee getEmplyeeAt(int i){
        return employee.get(i);
    }

    public void addEmployee(Employee employee){
        this.employee.add(employee);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}