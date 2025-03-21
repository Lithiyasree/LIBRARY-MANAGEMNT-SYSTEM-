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
public class ADD_BOOKS extends javax.swing.JFrame {

    /**
     * Creates new form ADD_BOOKS
     */
    public ADD_BOOKS() {
        initComponents();
        setDefaultCloseOperation(ADD_BOOKS.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        QUANTITY = new javax.swing.JTextField();
        BOOK_ID = new javax.swing.JTextField();
        AUTHOR = new javax.swing.JTextField();
        TITLE = new javax.swing.JTextField();
        CATEGORY = new javax.swing.JTextField();
        ADD = new javax.swing.JButton();
        CANCEL = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ISBN = new javax.swing.JTextField();
        PUBLISHER = new javax.swing.JTextField();
        PUBLISHER_YEAR = new javax.swing.JTextField();
        PRICE = new javax.swing.JTextField();
        RESET = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        BOOK_NO = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("BOOK NO");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("CATEGORY");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("TITLE");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("AUTHOR");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("QUANTITY");

        QUANTITY.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        BOOK_ID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        AUTHOR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        TITLE.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TITLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TITLEActionPerformed(evt);
            }
        });

        CATEGORY.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        ADD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ADD.setText("ADD ");
        ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDActionPerformed(evt);
            }
        });

        CANCEL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        CANCEL.setText("CANCEL");
        CANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CANCELActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ADD BOOKS");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("PUBLISHER YEAR");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("PRICE");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("ISBN");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("PUBLISHER");

        ISBN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ISBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ISBNActionPerformed(evt);
            }
        });

        PUBLISHER.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PUBLISHER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PUBLISHERActionPerformed(evt);
            }
        });

        PUBLISHER_YEAR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PUBLISHER_YEAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PUBLISHER_YEARActionPerformed(evt);
            }
        });

        PRICE.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PRICE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PRICEActionPerformed(evt);
            }
        });

        RESET.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        RESET.setText("RESET");
        RESET.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESETActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("BOOK ID");

        BOOK_NO.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TITLE, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                                    .addComponent(BOOK_ID)
                                    .addComponent(BOOK_NO)
                                    .addComponent(CATEGORY)
                                    .addComponent(AUTHOR, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ADD, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                                .addComponent(RESET, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(CANCEL, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(PRICE)
                            .addComponent(ISBN, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PUBLISHER, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PUBLISHER_YEAR, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(QUANTITY, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BOOK_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BOOK_NO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 32, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TITLE, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AUTHOR, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CATEGORY, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PUBLISHER, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PUBLISHER_YEAR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PRICE, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(QUANTITY, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(125, 125, 125)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RESET, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CANCEL, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ADD, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TITLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TITLEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TITLEActionPerformed

    private void ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDActionPerformed
        // TODO add your handling code here:
        String url = "jdbc:mysql://localhost:3306/LIBRARY_MANAGEMENT_SYSTEM";
        String user = "root";
        String pass = "Lithiyasree12@";

        String insertQuery = "INSERT INTO BOOK (BOOK_NO, TITLE, CATEGORY, AUTHOR, PUBLISHER, PUBLISH_YEAR, ISBN, PRICE, QUANTITY, STATUS, BOOK_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
        String updateQuery = "UPDATE BOOK SET QUANTITY = QUANTITY + ? WHERE BOOK_NO = ? AND TITLE = ? AND CATEGORY = ? AND AUTHOR = ? AND PUBLISHER = ? AND PUBLISH_YEAR = ? AND ISBN = ? AND PRICE = ? AND BOOK_ID=?;";

        try {
            
            Connection conn = DriverManager.getConnection(url, user, pass);
            
            String book_id = BOOK_ID.getText();
            String book_no = BOOK_NO.getText();
            String title = TITLE.getText();
            String category = CATEGORY.getText();
            String author = AUTHOR.getText();
            String publisher = PUBLISHER.getText();
            String isbn = ISBN.getText();
            int publish_year = Integer.parseInt(PUBLISHER_YEAR.getText());
            int price = Integer.parseInt(PRICE.getText());
            int quantity = Integer.parseInt(QUANTITY.getText());
            
            // Use PreparedStatement for update
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
            updateStmt.setInt(1, quantity);
            updateStmt.setString(2, book_no);
            updateStmt.setString(3,title);
            updateStmt.setString(4, category);
            updateStmt.setString(5, author);
            updateStmt.setString(6, publisher);
            updateStmt.setInt(7, publish_year);
            updateStmt.setString(8, isbn);
            updateStmt.setInt(9, price);
            updateStmt.setString(10, book_id);

            int rows = updateStmt.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "RECORD UPDATED SUCCESSFULLY");
            } else {
                // Use PreparedStatement for insert
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, book_no);
                insertStmt.setString(2, title);
                insertStmt.setString(3, category);
                insertStmt.setString(4, author);
                insertStmt.setString(5, publisher);
                insertStmt.setInt(6, publish_year);
                insertStmt.setString(7, isbn);
                insertStmt.setInt(8, price);
                insertStmt.setInt(9, quantity);
                insertStmt.setString(10, "NotIssued");
                insertStmt.setString(11, book_id);
                 
                insertStmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "RECORD ADDED SUCCESSFULLY");

                insertStmt.close();
            }

            // Close resources
            updateStmt.close();
            conn.close();

            // Reset fields
            BOOK_ID.setText(null);
            BOOK_NO.setText(null);
            CATEGORY.setText(null);
            TITLE.setText(null);
            AUTHOR.setText(null);
            QUANTITY.setText(null);
            PUBLISHER.setText(null);
            ISBN.setText(null);
            PUBLISHER_YEAR.setText(null);
            PRICE.setText(null);
            QUANTITY.setText(null);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format for Quantity.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
        }
    }//GEN-LAST:event_ADDActionPerformed

    private void CANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CANCELActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_CANCELActionPerformed

    private void ISBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ISBNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ISBNActionPerformed

    private void PUBLISHERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PUBLISHERActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PUBLISHERActionPerformed

    private void PUBLISHER_YEARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PUBLISHER_YEARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PUBLISHER_YEARActionPerformed

    private void PRICEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PRICEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PRICEActionPerformed

    private void RESETActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESETActionPerformed
        // TODO add your handling code here:
        BOOK_ID.setText(null);
        CATEGORY.setText(null);
        TITLE.setText(null);
        AUTHOR.setText(null);
        QUANTITY.setText(null);
        PUBLISHER.setText(null);
        ISBN.setText(null);
        PUBLISHER_YEAR.setText(null);
        PRICE.setText(null);
        QUANTITY.setText(null);        
        
    }//GEN-LAST:event_RESETActionPerformed

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
            java.util.logging.Logger.getLogger(ADD_BOOKS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ADD_BOOKS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ADD_BOOKS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ADD_BOOKS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ADD_BOOKS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADD;
    private javax.swing.JTextField AUTHOR;
    private javax.swing.JTextField BOOK_ID;
    private javax.swing.JTextField BOOK_NO;
    private javax.swing.JButton CANCEL;
    private javax.swing.JTextField CATEGORY;
    private javax.swing.JTextField ISBN;
    private javax.swing.JTextField PRICE;
    private javax.swing.JTextField PUBLISHER;
    private javax.swing.JTextField PUBLISHER_YEAR;
    private javax.swing.JTextField QUANTITY;
    private javax.swing.JButton RESET;
    private javax.swing.JTextField TITLE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
