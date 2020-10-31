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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;

// For adding food items
public class FoodItemsUpdate extends javax.swing.JFrame {

    java.time.LocalDate textFieldAsDate;
    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
    java.sql.Date sql_expiry_date;

    public FoodItemsUpdate() {
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
            pst.setString(1, Login.loggedInUser);
*/
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
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);

        } catch (IOException ex) {
            Logger.getLogger(FoodItemsUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        tf_quantity = new javax.swing.JTextField();
        tf_price_per_unit = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        button_update_item = new javax.swing.JButton();
        button_back = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tf_item_id = new javax.swing.JTextField();
        tf_threshold = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tf_item_description = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_inventory = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setForeground(new java.awt.Color(51, 102, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 217, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 153));
        jPanel2.setForeground(new java.awt.Color(255, 204, 204));

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 153, 0));
        jLabel8.setText("Price per unit");

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

        tf_price_per_unit.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tf_price_per_unit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_price_per_unitKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 153, 0));
        jLabel3.setText("Item Id");

        button_update_item.setBackground(new java.awt.Color(204, 204, 255));
        button_update_item.setFont(new java.awt.Font("Century Schoolbook", 1, 18)); // NOI18N
        button_update_item.setForeground(new java.awt.Color(0, 0, 0));
        button_update_item.setText("Update item");
        button_update_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_update_itemActionPerformed(evt);
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

        jLabel5.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 153, 0));
        jLabel5.setText("Quantity");

        tf_item_id.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tf_item_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_item_idActionPerformed(evt);
            }
        });
        tf_item_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_item_idKeyTyped(evt);
            }
        });

        tf_threshold.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 153, 51));
        jLabel6.setText("Threshold");

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Medium", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 153, 51));
        jLabel7.setText("Item Description");

        tf_item_description.setColumns(20);
        tf_item_description.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tf_item_description.setRows(5);
        jScrollPane1.setViewportView(tf_item_description);

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
        jScrollPane2.setViewportView(table_inventory);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(button_update_item)
                            .addGap(285, 285, 285)
                            .addComponent(button_back, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(21, 21, 21)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(tf_item_id, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(76, 76, 76)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tf_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(66, 66, 66)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(tf_price_per_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(46, 46, 46)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tf_threshold, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(159, 159, 159)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_item_id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_price_per_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_threshold, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(button_update_item, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addComponent(button_back, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void button_update_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_update_itemActionPerformed
        // TODO add your handling code here:
        String quantity = (tf_quantity.getText());
        String price = (tf_price_per_unit.getText());
        String item_id_as_string = tf_item_id.getText();
        String threshold = tf_threshold.getText();
        
        if (item_id_as_string.equals("") || quantity.equals("") || price.equals("")|| threshold.equals("")) {
            JOptionPane.showMessageDialog(this, "All fields are mandatory. Please don't leave any field empty.");
        } else {
            // first search and then update the item in database!
//            Connection con = null;
//            ResultSet rs = null;
            int res=0;
            try {
                /*
                Class.forName(classForName);
                con = DriverManager.getConnection(getConnection, username, password);

                String query = "Select * from foodInventory Where item_id=? AND username=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, Integer.parseInt(item_id_as_string));
                pst.setString(2, Login.loggedInUser);
                rs = pst.executeQuery();*/
                
                Socket s = new Socket("localhost",6666);
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());

                dout.writeUTF("updateItem");
                dout.writeUTF("checkUpdate");
                dout.writeUTF(Login.loggedInUser);
                dout.writeUTF(item_id_as_string);
                dout.writeUTF("null");
                dout.flush();


                DataInputStream din = new DataInputStream(s.getInputStream());
                res =  din.read();
                dout.close();
                s.close();
                
                
                int item_price = Integer.parseInt(price);
                int item_quantity = Integer.parseInt(quantity);

                if (res==1) {
                    //the item exists in the database
                    /*
                    query = "Update foodInventory set item_price=?, item_quantity=?, item_expiry_date=?, item_description=? WHERE item_id=? AND username=?";
                    pst = con.prepareStatement(query);
                    pst.setInt(1, item_price);
                    pst.setInt(2, item_quantity);
                    pst.setDate(3, sql_expiry_date);
                    pst.setString(4, tf_item_description.getText());
                    pst.setInt(5, Integer.parseInt(item_id_as_string));
                    pst.setString(6, Login.loggedInUser);
                    */

                    int answer = JOptionPane.showConfirmDialog(null, "Do you want to Update?");

                    if (answer == JOptionPane.YES_OPTION) {

//                        pst.executeUpdate();

                        s = new Socket("localhost",6666);
                        dout = new DataOutputStream(s.getOutputStream());

                        dout.writeUTF("updateItem");
                        dout.writeUTF("confirmUpdation");
                        dout.writeUTF(Login.loggedInUser);
                        dout.writeUTF(item_id_as_string);
                        dout.writeUTF(quantity);
                        dout.writeUTF(price);
                        dout.writeUTF(tf_item_description.getText());
                        dout.writeUTF(threshold);
                        dout.writeUTF("null");
                        dout.flush();


                        din = new DataInputStream(s.getInputStream());
                        res = din.read();
                        dout.close();
                        s.close();

                        if(res==1){
                            tf_quantity.setText("");
                            tf_price_per_unit.setText("");
                            tf_threshold.setText("");
                            tf_item_id.setText("");
                            tf_item_description.setText("");
                            JOptionPane.showMessageDialog(this, "Food item has been successfully updated for item id = " + item_id_as_string);
                            populateTable();
                        }
                        else{
                            JOptionPane.showMessageDialog(this,"Some Error Occured in Updation");
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

    }//GEN-LAST:event_button_update_itemActionPerformed

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

    private void tf_price_per_unitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_price_per_unitKeyTyped
        // TODO add your handling code here:
        char ch = evt.getKeyChar();
        if (!(Character.isDigit(ch) || (ch == KeyEvent.VK_BACK_SPACE) || ch == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_tf_price_per_unitKeyTyped

    private void tf_item_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_item_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_item_idActionPerformed

    private void tf_item_idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_item_idKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_item_idKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_back;
    private javax.swing.JButton button_update_item;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_inventory;
    private javax.swing.JTextArea tf_item_description;
    private javax.swing.JTextField tf_item_id;
    private javax.swing.JTextField tf_price_per_unit;
    private javax.swing.JTextField tf_quantity;
    private javax.swing.JTextField tf_threshold;
    // End of variables declaration//GEN-END:variables
}
