/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package LIBRARY_PACKAGE;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author rlith
 */
public class ISSUE_BOOK extends javax.swing.JFrame {
    private static final String url = "jdbc:mysql://localhost:3306/LIBRARY_MANAGEMENT_SYSTEM"; 
    private static final String user = "root"; 
    private static final String pass = "Lithiyasree12@";
    /**
     * Creates new form ISSUE_BOOK
     */
    public ISSUE_BOOK() {
        initComponents();
        initializeTable();
        
        BOOKS_RECORD.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectBookRow();
            }
        });
        CUSTOMER_RECORD.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectCustomerRow();
            }
        });
    }
    
    
    /////////////CONSTRUCTOR END 
    
    
    
     private void initializeTable() {
        // Simulate an empty action event to fetch all books
        FETCH_BOOKActionPerformed(null);
        FETCH_CUSTOMERActionPerformed(null);
    }
   
    private void selectBookRow() {
        int row = BOOKS_RECORD.getSelectedRow();
        if (row != -1) {
            BOOK_ID.setText(BOOKS_RECORD.getValueAt(row, 1).toString());
            BOOK_NO.setText(BOOKS_RECORD.getValueAt(row, 2).toString());
            TITLE.setText(BOOKS_RECORD.getValueAt(row, 3).toString());
            QUANTITY.setText(BOOKS_RECORD.getValueAt(row, 9).toString());
        }
    }

    private void selectCustomerRow() {
        int row = CUSTOMER_RECORD.getSelectedRow();
        if (row != -1) {
            USER_ID.setText(CUSTOMER_RECORD.getValueAt(row, 1).toString());
            USER_NAME.setText(CUSTOMER_RECORD.getValueAt(row, 2).toString());
            MOBILE.setText(CUSTOMER_RECORD.getValueAt(row, 3).toString());
            EMAIL.setText(CUSTOMER_RECORD.getValueAt(row, 4).toString());
        }
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
                  "DAYS_OVERDUE = CASE "+
                  "    WHEN RETURN_DATE IS NOT NULL AND RETURN_DATE > DUE_DATE THEN DATEDIFF(RETURN_DATE, DUE_DATE) "+
                  "    WHEN RETURN_DATE IS NULL AND CURDATE() > DUE_DATE THEN DATEDIFF(CURDATE(), DUE_DATE) "+
                  "     ELSE 0 END, "+
                "FINE = CASE " +
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
                "    ELSE FINE_STATUS END;";

//        String updateBlockQuery = "UPDATE USERS SET BLOCK = 'YES' " +
//                "WHERE USER_ID IN (SELECT DISTINCT USER_ID FROM BORROW " +
//                "WHERE FINE_STATUS = 'YES' AND FINE > 500)";

          String updateBlockQuery = "UPDATE BORROW AS b " +
                                    "JOIN (SELECT USER_ID FROM BORROW WHERE RETURN_STATUS = 'NO' GROUP BY USER_ID HAVING SUM(FINE) >= 1000  ) "+
                                    "AS overdue_users ON b.USER_ID = overdue_users.USER_ID" +
                                    "SET b.BLOCK = 'YES'"+
                                    "WHERE b.RETURN_STATUS = 'NO';";

        // Establishing a connection
        try {
             PreparedStatement pstmt1 = conn.prepareStatement(updateBorrowQuery);
             PreparedStatement pstmt2 = conn.prepareStatement(updateBlockQuery);

            // Execute the first update for BORROW table
            int rowsUpdated1 = pstmt1.executeUpdate();
            System.out.println("BORROW table updated. Rows affected: " + rowsUpdated1);

            // Execute the second update for blocking users
            int rowsUpdated2 = pstmt2.executeUpdate();
            System.out.println("USERS table updated for blocking. Rows affected: " + rowsUpdated2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
  
     
     private static void blockHighFineUsers(Connection conn) throws SQLException {
//        String query = "UPDATE BORROW SET BLOCK = 'YES' " +
//                   "WHERE USER_ID IN (SELECT USER_ID FROM BORROW " +
//                   "WHERE RETURN_STATUS = 'NO' " +
//                   "GROUP BY USER_ID " +
//                   "HAVING SUM(FINE) > 1000)";
    
           String query = "UPDATE BORROW AS b "
                     + "JOIN ( "
                     + "    SELECT USER_ID "
                     + "    FROM BORROW "
                     + "    WHERE RETURN_STATUS = 'NO' "
                     + "    GROUP BY USER_ID "
                     + "    HAVING SUM(FINE) >= 1000 "
                     + ") AS overdue_users "
                     + "ON b.USER_ID = overdue_users.USER_ID "
                     + "SET b.BLOCK = 'YES' "
                     + "WHERE b.RETURN_STATUS = 'NO';";
          try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        int rowsUpdated = pstmt.executeUpdate();
        System.out.println("Users blocked for high fines. Rows affected: " + rowsUpdated);
    }
    }
     
     
     private static boolean canUserBorrow(Connection conn, String userId) throws SQLException {
        String query = "SELECT BLOCK FROM BORROW WHERE USER_ID = ? AND RETURN_STATUS = 'NO'";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return "NO".equals(rs.getString("BLOCK")); // User can borrow if not blocked
            }
        }
        return false;
    } 


    // Get count of books user has borrowed
    private static int getUserBorrowedBookCount(Connection conn, String user_id) throws SQLException {
        String query = "SELECT  COUNT(DISTINCT BOOK_ID) AS counts FROM BORROW WHERE USER_ID = ? AND RETURN_STATUS = 'NO' GROUP BY USER_ID;";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
//                System.out.println(rs.getInt(1));
                return rs.getInt("counts");
            }
        }
        return 0;
    }
    
    private static boolean isBookAvailable(Connection conn, String bookId, int requestedCopies) throws SQLException {
        String query = "SELECT QUANTITY FROM BOOK WHERE BOOK_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, bookId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("QUANTITY") >= requestedCopies; // Check if enough copies exist
            }
        }
        return false;
    }

    
    private static void updateBookCopies(Connection conn, String bookId, int borrowedCopies) throws SQLException {
        String query = "UPDATE BOOK SET QUANTITY = QUANTITY - ? WHERE BOOK_ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, borrowedCopies);
            pstmt.setString(2, bookId);
            pstmt.executeUpdate();
        }
    }
    
    
