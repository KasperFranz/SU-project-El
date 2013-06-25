/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.SKS_question;

/**
 *
 * @author Kasper
 */
public class SKS_questionPanel extends javax.swing.JPanel {

    /**
     * Creates new form SKS_questionPanel
     */
    public SKS_questionPanel(SKS_question question) {
        initComponents();
        questionLabel.setText(question.getQuestion());
    }
    
    public int getAnswer(){
        return answerCombo.getSelectedIndex();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        questionLabel = new javax.swing.JLabel();
        answerCombo = new javax.swing.JComboBox();

        questionLabel.setText("Spørgsmål");

        answerCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ja", "Nej", "Ikke aktuelt" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(questionLabel)
                .addGap(136, 136, 136)
                .addComponent(answerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(questionLabel)
                    .addComponent(answerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox answerCombo;
    private javax.swing.JLabel questionLabel;
    // End of variables declaration//GEN-END:variables
}
