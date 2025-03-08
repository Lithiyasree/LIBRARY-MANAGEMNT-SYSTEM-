/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package LIBRARY_PACKAGE;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author rlith
 */
public class MEMBER_REGISTRATION extends javax.swing.JFrame {

    /**
     * Creates new form MEMBER_REGISTRATION
     */
    public MEMBER_REGISTRATION() {
        initComponents();
    }
    
    public void clear(){
        USER_ID.setText(null);
        USER_NAME.setText(null);
        QUALIFICATION.setText(null);
        DESIGNATION.setText(null);
        MOBILE_NUMBER.setText(null);
        EMAIL.setText(null);
         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        USER_ID = new javax.swing.JTextField();
        USER_NAME = new javax.swing.JTextField();
        MOBILE_NUMBER = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        EMAIL = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        RESET = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        SAVE = new javax.swing.JButton();
        QUALIFICATION = new javax.swing.JTextField();
        DESIGNATION = new javax.swing.JTextField();
        CANCEL = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Member ID");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Member Name");

        USER_ID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        USER_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                USER_IDActionPerformed(evt);
            }
        });

        USER_NAME.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        USER_NAME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                USER_NAMEActionPerformed(evt);
            }
        });

        MOBILE_NUMBER.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MOBILE_NUMBER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOBILE_NUMBERActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Qualification");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Designation");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Email");

        EMAIL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EMAIL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMAILActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Mobile Number");

        RESET.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        RESET.setText("RESET");
        RESET.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESETActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("MEMBER REGISTRATION");

        SAVE.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        SAVE.setText("SAVE");
        SAVE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SAVEActionPerformed(evt);
            }
        });

        QUALIFICATION.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        QUALIFICATION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QUALIFICATIONActionPerformed(evt);
            }
        });

        DESIGNATION.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DESIGNATION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DESIGNATIONActionPerformed(evt);
            }
        });

        CANCEL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        CANCEL.setText("CANCEL");
        CANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CANCELActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(297, 297, 297))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(SAVE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(66, 66, 66))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(41, 41, 41))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(63, 63, 63)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(QUALIFICATION)
                            .addComponent(USER_ID)
                            .addComponent(EMAIL)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(RESET, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(73, 73, 73)
                                .addComponent(CANCEL))
                            .addComponent(USER_NAME)
                            .addComponent(DESIGNATION)
                            .addComponent(MOBILE_NUMBER))))
                .addGap(115, 115, 115))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(USER_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(USER_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QUALIFICATION, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DESIGNATION, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MOBILE_NUMBER, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EMAIL, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SAVE, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RESET, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CANCEL, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void USER_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_USER_IDActionPerformed

    }//GEN-LAST:event_USER_IDActionPerformed

    private void USER_NAMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_USER_NAMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_USER_NAMEActionPerformed

    private void MOBILE_NUMBERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOBILE_NUMBERActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MOBILE_NUMBERActionPerformed

    private void EMAILActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMAILActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EMAILActionPerformed

    private void RESETActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESETActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_RESETActionPerformed

    private void SAVEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SAVEActionPerformed
        // TODO add your handling code here:
        String url = "jdbc:mysql://localhost:3306/LIBRARY_MANAGEMENT_SYSTEM";
        String user = "root";
        String pass = "Lithiyasree12@";

        try(Connection conn = DriverManager.getConnection(url, user, pass)){

            if(USER_ID.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Please enter Student ID");
                USER_ID.requestFocus();
            }
            else if(USER_NAME.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Please enter Student Name");
                USER_NAME.requestFocus();
            }
            else if(QUALIFICATION.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Please enter Semester");
                QUALIFICATION.requestFocus();
            }
            else if(DESIGNATION.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Please enter Semester");
                DESIGNATION.requestFocus();
            }
            else if(MOBILE_NUMBER.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Please enter Semester");
                MOBILE_NUMBER.requestFocus();
            }
            else if(EMAIL.getText().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Please enter Semester");
                EMAIL.requestFocus();
            }
            else{
                try {
                    PreparedStatement pst = conn.prepareStatement("INSERT INTO USER(USER_ID, USER_NAME, QUALIFICATION, DESIGNATION, MOBILE, EMAIL, USER_TYPE) VALUES (?,?,?,?,?,?,'MEMBER')");
                    pst.setString(1, USER_ID.getText());
                    pst.setString(2, USER_NAME.getText());
                    pst.setString(3, QUALIFICATION.getText());
                    pst.setString(4, DESIGNATION.getText());
                    pst.setString(5, MOBILE_NUMBER.getText());
                    pst.setString(6, EMAIL.getText());
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(rootPane, "Record Saved","Saved",JOptionPane.INFORMATION_MESSAGE);
                    clear();
                } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error updating database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error connecting to database: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
    }
    

        // TODO add your handling code here:

    }//GEN-LAST:event_SAVEActionPerformed

    private void QUALIFICATIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QUALIFICATIONActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QUALIFICATIONActionPerformed

    private void DESIGNATIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DESIGNATIONActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DESIGNATIONActionPerformed

    private void CANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CANCELActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_CANCELActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MEMBER_REGISTRATION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MEMBER_REGISTRATION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MEMBER_REGISTRATION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MEMBER_REGISTRATION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MEMBER_REGISTRATION().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CANCEL;
    private javax.swing.JTextField DESIGNATION;
    private javax.swing.JTextField EMAIL;
    private javax.swing.JTextField MOBILE_NUMBER;
    private javax.swing.JTextField QUALIFICATION;
    private javax.swing.JButton RESET;
    private javax.swing.JButton SAVE;
    private javax.swing.JTextField USER_ID;
    private javax.swing.JTextField USER_NAME;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
