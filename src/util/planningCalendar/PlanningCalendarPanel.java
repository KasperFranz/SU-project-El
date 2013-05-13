/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.planningCalendar;

import control.DBHandler;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.PlanningDatePanel;

/**
 *
 * @author Marc
 */
public class PlanningCalendarPanel extends javax.swing.JPanel {

    private Calendar calendar;
    private final int PANELS_WIDTH = 80;
    private final int PANELS_HEIGHT = 30;
    private final int HOURS = 11;
    private final int DAYS = 5;
    private DBHandler dbhandler;

    public PlanningCalendarPanel(DBHandler dbhandler) {

        this.dbhandler = dbhandler;

        this.calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);


        initComponents();
        generateDayBar();
        generateTopBar();
        generateContentContainer();


    }

    /**
     * *************************************************************************
     * Generates the top panel showing the current month and year being shown
     * *************************************************************************
     */
    private void generateTopBar() {

        monthContainer.removeAll();
        monthContainer.repaint();
        JLabel monthLabel = new JLabel("Uge " + calendar.get(Calendar.WEEK_OF_YEAR) + " " + calendar.get(Calendar.YEAR));
        
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

    /**
     * *************************************************************************
     * GENERATES THE CONTAINER RESPONSIBLE FOR THE WEEKPANELS CONTAINER AND FOR
     * THE DATE PANELS CONTAINER
     * *************************************************************************
     */
    private void generateContentContainer() {
        contentContainer.removeAll();
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
    }

    /**
     * *************************************************************************
     * GENERATES THE DATE PANELS FOR THE SELECTED MONTH
     * *************************************************************************
     */
    private void generateDatePanels(JPanel panel) {

        
        
        
        int dateCount = 1;
        int xLocation = 0;
        int yLocation = 0;
        System.out.println("CAL: " + calendar.getTime().toString());
        for (int i = 0; i < DAYS; i++) {
            
            if(i != 0){
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            Date date = calendar.getTime();
            System.out.println(date.toString());

            for (int j = 0; j < HOURS; j++) {

                xLocation = PANELS_WIDTH * i;
                yLocation = PANELS_HEIGHT * j;


                PlanningDatePanel datePanel = new PlanningDatePanel(date, dateCount, xLocation, yLocation, PANELS_WIDTH, PANELS_HEIGHT, null);
                panel.add(datePanel);

                dateCount++;

            }
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
            case 10:
                formattedTime = "17.00";
                break;
            default:
                formattedTime = "??.??";
                break;
        }

        return formattedTime;
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

//    private int firstDayInMonth() {
//
//        int firstDay;
//        java.util.Calendar tempCal = (java.util.Calendar) calendar.clone();
////        tempCal.setFirstDayOfWeek(1);
//        tempCal.set(selectedYear, selectedMonth, 0); // Sets the Year, Month, current day of Month (0 for first day)
//        firstDay = tempCal.get(java.util.Calendar.DAY_OF_WEEK);
//        System.out.println("FirstDayInMonth: " + firstDay);
//        return firstDay;
//    }

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
        backButtonPanel = new javax.swing.JPanel();
        backButtonLabel = new javax.swing.JLabel();
        forwardButtonPanel = new javax.swing.JPanel();
        forwardButtonLabel = new javax.swing.JLabel();

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

        backButtonPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        backButtonPanel.setPreferredSize(new java.awt.Dimension(30, 30));
        backButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButtonPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButtonPanelMouseExited(evt);
            }
        });
        backButtonPanel.setLayout(null);

        backButtonLabel.setText("<< ");
        backButtonLabel.setFocusable(false);
        backButtonLabel.setOpaque(true);
        backButtonLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButtonLabelMouseEntered(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonLabelMouseClicked(evt);
            }
        });
        backButtonPanel.add(backButtonLabel);
        backButtonLabel.setBounds(10, 10, 19, 14);

        add(backButtonPanel);
        backButtonPanel.setBounds(0, 0, 40, 30);

        forwardButtonPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        forwardButtonPanel.setMinimumSize(new java.awt.Dimension(30, 30));
        forwardButtonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forwardButtonPanelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                forwardButtonPanelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                forwardButtonPanelMouseEntered(evt);
            }
        });
        forwardButtonPanel.setLayout(null);

        forwardButtonLabel.setText(">>");
        forwardButtonLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                forwardButtonLabelMouseEntered(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forwardButtonLabelMouseClicked(evt);
            }
        });
        forwardButtonPanel.add(forwardButtonLabel);
        forwardButtonLabel.setBounds(10, 10, 16, 14);

        add(forwardButtonPanel);
        forwardButtonPanel.setBounds(440, 0, 40, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void forwardButtonPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forwardButtonPanelMouseClicked
     

    }//GEN-LAST:event_forwardButtonPanelMouseClicked

    private void backButtonPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonPanelMouseEntered
        backButtonSetBackground(1);
    }//GEN-LAST:event_backButtonPanelMouseEntered

    private void backButtonPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonPanelMouseExited
        backButtonSetBackground(0);
    }//GEN-LAST:event_backButtonPanelMouseExited

    private void backButtonLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonLabelMouseEntered
        backButtonSetBackground(1);
    }//GEN-LAST:event_backButtonLabelMouseEntered

    private void forwardButtonPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forwardButtonPanelMouseEntered
        forwardButtonSetBackground(1);
    }//GEN-LAST:event_forwardButtonPanelMouseEntered

    private void forwardButtonPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forwardButtonPanelMouseExited
        forwardButtonSetBackground(0);
    }//GEN-LAST:event_forwardButtonPanelMouseExited

    private void forwardButtonLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forwardButtonLabelMouseEntered
        forwardButtonSetBackground(1);
    }//GEN-LAST:event_forwardButtonLabelMouseEntered

    private void backButtonLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonLabelMouseClicked
        System.out.println("DEBUG");
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println("DEBUG: " + calendar.getTime().toString());
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        generateTopBar();
        generateContentContainer();
    }//GEN-LAST:event_backButtonLabelMouseClicked

    private void forwardButtonLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forwardButtonLabelMouseClicked
   
        System.out.println("DEBUG");
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println("DEBUG: " + calendar.getTime().toString());
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        generateTopBar();
        generateContentContainer();

    }//GEN-LAST:event_forwardButtonLabelMouseClicked

    private void backButtonSetBackground(int param) {
        if (param == 0) {
            backButtonPanel.setBackground(new Color(240, 240, 240));
            backButtonLabel.setBackground(new Color(240, 240, 240));
        }
        if (param == 1) {
            backButtonPanel.setBackground(new Color(200, 200, 200));
            backButtonLabel.setBackground(new Color(200, 200, 200));
        }
    }

    private void forwardButtonSetBackground(int param) {
        if (param == 0) {
            forwardButtonPanel.setBackground(new Color(240, 240, 240));
            forwardButtonLabel.setBackground(new Color(240, 240, 240));
        }
        if (param == 1) {
            forwardButtonPanel.setBackground(new Color(200, 200, 200));
            forwardButtonLabel.setBackground(new Color(200, 200, 200));
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backButtonLabel;
    private javax.swing.JPanel backButtonPanel;
    private javax.swing.JPanel contentContainer;
    private javax.swing.JPanel dayContainer;
    private javax.swing.JLabel forwardButtonLabel;
    private javax.swing.JPanel forwardButtonPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel monthContainer;
    private javax.swing.JPanel topCornerContainer;
    // End of variables declaration//GEN-END:variables
}
