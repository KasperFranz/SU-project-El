/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Marc
 */
public class Calendar extends javax.swing.JPanel {

    private java.util.Calendar calendar;
    private Date date;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("d");
    private SimpleDateFormat monthFormat = new SimpleDateFormat("mm");
    private int startWeek;
    private int currentWeek;
    private int selectedWeek;
    private int currentMonth;
    private int selectedMonth;
    private int selectedYear;
    private final int PANELS_WIDTH = 80;
    private final int PANELS_HEIGHT = 30;
    private final int HOURS = 11;
    private final int DAYS = 5;

    public Calendar() {

        calendar = calendar.getInstance();
        // calendar.setFirstDayOfWeek(Calendar.MONDAY);
        startWeek = calendar.get(java.util.Calendar.WEEK_OF_YEAR);
        selectedYear = calendar.get(java.util.Calendar.YEAR);
        currentWeek = calendar.get(java.util.Calendar.WEEK_OF_YEAR);
        selectedWeek = calendar.get(java.util.Calendar.WEEK_OF_YEAR);

        // String dato = dateFormat.format(date);
        System.out.println(calendar);
        System.out.println(currentMonth);


        initComponents();
        generateDayBar();
        generateTopBar(currentWeek);
        generateContentContainer();


    }

    /**
     * *************************************************************************
     * Generates the top panel showing the current month and year being shown
     * *************************************************************************
     */
    private void generateTopBar(int week) {

        monthContainer.removeAll();
        monthContainer.repaint();
        JLabel monthLabel = new JLabel("Uge " + selectedWeek + " " + selectedYear);
        monthLabel.setBounds(200, 5, 150, 20);
        monthContainer.add(monthLabel);
        System.out.println(monthLabel.getText());

    }

    /**
     * *************************************************************************
     * GENERATES THE TOP PANEL SHOWING THE DAYS OF THE WEEK
     * *************************************************************************
     */
    private void generateDayBar() {

        int yLocation = 0;
        int xLocation = 0;



        for (int i = 0; i < DAYS; i++) {

            JPanel dayPanel = new JPanel();
            JLabel dayLabel = new JLabel(defineDay(i));
            dayPanel.setBounds(xLocation, yLocation, PANELS_WIDTH, PANELS_HEIGHT);
            dayPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            dayPanel.setBackground(new Color(102, 102, 102));
            dayPanel.add(dayLabel);
            dayContainer.add(dayPanel);

            xLocation += PANELS_WIDTH;
        }

    }

    /**
     * *************************************************************************
     * A helping method for the generateDayBar method that returns a string with
     * the name of the day to be put on the jPanel.
     *
     * @param day
     * @return dayOfWeek
     * ************************************************************************
     */
    private String defineDay(int day) {

        String dayOfWeek;

        switch (day) {
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
//            case 5:
//                dayOfWeek = "Sat";
//                break;
//            case 6:
//                dayOfWeek = "Sun";
//                break;
            default:
                dayOfWeek = "Unknown";
                break;
        }
        return dayOfWeek;
    }

    /**
     * *************************************************************************
     * GENERATES THE SIDE PANELS SHOWING THE WEEK NUMBERS
     * *************************************************************************
     */
    private void generateTimeBar(JPanel panel) {


        int yLocation = 0;
        int xLocation = 0;


        for (int i = 0; i < HOURS; i++) {

            JPanel weekPanel = new JPanel();
            JLabel weekLabel = new JLabel(formatTime(i));
            weekPanel.setBounds(xLocation, yLocation, PANELS_WIDTH, PANELS_HEIGHT);
            weekPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            weekPanel.setBackground(new Color(204, 204, 204));
            weekPanel.add(weekLabel);
            panel.add(weekPanel);

            yLocation += PANELS_HEIGHT;
        }
    }

    private String formatTime(int time) {

        String formattedTime;

        switch (time) {
            case 0:
                formattedTime = "07.00";
                break;
            case 1:
                formattedTime = "08.00";
                break;
            case 2:
                formattedTime = "09.00";
                break;
            case 3:
                formattedTime = "10.00";
                break;
            case 4:
                formattedTime = "11.00";
                break;
            case 5:
                formattedTime = "12.00";
                break;
            case 6:
                formattedTime = "13.00";
                break;
            case 7:
                formattedTime = "14.00";
                break;
            case 8:
                formattedTime = "15.00";
                break;
            case 9:
                formattedTime = "16.00";
                break;
            default:
                formattedTime = "??.??";
                break;
        }

        return formattedTime;
    }

    /**
     * *************************************************************************
     * GENERATES THE CONTAINER RESPONSIBLE FOR THE WEEKPANELS CONTAINER AND FOR
     * THE DATE PANELS CONTAINER
     * *************************************************************************
     */
    private void generateContentContainer() {
        System.out.println("all clear - vi fylder op");
        contentContainer.validate();
        JPanel timeContainer = new JPanel();
        timeContainer.setLayout(null);
        timeContainer.setBounds(0, 0, PANELS_WIDTH, PANELS_HEIGHT * HOURS);
        timeContainer.setVisible(true);
        contentContainer.add(timeContainer);
        generateTimeBar(timeContainer);

        JPanel datePanelsContainer = new JPanel();
        datePanelsContainer.setLayout(null);
        datePanelsContainer.setBounds(PANELS_WIDTH, 0, PANELS_WIDTH * HOURS, PANELS_HEIGHT * HOURS);
        datePanelsContainer.setVisible(true);
        contentContainer.add(datePanelsContainer);
        generateDatePanels(datePanelsContainer);
        contentContainer.revalidate();
        contentContainer.repaint();
        System.out.println("så er der fyldt indhold i - så tegner vi");
    }

