
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class front implements ActionListener {
    JFrame f;
    JLabel l1;
    JButton b1, b2;

    front() {
        f = new JFrame();
        l1 = new JLabel("ONLINE BOOK STORE");
        l1.setBounds(150, 50, 2000, 20);
        l1.setForeground(Color.BLUE);
        l1.setFont(new Font("Constantia", Font.BOLD, 20));
        b1 = new JButton("User");
        b1.setBounds(180, 100, 100, 50);
        b1.addActionListener(this);
        b2 = new JButton("Admin");
        b2.setBounds(180, 200, 100, 50);
        b2.addActionListener(this);
        f.add(l1);
        f.add(b1);
        f.add(b2);
        f.setLayout(null);
        f.setVisible(true);
        f.setSize(500, 500);
        f.setTitle("BOOK SHOP");
        f.getContentPane().setBackground(Color.PINK);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == b1) {
            Userpage up = new Userpage();
        }
        if (a.getSource() == b2) {
            adminpage ap = new adminpage();
        }
    }

    public static void main(String[] args) {
        new front();
    }

}

    // Book Details–Button–Printing all details of database in Table
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            Connection c = DriverManager.getConnection("jdbc:derby://localhost:1527/ebookshop");
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("Select * from books");
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("id"));
                String t = rs.getString("title");
                String a = rs.getString("author");
                String p = String.valueOf(rs.getDouble("price"));
                String q = String.valueOf(rs.getInt("qty"));
                String data[] = { id, t, a, p, q };
                model.addRow(data);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    // Usermethod–To get author name from the database and insert it in the combobox
    public void usermethod() {
        Connection c;
        try {
            String n[] = { " ", " ", " ", " " };
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/ebookshop");
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("Select author from books");
            jComboBox1.removeAllItems();
            jComboBox1.addItem("Select Author");
            while (rs.next()) {
                jComboBox1.addItem(rs.getString("author"));
            }
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.addRow(n);
        } catch (SQLException ex) {
            Logger.getLogger(Userpage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // ComboBox1—Printing details of all details of selected author in Table
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        Connection c;
        try {
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/ebookshop");
            Statement st = c.createStatement();
            int b = jComboBox1.getSelectedIndex();

            String name = jComboBox1.getItemAt(b);
            ResultSet rs = st.executeQuery("Select * from BOOKS");
            while (rs.next()) {
                String t = rs.getString("title");
                String a = rs.getString("author");
                String p = String.valueOf(rs.getDouble("price"));
                String q = String.valueOf(rs.getInt("qty"));
                if (a.equals(name)) {
                    String data[] = { t, a, p, q };
                    model.addRow(data);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // For fetching and setting data in the comboboxes
    public void comboboxes() {
        Connection c;
        try {
            jComboBox1.removeAllItems();
            jComboBox2.removeAllItems();
            jComboBox3.removeAllItems();
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/ebookshop");
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("Select * from books");
            while (rs.next()) {
                jComboBox1.addItem(rs.getString("title"));
                jComboBox2.addItem(rs.getString("title"));
                jComboBox3.addItem(String.valueOf(rs.getInt("ID")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(adminpage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Update Price–Button–Update new price for the selected book
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String s = jTextField1.getText();
            Double d = Double.parseDouble(s);
            Connection c;
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/ebookshop");
            Statement st = c.createStatement();
            String b = jComboBox1.getItemAt(jComboBox1.getSelectedIndex());
            st.executeUpdate(" Update books set price=" + d + " where title='" + b + "' ");
            JOptionPane.showMessageDialog(null, "Price Updated Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Update Quantity–Button–Update new quantity for the selected book
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String s = jTextField2.getText();
            int d = Integer.parseInt(s);
            Connection c;
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/ebookshop");
            Statement st = c.createStatement();
            String b = jComboBox2.getItemAt(jComboBox2.getSelectedIndex());
            st.executeUpdate(" Update books set qty=" + d + " where title='" + b + "' ");
            JOptionPane.showMessageDialog(null, "Quantity Updated Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Delete id–Button–Delete all records of the selected id
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Connection c;
            c = DriverManager.getConnection("jdbc:derby://localhost:1527/ebookshop");
            Statement st = c.createStatement();
            String d = jComboBox3.getItemAt(jComboBox3.getSelectedIndex());
            st.executeUpdate(" Delete from books where id=" + d + " ");
            JOptionPane.showMessageDialog(null, "Deleted Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Insert Book–Button–Insert new Book and its details to the Database.
private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) { String ti = jTextField6.getText();//title
String id = jTextField3.getText();//id int i_d = Integer.parseInt(id);
String au = jTextField4.getText();//author String pr = jTextField7.getText();//price double pri = Double.parseDouble(pr); String qty = jTextField5.getText();//qty int quan = Integer.parseInt(qty);
try {
Connection c;
c = DriverManager.getConnection("jdbc:derby://localhost:1527/ebookshop"); Statement st = c.createStatement();
st.executeUpdate("insert into books values(" + i_d + ",'" + ti + "','" + au + "'," + pri + "," + quan
+ ")");
JOptionPane.showMessageDialog(null, "Inserted Successfully");
} catch (Exception e) { System.out.println(e);
}
}
