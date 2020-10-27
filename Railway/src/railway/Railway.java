/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railway;

import com.sun.rowset.CachedRowSetImpl;
import java.awt.List;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import static railway.JdbcConnection.classForName;
import static railway.JdbcConnection.getConnection;
import static railway.JdbcConnection.password;
import static railway.JdbcConnection.username;
import static railway.Railway.clientData;

/**
 *
 * @author abhi
 */

/*
class UserLogin{
    private String Uname,Pass;
    public static int result;
    private CachedRowSet crs=null;
    UserLogin(){
        Uname = Railway.clientData.get(0);
        Pass = Railway.clientData.get(1);
        LoginServerSide lss = new LoginServerSide();
        result = lss.userLogin(Uname,Pass);
//        System.out.println("username = "+Uname+" password = "+Pass);
    }
    public CachedRowSet userDashboard(){
        LoginServerSide lss = new LoginServerSide();
        Uname = Railway.clientData.get(0);
        crs = lss.userDashboard(Uname);
        return crs;
    }
    public int updatePassword(){
        Uname = Railway.clientData.get(0);
        Pass = Railway.clientData.get(1);
        LoginServerSide lss = new LoginServerSide();
        int status = lss.updatePassword(Uname,Pass);
        return status;
    }
}*/
/*
class UserSignup{
    private String Name,Email,Uname,Pass,Cpass;
    public static int result;
    UserSignup(){
        Name = Railway.clientData.get(0);
        Email = Railway.clientData.get(1);
        Uname = Railway.clientData.get(2);
        Pass = Railway.clientData.get(3);
        Cpass = Railway.clientData.get(4);
        SignupServerSide sss = new SignupServerSide();
        result = sss.userSignup(Name,Email,Uname,Pass,Cpass);
    }
}
*/
/*
class BookTicket{
    public static boolean bookingStatus;
    private int trainNo;
    //variables for booking
    private String dateForBooking,classOfBooking,prefferedBerth,fromStation,toStation;
    //variables for inserting into db if booking is successful
    
    private ArrayList<String> passengerRecord = new ArrayList<String>();
            
            
    BookTicket(){
        trainNo = Integer.parseInt(Railway.clientData.get(0));
        dateForBooking = Railway.clientData.get(1);
        classOfBooking = Railway.clientData.get(2);
        prefferedBerth = Railway.clientData.get(3);
        fromStation = Railway.clientData.get(8);
        toStation = Railway.clientData.get(9);
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        java.time.LocalDate textFieldAsDate = java.time.LocalDate.parse(dateForBooking, formatter);

        BookingTicket bookingTicket = new BookingTicket(trainNo,textFieldAsDate,classOfBooking,prefferedBerth,fromStation,toStation);
        System.out.println("Booking done "+BookingTicket.bookingStatus);
        bookingStatus = BookingTicket.bookingStatus;
        if(bookingStatus){
            for(String str:Railway.clientData){
                passengerRecord.add(str);
            }
            BookingTicket.insertPassengerRecordInDB(passengerRecord);
        }
    }
}
*/
/*
class UserSearchTrain{
    private String fromStation,toStation;
    private ArrayList<CachedRowSetImpl> searchedTrains;
    public static CachedRowSet crs;
    
    // variable for cancel ticket
    private String Uname;
    private CachedRowSet crs1;
    
    
    UserSearchTrain(){
        fromStation = Railway.clientData.get(0);
        toStation = Railway.clientData.get(1);
        SearchTrainForUserInServer search = new SearchTrainForUserInServer();
        crs = search.searchTrains(fromStation, toStation);
        
    }
    
    public CachedRowSet userCancelTicket(){
        CachedRowSet crs=null;
        Uname=Railway.clientData.get(0);
        SearchTrainForUserInServer cancel = new SearchTrainForUserInServer();
        crs1 = cancel.cancelTicket(Uname);
        return crs1;
    }
    
}
*/






