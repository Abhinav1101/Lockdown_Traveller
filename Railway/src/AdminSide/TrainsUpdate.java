package AdminSide;


import railway.*;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.time.LocalTime;
import javax.swing.JOptionPane;
import static railway.JdbcConnection.classForName;
import static railway.JdbcConnection.getConnection;
import static railway.JdbcConnection.password;
import static railway.JdbcConnection.username;

// For adding trains
public class TrainsUpdate extends javax.swing.JFrame {
    java.time.LocalDate textFieldAsDate;
    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");

java.sql.Date sqlDate;
String text="";
   public TrainsUpdate() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbTrainSelect = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFrom = new javax.swing.JTextField();
        txtTo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtArrivalTime = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSleeperFare = new javax.swing.JTextField();
        txtAC2Fare = new javax.swing.JTextField();
        txtAC1Fare = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTrainNo = new javax.swing.JTextField();
        txtTrainName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtDepartureTime = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        txtAC3Fare = new javax.swing.JTextField();
        SleeperSeats = new javax.swing.JTextField();
        AC1Seats = new javax.swing.JTextField();
        AC2Seats = new javax.swing.JTextField();
        AC3Seats = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Select train");

        cmbTrainSelect.setBackground(new java.awt.Color(51, 255, 255));
        cmbTrainSelect.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cmbTrainSelect.setForeground(new java.awt.Color(102, 0, 102));
        cmbTrainSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Passenger", "SupperFast", "Local", "InterCity", "Express" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("From");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("To");

        txtFrom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtFrom.setForeground(new java.awt.Color(0, 153, 153));

