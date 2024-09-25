/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package library_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class ViewRecord extends javax.swing.JFrame {

    /**
     * Creates new form ViewRecord
     */
    public ViewRecord() {
        initComponents();
    }

    
        public void loadData(){
        try{
         Class.forName("com.mysql.cj.jdbc.Driver");
           String url="jdbc:mysql://localhost:3306/library_management_system";
           String user="root";
           String password="";
           Connection con=DriverManager.getConnection(url,user,password);
           Statement st=con.createStatement();
         DefaultTableModel model=new DefaultTableModel(new String[]{"id","book_name","student_name","issue_date","due_date","status","semester"},0);
         tbl_issuedetails.setModel(model); 
         String sql="SELECT * FROM issue_bookdetails";
         ResultSet rs=st.executeQuery(sql);
         String id,bookname,student,issuedate,duedate,status,semester;
         while(rs.next()){
          id=rs.getString("id");
          bookname=rs.getString("book_name");
          student=rs.getString("student_name");
          issuedate=rs.getString("issue_date");
          duedate=rs.getString("due_date");
          status=rs.getString("status");
          semester=rs.getString("semester");
          
          model.addRow(new Object[]{id,bookname,student,issuedate,duedate,status,semester});
         }
         con.close();
         st.close();
        }
        catch(Exception e)
       {
        System.out.println("error"+e.getMessage());
       }    
    }
        
        
        // Fetch the data in search button
        
        public void search()
        {
            
            String id,bookname,student,issuedate,duedate,status,semester;
            
             Connection con = null;
             PreparedStatement pst = null;
             ResultSet rs = null;
    

            Date ufromdate=date_issuedate.getDatoFecha();
            Date utodate=date_duedate.getDatoFecha();
               
            
            long l1=ufromdate.getTime();
            long l2=utodate.getTime();
            
            java.sql.Date fromdate=new java.sql.Date(l1);
            java.sql.Date todate=new java.sql.Date(l2);
            
            try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/library_management_system";
        String user = "root";
        String password = "";

        con = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * from issue_bookdetails where issue_date BETWEEN ? and  ?";
        pst = con.prepareStatement(sql);
        DefaultTableModel model=new DefaultTableModel(new String[]{"id","book_name","student_name","issue_date","due_date","status","semester"},0);
        tbl_issuedetails.setModel(model); 
        pst.setDate(1, fromdate);
        pst.setDate(2, todate);
        
        rs=pst.executeQuery();
        if(rs.next()==false)
        {
           JOptionPane.showMessageDialog(this, "NO RECORD FOUND!!");
              }
        else{
            
          while(rs.next())
          {
             id=rs.getString("id");
          bookname=rs.getString("book_name");
          student=rs.getString("student_name");
          issuedate=rs.getString("issue_date");
          duedate=rs.getString("due_date");
          status=rs.getString("status");
          semester=rs.getString("semester");
          model.addRow(new Object[]{id,bookname,student,issuedate,duedate,status,semester}); 
          }
            }
        
            }
        
            catch (Exception e) {
        e.printStackTrace();
    } 
   
}
           
        
        
        
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel46 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        date_duedate = new rojeru_san.componentes.RSDateChooser();
        date_issuedate = new rojeru_san.componentes.RSDateChooser();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        search = new javax.swing.JButton();
        jPanel47 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_issuedetails = new rojeru_san.complementos.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel46.setBackground(new java.awt.Color(0, 0, 102));
        jPanel46.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel45.setText("View Records");
        jPanel46.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 330, -1));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("X");
        jLabel46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel46.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(1490, 0, -1, -1));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel46.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 370, 4));

        date_duedate.setColorBackground(new java.awt.Color(153, 0, 0));
        date_duedate.setColorForeground(new java.awt.Color(153, 0, 0));
        date_duedate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        date_duedate.setPlaceholder("Select Due date");
        jPanel46.add(date_duedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 210, 390, -1));

        date_issuedate.setColorBackground(new java.awt.Color(153, 0, 0));
        date_issuedate.setColorForeground(new java.awt.Color(153, 0, 0));
        date_issuedate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        date_issuedate.setPlaceholder("Select Issue date");
        jPanel46.add(date_issuedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 390, -1));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Due Date :");
        jPanel46.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, -1, -1));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Issue Date :");
        jPanel46.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, -1));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon 1/icons8_Rewind_48px.png"))); // NOI18N
        jLabel49.setText("Back");
        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel46.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 40));

        search.setBackground(new java.awt.Color(153, 0, 0));
        search.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        search.setForeground(new java.awt.Color(255, 255, 255));
        search.setText("Search");
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        jPanel46.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 210, 100, 40));

        getContentPane().add(jPanel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 270));

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_issuedetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Book Name", "Student", "Issue Date", "Due Date", "Status", "Semester"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_issuedetails.setColorBackgoundHead(new java.awt.Color(0, 0, 102));
        tbl_issuedetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_issuedetails.setColorFilasForeground1(new java.awt.Color(153, 0, 0));
        tbl_issuedetails.setColorFilasForeground2(new java.awt.Color(153, 0, 0));
        tbl_issuedetails.setColorSelBackgound(new java.awt.Color(153, 153, 153));
        tbl_issuedetails.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tbl_issuedetails.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tbl_issuedetails.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tbl_issuedetails.setFuenteHead(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tbl_issuedetails.setRowHeight(40);
        tbl_issuedetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_issuedetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_issuedetails);

        jPanel47.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 1520, 460));

        getContentPane().add(jPanel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 1600, 590));

        setSize(new java.awt.Dimension(1600, 857));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        
    }//GEN-LAST:event_searchActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        HomePage home=new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void tbl_issuedetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_issuedetailsMouseClicked

    }//GEN-LAST:event_tbl_issuedetailsMouseClicked
 
    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
     if(date_issuedate.getDatoFecha()!= null && date_duedate.getDatoFecha()!=null){
         
        search(); 
         
     }
     else
     {
         JOptionPane.showMessageDialog(this, "PLEASE SELECT THE DATE");
     }
        
        
        
    }//GEN-LAST:event_searchMouseClicked

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
            java.util.logging.Logger.getLogger(ViewRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewRecord.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                  
            
            
               ViewRecord v= new ViewRecord();
                 v.loadData();
               v.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_duedate;
    private rojeru_san.componentes.RSDateChooser date_issuedate;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton search;
    private rojeru_san.complementos.RSTableMetro tbl_issuedetails;
    // End of variables declaration//GEN-END:variables
}
