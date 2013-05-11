
package util;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DatePanel extends JPanel implements MouseListener {
    
    private int date;
    private int xLocation;
    private int yLocation;
    private int width;
    private int height;
  
    
    public DatePanel(int date, int xLocation, int yLocation,int width, int height){
        this.date = date;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.height = height;
        this.width = width;
        
        this.add(new JLabel(date + ""));
        this.setBounds(xLocation, yLocation, width, height);
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        addMouseListener(this);
        
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println(date);
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
