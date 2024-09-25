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
public class ReturnBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    public ReturnBook() {
        initComponents();
    }

    //To fetch the issue bookdetail  from database n display it to the pannel
    
 

public void getissuedetails() {
    String bookid = txt_bookid.getText();
    String studentid = txt_studentid.getText();
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/library_management_system";
        String user = "root";
        String password = "";
        
        con = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM issue_bookdetails WHERE book_id=? AND student_id=? AND status=?";
        
        pst = con.prepareStatement(sql);
        pst.setString(1, bookid);
        pst.setString(2, studentid);
        pst.setString(3, "pending");
        
        rs = pst.executeQuery();  
        
        if (rs.next()) {
            lbl_bookid.setText(rs.getString("book_id"));
            lbl_bookname.setText(rs.getString("book_name"));
            lbl_studentname.setText(rs.getString("student_name"));
            lbl_semester.setText(rs.getString("semester"));
            date_issuedate.setText(rs.getString("issue_date"));
            date_duedate.setText(rs.getString("due_date"));
        } else {
            JOptionPane.showMessageDialog(null, "RECORD NOT FOUND");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } 
   
}

    

// Return book entry

public boolean returnBook()
{
    boolean isreturn=false;
    
    String bookid = txt_bookid.getText();
    String studentid = txt_studentid.getText();
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
     try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/library_management_system";
        String user = "root";
        String password = "";
        
        con = DriverManager.getConnection(url, user, password);
        String sql = "Update issue_bookdetails SET status=? where student_id=? and book_id=? and status=?";
        
        pst = con.prepareStatement(sql);
        pst.setString(1, "returned");
        pst.setString(2, studentid);
         pst.setString(3,bookid);
        pst.setString(4, "pending");
        
        int rowcount = pst.executeUpdate();  
        if(rowcount>0){
            isreturn=true;
        }
        else
        {
          isreturn=false;   
        }
        
       
    }
     catch (Exception e) {
        e.printStackTrace();
    } 
     return isreturn;
   
}




    
    
    
    
    
    
    
    
    
    
    
//To Update the book count in database alc to bookmanage
public void updatebookcount()
{
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String bookid = txt_bookid.getText();
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/library_management_system";
        String user = "root";
        String password = "";

        con = DriverManager.getConnection(url, user, password);
        String sql="Update book_details set quantity=quantity+1 where book_id=? ";
          pst = con.prepareStatement(sql);
          pst.setString(1,bookid);
    
        int rowcount = pst.executeUpdate();
        if( rowcount > 0)
        {
            
           JOptionPane.showMessageDialog(this,"BOOk COUNT UPDATED SUCESSFULLY"); 
           
        }
        else
        {
            JOptionPane.showMessageDialog(this,"FAILED TO UPDATED BOOK COUNT"); 
        }
        
        
        
    }
    catch(Exception e){
        e.printStackTrace();
    }
     
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        date_duedate = new javax.swing.JLabel();
        lbl_semester = new javax.swing.JLabel();
        lbl_bookname = new javax.swing.JLabel();
        lbl_bookid = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        date_issuedate = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbl_studentname = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_studentid = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txt_bookid = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        returnbook = new javax.swing.JButton();
        find = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel1.setText("Book Details");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 4));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Due Date :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Book Name :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Semester :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Book ID :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        date_duedate.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        date_duedate.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(date_duedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 630, 200, 30));

        lbl_semester.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_semester.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_semester, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 490, 200, 30));

        lbl_bookname.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_bookname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 200, 30));

        lbl_bookid.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_bookid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 200, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Issue Date :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, -1, -1));

        date_issuedate.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        date_issuedate.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(date_issuedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, 200, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Std Name :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Std Name :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        lbl_studentname.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_studentname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_studentname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 200, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 470, 900));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 0, 0));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Books_52px_1.png"))); // NOI18N
        jLabel22.setText("Return Book");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, -1, -1));

        jPanel6.setBackground(new java.awt.Color(153, 0, 0));

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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 0, 0));
        jLabel4.setText("X");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, -1, -1));

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
        jPanel5.add(txt_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 290, 60));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 102));
        jLabel20.setText("Enter Book ID :");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

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
        jPanel5.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 290, 60));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 102));
        jLabel21.setText("Enter Std ID :");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, 30));

        returnbook.setBackground(new java.awt.Color(153, 0, 0));
        returnbook.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        returnbook.setForeground(new java.awt.Color(255, 255, 255));
        returnbook.setText("RETURN BOOK");
        returnbook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnbookActionPerformed(evt);
            }
        });
        jPanel5.add(returnbook, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 640, 470, 80));

        find.setBackground(new java.awt.Color(0, 0, 102));
        find.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        find.setForeground(new java.awt.Color(255, 255, 255));
        find.setText("FIND ISSUE DETAILS");
        find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findActionPerformed(evt);
            }
        });
        jPanel5.add(find, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 470, 80));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 0, 950, 900));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 0, 102));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Rewind_48px.png"))); // NOI18N
        jLabel2.setText("Back");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 50));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/Webp.net-resizeimage (1).jpg"))); // NOI18N
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 540, 630));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 900));

        setSize(new java.awt.Dimension(1723, 900));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void txt_studentidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentidActionPerformed

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void returnbookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnbookActionPerformed
            if(returnBook()==true) {   
                
                 JOptionPane.showMessageDialog(this,"BOOK RETURNED SUCESSFULLT");
                  
                 
                 updatebookcount();
                 
                 txt_bookid.setText("");
                 txt_studentid.setText("");
                 lbl_bookid.setText("");
                 lbl_bookname.setText("");
                 lbl_studentname.setText("");
                 date_issuedate.setText("");
                 date_duedate.setText("");
                 lbl_semester.setText("");
                 
            }
            else
            {
                
              JOptionPane.showMessageDialog(this,"BOOK RETURNED FAILED ");   
            }
            
            
       
    }//GEN-LAST:event_returnbookActionPerformed

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
       
    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentidFocusLost
       
    }//GEN-LAST:event_txt_studentidFocusLost

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        HomePage home=new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findActionPerformed
      getissuedetails(); 
    }//GEN-LAST:event_findActionPerformed

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
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date_duedate;
    private javax.swing.JLabel date_issuedate;
    private javax.swing.JButton find;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbl_bookid;
    private javax.swing.JLabel lbl_bookname;
    private javax.swing.JLabel lbl_semester;
    private javax.swing.JLabel lbl_studentname;
    private javax.swing.JButton returnbook;
    private javax.swing.JTextField txt_bookid;
    private javax.swing.JTextField txt_studentid;
    // End of variables declaration//GEN-END:variables
}