class ClientThread extends Thread{
    protected Socket s;
    ClientThread(Socket socket){
        s = socket;
    }
    public void run(){
        try{
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            
            
            String str="";
            String typeOfAction = (String)din.readUTF();
            while(!str.equals("null")){
                str = (String)din.readUTF();
                clientData.add(str);
                System.out.println(str);
            }
            if(typeOfAction.equals("userLogin")){
                User ul = new User();
                int result  = ul.userLogin();
                str = Integer.toString(result);
                dout.writeUTF(str);
                dout.flush();
                dout.close();
                System.out.println(result);
            }
            else if(typeOfAction.equals("userSignup")){
                User user = new User();
                int result = user.userSignup();
                str = Integer.toString(result);
                dout.writeUTF(str);
                dout.flush();
                dout.close();
                System.out.println(result);
            }
            else if(typeOfAction.equals("bookTicket")){
                BookTicket bookTicket = new BookTicket();
                boolean bookingStatus = bookTicket.bookTicketCaller();
                
                CachedRowSet crs=null;
                if(bookingStatus){
                    crs = bookTicket.printTicket();
                }
                str = Boolean.toString(bookingStatus);
                dout.writeUTF(str);
                dout.flush();
                if(bookingStatus){
                    ObjectOutputStream objectOut = new ObjectOutputStream(s.getOutputStream());
                    objectOut.writeObject(crs);
                    objectOut.close();
                }
                dout.close();
            }
            else if(typeOfAction.equals("userSearchTrain")){
                UserSearchTrain userSearchTrain = new UserSearchTrain();
                
                CachedRowSet crs = userSearchTrain.userSearchingTrain();
                
                ArrayList<String> cancelledTrain = userSearchTrain.listOfCancelledTrain();
                
                ObjectOutputStream objectOut = new ObjectOutputStream(s.getOutputStream());
                // THIS TOOK ME OVER 7 HOURS TO FIGURE OUT HOW TO SEND CRS OVER SOCKET
                // I WAS USING ONLY WRITE INSTEAD OF WRITEOBJECT AND NO HELP PRESENT ON INTERNET FOR CRS
                // FOUND WAY ACCIDENTALLY WHEN I LOST HOPE :)
                objectOut.writeObject(crs);
                objectOut.writeObject(cancelledTrain);
                objectOut.close();
            }
            else if(typeOfAction.equals("userDashboard")){
                User ul = new User();
                CachedRowSet crs = ul.userDashboard();
                ObjectOutputStream objectOut = new ObjectOutputStream(s.getOutputStream());
                objectOut.writeObject(crs);
                objectOut.close();
            }
            else if(typeOfAction.equals("userUpdatePassword")){
                User ul = new User();
                int status = ul.updatePassword();
                dout.write(status);
                dout.flush();
                dout.close();
            }
            else if(typeOfAction.equals("userFetchTicketToCancel")){
                DashboardFunctionality cancel = new DashboardFunctionality();
                CachedRowSet crs = cancel.userCancelTicket();
                ObjectOutputStream objectOut = new ObjectOutputStream(s.getOutputStream());
                objectOut.writeObject(crs);
                objectOut.close();
            }
            else if(typeOfAction.equals("userBookMeal")){
                MealBooking meal = new MealBooking("User");
                int status = meal.bookMeal();
                dout.write(status);
                dout.flush();
                dout.close();
            }
            else if(typeOfAction.equals("pantrySearchMealOrder")){
                MealBooking meal = new MealBooking("Pantry");
                CachedRowSet crs = meal.searchMealOrder();
                ObjectOutputStream objectOut = new ObjectOutputStream(s.getOutputStream());
                objectOut.writeObject(crs);
                objectOut.close();
            }
            else if(typeOfAction.equals("tatkalSearchTrain")){
                Tatkal tatkal = new Tatkal("Search");
                CachedRowSet crs = tatkal.searchTatkalSeat();
                UserSearchTrain userSearchTrain = new UserSearchTrain();
                ArrayList<String> cancelledTrain = userSearchTrain.listOfCancelledTrain();
                
                ObjectOutputStream objectOut = new ObjectOutputStream(s.getOutputStream());
                objectOut.writeObject(crs);
                objectOut.writeObject(cancelledTrain);
                objectOut.close();
                
            }
            else if(typeOfAction.equals("tatkalBookTicket")){
                Tatkal tatkal = new Tatkal("Book");
                int bookingStatus = tatkal.bookTatkalTicket();
                dout.write(bookingStatus);
                dout.close();
            }
            else if(typeOfAction.equals("userComplaintRegistration")){
                HandlingComplaint handleComplain = new HandlingComplaint();
                int result = handleComplain.handlingComplaint(clientData);
                dout.write(result);
                dout.flush();
                dout.close();
            }
            din.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}



public class Railway {

    /**
     * @param args the command line arguments
     */
    
    static ArrayList<String> clientData = new ArrayList<String>();
    static java.util.TimerTask task = new java.util.TimerTask() {
        int prevCount = 0; // you can declare it static
        @Override
        public void run() {
            int count=0;
            Connection con;
            try {
                Class.forName(classForName);
                con = DriverManager.getConnection(getConnection, username,password);
                
                String checkAvailability = "Select * from vacantSeat where bookingForDate = date_add(CURRENT_DATE,INTERVAL 6 day)";
                PreparedStatement pstCheckAvailability = con.prepareStatement(checkAvailability);
                ResultSet rs = pstCheckAvailability.executeQuery();
                if(rs.next()){
                    System.out.println("Already updated");
                    return;
                }
                
                String sql = "INSERT INTO vacantSeat(train_no,bookingForDate,sleeper,ac3,ac2,ac1) SELECT train_no,date_add(CURRENT_DATE,INTERVAL 6 day),SLSeat,AC3Seat,AC2Seat,AC1Seat FROM train";
                PreparedStatement pstInsert = con.prepareStatement(sql);
                pstInsert.executeUpdate();
                String sqlDel = "DELETE FROM vacantSeat where bookingForDate = CURRENT_DATE ";
                PreparedStatement pstDelete = con.prepareStatement(sqlDel);
                pstDelete.executeUpdate();
                
                
                sql = "Insert into bookingTicket(train_no,bookingForDate) Select train_no,date_add(CURRENT_DATE,INTERVAL 6 day) from train";
                pstInsert = con.prepareStatement(sql);
                pstInsert.executeUpdate();
                
                sqlDel = "Delete from bookingTicket where bookingForDate = CURRENT_DATE";
                pstDelete = con.prepareStatement(sqlDel);
                pstDelete.executeUpdate();
                
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };
    
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        //I HAVE WRITTEN THIS LINE JUST TO MAKE THIS SERVER KEEP RUNNING BUT IF I FIND BETTER METHOD TO KEEP
        // IT RUNNING I WILL COMMENT IT. VISIBITLITY IS 
//        login log = new login();
//        log.setVisible(false);
        
        try{
            Calendar today;
            today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 2);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            System.out.println("time = "+today.getTime());
                
                
            // every night at 2am you run your task
                  
            Timer timer = new Timer(true);
            timer.schedule(task, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period: 1 day
        }catch(Exception e){
            e.printStackTrace();
        }        



        ServerSocket ss = null;
        Socket s=null;
        try {
            ss = new ServerSocket(6666);
        } catch (IOException ex) {
            Logger.getLogger(Railway.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true){
            
            try{
                clientData.clear();
                
                s = ss.accept();
//                ss.close();
                
                
            }
            catch(Exception e){
//                e.printStackTrace();
            }
            new ClientThread(s).start();
            
        }
        
        
        
        
        
        
/*      
    java.util.Timer timer = new java.util.Timer(true);// true to run timer as daemon thread
    timer.schedule(task, 0, 86400000);// Run task every 5 second// Run task every 5 second
    try {
        // IMPORTANT
        // MAKE THE INTERRUPTEDEXCEPTION CATCH UNCOMMENT WHEN YOU WILL USE THE SLEEP BELOW
        // INCREASE THE TIME AS YOUR WISH AS LONG AS YOU WANT IT RUN
        Thread.sleep(86400005); // Cancel task after 1 minute.
    } catch (InterruptedException ex) {
        Logger.getLogger(Railway.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch(Exception e){
        e.printStackTrace();
    }
    timer.cancel();
*/
        
        
    }
    
}
