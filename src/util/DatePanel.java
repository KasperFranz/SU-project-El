
package util;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date; // SQL.DATE BECAUSE IT DOES NOT CONTAIN TIME ONLY DATE! UNLIKE UTIL.DATE WHICH ALSO CONTAINS TIME
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Worksheet;

public class DatePanel extends JPanel implements MouseListener {
    
    private Date date;
    private int time;
    private int xLocation;
    private int yLocation;
    private int width;
    private int height;
    private Worksheet worksheet;
    
    public DatePanel(Date date, int time, int xLocation, int yLocation,int width, int height, Worksheet worksheet){
        this.date = date;
        this.time = time;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.height = height;
        this.width = width;
        this.worksheet = worksheet;
        
        this.add(new JLabel(this.time + ""));
        this.setBounds(xLocation, yLocation, width, height);
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        addMouseListener(this);
        
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("Dato: " + date + " and time:" + time);
        
    }



    @Override
    public void mouseEntered(MouseEvent me) {
        setPanelBackground(1);
    }

    @Override
    public void mouseExited(MouseEvent me) {
        setPanelBackground(0);
    }
    
     private void setPanelBackground(int param){
        if(param == 0){
            this.setBackground(new Color(240,240,240));
        }
        if(param == 1){
            this.setBackground(new Color(200,200,200));
        }
    }
     
    // UN-USED - ONLY HERE BECAUSE WE IMPLEMENT MOUSELISTENER 
    @Override
    public void mousePressed(MouseEvent me) {
    }
    @Override
    public void mouseReleased(MouseEvent me) {
    }
  

    
    
}