        txtTo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTo.setForeground(new java.awt.Color(0, 153, 153));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Arrival Time");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Departure Time");

        txtArrivalTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtArrivalTime.setForeground(new java.awt.Color(0, 153, 153));
        txtArrivalTime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtArrivalTimeKeyTyped(evt);
            }
        });

        txtDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDate.setForeground(new java.awt.Color(0, 153, 153));
        txtDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDateKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Sleeper Fare");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("AC3 Fare");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("AC2 Fare");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("AC1 Fare");

        txtSleeperFare.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSleeperFare.setForeground(new java.awt.Color(0, 153, 153));
        txtSleeperFare.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSleeperFareKeyTyped(evt);
            }
        });

        txtAC2Fare.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAC2Fare.setForeground(new java.awt.Color(0, 153, 153));
        txtAC2Fare.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAC2FareKeyTyped(evt);
            }
        });

        txtAC1Fare.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAC1Fare.setForeground(new java.awt.Color(0, 153, 153));
        txtAC1Fare.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAC1FareKeyTyped(evt);
            }
        });

        btnSave.setBackground(new java.awt.Color(51, 255, 255));
        btnSave.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(153, 0, 153));
        btnSave.setText("UPDATE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(51, 255, 255));
        btnReset.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReset.setForeground(new java.awt.Color(153, 0, 153));
        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("Train No");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("Name");

        txtTrainNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTrainNo.setForeground(new java.awt.Color(0, 153, 153));
        txtTrainNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTrainNoKeyTyped(evt);
            }
        });

        txtTrainName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTrainName.setForeground(new java.awt.Color(0, 153, 153));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("Date");

        txtDepartureTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDepartureTime.setForeground(new java.awt.Color(0, 153, 153));
        txtDepartureTime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDepartureTimeKeyTyped(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(51, 255, 255));
        btnBack.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnBack.setForeground(new java.awt.Color(153, 0, 153));
        btnBack.setText("BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        txtAC3Fare.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAC3Fare.setForeground(new java.awt.Color(0, 153, 153));
        txtAC3Fare.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAC3FareKeyTyped(evt);
            }
        });

        SleeperSeats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SleeperSeatsActionPerformed(evt);
            }
        });

        AC1Seats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AC1SeatsActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText("Sleeper Seats");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("AC1 Seats");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 0, 0));
        jLabel21.setText("AC2 Seats");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 0, 0));
        jLabel22.setText("AC3 Seats");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(950, 950, 950)
                        .addComponent(btnBack))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(cmbTrainSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(200, 200, 200)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(txtTrainNo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(txtTrainName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(128, 128, 128)
                                .addComponent(jLabel7)
                                .addGap(132, 132, 132)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(137, 137, 137)
                                        .addComponent(jLabel9))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtAC2Fare, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(AC2Seats, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(144, 144, 144)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(79, 79, 79)
                                        .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(SleeperSeats, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(107, 107, 107)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(AC1Seats, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtArrivalTime, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(145, 145, 145)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDepartureTime, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 87, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(txtSleeperFare, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(txtAC3Fare, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(368, 368, 368)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AC3Seats, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(307, 307, 307)
                        .addComponent(txtAC1Fare, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(473, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBack))
                .addGap(10, 10, 10)
                .addComponent(cmbTrainSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTrainNo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTrainName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDepartureTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtArrivalTime, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSleeperFare, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAC3Fare, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAC2Fare, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAC1Fare, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SleeperSeats, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AC2Seats, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(AC1Seats, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AC3Seats, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        int flag = -1;int x=999;
        String strTrainNo = txtTrainNo.getText();
        String cmbTrainType = (String) cmbTrainSelect.getSelectedItem();
        String time = txtDepartureTime.getText();
        String time1 = txtArrivalTime.getText();
        text=txtDate.getText();
                         try{
                         textFieldAsDate = java.time.LocalDate.parse(text, formatter);
                         }
                         catch(Exception e)
                         {
                          JOptionPane.showMessageDialog(this, "Invalid Date! Please enter the date in DD/MM/YYYY format!");  
//                          System.out.println(e);
                         }
                         sqlDate= java.sql.Date.valueOf(textFieldAsDate);
        
        int answer = JOptionPane.showConfirmDialog(null, "Do you want to Update?");
        if (answer == JOptionPane.YES_OPTION) {
            Connection con=null;
            label:
            try {
                Class.forName(classForName);
                con = DriverManager.getConnection(getConnection, username,password);

                //Statement statement = conn.createStatement();
                try (
                        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                    String query = "SELECT * FROM train;";
                    try (ResultSet result = statement.executeQuery(query)) {
                        while (result.next()) {
                            
                            String trainno = result.getString("train_no");
                            if (strTrainNo.equals(trainno)) {
                                  String sql = "Update train set TrainType=? , name=? , src=? , dest=? , ArrTime=? , DepTime=? , SLFare=? , AC3Fare=? , AC2Fare=? , AC1Fare=? , date=? ,SLSeat=? , AC1Seat=? , AC2Seat=? , AC3Seat=? where train_no=?";   
                                  PreparedStatement pst = con.prepareStatement(sql);
           
            pst.setString(1,cmbTrainType);
//            pst.setString(2, txtTrainNo.getText());
            pst.setString(2,txtTrainName.getText() );
            pst.setString(3, txtFrom.getText());
            pst.setString(4,txtTo.getText());
            pst.setString(5, time);
            pst.setString(6,time1 );
            pst.setString(7, txtSleeperFare.getText());
            pst.setString(8,txtAC3Fare.getText() );
            pst.setString(9,txtAC2Fare.getText());
            
            pst.setString(10,txtAC1Fare.getText() );
            pst.setDate(11,sqlDate );
            pst.setString(12,SleeperSeats.getText());
            pst.setString(13,AC1Seats.getText());
            pst.setString(14,AC2Seats.getText() );
            pst.setString(15,AC3Seats.getText());
            pst.setString(16,txtTrainNo.getText());
           
           
             x = pst.executeUpdate();
            System.out.println("x = "+x);
            if(x==0){
                JOptionPane.showMessageDialog(null,"Update Failed");
            }
            else{
                x=333;
                JOptionPane.showMessageDialog(null, "Updated Successfully");
             
            }
//                                JOptionPane.showMessageDialog(this, "This train already exist, please add other train!!!");
                                flag = 0;
                                break;
                            }
                        }
                        
                        if (flag != 0) {
                            try (Statement statement1 = con.createStatement()) {
                                
                                JOptionPane.showMessageDialog(this, "This train does not exist!");
                                
                                
                            }
                        }
                    }
                }
            } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                     JOptionPane.showMessageDialog(null,"All the fields must be filled correctly!");
                     
                System.out.println(e);
                
            }
            
        }

                                 
                                 if(x==333)
                                 {
                                    btnSave.setEnabled(false);
                                     AdminPage adb;
                                adb = new AdminPage();
                                adb.setVisible(true);
                                dispose();
                                 }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtTrainNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTrainNoKeyTyped
        char ch = evt.getKeyChar();
        if(!(Character.isDigit(ch) ||  (ch == KeyEvent.VK_BACK_SPACE) || ch == KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTrainNoKeyTyped
    // Enter Date
    private void txtDateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDateKeyTyped
        char ch = evt.getKeyChar();
        if(Character.isLetter(ch)&& ! evt.isAltDown()){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtDateKeyTyped
    // Enter Arrival Time
    private void txtArrivalTimeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtArrivalTimeKeyTyped
       char ch = evt.getKeyChar();
        if(Character.isLetter(ch)&& ! evt.isAltDown()){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtArrivalTimeKeyTyped
    // Enter departure time
    private void txtDepartureTimeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepartureTimeKeyTyped
       char ch = evt.getKeyChar();
        if(Character.isLetter(ch)&& ! evt.isAltDown()){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtDepartureTimeKeyTyped

   // Enter Fares
    private void txtAC1FareKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAC1FareKeyTyped
       char ch = evt.getKeyChar();
        if(Character.isLetter(ch)&& ! evt.isAltDown()){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtAC1FareKeyTyped

    private void txtAC2FareKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAC2FareKeyTyped
       char ch = evt.getKeyChar();
        if(Character.isLetter(ch)&& ! evt.isAltDown()){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtAC2FareKeyTyped

    private void txtSleeperFareKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSleeperFareKeyTyped
        char ch = evt.getKeyChar();
        if(Character.isLetter(ch)&& ! evt.isAltDown()){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtSleeperFareKeyTyped

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        AdminPage pdf = new AdminPage();
        pdf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed
    // Action button
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        btnSave.setEnabled(true);
        txtAC2Fare.setText("");
        txtAC3Fare.setText("");
        txtArrivalTime.setText("");
        txtDate.setText("");
        
        txtDepartureTime.setText("");
        
        txtFrom.setText("");
        txtSleeperFare.setText("");
        txtTo.setText("");
        txtTrainName.setText("");
        txtTrainNo.setText("");
        txtAC1Fare.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtAC3FareKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAC3FareKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAC3FareKeyTyped

    private void SleeperSeatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SleeperSeatsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SleeperSeatsActionPerformed

    private void AC1SeatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AC1SeatsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AC1SeatsActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AC1Seats;
    private javax.swing.JTextField AC2Seats;
    private javax.swing.JTextField AC3Seats;
    private javax.swing.JTextField SleeperSeats;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbTrainSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JTextField txtAC1Fare;
    private javax.swing.JTextField txtAC2Fare;
    private javax.swing.JTextField txtAC3Fare;
    private javax.swing.JTextField txtArrivalTime;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDepartureTime;
    private javax.swing.JTextField txtFrom;
    private javax.swing.JTextField txtSleeperFare;
    private javax.swing.JTextField txtTo;
    private javax.swing.JTextField txtTrainName;
    private javax.swing.JTextField txtTrainNo;
    // End of variables declaration//GEN-END:variables
}
