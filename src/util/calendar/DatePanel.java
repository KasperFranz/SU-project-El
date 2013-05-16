
package util.calendar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DatePanel extends JPanel {
    
    private Date date;
    private int xLocation;
    private int yLocation;
    private int width;
    private int height;
  
    
    public DatePanel(Date date, int xLocation, int yLocation,int width, int height){
        this.date = date;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.height = height;
        this.width = width;
        
        this.add(new JLabel(date.getDate() + ""));
        this.setBounds(xLocation, yLocation, width, height);
        this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        this.addMouseListener(new datePanelPressedListener());
    }
    
  
     /***************************************************************************
     * A listener for when a date panel is clicked.
     **************************************************************************/
    private class datePanelPressedListener extends MouseAdapter{
        
        @Override
        public void mouseClicked(MouseEvent event){
                System.out.println(date);
        }
        
        @Override
        public void mouseEntered(MouseEvent event){
                System.out.println("ENTERED " + date.toString());
        }
        
        @Override
        public void mouseExited(MouseEvent event){
            System.out.println("EXITED" + date.toString());
        }
            
    }
    
    
}
