package foodpile;

/**
 * *
 * @author Harsh
 */
import static foodpile.JdbcConnection.classForName;
import static foodpile.JdbcConnection.getConnection;
import static foodpile.JdbcConnection.password;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.time.LocalTime;
import javax.swing.JOptionPane;
import static foodpile.JdbcConnection.username;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;

// For updating food items
public class FoodItemsRemove extends javax.swing.JFrame {

    public FoodItemsRemove() {
        initComponents();
        populateTable();
    }

    private void populateTable() {
        Connection con = null;
        ResultSet rs = null;
        DefaultTableModel model = (DefaultTableModel) table_inventory.getModel();
        model.setRowCount(0);

        try {
            /*
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username, password);

            String query = "SELECT * FROM foodInventory WHERE username=? ORDER By item_id";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, Login.loggedInUser);*/
            
            
            Socket s = new Socket("localhost",6666);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            dout.writeUTF("populateTable");
            dout.writeUTF(Login.loggedInUser);
            dout.writeUTF("item_id");
            dout.writeUTF("null");
            dout.flush();


            ObjectInputStream oin = new ObjectInputStream(s.getInputStream());
            CachedRowSet result = (CachedRowSet) oin.readObject();
            dout.close();
            s.close();
            
            
            
                while (result.next()) {
                    int item_id = result.getInt("item_id");
                    String category = result.getString("item_category");
                    String name = result.getString("item_name");
                    int quantity = result.getInt("item_quantity");
                    int price = result.getInt("item_price");
                    int threshold = result.getInt("threshold");
                    model.addRow(new Object[]{item_id, category, name, quantity, price,threshold});

                }
            
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tf_quantity = new javax.swing.JTextField();
        tf_item_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        button_remove_item = new javax.swing.JButton();
        button_back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_inventory = new javax.swing.JTable();
        check_box_removedb = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));
        jPanel1.setForeground(new java.awt.Color(51, 102, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 291, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setForeground(new java.awt.Color(255, 204, 204));

        tf_quantity.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tf_quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_quantityActionPerformed(evt);
            }
        });
        tf_quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_quantityKeyTyped(evt);
            }
        });

        tf_item_id.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 0));
        jLabel3.setText("Quantity to be removed");

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 0));
        jLabel4.setText("Item Id");

        button_remove_item.setBackground(new java.awt.Color(204, 204, 255));
        button_remove_item.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        button_remove_item.setForeground(new java.awt.Color(0, 0, 0));
        button_remove_item.setText("Remove item");
        button_remove_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_remove_itemActionPerformed(evt);
            }
        });

        button_back.setBackground(new java.awt.Color(204, 204, 255));
        button_back.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        button_back.setForeground(new java.awt.Color(0, 0, 0));
        button_back.setText("Back");
        button_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_backActionPerformed(evt);
            }
        });

        table_inventory.setBackground(new java.awt.Color(255, 255, 204));
        table_inventory.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        table_inventory.setForeground(new java.awt.Color(0, 0, 0));
        table_inventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Id", "Category", "Name", "Available Quantity", "Price oer unit", "Threshold"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_inventory);

        check_box_removedb.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 16)); // NOI18N
        check_box_removedb.setForeground(new java.awt.Color(255, 51, 0));
        check_box_removedb.setText("Remove Item From the database.");
        check_box_removedb.setMaximumSize(new java.awt.Dimension(352, 270));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_remove_item, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(291, 291, 291)
                .addComponent(button_back, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_item_id, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(tf_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(137, 137, 137)
                                .addComponent(check_box_removedb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 885, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_item_id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(check_box_removedb, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(button_back, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(button_remove_item, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_quantityActionPerformed

    private void button_remove_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_remove_itemActionPerformed
        // TODO add your handling code here:
        String item_id_as_string = tf_item_id.getText();
        String quantity = (tf_quantity.getText());
        if (item_id_as_string.equals("") || (!check_box_removedb.isSelected() && quantity.equals(""))) {
            JOptionPane.showMessageDialog(this, "All fields are mandatory. Please don't leave any field empty.");
        } else {
            // first search in the database if given category and name exists or not and then, check the available quantity of the item in database!
            Connection con = null;
            ResultSet rs = null;

            try {
                /*
                Class.forName(classForName);
                con = DriverManager.getConnection(getConnection, username, password);

                String query = "Select * from foodInventory Where item_id=? AND username=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, Integer.parseInt(item_id_as_string));
                pst.setString(2, Login.loggedInUser);
                rs = pst.executeQuery();
                */
                int res = -1;
                Socket s = new Socket("localhost",6666);
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());



                dout.writeUTF("removeItem");
                dout.writeUTF("check");
                dout.writeUTF(Login.loggedInUser);
                dout.writeUTF(item_id_as_string);
                
                dout.writeUTF("null");
                dout.flush();


                DataInputStream din = new DataInputStream(s.getInputStream());
                res = din.read();
                dout.close();
                s.close();

                int quantity_to_be_removed = Integer.parseInt(quantity);

                if (res >= 0) {
                    //the item exists in the database
                    if (!check_box_removedb.isSelected()) {
//                        int cur_quantity = rs.getInt("item_quantity");
                        int cur_quantity = res;
                        if (cur_quantity >= quantity_to_be_removed) {
                            /*
                            query = "Update foodInventory set item_quantity=? WHERE item_id=? AND username=?";
                            pst = con.prepareStatement(query);
                            pst.setInt(1, cur_quantity - quantity_to_be_removed);
                            pst.setInt(2, Integer.parseInt(item_id_as_string));
                            pst.setString(3, Login.loggedInUser);
                            */
                            int answer = JOptionPane.showConfirmDialog(null, "Do you want to remove items?");
                            if (answer == JOptionPane.YES_OPTION) {
//                                pst.executeUpdate();
                                
                                s = new Socket("localhost",6666);
                                dout = new DataOutputStream(s.getOutputStream());



                                dout.writeUTF("removeItem");
                                dout.writeUTF("confirmRemoval");
                                dout.writeUTF(Login.loggedInUser);
                                dout.writeUTF(item_id_as_string);
                                dout.writeUTF(quantity);

                                dout.writeUTF("null");
                                dout.flush();


                                din = new DataInputStream(s.getInputStream());
                                res = din.read();
                                dout.close();
                                s.close();
                                
                                
                                if(res==1){
                                    tf_quantity.setText("");
                                    tf_item_id.setText("");
                                    JOptionPane.showMessageDialog(this, "Food item has been successfully removed for item id = " + item_id_as_string);
                                    populateTable();
                                }
                                else{
                                    JOptionPane.showMessageDialog(this, "Some error occured");
                                }
                                
                            }

                        } else {
                            JOptionPane.showMessageDialog(this, "The available quantity is LESS THAN the quantity to be removed. Please provide correct details.");
                        }
                    } else {
                        /*
                        query = "Delete from foodInventory where item_id=?";
                        pst = con.prepareStatement(query);
                        pst.setInt(1, rs.getInt("item_id"));*/
                        int answer = JOptionPane.showConfirmDialog(null, "Do you want to remove items? The item will be permanently deleted from the databse.");
                        if (answer == JOptionPane.YES_OPTION) {
//                            int x = pst.executeUpdate();
                            s = new Socket("localhost",6666);
                            dout = new DataOutputStream(s.getOutputStream());
                            dout.writeUTF("removeItem");
                            dout.writeUTF("confirmDeletion");
                            dout.writeUTF(Login.loggedInUser);
                            dout.writeUTF(item_id_as_string);
                            dout.writeUTF("null");
                            dout.flush();


                            din = new DataInputStream(s.getInputStream());
                            int x = din.read();
                            dout.close();
                            s.close();
                            if (x == 0) {
                                JOptionPane.showMessageDialog(null, "Deletion Failed");
                            } else {
                                JOptionPane.showMessageDialog(null, "Item Deleted Successfully");
                                tf_quantity.setText("");
                                tf_item_id.setText("");
                                check_box_removedb.setSelected(false);
                                populateTable();
                            }
                        }
                    }
                } else {
                    //the item does not exist in the database
                    JOptionPane.showMessageDialog(this, "The given item does not exist in your inventory. Please provide correct details.");

                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }//GEN-LAST:event_button_remove_itemActionPerformed

    private void button_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_backActionPerformed
        // TODO add your handling code here:
        DashboardFood dbu = new DashboardFood();
        dbu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_button_backActionPerformed

    private void tf_quantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_quantityKeyTyped
        // TODO add your handling code here:
        char ch = evt.getKeyChar();
        if (!(Character.isDigit(ch) || (ch == KeyEvent.VK_BACK_SPACE) || ch == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_tf_quantityKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_back;
    private javax.swing.JButton button_remove_item;
    private javax.swing.JCheckBox check_box_removedb;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_inventory;
    private javax.swing.JTextField tf_item_id;
    private javax.swing.JTextField tf_quantity;
    // End of variables declaration//GEN-END:variables
}
