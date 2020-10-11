/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abhi
 */
public class Railway {

    /**
     * @param args the command line arguments
     */
    
    
    static java.util.TimerTask task = new java.util.TimerTask() {
        int prevCount = 0; // you can declare it static
        @Override
        public void run() {
            int count=0;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Passenger", "root","");
                String sql = "INSERT INTO ticket SELECT train_no,date_add(CURRENT_DATE,INTERVAL 6 day),720,288,108,36 FROM train";
                PreparedStatement pstInsert = con.prepareStatement(sql);
                pstInsert.executeUpdate();
                String sqlDel = "DELETE FROM ticket where travel_date = CURRENT_DATE ";
                PreparedStatement pstDelete = con.prepareStatement(sqlDel);
                pstDelete.executeUpdate();
//                while (res.next()){
//                    count = res.getInt(1);
//                }
//                System.out.println("Number of row:"+count);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        login log = new login();
        log.setVisible(true);
        
//    java.util.Timer timer = new java.util.Timer(true);// true to run timer as daemon thread
//    timer.schedule(task, 0, 86400000);// Run task every 5 second// Run task every 5 second
//    try {
//        // IMPORTANT
//        // MAKE THE INTERRUPTEDEXCEPTION CATCH UNCOMMENT WHEN YOU WILL USE THE SLEEP BELOW
//        // INCREASE THE TIME AS YOUR WISH AS LONG AS YOU WANT IT RUN
//        Thread.sleep(86400005); // Cancel task after 1 minute.
//    } catch (InterruptedException ex) {
//        Logger.getLogger(Railway.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    catch(Exception e){
//        e.printStackTrace();
//    }
//    timer.cancel();

    Calendar today = Calendar.getInstance();
    today.set(Calendar.HOUR_OF_DAY, 2);
    today.set(Calendar.MINUTE, 0);
    today.set(Calendar.SECOND, 0);

    // every night at 2am you run your task
    Timer timer = new Timer(true);
    timer.schedule(task, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period: 1 day
    
    }
    
}
