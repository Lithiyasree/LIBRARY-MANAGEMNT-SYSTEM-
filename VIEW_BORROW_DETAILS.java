package LIBRARY_PACKAGE;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;


/**
 *
 * @author rlith
 */
public class VIEW_BORROW_DETAILS extends javax.swing.JFrame {
    private static final String url = "jdbc:mysql://localhost:3306/LIBRARY_MANAGEMENT_SYSTEM";
    private static final String user = "root";
    private static final String pass = "Lithiyasree12@";
   
    /**
     * Creates new form VIEW_BORROW_DETAILS
     */
    public VIEW_BORROW_DETAILS() {
        initComponents();
        setExtendedState(VIEW_BORROW_DETAILS.MAXIMIZED_BOTH); 
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(VIEW_BORROW_DETAILS.DISPOSE_ON_CLOSE);
        
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow != -1) { // Ensure a row is selected
                    TRANSACTION_ID.setText(jTable1.getValueAt(selectedRow, 0).toString());
                    USER_ID.setText(jTable1.getValueAt(selectedRow, 1).toString());
                    USER_NAME.setText(jTable1.getValueAt(selectedRow, 2).toString());

                    BOOK_ID.setText(jTable1.getValueAt(selectedRow, 3).toString());
                    BOOK_NAME.setText(jTable1.getValueAt(selectedRow, 4).toString());
                    COPIES.setText(jTable1.getValueAt(selectedRow, 5).toString());
                    RETURN_STATUS.setText(jTable1.getValueAt(selectedRow, 9).toString());
                    FINE.setText(jTable1.getValueAt(selectedRow, 10).toString());
                    FINE_STATUS.setText(jTable1.getValueAt(selectedRow, 11).toString());
                    BLOCK.setText(jTable1.getValueAt(selectedRow, 12).toString());

                    // Set RETURN_DATE in JDateChooser
                    Object returnDateObj = jTable1.getValueAt(selectedRow, 8); // Assuming column 6 is RETURN_DATE
                    if (returnDateObj != null) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date returnDate = sdf.parse(returnDateObj.toString());
                            RETURN_DATE.setDate(returnDate);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        RETURN_DATE.setDate(null);
                    }
                }
            }
        });


        
    }
    private static void updateFineStatus(Connection conn) throws SQLException {

        String updateBorrowQuery = "UPDATE BORROW SET " +
                "RETURN_DATE = CASE " +
                "    WHEN RETURN_DATE IS NOT NULL AND RETURN_DATE <= DUE_DATE THEN RETURN_DATE " +
                "    WHEN RETURN_DATE IS NULL AND CURDATE() < DUE_DATE THEN NULL " +
                "    WHEN RETURN_DATE IS NULL AND CURDATE() > DUE_DATE THEN NULL " +
                "    WHEN RETURN_DATE IS NOT NULL AND RETURN_DATE > DUE_DATE THEN RETURN_DATE " +
                "    ELSE RETURN_DATE END, " +
                "RETURN_STATUS = CASE " +
                "    WHEN RETURN_DATE IS NOT NULL AND RETURN_DATE <= DUE_DATE THEN 'YES' " +
                "    WHEN RETURN_DATE IS NULL AND CURDATE() < DUE_DATE THEN 'NO' " +
                "    WHEN RETURN_DATE IS NULL AND CURDATE() > DUE_DATE THEN 'NO' " +
                "    WHEN RETURN_DATE IS NOT NULL AND RETURN_DATE > DUE_DATE THEN 'YES' " +
                "    ELSE RETURN_STATUS END, " +
                "FINE = CASE " +
                "    WHEN RETURN_DATE IS NOT NULL AND FINE_STATUS = 'PAID' THEN 0"+
                "    WHEN RETURN_DATE IS NOT NULL AND RETURN_DATE <= DUE_DATE THEN 0 " +
                "    WHEN RETURN_DATE IS NULL AND CURDATE() < DUE_DATE THEN 0 " +
                "    WHEN RETURN_DATE IS NULL AND CURDATE() > DUE_DATE THEN DATEDIFF(CURDATE(), DUE_DATE) * 10 " +
                "    WHEN RETURN_DATE IS NOT NULL AND RETURN_DATE > DUE_DATE THEN DATEDIFF(RETURN_DATE, DUE_DATE) * 10 " +
                "    ELSE FINE END, " +
                "FINE_STATUS = CASE " +
                "    WHEN RETURN_DATE IS NOT NULL AND RETURN_DATE <= DUE_DATE THEN 'NO' " +
                "    WHEN RETURN_DATE IS NULL AND CURDATE() < DUE_DATE THEN 'NO' " +
                "    WHEN RETURN_DATE IS NULL AND CURDATE() > DUE_DATE THEN 'YES' " +
                "    WHEN RETURN_DATE IS NOT NULL AND RETURN_DATE > DUE_DATE THEN 'YES' " +
                "    ELSE 'PAID' END;";
        
        
        String updateQuery = "UPDATE BORROW b " +
                "LEFT JOIN (SELECT USER_ID, SUM(FINE) AS total_fine, " +
                "                  SUM(CASE WHEN RETURN_STATUS != 'YES' OR FINE_STATUS != 'PAID' THEN 1 ELSE 0 END) AS unreturned_or_unpaid " +
                "           FROM BORROW " +
                "           GROUP BY USER_ID) AS subquery " +
                "ON b.USER_ID = subquery.USER_ID " +
                "SET " +
                "    b.BLOCK = CASE WHEN subquery.total_fine > 500 THEN 'YES' ELSE b.BLOCK END, " +
                "    b.FINE = CASE WHEN b.FINE_STATUS = 'PAID' THEN 0 ELSE b.FINE END, " +
                "    b.FINE = CASE WHEN subquery.total_fine < 500 THEN 0 ELSE b.FINE END, " +
                "    b.FINE_STATUS = CASE WHEN subquery.total_fine < 500 THEN 'PAID' ELSE b.FINE_STATUS END, " +
                "    b.BLOCK = CASE WHEN subquery.unreturned_or_unpaid = 0 THEN 'NO' ELSE b.BLOCK END;";

        // Establishing a connection
        try {
             PreparedStatement pstmt1 = conn.prepareStatement(updateBorrowQuery);
             PreparedStatement pstmt2 = conn.prepareStatement(updateQuery);

            // Execute the first update for BORROW table
            int rowsUpdated1 = pstmt1.executeUpdate();
            System.out.println("BORROW table updated. Rows affected: " + rowsUpdated1);

            // Execute the second update for blocking users
            int rowsUpdated2 = pstmt2.executeUpdate();
            System.out.println("USERS table updated for blocking. Rows affected: " + rowsUpdated2);

        } catch (SQLException e) {
            System.out.println("error "+ e.getMessage());

            e.printStackTrace();
        }
    }
