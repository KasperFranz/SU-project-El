/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.SKS_Headline;

/**
 *
 * @author Kasper
 */
public class SKS_headlinePanel extends javax.swing.JPanel {
private int height;
    /**
     * Creates new form SKS_headlinePanel
     */
  public SKS_headlinePanel(SKS_Headline headline,int offset) {
        initComponents();
        headlineLable.setText(headline.getHeadline());
        height = 0;
        for (int i = 0; i < headline.getQuestions().size(); i++) {
            SKS_questionPanel sksquestion = new SKS_questionPanel(headline.getQuestions().get(i));
            sksquestion.setVisible(true);
            height = 30*(1+i)+20+offset;
            System.out.println("x:"+height+"i:"+i);
            sksquestion.setBounds(0, height, 600, 25);
            this.add(sksquestion);
        }
        System.out.println("PanelHeight"+height+" i:"+headline.getQuestions().size());
        setSize(700, height);
    }
  
  public int getCurrentHeight(){
     return height;
  }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headlineLable = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(600, 100));
        setLayout(null);

        headlineLable.setText("jLabel1");
        add(headlineLable);
        headlineLable.setBounds(0, 0, 248, 48);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel headlineLable;
    // End of variables declaration//GEN-END:variables
}
