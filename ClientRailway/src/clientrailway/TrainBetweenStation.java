package clientrailway;

// Import packages
import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
// Class for searching trains
public class TrainBetweenStation extends javax.swing.JFrame {
    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
    java.time.LocalDate textFieldAsDate;
    java.sql.Date sqlDate;
    private String from,to,searchDate,typeOfBooking;
    
public TrainBetweenStation() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFrom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnSearchTrain = new javax.swing.JButton();
        searchdate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        bookingtype = new javax.swing.JComboBox<>();
        btnBookTicket = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTrainsBetweenStationResult = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));
        jPanel1.setForeground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("TRAIN BETWEEN STATION");

        txtFrom.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtFrom.setForeground(new java.awt.Color(0, 153, 153));
        txtFrom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFromKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("FROM");

        txtTo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTo.setForeground(new java.awt.Color(0, 153, 153));
        txtTo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtToKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("TO");

        btnSearchTrain.setBackground(new java.awt.Color(255, 204, 204));
        btnSearchTrain.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSearchTrain.setForeground(new java.awt.Color(153, 0, 153));
        btnSearchTrain.setText("SEARCH TRAIN");
        btnSearchTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchTrainActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Search Date");

        bookingtype.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        bookingtype.setForeground(new java.awt.Color(255, 0, 0));
        bookingtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General", "Tatkal" }));

        btnBookTicket.setBackground(new java.awt.Color(255, 204, 204));
        btnBookTicket.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnBookTicket.setForeground(new java.awt.Color(153, 0, 153));
        btnBookTicket.setText("BOOK TICKET");
        btnBookTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookTicketActionPerformed(evt);
            }
        });

        tblTrainsBetweenStationResult.setForeground(new java.awt.Color(0, 153, 153));
        tblTrainsBetweenStationResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Train No", "Name", "Type", "From", "Arrival", "To", "Departure", "Sleeper", "AC1", "AC2", "AC3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTrainsBetweenStationResult);

        btnBack.setBackground(new java.awt.Color(255, 204, 204));
        btnBack.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnBack.setForeground(new java.awt.Color(153, 0, 153));
        btnBack.setText("BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchdate, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(181, 181, 181)
                .addComponent(bookingtype, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(976, 976, 976)
                .addComponent(btnSearchTrain, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(1030, 1030, 1030)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(122, 122, 122)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                            .addComponent(btnBookTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(11, 11, 11)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(116, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bookingtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(btnSearchTrain, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnBookTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 639, Short.MAX_VALUE)
                    .addComponent(btnBack)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFromKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFromKeyTyped
        btnSearchTrain.setEnabled(true);
    }//GEN-LAST:event_txtFromKeyTyped

    private void txtToKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtToKeyTyped
        btnSearchTrain.setEnabled(true);
    }//GEN-LAST:event_txtToKeyTyped

    private void btnSearchTrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchTrainActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblTrainsBetweenStationResult.getModel();
        model.setRowCount(0);
        int flag = -1;
        ArrayList<Integer> seatLeftTatkal = new ArrayList<>();
        ArrayList<String> cancelledTrain = new ArrayList<>();
        CachedRowSet crs=null;
        from = txtFrom.getText();
        to = txtTo.getText();
        searchDate = searchdate.getText();
        typeOfBooking = (String) bookingtype.getSelectedItem();
        try{
            textFieldAsDate = java.time.LocalDate.parse(searchDate, formatter);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Invalid Date! Please enter the date in DD/MM/YYYY format!");
            System.out.println(e);
            return;
        }

        // Ensuring tatkal ticket availability can be seen for next date only
        LocalDate nextDate = LocalDate.now();
        nextDate = nextDate.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD/MM/YYYY");
        String strNextDate = this.formatter.format(nextDate);
        if(typeOfBooking.equals("Tatkal") && !strNextDate.equals(searchDate)){
            JOptionPane.showMessageDialog(null, "Tatkal booking available only for"+strNextDate);
            return;
        }

        // Exception handling
        /*
        try {

            Class.forName("com.mysql.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3336/Passenger", "root", "Harsh@2000"); Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                String query = "SELECT * FROM train WHERE StationFrom='" + from + "' AND StationTo= '" + to + "'";
                try (ResultSet result = statement.executeQuery(query)) {
                    while (result.next()) {
                        String from1 = result.getString("StationFrom");
                        String to1 = result.getString("StationTo");
                        String type = result.getString("TrainType");
                        String no = result.getString("train_no");
                        String name = result.getString("NameOfTrain");
                        String arrtime = result.getString("ArrTime");
                        String deptime = result.getString("DepTime");
                        String SLSeat=result.getString("SLSeat");
                        String AC1Seat=result.getString("AC1Seat");
                        String AC2Seat=result.getString("AC2Seat");
                        String AC3Seat=result.getString("AC3Seat");

                        model.addRow(new Object[]{no, name, type, from1, arrtime, to1, deptime,SLSeat,AC1Seat,AC2Seat,AC3Seat});
                        if (from.equals(from1) && to.equals(to1)) {
                            flag = 0;

                        }
                    }
                }
            }

            if (flag != 0) {
                JOptionPane.showMessageDialog(this, "We can't find any train of this route!!, Please try some other route!!");
                txtFrom.setText("");
                txtTo.setText("");
            }

        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        */

        try{
            Socket s = new Socket("localhost",6666);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            if(typeOfBooking.equals("General")){
                dout.writeUTF("userSearchTrain");
            }
            else if(typeOfBooking.equals("Tatkal")){
                dout.writeUTF("tatkalSearchTrain");
            }
            dout.writeUTF(from);
            dout.writeUTF(to);
            dout.writeUTF(searchDate);
            dout.writeUTF("null");
            dout.flush();

            ObjectInputStream objectIn = new ObjectInputStream(s.getInputStream());

            crs = (CachedRowSet) objectIn.readObject();

            cancelledTrain = (ArrayList<String>) objectIn.readObject();

            dout.close();

            s.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

        try{

            while (crs.next()) {
                String from1 = crs.getString("src");
                String to1 = crs.getString("dest");
                String type = crs.getString("TrainType");
                String no = crs.getString("train_no");
                String name = crs.getString("name");
                String arrtime = crs.getString("ArrTime");
                String deptime = crs.getString("DepTime");
                String SLSeat=crs.getString("sleeper");
                String AC1Seat=crs.getString("ac1");
                String AC2Seat=crs.getString("ac2");
                String AC3Seat=crs.getString("ac3");

                if(cancelledTrain.contains(no)){
                    arrtime = deptime = SLSeat = AC1Seat = AC2Seat = AC3Seat = "CANCELLED";
                }

                model.addRow(new Object[]{no, name, type, from1, arrtime, to1, deptime,SLSeat,AC1Seat,AC2Seat,AC3Seat});
                if (from.equals(from1) && to.equals(to1)) {
                    flag = 0;
                }
            }
            if (flag != 0) {
                JOptionPane.showMessageDialog(this, "We can't find any train of this route!!, Please try some other route!!");
                txtFrom.setText("");
                txtTo.setText("");
                searchdate.setText("");
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("train between station client side me problem hai");
        }

        btnSearchTrain.setEnabled(false);
    }//GEN-LAST:event_btnSearchTrainActionPerformed

    private void btnBookTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookTicketActionPerformed
        if(bookingtype.getSelectedItem().equals("General")){
            PassengerDetailsForm pdf = new PassengerDetailsForm();
            pdf.setVisible(true);
            this.dispose();
        }
        else{
            TatkalTicketBooking ttb = new TatkalTicketBooking();
            ttb.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnBookTicketActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        //        AdminPage adp = new AdminPage();
        //        adp.setVisible(true);
        this.dispose();
        Dashboarduser obj=new Dashboarduser();
        obj.setVisible(true);

    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> bookingtype;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBookTicket;
    private javax.swing.JButton btnSearchTrain;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchdate;
    private javax.swing.JTable tblTrainsBetweenStationResult;
    private javax.swing.JTextField txtFrom;
    private javax.swing.JTextField txtTo;
    // End of variables declaration//GEN-END:variables
}