    /**
     * *************************************************************************
     * GENERATES THE DATE PANELS FOR THE SELECTED MONTH
     * *************************************************************************
     */
    private void generateDatePanels(JPanel panel) {

//        final int DAYS_IN_MONTH = daysInMonth(selectedMonth);
//        
//        int xLocation = (firstDayInMonth()-1)*PANELS_WIDTH; // The location on the x-axis of the panel, the starting location is defined by the starting day of the month
//        int yLocation = 0; // The location on the y-axis of the next panel
//        System.out.println("Første dag i måneden: " + (firstDayInMonth()-1) + " *30 = " + xLocation );
//        int dateCount = 1;
//        for (int i = 0; i < DAYS_IN_MONTH; i++) {
//                DatePanel datePanel = new DatePanel(dateCount, xLocation, yLocation, PANELS_WIDTH, PANELS_HEIGHT);
//                panel.add(datePanel);
//                
//                xLocation += PANELS_WIDTH;
//                dateCount++;
//                
//                if(xLocation == 210){
//                    xLocation = 0;
//                    yLocation += PANELS_WIDTH;
//                }
//        }

        int dateCount = 1;
        int xLocation = 0;
        int yLocation = 0;

        for (int i = 0; i < DAYS; i++) {
            for (int j = 0; j < HOURS; j++) {

                xLocation = PANELS_WIDTH * i;
                yLocation = PANELS_HEIGHT * j;

                DatePanel datePanel = new DatePanel(dateCount, xLocation, yLocation, PANELS_WIDTH, PANELS_HEIGHT);
                panel.add(datePanel);

                dateCount++;

            }
        }
    }

    private int firstDayInMonth() {

        int firstDay;
        java.util.Calendar tempCal = calendar;
//        tempCal.setFirstDayOfWeek(1);
        tempCal.set(selectedYear, selectedMonth, 0); // Sets the Year, Month, current day of Month (0 for first day)
        firstDay = tempCal.get(java.util.Calendar.DAY_OF_WEEK);
        System.out.println("FirstDayInMonth: " + firstDay);
        return firstDay;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentContainer = new javax.swing.JPanel();
        dayContainer = new javax.swing.JPanel();
        topCornerContainer = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        monthContainer = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(480, 416));
        setMinimumSize(new java.awt.Dimension(480, 416));
        setPreferredSize(new java.awt.Dimension(480, 416));
        setLayout(null);

        contentContainer.setLayout(null);
        add(contentContainer);
        contentContainer.setBounds(0, 60, 480, 350);

        dayContainer.setMinimumSize(new java.awt.Dimension(210, 30));
        dayContainer.setLayout(null);
        add(dayContainer);
        dayContainer.setBounds(80, 30, 400, 30);

        topCornerContainer.setBackground(new java.awt.Color(102, 102, 102));
        topCornerContainer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        topCornerContainer.setMinimumSize(new java.awt.Dimension(30, 30));
        topCornerContainer.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel1.setText("Time");
        topCornerContainer.add(jLabel1);
        jLabel1.setBounds(0, 17, 30, 10);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel2.setText(" day");
        topCornerContainer.add(jLabel2);
        jLabel2.setBounds(12, 2, 20, 10);

        add(topCornerContainer);
        topCornerContainer.setBounds(0, 30, 80, 30);

        monthContainer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        monthContainer.setMinimumSize(new java.awt.Dimension(180, 30));
        monthContainer.setPreferredSize(new java.awt.Dimension(180, 20));
        monthContainer.setLayout(null);
        add(monthContainer);
        monthContainer.setBounds(40, 0, 400, 30);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel2.setLayout(null);

        jLabel3.setText("<< ");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 10, 19, 14);

        add(jPanel2);
        jPanel2.setBounds(0, 0, 40, 30);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setMinimumSize(new java.awt.Dimension(30, 30));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.setLayout(null);

        jLabel4.setText(">>");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 10, 16, 14);

        add(jPanel1);
        jPanel1.setBounds(440, 0, 40, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        if (selectedWeek == 1) {
            selectedWeek = 52;
            selectedYear -= 1;
        } else {
            selectedWeek -= 1;
        }
        generateTopBar(selectedWeek);
        generateContentContainer();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        generateContentContainer();
        if (selectedWeek == 52) {
            selectedWeek = 1;
            selectedYear += 1;
        } else {
            selectedWeek += 1;
        }

        generateTopBar(selectedWeek);
        contentContainer.removeAll();
        // Når der trykkes skal contentContainer RYDDES og de to panels skal oprettes igen
        generateContentContainer();

    }//GEN-LAST:event_jPanel1MouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentContainer;
    private javax.swing.JPanel dayContainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel monthContainer;
    private javax.swing.JPanel topCornerContainer;
    // End of variables declaration//GEN-END:variables
}
