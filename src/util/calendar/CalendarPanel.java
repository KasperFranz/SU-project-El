/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.calendar;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Marc
 */
public class CalendarPanel extends javax.swing.JPanel {

    private Calendar calendar;
    private Date date;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("d");
    private SimpleDateFormat monthFormat = new SimpleDateFormat("mm");
    private final int PANELS_WIDTH = 30;
    private final int PANELS_HEIGHT = 30;

    /**
     * Creates new form CalendarPanel
     */
    public CalendarPanel() {
        
        calendar = Calendar.getInstance();
        
        initComponents();
        generateDayBar();
        generateTopBar();
        generateContentContainer();
        
        initComponents();
    }

     /***************************************************************************
    *  Generates the top panel showing the current month and year
    ***************************************************************************/
    private void generateTopBar(){
        
        monthContainer.removeAll();
        monthContainer.repaint();
        JLabel monthLabel = new JLabel(monthToString(calendar.get(Calendar.MONTH)) + " - " + calendar.get(Calendar.YEAR));
        
        monthLabel.setBounds(50, 2, 150, 14);
        monthContainer.add(monthLabel);
        System.out.println(monthLabel.getText());
        
    }
    
    /***************************************************************************
    *  Returns the parameter months name as a String
    ***************************************************************************/  
    private String monthToString(int monthNumber){
        
        String month;
        
        switch(monthNumber){
            case 0:
                month = "January";
                break;
            case 1:
                month = "February";
                break;
            case 2:
                month = "Marts";
                break;
            case 3:
                month = "April";
                break;
            case 4:
                month = "May";
                break;
            case 5:
                month = "June";
                break;
            case 6:
                month = "July";
                break;
            case 7:
                month = "August";
                break;
            case 8:
                month = "September";
                break;
            case 9:
                month = "October";
                break;
            case 10:
                month = "November";
                break;
            case 11:
                month = "December";
                break;
            default:
                month = "Unknown";
                break;
        }
        return month;
    }
  
    /***************************************************************************
    * GENERATES THE TOP PANEL SHOWING THE DAYS OF THE WEEK
    ***************************************************************************/
    private void generateDayBar(){
        
        int yLocation = 0;
        int xLocation = 0;
        int width = 30;
        int height = 30;
         
        
        for (int i = 0; i < 7; i++) {
            
            JPanel dayPanel = new JPanel();
            JLabel dayLabel = new JLabel(defineDay(i));
            dayPanel.setBounds(xLocation, yLocation, width, height);
            dayPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            dayPanel.setBackground(new Color(102, 102, 102));
            dayPanel.add(dayLabel);
            dayContainer.add(dayPanel);
                    
            xLocation += width;
        }
 
    }
    
    /***************************************************************************
     * A helping method for the generateDayBar method that returns a string
     * with the name of the day to be put on the jPanel.
     * @param day
     * @return dayOfWeek
     **************************************************************************/
    private String defineDay(int day){
        
        String dayOfWeek;
        
        switch(day){
            case 0:
                dayOfWeek = "Mon";
                break;
            case 1:
                dayOfWeek = "Tue";
                break;
            case 2:
                dayOfWeek = "Wed";
                break;
            case 3:
                dayOfWeek = "Thu";
                break;
            case 4:
                dayOfWeek = "Fri";
                break;
            case 5:
                dayOfWeek = "Sat";
                break;
            case 6:
                dayOfWeek = "Sun";
                break;
            default:
                dayOfWeek = "Unknown";
                break;
        }
        return dayOfWeek;
    }
    
    /***************************************************************************
    * GENERATES THE SIDE PANELS SHOWING THE WEEK NUMBERS
    ***************************************************************************/
    private void generateWeekBar(JPanel panel){
        
        int yLocation = 0;
        int xLocation = 0;
        int width = 30;
        int height = 30;
        
        Calendar calCopy = (Calendar) calendar.clone();
        
        for (int i = 0; i < 5; i++) {
            
            JPanel weekPanel = new JPanel();
            JLabel weekLabel = new JLabel(calCopy.get(Calendar.WEEK_OF_YEAR) + "");
            weekPanel.setBounds(xLocation, yLocation, width, height);
            weekPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            weekPanel.setBackground(new Color(204, 204, 204));
            weekPanel.add(weekLabel);
            panel.add(weekPanel);
            
            calCopy.add(Calendar.WEEK_OF_YEAR, 1);
            yLocation += height;
        }
    }
    /***************************************************************************
    * GENERATES THE CONTAINER RESPONSIBLE FOR THE WEEKPANELS CONTAINER AND
    * FOR THE DATE PANELS CONTAINER
    ***************************************************************************/
    private void generateContentContainer(){
       contentContainer.removeAll();
       contentContainer.validate();
       JPanel weekContainer = new JPanel();
       weekContainer.setLayout(null);
       weekContainer.setBounds(0, 0, PANELS_WIDTH, PANELS_HEIGHT*5);
       weekContainer.setVisible(true);
       contentContainer.add(weekContainer);
       generateWeekBar(weekContainer);
       
       JPanel datePanelsContainer = new JPanel();
       datePanelsContainer.setLayout(null);
       datePanelsContainer.setBounds(PANELS_WIDTH, 0, PANELS_WIDTH*7, PANELS_HEIGHT*5);
       datePanelsContainer.setVisible(true);
       contentContainer.add(datePanelsContainer);
       generateDatePanels(datePanelsContainer);
       contentContainer.revalidate();
       contentContainer.repaint();
    }
    
