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

    long time;
    String text;
    Employee employee;

    public CalendarItem(long time, String text, Employee employee) {
        this.employee = employee;
        this.text = text;
        this.time = time;
    }

    public CalendarItem(long time, String text) {
        this.text = text;
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
        return getTimeTxt() + " " + text + " - " + tempEmployee;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    
}