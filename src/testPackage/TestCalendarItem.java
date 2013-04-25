/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testPackage;

import java.util.Date;
import model.CalendarItem;

/**
 *
 * @author Kasper
 */
public class TestCalendarItem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Date date = new Date();
        CalendarItem g = new CalendarItem(date.getTime(), "This world");
        System.out.println(g);
    }
}