    /***************************************************************************
    * GENERATES THE DATE PANELS FOR THE SELECTED MONTH
    ***************************************************************************/
    private void generateDatePanels(JPanel panel){
        
        final int DAYS_IN_MONTH = daysInMonth(calendar.get(Calendar.MONTH));
        
        Calendar calCopy = (Calendar) calendar.clone();
        calCopy.set(Calendar.DAY_OF_MONTH, 1);
        
        int xLocation = (firstDayInMonth()-1)*30; // The location on the x-axis of the panel, the starting location is defined by the starting day of the month
        int yLocation = 0; // The location on the y-axis of the next panel
        for (int i = 0; i < DAYS_IN_MONTH; i++) {
                datePanel datePanel = new datePanel(calCopy.getTime(), xLocation, yLocation, PANELS_WIDTH, PANELS_HEIGHT);
                panel.add(datePanel);
                
                xLocation += PANELS_WIDTH;
                calCopy.add(Calendar.DAY_OF_YEAR, 1);
                
                if(xLocation == 210){
                    xLocation = 0;
                    yLocation += 30;
                }
        }
         
    }
    
    private int firstDayInMonth(){
        
        int firstDay;
        Calendar tempCal = (Calendar) calendar.clone();
        tempCal.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 0); // Sets the Year, Month, current day of Month (0 for first day)
        firstDay = tempCal.get(Calendar.DAY_OF_WEEK);       
        return firstDay;
    }
    
    /***************************************************************************
    * Returns the number of days the parameter month consists of
    ***************************************************************************/
    private int daysInMonth(int month){
        
        int daysInMonth;
        switch(month){
            case 0:
                daysInMonth = 31;
                break;
            case 1:
                daysInMonth = 28;
                break;
            case 2:
                daysInMonth = 31;
                break;
            case 3:
                daysInMonth = 30;
                break;
            case 4: 
                daysInMonth = 31;
                break;
            case 5:
                daysInMonth = 30;
                break;
            case 6:
                daysInMonth = 31;
                break;
            case 7:
                daysInMonth = 31;
                break;
            case 8:
                daysInMonth = 30;
                break;
            case 9:
                daysInMonth = 31;
                break;
            case 10:
                daysInMonth = 30;
                break;
            case 11:
                daysInMonth = 31;
                break;
            default:
                daysInMonth = 0;
                break;
        }
        if(month == 1 && isLeapYear(month)){
            daysInMonth += 1;
        }
        
        return daysInMonth;
    }
    
    /***************************************************************************
    * Defines whether or not the parameter year is a leap year
    **************************************************************************/
    private boolean isLeapYear(int year){
        
        boolean leapYear;
        
        if(year%4 == 0){
            if(year%100 == 0){
                leapYear = (year % 400 == 0);
            }
            else{
                leapYear = true;
            }
        }
        else{
            leapYear = false;
        }
        return leapYear;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        monthContainer = new javax.swing.JPanel();
        topCornerContainer = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dayContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        monthForwardLabelButton = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        monthBackLabelButton = new javax.swing.JLabel();
        contentContainer = new javax.swing.JPanel();

        setLayout(null);

        monthContainer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        monthContainer.setMinimumSize(new java.awt.Dimension(180, 30));
        monthContainer.setPreferredSize(new java.awt.Dimension(180, 20));
        monthContainer.setLayout(null);
        add(monthContainer);
        monthContainer.setBounds(30, 0, 180, 20);

        topCornerContainer.setBackground(new java.awt.Color(102, 102, 102));
        topCornerContainer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        topCornerContainer.setMinimumSize(new java.awt.Dimension(30, 30));
        topCornerContainer.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel1.setText(" Week");
        topCornerContainer.add(jLabel1);
        jLabel1.setBounds(0, 17, 30, 10);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel2.setText(" day");
        topCornerContainer.add(jLabel2);
        jLabel2.setBounds(12, 2, 20, 10);

        add(topCornerContainer);
        topCornerContainer.setBounds(0, 20, 30, 30);

        dayContainer.setMinimumSize(new java.awt.Dimension(210, 30));
        dayContainer.setLayout(null);
        add(dayContainer);
        dayContainer.setBounds(30, 20, 210, 30);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setMinimumSize(new java.awt.Dimension(30, 30));
        jPanel1.setLayout(null);

        monthForwardLabelButton.setText(">>");
        monthForwardLabelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monthForwardLabelButtonMouseClicked(evt);
            }
        });
        jPanel1.add(monthForwardLabelButton);
        monthForwardLabelButton.setBounds(6, 2, 16, 14);

        add(jPanel1);
        jPanel1.setBounds(210, 0, 30, 20);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel2.setLayout(null);

        monthBackLabelButton.setText("<< ");
        monthBackLabelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                monthBackLabelButtonMouseClicked(evt);
            }
        });
        jPanel2.add(monthBackLabelButton);
        monthBackLabelButton.setBounds(6, 2, 19, 14);

        add(jPanel2);
        jPanel2.setBounds(0, 0, 30, 20);

        contentContainer.setLayout(null);
        add(contentContainer);
        contentContainer.setBounds(0, 50, 240, 150);
    }// </editor-fold>//GEN-END:initComponents

    private void monthForwardLabelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monthForwardLabelButtonMouseClicked
        calendar.add(Calendar.MONTH, 1);
        generateTopBar();
        generateContentContainer();
    }//GEN-LAST:event_monthForwardLabelButtonMouseClicked

    private void monthBackLabelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_monthBackLabelButtonMouseClicked
        calendar.add(Calendar.MONTH, -1);
        generateTopBar();
        generateContentContainer();
    }//GEN-LAST:event_monthBackLabelButtonMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentContainer;
    private javax.swing.JPanel dayContainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel monthBackLabelButton;
    private javax.swing.JPanel monthContainer;
    private javax.swing.JLabel monthForwardLabelButton;
    private javax.swing.JPanel topCornerContainer;
    // End of variables declaration//GEN-END:variables
}
