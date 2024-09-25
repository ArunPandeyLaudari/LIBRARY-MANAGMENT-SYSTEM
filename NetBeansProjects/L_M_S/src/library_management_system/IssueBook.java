/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package library_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


/**
 *
 * @author Acer
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    public IssueBook() {
        initComponents();
    }


//fetch book details from database n display onto sceen/pannel
public void getBookDetails() {
    String bookId = txt_bookid.getText();
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/library_management_system";
        String user = "root";
        String password = "";

        con = DriverManager.getConnection(url, user, password);

        if (bookId.isEmpty()) {
            JOptionPane.showMessageDialog(new JFrame(), "Book ID required!", "Oops", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "SELECT * FROM book_details WHERE book_id = ?";
        pst = con.prepareStatement(query);
        pst.setString(1, bookId);
        rs = pst.executeQuery();

        if (rs.next()) {
            lbl_bookid.setText(rs.getString("book_id"));
            lbl_bookname.setText(rs.getString("book_name"));
            lbl_author.setText(rs.getString("author"));
            booksemester.setText(rs.getString("semester"));
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Book not found!", "Oops", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(new JFrame(), "Error: " + e.getMessage(), "Oops", JOptionPane.ERROR_MESSAGE);
    } 
}


//fetch student details from database n display onto sceen/pannel
public void getStudentDetails() {
    String StudentId = txt_studentid.getText();
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/library_management_system";
        String user = "root";
        String password = "";

        con = DriverManager.getConnection(url, user, password);

        if (StudentId.isEmpty()) {
            JOptionPane.showMessageDialog(new JFrame(), "Student ID required!", "Oops", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "SELECT * FROM student_details WHERE sid = ?";
        pst = con.prepareStatement(query);
        pst.setString(1, StudentId);
        rs = pst.executeQuery();

        if (rs.next()) {
            lbl_studentid.setText(rs.getString("sid"));
            lbl_studentname.setText(rs.getString("sname"));
            lbl_faculty.setText(rs.getString("faculty"));
            lbl_semester.setText(rs.getString("semester"));
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Student not found!", "Oops", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(new JFrame(), "Error: " + e.getMessage(), "Oops", JOptionPane.ERROR_MESSAGE);
    } 
}

// to insert issue bookdetails into database


public boolean issueBook() {
    boolean isIssued = false;
    String bookId = txt_bookid.getText();
    String studentId = txt_studentid.getText();
    String bookName = lbl_bookname.getText();
    String studentName = lbl_studentname.getText();
    String semester = lbl_semester.getText();
    Date uIssueDate = date_issuedate.getDatoFecha();
    Date uDueDate = date_duedate.getDatoFecha();

    java.sql.Date sIssueDate = new java.sql.Date(uIssueDate.getTime());
    java.sql.Date sDueDate = new java.sql.Date(uDueDate.getTime());

    String url = "jdbc:mysql://localhost:3306/library_management_system";
    String user = "root";
    String password = "";

    String sql = "INSERT INTO issue_bookdetails(book_id, book_name, student_id, student_name,issue_date, due_date, status,semester) VALUES (?, ?, ?, ?,?, ?, ?, ?)";

    try (Connection con = DriverManager.getConnection(url, user, password);
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        pst.setString(1, bookId);
        pst.setString(2, bookName);
        pst.setString(3, studentId);
        pst.setString(4, studentName);
        pst.setDate(5, sIssueDate);
        pst.setDate(6, sDueDate);
        pst.setString(7, "pending");
         pst.setString(8,semester);

        int rowCount = pst.executeUpdate();
        isIssued = rowCount > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return isIssued;
}



//Checking whether book already allocated or not by the student

public boolean isAlreadyIssued() {
    boolean isAlreadyIssued = false;
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    String bookId = txt_bookid.getText();
    String studentId = txt_studentid.getText();

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/library_management_system";
        String user = "root";
        String password = "";

        con = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM issue_bookdetails WHERE book_id = ? AND student_id = ? AND status = ?";
        
        pst = con.prepareStatement(sql);
        pst.setString(1, bookId);
        pst.setString(2, studentId);
        pst.setString(3, "pending");
        
        rs = pst.executeQuery();

        isAlreadyIssued = rs.next();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Close resources in the reverse order of their opening
        try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
        try { if (pst != null) pst.close(); } catch (Exception e) { e.printStackTrace(); }
        try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    return isAlreadyIssued;
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        booksemester = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        lbl_bookname = new javax.swing.JLabel();
        lbl_bookid = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lbl_semester1 = new javax.swing.JLabel();
        lbl_studentid1 = new javax.swing.JLabel();
        lbl_studentname1 = new javax.swing.JLabel();
        lbl_faculty1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_studentid = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txt_bookid = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        date_duedate = new rojeru_san.componentes.RSDateChooser();
        jLabel23 = new javax.swing.JLabel();
        date_issuedate = new rojeru_san.componentes.RSDateChooser();
        ISSUEBOOK = new javax.swing.JButton();
        jPanel37 = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        lbl_semester = new javax.swing.JLabel();
        lbl_studentid = new javax.swing.JLabel();
        lbl_studentname = new javax.swing.JLabel();
        lbl_faculty = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jLabel124 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        lbl_semester17 = new javax.swing.JLabel();
        lbl_studentid17 = new javax.swing.JLabel();
        lbl_studentname17 = new javax.swing.JLabel();
        lbl_faculty17 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jLabel130 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        lbl_semester18 = new javax.swing.JLabel();
        lbl_studentid18 = new javax.swing.JLabel();
        lbl_studentname18 = new javax.swing.JLabel();
        lbl_faculty18 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jLabel136 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        lbl_semester19 = new javax.swing.JLabel();
        lbl_studentid19 = new javax.swing.JLabel();
        lbl_studentname19 = new javax.swing.JLabel();
        lbl_faculty19 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jLabel142 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        lbl_semester20 = new javax.swing.JLabel();
        lbl_studentid20 = new javax.swing.JLabel();
        lbl_studentname20 = new javax.swing.JLabel();
        lbl_faculty20 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        jLabel148 = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        lbl_semester21 = new javax.swing.JLabel();
        lbl_studentid21 = new javax.swing.JLabel();
        lbl_studentname21 = new javax.swing.JLabel();
        lbl_faculty21 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        jLabel154 = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        lbl_semester22 = new javax.swing.JLabel();
        lbl_studentid22 = new javax.swing.JLabel();
        lbl_studentname22 = new javax.swing.JLabel();
        lbl_faculty22 = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        jLabel160 = new javax.swing.JLabel();
        jPanel52 = new javax.swing.JPanel();
        jLabel161 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        jLabel163 = new javax.swing.JLabel();
        jLabel164 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        lbl_semester23 = new javax.swing.JLabel();
        lbl_studentid23 = new javax.swing.JLabel();
        lbl_studentname23 = new javax.swing.JLabel();
        lbl_faculty23 = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        jLabel166 = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        jLabel167 = new javax.swing.JLabel();
        jLabel168 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        jLabel171 = new javax.swing.JLabel();
        lbl_semester24 = new javax.swing.JLabel();
        lbl_studentid24 = new javax.swing.JLabel();
        lbl_studentname24 = new javax.swing.JLabel();
        lbl_faculty24 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        jLabel172 = new javax.swing.JLabel();
        jPanel56 = new javax.swing.JPanel();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        jLabel175 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        lbl_semester25 = new javax.swing.JLabel();
        lbl_studentid25 = new javax.swing.JLabel();
        lbl_studentname25 = new javax.swing.JLabel();
        lbl_faculty25 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        jLabel178 = new javax.swing.JLabel();
        jPanel58 = new javax.swing.JPanel();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        jLabel183 = new javax.swing.JLabel();
        lbl_semester26 = new javax.swing.JLabel();
        lbl_studentid26 = new javax.swing.JLabel();
        lbl_studentname26 = new javax.swing.JLabel();
        lbl_faculty26 = new javax.swing.JLabel();
        jPanel59 = new javax.swing.JPanel();
        jLabel184 = new javax.swing.JLabel();
        jPanel60 = new javax.swing.JPanel();
        jLabel185 = new javax.swing.JLabel();
        jLabel186 = new javax.swing.JLabel();
        jLabel187 = new javax.swing.JLabel();
        jLabel188 = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        lbl_semester27 = new javax.swing.JLabel();
        lbl_studentid27 = new javax.swing.JLabel();
        lbl_studentname27 = new javax.swing.JLabel();
        lbl_faculty27 = new javax.swing.JLabel();
        jPanel61 = new javax.swing.JPanel();
        jLabel190 = new javax.swing.JLabel();
        jPanel62 = new javax.swing.JPanel();
        jLabel191 = new javax.swing.JLabel();
        jLabel192 = new javax.swing.JLabel();
        jLabel193 = new javax.swing.JLabel();
        jLabel194 = new javax.swing.JLabel();
        jLabel195 = new javax.swing.JLabel();
        lbl_semester28 = new javax.swing.JLabel();
        lbl_studentid28 = new javax.swing.JLabel();
        lbl_studentname28 = new javax.swing.JLabel();
        lbl_faculty28 = new javax.swing.JLabel();
        jPanel63 = new javax.swing.JPanel();
        jLabel196 = new javax.swing.JLabel();
        jPanel64 = new javax.swing.JPanel();
        jLabel197 = new javax.swing.JLabel();
        jLabel198 = new javax.swing.JLabel();
        jLabel199 = new javax.swing.JLabel();
        jLabel200 = new javax.swing.JLabel();
        jLabel201 = new javax.swing.JLabel();
        lbl_semester29 = new javax.swing.JLabel();
        lbl_studentid29 = new javax.swing.JLabel();
        lbl_studentname29 = new javax.swing.JLabel();
        lbl_faculty29 = new javax.swing.JLabel();
        jPanel65 = new javax.swing.JPanel();
        jLabel202 = new javax.swing.JLabel();
        jPanel66 = new javax.swing.JPanel();
        jLabel203 = new javax.swing.JLabel();
        jLabel204 = new javax.swing.JLabel();
        jLabel205 = new javax.swing.JLabel();
        jLabel206 = new javax.swing.JLabel();
        jLabel207 = new javax.swing.JLabel();
        lbl_semester30 = new javax.swing.JLabel();
        lbl_studentid30 = new javax.swing.JLabel();
        lbl_studentname30 = new javax.swing.JLabel();
        lbl_faculty30 = new javax.swing.JLabel();
        jPanel67 = new javax.swing.JPanel();
        jLabel208 = new javax.swing.JLabel();
        jPanel68 = new javax.swing.JPanel();
        jLabel209 = new javax.swing.JLabel();
        jLabel210 = new javax.swing.JLabel();
        jLabel211 = new javax.swing.JLabel();
        jLabel212 = new javax.swing.JLabel();
        jLabel213 = new javax.swing.JLabel();
        lbl_semester31 = new javax.swing.JLabel();
        lbl_studentid31 = new javax.swing.JLabel();
        lbl_studentname31 = new javax.swing.JLabel();
        lbl_faculty31 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Rewind_48px.png"))); // NOI18N
        jLabel2.setText("Back");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel1.setText("Book Details");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Semester :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Book Name :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Author :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Book ID :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        booksemester.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        booksemester.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(booksemester, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 530, 200, 30));

        lbl_author.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        lbl_bookname.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_bookname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_bookid.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_bookid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 900));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 0, 0));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Books_52px_1.png"))); // NOI18N
        jLabel22.setText("Issue Book");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        jPanel6.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jPanel7.setBackground(new java.awt.Color(0, 0, 102));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel26.setText("Student Details");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Semester :");
        jPanel7.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("  Std Name :");
        jPanel7.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Faculty :");
        jPanel7.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Student ID :");
        jPanel7.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Student ID :");
        jPanel7.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.add(lbl_semester1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.add(lbl_studentid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.add(lbl_studentname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.add(lbl_faculty1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 420, 900));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 0, 0));
        jLabel4.setText("X");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(153, 0, 0));
        jLabel19.setText("Due Date :");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, -1, -1));

        txt_studentid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_studentid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 102)));
        txt_studentid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentidFocusLost(evt);
            }
        });
        txt_studentid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentidActionPerformed(evt);
            }
        });
        jPanel5.add(txt_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 270, 50));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(153, 0, 0));
        jLabel20.setText("Enter Book ID :");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        txt_bookid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 102)));
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        txt_bookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookidActionPerformed(evt);
            }
        });
        jPanel5.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 270, 50));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(153, 0, 0));
        jLabel21.setText("Enter Std ID :");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, 30));

        date_duedate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        date_duedate.setPlaceholder("Select Due date");
        jPanel5.add(date_duedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 510, 270, 50));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(153, 0, 0));
        jLabel23.setText("Issue Date :");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        date_issuedate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        date_issuedate.setPlaceholder("Select  Issue date");
        jPanel5.add(date_issuedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 270, 50));

        ISSUEBOOK.setBackground(new java.awt.Color(153, 0, 0));
        ISSUEBOOK.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        ISSUEBOOK.setForeground(new java.awt.Color(255, 255, 255));
        ISSUEBOOK.setText("ISSUE BOOK");
        ISSUEBOOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ISSUEBOOKActionPerformed(evt);
            }
        });
        jPanel5.add(ISSUEBOOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 470, 60));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 0, 560, 900));

        jPanel37.setBackground(new java.awt.Color(0, 0, 102));
        jPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel118.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(255, 255, 255));
        jLabel118.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel118.setText("Student Details");
        jPanel37.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel37.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel119.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(255, 255, 255));
        jLabel119.setText("Semester :");
        jPanel37.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel120.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(255, 255, 255));
        jLabel120.setText("  Std Name :");
        jPanel37.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel121.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(255, 255, 255));
        jLabel121.setText("Faculty :");
        jPanel37.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel122.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(255, 255, 255));
        jLabel122.setText("Student ID :");
        jPanel37.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel123.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(255, 255, 255));
        jLabel123.setText("Student ID :");
        jPanel37.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester.setForeground(new java.awt.Color(255, 255, 255));
        jPanel37.add(lbl_semester, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel37.add(lbl_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel37.add(lbl_studentname, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty.setForeground(new java.awt.Color(255, 255, 255));
        jPanel37.add(lbl_faculty, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel39.setBackground(new java.awt.Color(0, 0, 102));
        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel124.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(255, 255, 255));
        jLabel124.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel124.setText("Student Details");
        jPanel39.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel39.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel125.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(255, 255, 255));
        jLabel125.setText("Semester :");
        jPanel39.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel126.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel126.setForeground(new java.awt.Color(255, 255, 255));
        jLabel126.setText("  Std Name :");
        jPanel39.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel127.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(255, 255, 255));
        jLabel127.setText("Faculty :");
        jPanel39.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel128.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel128.setForeground(new java.awt.Color(255, 255, 255));
        jLabel128.setText("Student ID :");
        jPanel39.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel129.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(255, 255, 255));
        jLabel129.setText("Student ID :");
        jPanel39.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester17.setForeground(new java.awt.Color(255, 255, 255));
        jPanel39.add(lbl_semester17, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid17.setForeground(new java.awt.Color(255, 255, 255));
        jPanel39.add(lbl_studentid17, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname17.setForeground(new java.awt.Color(255, 255, 255));
        jPanel39.add(lbl_studentname17, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty17.setForeground(new java.awt.Color(255, 255, 255));
        jPanel39.add(lbl_faculty17, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel37.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        jPanel41.setBackground(new java.awt.Color(0, 0, 102));
        jPanel41.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel130.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(255, 255, 255));
        jLabel130.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel130.setText("Student Details");
        jPanel41.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel41.add(jPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel131.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel131.setForeground(new java.awt.Color(255, 255, 255));
        jLabel131.setText("Semester :");
        jPanel41.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel132.setForeground(new java.awt.Color(255, 255, 255));
        jLabel132.setText("  Std Name :");
        jPanel41.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel133.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel133.setForeground(new java.awt.Color(255, 255, 255));
        jLabel133.setText("Faculty :");
        jPanel41.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel134.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel134.setForeground(new java.awt.Color(255, 255, 255));
        jLabel134.setText("Student ID :");
        jPanel41.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel135.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel135.setForeground(new java.awt.Color(255, 255, 255));
        jLabel135.setText("Student ID :");
        jPanel41.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester18.setForeground(new java.awt.Color(255, 255, 255));
        jPanel41.add(lbl_semester18, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid18.setForeground(new java.awt.Color(255, 255, 255));
        jPanel41.add(lbl_studentid18, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname18.setForeground(new java.awt.Color(255, 255, 255));
        jPanel41.add(lbl_studentname18, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty18.setForeground(new java.awt.Color(255, 255, 255));
        jPanel41.add(lbl_faculty18, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel43.setBackground(new java.awt.Color(0, 0, 102));
        jPanel43.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel136.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel136.setForeground(new java.awt.Color(255, 255, 255));
        jLabel136.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel136.setText("Student Details");
        jPanel43.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel43.add(jPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel137.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(255, 255, 255));
        jLabel137.setText("Semester :");
        jPanel43.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel138.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel138.setForeground(new java.awt.Color(255, 255, 255));
        jLabel138.setText("  Std Name :");
        jPanel43.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel139.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel139.setForeground(new java.awt.Color(255, 255, 255));
        jLabel139.setText("Faculty :");
        jPanel43.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel140.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel140.setForeground(new java.awt.Color(255, 255, 255));
        jLabel140.setText("Student ID :");
        jPanel43.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel141.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel141.setForeground(new java.awt.Color(255, 255, 255));
        jLabel141.setText("Student ID :");
        jPanel43.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester19.setForeground(new java.awt.Color(255, 255, 255));
        jPanel43.add(lbl_semester19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid19.setForeground(new java.awt.Color(255, 255, 255));
        jPanel43.add(lbl_studentid19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname19.setForeground(new java.awt.Color(255, 255, 255));
        jPanel43.add(lbl_studentname19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty19.setForeground(new java.awt.Color(255, 255, 255));
        jPanel43.add(lbl_faculty19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel41.add(jPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        jPanel37.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        jPanel45.setBackground(new java.awt.Color(0, 0, 102));
        jPanel45.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel142.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel142.setForeground(new java.awt.Color(255, 255, 255));
        jLabel142.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel142.setText("Student Details");
        jPanel45.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel45.add(jPanel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel143.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel143.setForeground(new java.awt.Color(255, 255, 255));
        jLabel143.setText("Semester :");
        jPanel45.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel144.setForeground(new java.awt.Color(255, 255, 255));
        jLabel144.setText("  Std Name :");
        jPanel45.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel145.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel145.setForeground(new java.awt.Color(255, 255, 255));
        jLabel145.setText("Faculty :");
        jPanel45.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel146.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel146.setForeground(new java.awt.Color(255, 255, 255));
        jLabel146.setText("Student ID :");
        jPanel45.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel147.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel147.setForeground(new java.awt.Color(255, 255, 255));
        jLabel147.setText("Student ID :");
        jPanel45.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester20.setForeground(new java.awt.Color(255, 255, 255));
        jPanel45.add(lbl_semester20, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid20.setForeground(new java.awt.Color(255, 255, 255));
        jPanel45.add(lbl_studentid20, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname20.setForeground(new java.awt.Color(255, 255, 255));
        jPanel45.add(lbl_studentname20, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty20.setForeground(new java.awt.Color(255, 255, 255));
        jPanel45.add(lbl_faculty20, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel47.setBackground(new java.awt.Color(0, 0, 102));
        jPanel47.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel148.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel148.setForeground(new java.awt.Color(255, 255, 255));
        jLabel148.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel148.setText("Student Details");
        jPanel47.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel47.add(jPanel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel149.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel149.setForeground(new java.awt.Color(255, 255, 255));
        jLabel149.setText("Semester :");
        jPanel47.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel150.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel150.setForeground(new java.awt.Color(255, 255, 255));
        jLabel150.setText("  Std Name :");
        jPanel47.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel151.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel151.setForeground(new java.awt.Color(255, 255, 255));
        jLabel151.setText("Faculty :");
        jPanel47.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel152.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel152.setForeground(new java.awt.Color(255, 255, 255));
        jLabel152.setText("Student ID :");
        jPanel47.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel153.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel153.setForeground(new java.awt.Color(255, 255, 255));
        jLabel153.setText("Student ID :");
        jPanel47.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester21.setForeground(new java.awt.Color(255, 255, 255));
        jPanel47.add(lbl_semester21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid21.setForeground(new java.awt.Color(255, 255, 255));
        jPanel47.add(lbl_studentid21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname21.setForeground(new java.awt.Color(255, 255, 255));
        jPanel47.add(lbl_studentname21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty21.setForeground(new java.awt.Color(255, 255, 255));
        jPanel47.add(lbl_faculty21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel45.add(jPanel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        jPanel49.setBackground(new java.awt.Color(0, 0, 102));
        jPanel49.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel154.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel154.setForeground(new java.awt.Color(255, 255, 255));
        jLabel154.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel154.setText("Student Details");
        jPanel49.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel49.add(jPanel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel155.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel155.setForeground(new java.awt.Color(255, 255, 255));
        jLabel155.setText("Semester :");
        jPanel49.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel156.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel156.setForeground(new java.awt.Color(255, 255, 255));
        jLabel156.setText("  Std Name :");
        jPanel49.add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel157.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel157.setForeground(new java.awt.Color(255, 255, 255));
        jLabel157.setText("Faculty :");
        jPanel49.add(jLabel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel158.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel158.setForeground(new java.awt.Color(255, 255, 255));
        jLabel158.setText("Student ID :");
        jPanel49.add(jLabel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel159.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel159.setForeground(new java.awt.Color(255, 255, 255));
        jLabel159.setText("Student ID :");
        jPanel49.add(jLabel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester22.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester22.setForeground(new java.awt.Color(255, 255, 255));
        jPanel49.add(lbl_semester22, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid22.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid22.setForeground(new java.awt.Color(255, 255, 255));
        jPanel49.add(lbl_studentid22, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname22.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname22.setForeground(new java.awt.Color(255, 255, 255));
        jPanel49.add(lbl_studentname22, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty22.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty22.setForeground(new java.awt.Color(255, 255, 255));
        jPanel49.add(lbl_faculty22, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel51.setBackground(new java.awt.Color(0, 0, 102));
        jPanel51.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel160.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel160.setForeground(new java.awt.Color(255, 255, 255));
        jLabel160.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel160.setText("Student Details");
        jPanel51.add(jLabel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel51.add(jPanel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel161.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel161.setForeground(new java.awt.Color(255, 255, 255));
        jLabel161.setText("Semester :");
        jPanel51.add(jLabel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel162.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel162.setForeground(new java.awt.Color(255, 255, 255));
        jLabel162.setText("  Std Name :");
        jPanel51.add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel163.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel163.setForeground(new java.awt.Color(255, 255, 255));
        jLabel163.setText("Faculty :");
        jPanel51.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel164.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel164.setForeground(new java.awt.Color(255, 255, 255));
        jLabel164.setText("Student ID :");
        jPanel51.add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel165.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel165.setForeground(new java.awt.Color(255, 255, 255));
        jLabel165.setText("Student ID :");
        jPanel51.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester23.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester23.setForeground(new java.awt.Color(255, 255, 255));
        jPanel51.add(lbl_semester23, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid23.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid23.setForeground(new java.awt.Color(255, 255, 255));
        jPanel51.add(lbl_studentid23, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname23.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname23.setForeground(new java.awt.Color(255, 255, 255));
        jPanel51.add(lbl_studentname23, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty23.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty23.setForeground(new java.awt.Color(255, 255, 255));
        jPanel51.add(lbl_faculty23, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel49.add(jPanel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        jPanel45.add(jPanel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        jPanel37.add(jPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        jPanel53.setBackground(new java.awt.Color(0, 0, 102));
        jPanel53.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel166.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel166.setForeground(new java.awt.Color(255, 255, 255));
        jLabel166.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel166.setText("Student Details");
        jPanel53.add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel53.add(jPanel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel167.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel167.setForeground(new java.awt.Color(255, 255, 255));
        jLabel167.setText("Semester :");
        jPanel53.add(jLabel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel168.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel168.setForeground(new java.awt.Color(255, 255, 255));
        jLabel168.setText("  Std Name :");
        jPanel53.add(jLabel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel169.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel169.setForeground(new java.awt.Color(255, 255, 255));
        jLabel169.setText("Faculty :");
        jPanel53.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel170.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel170.setForeground(new java.awt.Color(255, 255, 255));
        jLabel170.setText("Student ID :");
        jPanel53.add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel171.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel171.setForeground(new java.awt.Color(255, 255, 255));
        jLabel171.setText("Student ID :");
        jPanel53.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester24.setForeground(new java.awt.Color(255, 255, 255));
        jPanel53.add(lbl_semester24, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid24.setForeground(new java.awt.Color(255, 255, 255));
        jPanel53.add(lbl_studentid24, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname24.setForeground(new java.awt.Color(255, 255, 255));
        jPanel53.add(lbl_studentname24, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty24.setForeground(new java.awt.Color(255, 255, 255));
        jPanel53.add(lbl_faculty24, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel55.setBackground(new java.awt.Color(0, 0, 102));
        jPanel55.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel172.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel172.setForeground(new java.awt.Color(255, 255, 255));
        jLabel172.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel172.setText("Student Details");
        jPanel55.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel55.add(jPanel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel173.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel173.setForeground(new java.awt.Color(255, 255, 255));
        jLabel173.setText("Semester :");
        jPanel55.add(jLabel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel174.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel174.setForeground(new java.awt.Color(255, 255, 255));
        jLabel174.setText("  Std Name :");
        jPanel55.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel175.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel175.setForeground(new java.awt.Color(255, 255, 255));
        jLabel175.setText("Faculty :");
        jPanel55.add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel176.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel176.setForeground(new java.awt.Color(255, 255, 255));
        jLabel176.setText("Student ID :");
        jPanel55.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel177.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel177.setForeground(new java.awt.Color(255, 255, 255));
        jLabel177.setText("Student ID :");
        jPanel55.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester25.setForeground(new java.awt.Color(255, 255, 255));
        jPanel55.add(lbl_semester25, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid25.setForeground(new java.awt.Color(255, 255, 255));
        jPanel55.add(lbl_studentid25, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname25.setForeground(new java.awt.Color(255, 255, 255));
        jPanel55.add(lbl_studentname25, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty25.setForeground(new java.awt.Color(255, 255, 255));
        jPanel55.add(lbl_faculty25, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel53.add(jPanel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        jPanel57.setBackground(new java.awt.Color(0, 0, 102));
        jPanel57.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel178.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel178.setForeground(new java.awt.Color(255, 255, 255));
        jLabel178.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel178.setText("Student Details");
        jPanel57.add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel57.add(jPanel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel179.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel179.setForeground(new java.awt.Color(255, 255, 255));
        jLabel179.setText("Semester :");
        jPanel57.add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel180.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel180.setForeground(new java.awt.Color(255, 255, 255));
        jLabel180.setText("  Std Name :");
        jPanel57.add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel181.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel181.setForeground(new java.awt.Color(255, 255, 255));
        jLabel181.setText("Faculty :");
        jPanel57.add(jLabel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel182.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel182.setForeground(new java.awt.Color(255, 255, 255));
        jLabel182.setText("Student ID :");
        jPanel57.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel183.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel183.setForeground(new java.awt.Color(255, 255, 255));
        jLabel183.setText("Student ID :");
        jPanel57.add(jLabel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester26.setForeground(new java.awt.Color(255, 255, 255));
        jPanel57.add(lbl_semester26, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid26.setForeground(new java.awt.Color(255, 255, 255));
        jPanel57.add(lbl_studentid26, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname26.setForeground(new java.awt.Color(255, 255, 255));
        jPanel57.add(lbl_studentname26, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty26.setForeground(new java.awt.Color(255, 255, 255));
        jPanel57.add(lbl_faculty26, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel59.setBackground(new java.awt.Color(0, 0, 102));
        jPanel59.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel184.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel184.setForeground(new java.awt.Color(255, 255, 255));
        jLabel184.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel184.setText("Student Details");
        jPanel59.add(jLabel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel59.add(jPanel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel185.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel185.setForeground(new java.awt.Color(255, 255, 255));
        jLabel185.setText("Semester :");
        jPanel59.add(jLabel185, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel186.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel186.setForeground(new java.awt.Color(255, 255, 255));
        jLabel186.setText("  Std Name :");
        jPanel59.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel187.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel187.setForeground(new java.awt.Color(255, 255, 255));
        jLabel187.setText("Faculty :");
        jPanel59.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel188.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel188.setForeground(new java.awt.Color(255, 255, 255));
        jLabel188.setText("Student ID :");
        jPanel59.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel189.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel189.setForeground(new java.awt.Color(255, 255, 255));
        jLabel189.setText("Student ID :");
        jPanel59.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester27.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester27.setForeground(new java.awt.Color(255, 255, 255));
        jPanel59.add(lbl_semester27, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid27.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid27.setForeground(new java.awt.Color(255, 255, 255));
        jPanel59.add(lbl_studentid27, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname27.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname27.setForeground(new java.awt.Color(255, 255, 255));
        jPanel59.add(lbl_studentname27, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty27.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty27.setForeground(new java.awt.Color(255, 255, 255));
        jPanel59.add(lbl_faculty27, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel57.add(jPanel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        jPanel53.add(jPanel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        jPanel61.setBackground(new java.awt.Color(0, 0, 102));
        jPanel61.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel190.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel190.setForeground(new java.awt.Color(255, 255, 255));
        jLabel190.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel190.setText("Student Details");
        jPanel61.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel61.add(jPanel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel191.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel191.setForeground(new java.awt.Color(255, 255, 255));
        jLabel191.setText("Semester :");
        jPanel61.add(jLabel191, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel192.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel192.setForeground(new java.awt.Color(255, 255, 255));
        jLabel192.setText("  Std Name :");
        jPanel61.add(jLabel192, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel193.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel193.setForeground(new java.awt.Color(255, 255, 255));
        jLabel193.setText("Faculty :");
        jPanel61.add(jLabel193, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel194.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel194.setForeground(new java.awt.Color(255, 255, 255));
        jLabel194.setText("Student ID :");
        jPanel61.add(jLabel194, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel195.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel195.setForeground(new java.awt.Color(255, 255, 255));
        jLabel195.setText("Student ID :");
        jPanel61.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester28.setForeground(new java.awt.Color(255, 255, 255));
        jPanel61.add(lbl_semester28, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid28.setForeground(new java.awt.Color(255, 255, 255));
        jPanel61.add(lbl_studentid28, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname28.setForeground(new java.awt.Color(255, 255, 255));
        jPanel61.add(lbl_studentname28, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty28.setForeground(new java.awt.Color(255, 255, 255));
        jPanel61.add(lbl_faculty28, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel63.setBackground(new java.awt.Color(0, 0, 102));
        jPanel63.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel196.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel196.setForeground(new java.awt.Color(255, 255, 255));
        jLabel196.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel196.setText("Student Details");
        jPanel63.add(jLabel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel63.add(jPanel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel197.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel197.setForeground(new java.awt.Color(255, 255, 255));
        jLabel197.setText("Semester :");
        jPanel63.add(jLabel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel198.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel198.setForeground(new java.awt.Color(255, 255, 255));
        jLabel198.setText("  Std Name :");
        jPanel63.add(jLabel198, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel199.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel199.setForeground(new java.awt.Color(255, 255, 255));
        jLabel199.setText("Faculty :");
        jPanel63.add(jLabel199, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel200.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel200.setForeground(new java.awt.Color(255, 255, 255));
        jLabel200.setText("Student ID :");
        jPanel63.add(jLabel200, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel201.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel201.setForeground(new java.awt.Color(255, 255, 255));
        jLabel201.setText("Student ID :");
        jPanel63.add(jLabel201, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester29.setForeground(new java.awt.Color(255, 255, 255));
        jPanel63.add(lbl_semester29, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid29.setForeground(new java.awt.Color(255, 255, 255));
        jPanel63.add(lbl_studentid29, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname29.setForeground(new java.awt.Color(255, 255, 255));
        jPanel63.add(lbl_studentname29, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty29.setForeground(new java.awt.Color(255, 255, 255));
        jPanel63.add(lbl_faculty29, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel61.add(jPanel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        jPanel65.setBackground(new java.awt.Color(0, 0, 102));
        jPanel65.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel202.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel202.setForeground(new java.awt.Color(255, 255, 255));
        jLabel202.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel202.setText("Student Details");
        jPanel65.add(jLabel202, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel65.add(jPanel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel203.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel203.setForeground(new java.awt.Color(255, 255, 255));
        jLabel203.setText("Semester :");
        jPanel65.add(jLabel203, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel204.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel204.setForeground(new java.awt.Color(255, 255, 255));
        jLabel204.setText("  Std Name :");
        jPanel65.add(jLabel204, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel205.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel205.setForeground(new java.awt.Color(255, 255, 255));
        jLabel205.setText("Faculty :");
        jPanel65.add(jLabel205, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel206.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel206.setForeground(new java.awt.Color(255, 255, 255));
        jLabel206.setText("Student ID :");
        jPanel65.add(jLabel206, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel207.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel207.setForeground(new java.awt.Color(255, 255, 255));
        jLabel207.setText("Student ID :");
        jPanel65.add(jLabel207, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester30.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester30.setForeground(new java.awt.Color(255, 255, 255));
        jPanel65.add(lbl_semester30, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid30.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid30.setForeground(new java.awt.Color(255, 255, 255));
        jPanel65.add(lbl_studentid30, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname30.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname30.setForeground(new java.awt.Color(255, 255, 255));
        jPanel65.add(lbl_studentname30, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty30.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty30.setForeground(new java.awt.Color(255, 255, 255));
        jPanel65.add(lbl_faculty30, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel67.setBackground(new java.awt.Color(0, 0, 102));
        jPanel67.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel208.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel208.setForeground(new java.awt.Color(255, 255, 255));
        jLabel208.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel208.setText("Student Details");
        jPanel67.add(jLabel208, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel67.add(jPanel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel209.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel209.setForeground(new java.awt.Color(255, 255, 255));
        jLabel209.setText("Semester :");
        jPanel67.add(jLabel209, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 530, -1, -1));

        jLabel210.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel210.setForeground(new java.awt.Color(255, 255, 255));
        jLabel210.setText("  Std Name :");
        jPanel67.add(jLabel210, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel211.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel211.setForeground(new java.awt.Color(255, 255, 255));
        jLabel211.setText("Faculty :");
        jPanel67.add(jLabel211, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel212.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel212.setForeground(new java.awt.Color(255, 255, 255));
        jLabel212.setText("Student ID :");
        jPanel67.add(jLabel212, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        jLabel213.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel213.setForeground(new java.awt.Color(255, 255, 255));
        jLabel213.setText("Student ID :");
        jPanel67.add(jLabel213, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));

        lbl_semester31.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester31.setForeground(new java.awt.Color(255, 255, 255));
        jPanel67.add(lbl_semester31, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, 200, 30));

        lbl_studentid31.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentid31.setForeground(new java.awt.Color(255, 255, 255));
        jPanel67.add(lbl_studentid31, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 200, 30));

        lbl_studentname31.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname31.setForeground(new java.awt.Color(255, 255, 255));
        jPanel67.add(lbl_studentname31, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 200, 30));

        lbl_faculty31.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_faculty31.setForeground(new java.awt.Color(255, 255, 255));
        jPanel67.add(lbl_faculty31, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 200, 30));

        jPanel65.add(jPanel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        jPanel61.add(jPanel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        jPanel53.add(jPanel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        jPanel37.add(jPanel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 510, 900));

        getContentPane().add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 490, 900));

        setSize(new java.awt.Dimension(1920, 900));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        HomePage home=new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void txt_studentidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentidActionPerformed

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void ISSUEBOOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ISSUEBOOKActionPerformed
        
                                    
       
    

        
    if (!isAlreadyIssued()) {
        if (issueBook()) {
            JOptionPane.showMessageDialog(this, "Book Issued Successfully");

            // Clear the labels and text fields
            lbl_bookid.setText("");
            lbl_bookname.setText("");
            lbl_author.setText("");
            booksemester.setText("");
            lbl_studentid.setText("");
            lbl_studentname.setText("");
            lbl_faculty.setText("");
            lbl_semester.setText("");
            txt_bookid.setText("");
            txt_studentid.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed To Issue Book");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Book is already issued to the student");
    }




    }//GEN-LAST:event_ISSUEBOOKActionPerformed

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
       getBookDetails();
    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentidFocusLost
       getStudentDetails();
    }//GEN-LAST:event_txt_studentidFocusLost

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
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ISSUEBOOK;
    private javax.swing.JLabel booksemester;
    private rojeru_san.componentes.RSDateChooser date_duedate;
    private rojeru_san.componentes.RSDateChooser date_issuedate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel199;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel200;
    private javax.swing.JLabel jLabel201;
    private javax.swing.JLabel jLabel202;
    private javax.swing.JLabel jLabel203;
    private javax.swing.JLabel jLabel204;
    private javax.swing.JLabel jLabel205;
    private javax.swing.JLabel jLabel206;
    private javax.swing.JLabel jLabel207;
    private javax.swing.JLabel jLabel208;
    private javax.swing.JLabel jLabel209;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel210;
    private javax.swing.JLabel jLabel211;
    private javax.swing.JLabel jLabel212;
    private javax.swing.JLabel jLabel213;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookid;
    private javax.swing.JLabel lbl_bookname;
    private javax.swing.JLabel lbl_faculty;
    private javax.swing.JLabel lbl_faculty1;
    private javax.swing.JLabel lbl_faculty17;
    private javax.swing.JLabel lbl_faculty18;
    private javax.swing.JLabel lbl_faculty19;
    private javax.swing.JLabel lbl_faculty20;
    private javax.swing.JLabel lbl_faculty21;
    private javax.swing.JLabel lbl_faculty22;
    private javax.swing.JLabel lbl_faculty23;
    private javax.swing.JLabel lbl_faculty24;
    private javax.swing.JLabel lbl_faculty25;
    private javax.swing.JLabel lbl_faculty26;
    private javax.swing.JLabel lbl_faculty27;
    private javax.swing.JLabel lbl_faculty28;
    private javax.swing.JLabel lbl_faculty29;
    private javax.swing.JLabel lbl_faculty30;
    private javax.swing.JLabel lbl_faculty31;
    private javax.swing.JLabel lbl_semester;
    private javax.swing.JLabel lbl_semester1;
    private javax.swing.JLabel lbl_semester17;
    private javax.swing.JLabel lbl_semester18;
    private javax.swing.JLabel lbl_semester19;
    private javax.swing.JLabel lbl_semester20;
    private javax.swing.JLabel lbl_semester21;
    private javax.swing.JLabel lbl_semester22;
    private javax.swing.JLabel lbl_semester23;
    private javax.swing.JLabel lbl_semester24;
    private javax.swing.JLabel lbl_semester25;
    private javax.swing.JLabel lbl_semester26;
    private javax.swing.JLabel lbl_semester27;
    private javax.swing.JLabel lbl_semester28;
    private javax.swing.JLabel lbl_semester29;
    private javax.swing.JLabel lbl_semester30;
    private javax.swing.JLabel lbl_semester31;
    private javax.swing.JLabel lbl_studentid;
    private javax.swing.JLabel lbl_studentid1;
    private javax.swing.JLabel lbl_studentid17;
    private javax.swing.JLabel lbl_studentid18;
    private javax.swing.JLabel lbl_studentid19;
    private javax.swing.JLabel lbl_studentid20;
    private javax.swing.JLabel lbl_studentid21;
    private javax.swing.JLabel lbl_studentid22;
    private javax.swing.JLabel lbl_studentid23;
    private javax.swing.JLabel lbl_studentid24;
    private javax.swing.JLabel lbl_studentid25;
    private javax.swing.JLabel lbl_studentid26;
    private javax.swing.JLabel lbl_studentid27;
    private javax.swing.JLabel lbl_studentid28;
    private javax.swing.JLabel lbl_studentid29;
    private javax.swing.JLabel lbl_studentid30;
    private javax.swing.JLabel lbl_studentid31;
    private javax.swing.JLabel lbl_studentname;
    private javax.swing.JLabel lbl_studentname1;
    private javax.swing.JLabel lbl_studentname17;
    private javax.swing.JLabel lbl_studentname18;
    private javax.swing.JLabel lbl_studentname19;
    private javax.swing.JLabel lbl_studentname20;
    private javax.swing.JLabel lbl_studentname21;
    private javax.swing.JLabel lbl_studentname22;
    private javax.swing.JLabel lbl_studentname23;
    private javax.swing.JLabel lbl_studentname24;
    private javax.swing.JLabel lbl_studentname25;
    private javax.swing.JLabel lbl_studentname26;
    private javax.swing.JLabel lbl_studentname27;
    private javax.swing.JLabel lbl_studentname28;
    private javax.swing.JLabel lbl_studentname29;
    private javax.swing.JLabel lbl_studentname30;
    private javax.swing.JLabel lbl_studentname31;
    private javax.swing.JTextField txt_bookid;
    private javax.swing.JTextField txt_studentid;
    // End of variables declaration//GEN-END:variables
}
