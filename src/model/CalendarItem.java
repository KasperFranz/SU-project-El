/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Kasper
 */
public class CalendarItem {

    long time;
    String customerName, customerAdress, customerPhone, jobDescription;
    Employee employee;

    public CalendarItem(long time, String customerName, String customerAdress, String customerPhone, String jobDescription, Employee employee) {
        this.time = time;
        this.customerName = customerName;
        this.customerAdress = customerAdress;
        this.customerPhone = customerPhone;
        this.jobDescription = jobDescription;
        this.employee = employee;
    }
    
    public CalendarItem(long time, String jobDescription) {
        this.jobDescription = jobDescription;
        this.time = time;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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
    
    
}