//    private void calculateFine() {
//        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
//            String fineQuery = "SELECT TRANSACTION_ID, RETURN_DATE, FINE FROM BORROW WHERE RETURN_STATUS = 'NO' AND RETURN_DATE < CURDATE()";
//            try (PreparedStatement fineStmt = conn.prepareStatement(fineQuery);
//                 ResultSet fineRs = fineStmt.executeQuery()) {
//
//                while (fineRs.next()) {
//                    int transactionId = fineRs.getInt("TRANSACTION_ID");
//                    Date returnDate = fineRs.getDate("RETURN_DATE");
//                    int currentFine = fineRs.getInt("FINE");
//
//                    // Calculate overdue days
//                    int daysOverdue = (int) ChronoUnit.DAYS.between(returnDate.toLocalDate(), LocalDate.now());
//
//                    if (daysOverdue > 0) { 
//                        int newFine = currentFine + (daysOverdue * 10);
//
//                        String updateFineQuery = "UPDATE BORROW SET FINE = ?, FINE_STATUS = 'NO' WHERE TRANSACTION_ID = ?";
//                        try (PreparedStatement updateStmt = conn.prepareStatement(updateFineQuery)) {
//                            updateStmt.setInt(1, newFine);
//                            updateStmt.setInt(2, transactionId);
//                            updateStmt.executeUpdate();
//                        }
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, "Error calculating fine: " + e.getMessage());
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        FETCH = new javax.swing.JButton();
        CANCEL = new javax.swing.JButton();
        FILTERS = new javax.swing.JComboBox<>();
        SEARCH_BAR = new javax.swing.JTextField();
        UPDATE_DATABASE = new javax.swing.JButton();
        USER_NAME = new javax.swing.JTextField();
        TRANSACTION_ID = new javax.swing.JTextField();
        RETURN_STATUS = new javax.swing.JTextField();
        BOOK_NAME = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        USER_ID = new javax.swing.JTextField();
        BOOK_ID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        BLOCK = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        FINE = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        COPIES = new javax.swing.JTextField();
        RETURN_DATE = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        FINE_STATUS = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TRANSACTION_ID", "USER_NAME", "USER_ID", "BOOK_ID", "BOOK_NAME", "COPIES", "ISSUE DATE", "DUE_DATE", "RETURN DATE", "RETURN STATUS", "FINE", "FINE STATUS", "BLOCK"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Library Borrowing History");

        FETCH.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        FETCH.setText("FETCH");
        FETCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FETCHActionPerformed(evt);
            }
        });

        CANCEL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        CANCEL.setText("CANCEL");
        CANCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CANCELActionPerformed(evt);
            }
        });

        FILTERS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALL  ", "TRANSACTION_ID", "U.USER_NAME", "B.USER_ID  ", "B.BOOK_ID  ", "BO.BOOK_NAME  ", "B.COPIES  ", "B.ISSUE_DATE  ", "B.DUE_DATE", "B.RETURN_DATE  ", "B.RETURN_STATUS  ", "B.FINE  ", "B.FINE_STATUS  ", "B.BLOCK", " ", " ", " ", " ", " ", " ", " " }));
        FILTERS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FILTERSActionPerformed(evt);
            }
        });

        UPDATE_DATABASE.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        UPDATE_DATABASE.setText("UPDATE DATABASE");
        UPDATE_DATABASE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPDATE_DATABASEActionPerformed(evt);
            }
        });

        USER_NAME.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        TRANSACTION_ID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        RETURN_STATUS.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        RETURN_STATUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RETURN_STATUSActionPerformed(evt);
            }
        });

        BOOK_NAME.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BOOK_NAME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BOOK_NAMEActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("USER ID");

        BOOK_ID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("TRANSACTION_ID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("BOOK_ID");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("BOOK NAME");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("RETURN_STATUS");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("USER_NAME");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("RETURN DATE");

        BLOCK.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BLOCK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLOCKActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("FINE");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("BLOCK");

        FINE.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        FINE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FINEActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("COPIES");

        COPIES.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        RETURN_DATE.setDateFormatString("yyyy-MM-dd");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("FINE_STATUS");

        FINE_STATUS.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        FINE_STATUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FINE_STATUSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(COPIES, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(USER_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BOOK_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TRANSACTION_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(USER_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BOOK_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(RETURN_DATE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(RETURN_STATUS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FINE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BLOCK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FINE_STATUS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(FILTERS, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SEARCH_BAR, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(FETCH, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(UPDATE_DATABASE)
                                .addGap(18, 18, 18)
                                .addComponent(CANCEL)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FILTERS, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SEARCH_BAR, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FETCH, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UPDATE_DATABASE, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CANCEL, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TRANSACTION_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(USER_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BOOK_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(USER_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BOOK_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(COPIES, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RETURN_DATE, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RETURN_STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FINE, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(FINE_STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BLOCK, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        RETURN_DATE.getAccessibleContext().setAccessibleParent(RETURN_DATE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FETCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FETCHActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear table before fetching

        try {
                Connection conn = DriverManager.getConnection(url, user, pass);
                String searchQuery = SEARCH_BAR.getText().trim();
                String selectedColumn = FILTERS.getSelectedItem().toString();

                // Base SQL Query with explicit table aliases
                String query = "SELECT B.TRANSACTION_ID, U.USER_NAME, B.USER_ID, B.BOOK_ID, BO.BOOK_NAME, B.COPIES, " +
                                "B.ISSUE_DATE, B.DUE_DATE, B.RETURN_DATE, B.RETURN_STATUS, B.FINE, B.FINE_STATUS, B.BLOCK" +
                                "FROM BORROW B " +
                                "JOIN USERS U ON B.USER_ID = U.USER_ID " +
                                "JOIN BOOKS BO ON B.BOOK_ID = BO.BOOK_ID ORDER BY TRANSACTION_ID ASC";

                // Add search filters based on input
                if (!searchQuery.isEmpty() && !selectedColumn.equals("ALL")) {
                    if (selectedColumn.equals("USER_ID")) {
                        query += " WHERE U.USER_ID = ?"; // Explicitly reference U.USER_ID
                    } else if (selectedColumn.equals("BOOK_ID")) {
                        query += " WHERE BO.BOOK_ID = ?"; // Explicitly reference B.BOOK_ID
                    } else {
                        query += " WHERE " + selectedColumn + " LIKE ?"; // For other fields
                    }
                } else if (!searchQuery.isEmpty()) {
                    query += " WHERE CAST(U.USER_ID AS CHAR) LIKE ? OR U.USER_NAME LIKE ? " +
                              "OR CAST(BO.BOOK_ID AS CHAR) LIKE ? OR BO.BOOK_NAME LIKE ? " +
                              "OR B.RETURN_STATUS LIKE ? OR B.FINE_STATUS LIKE ? OR B.BLOCK LIKE ? ";
                }

                PreparedStatement pst = conn.prepareStatement(query);

                // Bind parameters
                if (!searchQuery.isEmpty() && !selectedColumn.equals("ALL")) {
                    if (selectedColumn.equals("USER_ID") || selectedColumn.equals("BOOK_ID")) {
                        try {
                            pst.setInt(1, Integer.parseInt(searchQuery)); // Convert to integer
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Invalid numeric input for " + selectedColumn);
                            return;
                        }
                    } else {
                        pst.setString(1, "%" + searchQuery + "%"); // For string fields
                    }
                } else if (!searchQuery.isEmpty()) {
                    for (int i = 1; i <= 6; i++) {
                        pst.setString(i, "%" + searchQuery + "%");
                    }
                }
//                calculateFine();
updateFineStatus(conn);
                 // Execute the query
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                    model.addRow(new Object[]{
                        rs.getInt("TRANSACTION_ID"),
                        rs.getInt("USER_ID"),
                        rs.getString("USER_NAME"),
                        rs.getInt("BOOK_ID"),
                        rs.getString("BOOK_NAME"),
                        rs.getInt("COPIES"),
                        rs.getDate("ISSUE_DATE"),
                        rs.getDate("DUE_DATE"),
                        rs.getDate("RETURN_DATE"),
                        rs.getString("RETURN_STATUS"),
                        rs.getInt("FINE"),
                        rs.getString("FINE_STATUS"),
                        rs.getString("BLOCK")
                    });
                    
                }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage());
        }

    }//GEN-LAST:event_FETCHActionPerformed

    private void CANCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CANCELActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_CANCELActionPerformed

    private void UPDATE_DATABASEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPDATE_DATABASEActionPerformed
         
                                              

        try {
            int transactionId = parseInteger(TRANSACTION_ID.getText(), "Transaction ID");
            String userName = USER_NAME.getText();
            int userId = parseInteger(USER_ID.getText(), "User ID");
            int bookId = parseInteger(BOOK_ID.getText(), "Book ID");
            String bookName = BOOK_NAME.getText();
            int copies = parseInteger(COPIES.getText(), "Copies");
            String returnStatus = RETURN_STATUS.getText();
            int fine = parseInteger(FINE.getText(), "Fine");
            String fineStatus = FINE_STATUS.getText();
            String block = BLOCK.getText();
            // Handle date conversion
            java.util.Date selectedDate = RETURN_DATE.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = (selectedDate != null) ? sdf.format(selectedDate) : null;
            java.sql.Date sqlReturnDate = (formattedDate != null) ? java.sql.Date.valueOf(formattedDate) : null;

            try (Connection conn = DriverManager.getConnection(url, user, pass)) {
                conn.setAutoCommit(false);

                // Update USERS table
                String updateUserQuery = "UPDATE USERS SET USER_NAME = ? WHERE USER_ID = ?";
                try (PreparedStatement pstUser = conn.prepareStatement(updateUserQuery)) {
                    pstUser.setString(1, userName);
                    pstUser.setInt(2, userId);
                    pstUser.executeUpdate();
                }

                // Update BOOKS table
                String updateBookQuery = "UPDATE BOOKS SET BOOK_NAME = ? WHERE BOOK_ID = ?";
                try (PreparedStatement pstBook = conn.prepareStatement(updateBookQuery)) {
                    pstBook.setString(1, bookName);
                    pstBook.setInt(2, bookId);
                    pstBook.executeUpdate();
                }

                // Update BORROW table
                String updateBorrowQuery = "UPDATE BORROW SET COPIES = ?, RETURN_STATUS = ?, FINE = ?, FINE_STATUS = ?,BLOCK = ?, RETURN_DATE = ? WHERE TRANSACTION_ID = ?";
                      updateFineStatus(conn);
                try (PreparedStatement pstBorrow = conn.prepareStatement(updateBorrowQuery)) {
                    pstBorrow.setInt(1, copies);
                    pstBorrow.setString(2, returnStatus);
                    pstBorrow.setInt(3, fine);
                    pstBorrow.setString(4, fineStatus);
                    pstBorrow.setString(5, block);
                    pstBorrow.setDate(6, sqlReturnDate);
                    pstBorrow.setInt(7, transactionId);
                    pstBorrow.executeUpdate();
                }
          
                conn.commit();
                JOptionPane.showMessageDialog(this, "Database Updated Successfully!");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error updating database: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
                       
    }//GEN-LAST:event_UPDATE_DATABASEActionPerformed

// Utility method to safely parse integers
    private int parseInteger(String text, String fieldName) throws NumberFormatException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(fieldName + " must be a valid number. Given: " + text);
        }
    }
    private void FILTERSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FILTERSActionPerformed
        
    }//GEN-LAST:event_FILTERSActionPerformed

    private void BOOK_NAMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BOOK_NAMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BOOK_NAMEActionPerformed

    private void RETURN_STATUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RETURN_STATUSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RETURN_STATUSActionPerformed

    private void BLOCKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLOCKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BLOCKActionPerformed

    private void FINEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FINEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FINEActionPerformed

    private void FINE_STATUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FINE_STATUSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FINE_STATUSActionPerformed

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
            java.util.logging.Logger.getLogger(VIEW_BORROW_DETAILS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VIEW_BORROW_DETAILS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VIEW_BORROW_DETAILS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VIEW_BORROW_DETAILS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VIEW_BORROW_DETAILS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BLOCK;
    private javax.swing.JTextField BOOK_ID;
    private javax.swing.JTextField BOOK_NAME;
    private javax.swing.JButton CANCEL;
    private javax.swing.JTextField COPIES;
    private javax.swing.JButton FETCH;
    private javax.swing.JComboBox<String> FILTERS;
    private javax.swing.JTextField FINE;
    private javax.swing.JTextField FINE_STATUS;
    private com.toedter.calendar.JDateChooser RETURN_DATE;
    private javax.swing.JTextField RETURN_STATUS;
    private javax.swing.JTextField SEARCH_BAR;
    private javax.swing.JTextField TRANSACTION_ID;
    private javax.swing.JButton UPDATE_DATABASE;
    private javax.swing.JTextField USER_ID;
    private javax.swing.JTextField USER_NAME;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