//    private static void borrowBook(Connection conn, String userId, String bookId, int copies) throws SQLException {
//        String query = "INSERT INTO BORROW (USER_ID, BOOK_ID, QUANTITY, ISSUE_DATE, DUE_DATE, RETURN_STATUS, FINE, FINE_STATUS) VALUES (?, ?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 14 DAY),'NO', 0, 'NO')";
//        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
//            pstmt.setString(1, userId);
//            pstmt.setString(2, bookId);
//            pstmt.setInt(3, copies);
//            pstmt.executeUpdate();
//        }
//    }
    
    private static void borrowBook(Connection conn, String userId, String bookId, int copies, String issueDate, String dueDate) throws SQLException {
    String query = "INSERT INTO BORROW (USER_ID, BOOK_ID, QUANTITY, ISSUE_DATE, DUE_DATE, RETURN_STATUS, FINE, FINE_STATUS) VALUES (?, ?, ?, ?, ?, 'NO', 0, 'NO')";

    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, userId);
        pstmt.setString(2, bookId);
        pstmt.setInt(3, copies);
        pstmt.setString(4, issueDate);
        pstmt.setString(5, dueDate);
        pstmt.executeUpdate();
    }
}
     
     
     
     
     
     
     
     
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        BOOKS_RECORD = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        CUSTOMER_RECORD = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TITLE = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        USER_NAME = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ISSUE_BOOK = new javax.swing.JButton();
        RESET = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        CLEAR_BOOK = new javax.swing.JButton();
        FETCH_BOOK = new javax.swing.JButton();
        BOOK_FILTERS = new javax.swing.JComboBox<>();
        BOOK_SEARCH_BAR = new javax.swing.JTextField();
        CLEAR_CUSTOMER = new javax.swing.JButton();
        FETCH_CUSTOMER = new javax.swing.JButton();
        CUSTOMER_FILTERS = new javax.swing.JComboBox<>();
        CUSTOMER_SEARCH_BAR = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        BOOK_NO = new javax.swing.JTextField();
        BOOK_ID = new javax.swing.JTextField();
        USER_ID = new javax.swing.JTextField();
        QUANTITY = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        MOBILE = new javax.swing.JTextField();
        EMAIL = new javax.swing.JTextField();
        ISSUE_DATE = new com.toedter.calendar.JDateChooser();
        DUE_DATE = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BOOKS_RECORD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "BOOK_ID", "BOOK NO", "TITLE", "CATEGORY", "AUTHOR", "PUBLISHER", "PUBLISH YEAR", "PRICE", "QUANTITY", "ISBN", "AVAILABLE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(BOOKS_RECORD);

        CUSTOMER_RECORD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "USER_ID", "USER NAME", "MOBILE", "EMAIL", "TYPE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(CUSTOMER_RECORD);

        jLabel3.setText("TITLE");

        jLabel4.setText("DUE DATE");

        jLabel5.setText("ISSUE DATE");

        jLabel6.setText("USER NAME");

        ISSUE_BOOK.setText("ISSUE BOOK");
        ISSUE_BOOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ISSUE_BOOKActionPerformed(evt);
            }
        });

        RESET.setText("RESET");
        RESET.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESETActionPerformed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ISSUE BOOKS ");

        CLEAR_BOOK.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        CLEAR_BOOK.setText("X");
        CLEAR_BOOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEAR_BOOKActionPerformed(evt);
            }
        });

        FETCH_BOOK.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        FETCH_BOOK.setText("Fetch Book");
        FETCH_BOOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FETCH_BOOKActionPerformed(evt);
            }
        });

        BOOK_FILTERS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BOOK_FILTERS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "BOOK_ID", "BOOK_NO", "TITLE", "CATEGORY", "AUTHOR", "ISBN", "PUBLISHER", "PUBLISH_YEAR", "PRICE", "QUANTITY", " ", " ", " ", " ", " ", " " }));
        BOOK_FILTERS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BOOK_FILTERSActionPerformed(evt);
            }
        });

        BOOK_SEARCH_BAR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BOOK_SEARCH_BAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BOOK_SEARCH_BARActionPerformed(evt);
            }
        });

        CLEAR_CUSTOMER.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        CLEAR_CUSTOMER.setText("X");
        CLEAR_CUSTOMER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEAR_CUSTOMERActionPerformed(evt);
            }
        });

        FETCH_CUSTOMER.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        FETCH_CUSTOMER.setText("Fetch Customer");
        FETCH_CUSTOMER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FETCH_CUSTOMERActionPerformed(evt);
            }
        });

        CUSTOMER_FILTERS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CUSTOMER_FILTERS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "USER_ID", "USER_NAME", "MOBILE", "EMAIL", "USER_TYPE", " ", " ", " ", " ", " ", " " }));
        CUSTOMER_FILTERS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CUSTOMER_FILTERSActionPerformed(evt);
            }
        });

        CUSTOMER_SEARCH_BAR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CUSTOMER_SEARCH_BAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CUSTOMER_SEARCH_BARActionPerformed(evt);
            }
        });

        jLabel8.setText("USER ID");

        jLabel9.setText("BOOK ID");

        jLabel10.setText("BOOK NO");

        jLabel12.setText("MOBILE");

        jLabel13.setText("EMAIL");

        jLabel14.setText("QUANTITY");

        MOBILE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MOBILEActionPerformed(evt);
            }
        });

        EMAIL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMAILActionPerformed(evt);
            }
        });

        ISSUE_DATE.setDateFormatString("yyyy MM dd");

        DUE_DATE.setDateFormatString("yyyy MM dd");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1199, Short.MAX_VALUE)
                        .addGap(112, 112, 112))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CUSTOMER_FILTERS, 0, 161, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(CUSTOMER_SEARCH_BAR, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CLEAR_CUSTOMER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(FETCH_CUSTOMER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(686, 686, 686))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(ISSUE_BOOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(RESET, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(QUANTITY)
                                    .addComponent(MOBILE, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(EMAIL)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(ISSUE_DATE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(TITLE, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(USER_NAME, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(BOOK_NO, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                            .addComponent(BOOK_ID, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                            .addComponent(USER_ID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                            .addComponent(DUE_DATE, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                                        .addGap(2, 2, 2))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BOOK_FILTERS, 0, 164, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(BOOK_SEARCH_BAR, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CLEAR_BOOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(FETCH_BOOK, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addGap(675, 675, 675)))))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BOOK_FILTERS, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BOOK_SEARCH_BAR, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FETCH_BOOK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CLEAR_BOOK, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(USER_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BOOK_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BOOK_NO, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TITLE, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(USER_NAME, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ISSUE_DATE, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CUSTOMER_FILTERS, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CUSTOMER_SEARCH_BAR, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FETCH_CUSTOMER, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CLEAR_CUSTOMER, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(DUE_DATE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(QUANTITY, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MOBILE, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EMAIL, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RESET, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ISSUE_BOOK, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CLEAR_BOOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEAR_BOOKActionPerformed
        // TODO add your handling code here:
        BOOK_SEARCH_BAR.setText(null);
    }//GEN-LAST:event_CLEAR_BOOKActionPerformed

    private void FETCH_BOOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FETCH_BOOKActionPerformed

        DefaultTableModel model = (DefaultTableModel) BOOKS_RECORD.getModel();
        model.setRowCount(0); // Clear table before fetching

        String keyword = BOOK_SEARCH_BAR.getText().trim();
        String selectedFilter = BOOK_FILTERS.getSelectedItem().toString();
        boolean applyFilter = !keyword.isEmpty() && !selectedFilter.equalsIgnoreCase("ALL");

        String query;
        if (applyFilter) {
            query = "SELECT ID, BOOK_ID, BOOK_NO, TITLE, CATEGORY, AUTHOR, ISBN, PUBLISHER, PUBLISH_YEAR, PRICE, QUANTITY " +
            "FROM BOOK WHERE " + selectedFilter + " LIKE ?";
        } else {
            query = "SELECT ID, BOOK_ID, BOOK_NO, TITLE, CATEGORY, AUTHOR, ISBN, PUBLISHER, PUBLISH_YEAR, PRICE, QUANTITY FROM BOOK";
        }

        try (Connection conn = DriverManager.getConnection(url, user, pass);
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            if (applyFilter) {
                pstmt.setString(1, "%" + keyword + "%");  // Apply wildcard search
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("BOOK_ID"),
                    rs.getString("BOOK_NO"),
                    rs.getString("TITLE"),
                    rs.getString("CATEGORY"),
                    rs.getString("AUTHOR"),
                    rs.getString("PUBLISHER"),
                    rs.getString("PUBLISH_YEAR"),
                    rs.getString("PRICE"),
                    rs.getString("QUANTITY"),
                    rs.getString("ISBN")
                  
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading student data: " + e.getMessage());
        }
    }//GEN-LAST:event_FETCH_BOOKActionPerformed

    private void BOOK_FILTERSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BOOK_FILTERSActionPerformed

    }//GEN-LAST:event_BOOK_FILTERSActionPerformed

    private void BOOK_SEARCH_BARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BOOK_SEARCH_BARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BOOK_SEARCH_BARActionPerformed

    private void CLEAR_CUSTOMERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEAR_CUSTOMERActionPerformed
         CUSTOMER_SEARCH_BAR.setText(null);
    }//GEN-LAST:event_CLEAR_CUSTOMERActionPerformed

    private void FETCH_CUSTOMERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FETCH_CUSTOMERActionPerformed
                                                         
    DefaultTableModel model = (DefaultTableModel) CUSTOMER_RECORD.getModel();
    model.setRowCount(0); // Clear table before fetching

    String keyword = CUSTOMER_SEARCH_BAR.getText().trim();
    String selectedFilter = CUSTOMER_FILTERS.getSelectedItem().toString();
    boolean applyFilter = !keyword.isEmpty() && !selectedFilter.equalsIgnoreCase("ALL");

    String query;
    if (applyFilter) {
        query = "SELECT ID, USER_ID, USER_NAME, EMAIL, MOBILE, USER_TYPE FROM USER WHERE " + selectedFilter + " LIKE ?";
    } else {
        query = "SELECT ID, USER_ID, USER_NAME, EMAIL, MOBILE, USER_TYPE FROM USER";
    }

    try (Connection conn = DriverManager.getConnection(url, user, pass);
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        if (applyFilter) {
            pstmt.setString(1, "%" + keyword + "%");  // Apply wildcard search
        }

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("USER_ID"),
                    rs.getString("USER_NAME"),
                    rs.getString("EMAIL"),
                    rs.getString("MOBILE"),
                    rs.getString("USER_TYPE"),  // "STUDENT" or "MEMBER"
//                    rs.getString("BRANCH"),
//                    rs.getString("DEPARTMENT"),
//                    rs.getString("SEMESTER"),
//                    rs.getString("QUALIFICATION"),
//                    rs.getString("DESIGNATION"),
            });
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading customer data: " + e.getMessage());
    }

  

    }//GEN-LAST:event_FETCH_CUSTOMERActionPerformed

    private void CUSTOMER_FILTERSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CUSTOMER_FILTERSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CUSTOMER_FILTERSActionPerformed

    private void CUSTOMER_SEARCH_BARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CUSTOMER_SEARCH_BARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CUSTOMER_SEARCH_BARActionPerformed

    private void ISSUE_BOOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ISSUE_BOOKActionPerformed
        
        
        String user_id = USER_ID.getText().trim();
        String book_id = BOOK_ID.getText().trim();
        String copiesText = QUANTITY.getText().trim();
        
                 
//**********************************************************************

         // Get selected date from JDateChooser
java.util.Date selectedIssueDate = ISSUE_DATE.getDate();
java.util.Date selectedDueDate = DUE_DATE.getDate();

java.util.Date issueDate, dueDate;

// If ISSUE_DATE is not selected, set it to the current date
if (selectedIssueDate == null) {
    issueDate = new java.util.Date(); // Set as current date
} else {
    issueDate = selectedIssueDate;
}

// If DUE_DATE is not selected, calculate it automatically (14 days from issue date)
if (selectedDueDate == null) {
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.setTime(issueDate);
    cal.add(java.util.Calendar.DAY_OF_MONTH, 14); // Add 14 days for due date
    dueDate = cal.getTime();
} else {
    dueDate = selectedDueDate;
}

// Set the dates in JDateChooser
ISSUE_DATE.setDate(issueDate);
DUE_DATE.setDate(dueDate);

// Convert to SQL format
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String issueDateStr = sdf.format(issueDate);
String dueDateStr = sdf.format(dueDate);


//*************************************************************************

        if (user_id.isEmpty() || book_id.isEmpty() || copiesText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }
        
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            
            int copies;
            try{
                copies = Integer.parseInt(copiesText);
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input! Copies must be numbers.");
                return;
            }
            
            updateFineStatus(conn);
            blockHighFineUsers(conn);

            // Check if the user has unpaid fines or overdue books
            if (!canUserBorrow(conn, user_id)) {
                JOptionPane.showMessageDialog(this, "You have unpaid fines or overdue books. Please return books and pay the fine.");
                System.out.println("You have unpaid fines or overdue books. Please return books and pay the fine."+" "+user_id);
                return;
            }       
            
            if (copies > 5 || copies <= 0) {
                System.out.println("You can only borrow between 1 to 5 copies of a book.");
                JOptionPane.showMessageDialog(this, "You can only borrow between 1 to 5 copies of a book.");
                return;
            }

            // Check if user already borrowed 10 books
            if (getUserBorrowedBookCount(conn, user_id) > 5) {
                System.out.println("You have reached the maximum limit of 5 borrowed books.");
                JOptionPane.showMessageDialog(this, "You have reached the maximum limit of 5 borrowed books.");
                return;
            }
            
            if (!isBookAvailable(conn, book_id, copies)) {
                JOptionPane.showMessageDialog(this, "Not enough copies available!");
                return;
            }
            

        // Insert Borrow Transaction
        borrowBook(conn, user_id, book_id, copies, issueDateStr, dueDateStr);

            
            System.out.println("Book borrowed successfully! Return within 14 or 30 days to avoid fines.");
            JOptionPane.showMessageDialog(this, "Book borrowed successfully! Return within 14 or 30 days to avoid fines.");
            
            updateBookCopies(conn, book_id, copies);
            //updateFineStatus(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_ISSUE_BOOKActionPerformed

    private void RESETActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESETActionPerformed
        // TODO add your handling code here:
        BOOK_ID.setText(null);
        USER_ID.setText(null);
        TITLE.setText(null);
        USER_NAME.setText(null);
        QUANTITY.setText(null);
        MOBILE.setText(null);
        EMAIL.setText(null);
        BOOK_NO.setText(null);
    }//GEN-LAST:event_RESETActionPerformed

    private void MOBILEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MOBILEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MOBILEActionPerformed

    private void EMAILActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMAILActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EMAILActionPerformed

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
            java.util.logging.Logger.getLogger(ISSUE_BOOK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ISSUE_BOOK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ISSUE_BOOK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ISSUE_BOOK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ISSUE_BOOK().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable BOOKS_RECORD;
    private javax.swing.JComboBox<String> BOOK_FILTERS;
    private javax.swing.JTextField BOOK_ID;
    private javax.swing.JTextField BOOK_NO;
    private javax.swing.JTextField BOOK_SEARCH_BAR;
    private javax.swing.JButton CLEAR_BOOK;
    private javax.swing.JButton CLEAR_CUSTOMER;
    private javax.swing.JComboBox<String> CUSTOMER_FILTERS;
    private javax.swing.JTable CUSTOMER_RECORD;
    private javax.swing.JTextField CUSTOMER_SEARCH_BAR;
    private com.toedter.calendar.JDateChooser DUE_DATE;
    private javax.swing.JTextField EMAIL;
    private javax.swing.JButton FETCH_BOOK;
    private javax.swing.JButton FETCH_CUSTOMER;
    private javax.swing.JButton ISSUE_BOOK;
    private com.toedter.calendar.JDateChooser ISSUE_DATE;
    private javax.swing.JTextField MOBILE;
    private javax.swing.JTextField QUANTITY;
    private javax.swing.JButton RESET;
    private javax.swing.JTextField TITLE;
    private javax.swing.JTextField USER_ID;
    private javax.swing.JTextField USER_NAME;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
