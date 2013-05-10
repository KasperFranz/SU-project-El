/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Kasper
 */
public class CalendarItem {
    private int orderId;
    private Date timeOfJob;
    private String customerName, customerAdress, customerPhone, jobDescription,comment;
    private Employee employee;

    public CalendarItem(int orderId,Date timeOfJob, String customerName, String customerAdress, String customerPhone, String jobDescription, Employee employee, String comment) {
        this.orderId = orderId;
        this.timeOfJob = timeOfJob;
        this.customerName = customerName;
        this.customerAdress = customerAdress;
        this.customerPhone = customerPhone;
        this.jobDescription = jobDescription;
        this.employee = employee;
        this.comment = comment;
    }
    
        public CalendarItem(int orderId,Date timeOfJob, String customerName, String customerAdress, String customerPhone, String jobDescription) {
        this.orderId = orderId;
        this.timeOfJob = timeOfJob;
        this.customerName = customerName;
        this.customerAdress = customerAdress;
        this.customerPhone = customerPhone;
        this.jobDescription = jobDescription;
    }

    public CalendarItem(Date timeOfJob, String jobDescription) {
        this.jobDescription = jobDescription;
        this.timeOfJob = timeOfJob;
    }

    private String getTimeTxt() {

        String dateFormat = "dd/MM-yyyy HH:mm";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    @Override
    public String toString() {
        String tempEmployee;
        if (employee == null) {
            tempEmployee = "No Employee";
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}