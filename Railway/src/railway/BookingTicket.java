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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static railway.JdbcConnection.classForName;
import static railway.JdbcConnection.getConnection;
import static railway.JdbcConnection.password;
import static railway.JdbcConnection.username;
import static railway.JdbcConnection.username;

/**
 *
 * @author abhi
 */

abstract class TrainClasses{
    int counter=900;
    String coachNumber;
    int seatNumber;
    abstract public void lowerBerth();
    abstract public void middleBerth();
    abstract public void upperBerth();
    abstract public void sideLowerBerth();
    abstract public void sideUpperBerth();
    abstract public void getCoachNumberAndSeatNumber();
    public String seat(){
        return coachNumber+" "+String.valueOf(seatNumber);
    }
}






public class BookingTicket {
    static boolean bookingStatus=false;
    static int trainNo;
    static public String classOfBooking,prefferedBerth;
    static public LocalDate dateForBooking;
    static public java.sql.Date sqlDateForBooking;
    static public String seatNumber;
    
    //variable to insert details into db if booking is successful
//    private String username,passName,gender,fromStation,toStation;
//    private int age,ticketFare;
    
    
    
    BookingTicket(int trainNo,LocalDate dateForBooking,String classOfBooking,String prefferedBerth,String fromStation,String toStation){
        this.trainNo=trainNo;
        this.dateForBooking = dateForBooking;
        this.classOfBooking = classOfBooking;
        this.prefferedBerth = prefferedBerth;
        sqlDateForBooking = java.sql.Date.valueOf(dateForBooking);
        
        
        Connection con=null;
        ResultSet rs=null;
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);       
            
            String sql="Select train_no from train where train_no=? and src=? and dest=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, trainNo);
            pst.setString(2,fromStation);
            pst.setString(3,toStation);
            rs = pst.executeQuery();
            if(!rs.next()){
                System.out.println("Not this train number for this source and destination");
                return;
            }
            
        }
        catch(Exception e){
            System.out.println("fail in checking fromStation and toStation for booking");
            
        }finally{
            try{
                if(rs!=null)rs.close();
                if(con!=null)con.close();
                
            }
            catch(Exception e){
                System.out.println("Connection not closed here in checking from and to station for booking");
            }
        }
        
        
        
        if(this.classOfBooking.equals("Sleeper")){
            TrainClasses sleeper = new Sleeper();
            bookingStatus = Sleeper.validSeat;
            seatNumber = sleeper.seat();
        }
        else if(this.classOfBooking.equals("AC3")){
            TrainClasses ac3 = new AC3();
            bookingStatus = AC3.validSeat;
            seatNumber = ac3.seat();
        }
        else if(this.classOfBooking.equals("AC2")){
            TrainClasses ac2 = new AC2();
            bookingStatus = AC2.validSeat;
            seatNumber = ac2.seat();
        }
        else if(this.classOfBooking.equals("AC1")){
            TrainClasses ac1 = new AC1();
            bookingStatus = AC1.validSeat;
            seatNumber = ac1.seat();
        }
        else{
            JOptionPane.showMessageDialog(null, "No such class of train exist");
            return;
        }
        if(bookingStatus){
            updateBookingDB();
        }
        
    }
    private void updateBookingDB(){
       
        /*
            Updating the bookingTicket table by increasing the x of the function 8x+b so that we can 
            book only those seats which are actually present in the train and also give unique seat to 
            every passenger
        */
        try{
           Class.forName(classForName);
           Connection con = DriverManager.getConnection(getConnection, username,password);
           String sql=null;
           
           if(classOfBooking.equals("Sleeper")){
               if(prefferedBerth.equals("Lower")){
                    sql = "Update bookingTicket Set sleeperLower=sleeperLower+1 where train_no =? and bookingForDate=?";
               }
               else if(prefferedBerth.equals("Middle")){
                    sql = "Update bookingTicket Set sleeperMiddle=sleeperMiddle+1 where train_no =? and bookingForDate=?";
               }
               else if(prefferedBerth.equals("Upper")){
                    sql = "Update bookingTicket Set sleeperUpper=sleeperUpper+1 where train_no =? and bookingForDate=?";
               }
               else if(prefferedBerth.equals("SideLower")){
                    sql = "Update bookingTicket Set sleeperSideLower=sleeperSideLower+1 where train_no =? and bookingForDate=?";
               }
               else if(prefferedBerth.equals("SideUpper")){
                    sql = "Update bookingTicket Set sleeperSideUpper=sleeperSideUpper+1 where train_no =? and bookingForDate=?";
               }
           }
           else if(classOfBooking.equals("AC3")){
               if(prefferedBerth.equals("Lower")){
                    sql = "Update bookingTicket Set ac3Lower=ac3Lower+1 where train_no =? and bookingForDate=?";
               }
               else if(prefferedBerth.equals("Middle")){
                    sql = "Update bookingTicket Set ac3Middle=ac3Middle+1 where train_no =? and bookingForDate=?";
               }
               else if(prefferedBerth.equals("Upper")){
                    sql = "Update bookingTicket Set ac3Upper=ac3Upper+1 where train_no =? and bookingForDate=?";
               }
               else if(prefferedBerth.equals("SideLower")){
                    sql = "Update bookingTicket Set ac3SideLower=ac3SideLower+1 where train_no =? and bookingForDate=?";
               }
               else if(prefferedBerth.equals("SideUpper")){
                    sql = "Update bookingTicket Set ac3SideUpper=ac3SideUpper+1 where train_no =? and bookingForDate=?";
               }
           }
           else if(classOfBooking.equals("AC2")){
               if(prefferedBerth.equals("Lower")){
                    sql = "Update bookingTicket Set ac2Lower=ac2Lower+1 where train_no =? and bookingForDate=?";
               }
               
               else if(prefferedBerth.equals("Upper")){
                    sql = "Update bookingTicket Set ac2Upper=ac2Upper+1 where train_no =? and bookingForDate=?";
               }
               else if(prefferedBerth.equals("SideLower")){
                    sql = "Update bookingTicket Set ac2SideLower=ac2SideLower+1 where train_no =? and bookingForDate=?";
               }
               else if(prefferedBerth.equals("SideUpper")){
                    sql = "Update bookingTicket Set ac2SideUpper=ac2SideUpper+1 where train_no =? and bookingForDate=?";
               }
           }
           else if(classOfBooking.equals("AC1")){
               if(prefferedBerth.equals("Lower")){
                    sql = "Update bookingTicket Set ac1Lower=ac1Lower+1 where train_no =? and bookingForDate=?";
               }
               
               else if(prefferedBerth.equals("Upper")){
                    sql = "Update bookingTicket Set ac1Upper=ac1Upper+1 where train_no =? and bookingForDate=?";
               }
           }
           
           PreparedStatement pst=con.prepareStatement(sql);  
           pst.setInt(1, trainNo);
           pst.setDate(2,sqlDateForBooking);
           pst.executeUpdate();
           
        }catch(Exception e){
           e.printStackTrace();
        }
       
    }
    
    public static int insertPassengerRecordInDB(ArrayList<String>passengerRecord){
        Connection con=null;
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
            
            // Fetching the fare for the ticket booked
            String sql1 = null;
            String category=passengerRecord.get(2);
            if(category.equals("Sleeper")){
                sql1 = "Select SLFare from train where train_no=?";
            }
            else if(category.equals("AC3")){
                sql1 = "Select AC3Fare from train where train_no=?";
            }
            else if(category.equals("AC2")){
                sql1 = "Select AC2Fare from train where train_no=?";
            }
            else if(category.equals("AC1")){
                sql1 = "Select AC1Fare from train where train_no=?";
            }
            PreparedStatement pst1 = con.prepareStatement(sql1);
            pst1.setInt(1, Integer.parseInt(passengerRecord.get(0)));
            ResultSet rs = pst1.executeQuery();
            int fare=0;
            if(rs.next()){
                fare = rs.getInt(1);
            }
            System.out.println("Fare  = "+fare);
            
            
            
            
            // checking if the username exist or not in the userExpenditure table and if not exist then insert it
            String sql = "Select username from userExpenditure where username = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, passengerRecord.get(4));
            rs = pst.executeQuery();
            if(!rs.next()){
                sql = "Insert into userExpenditure(username) values(?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, passengerRecord.get(4));
                pst.executeUpdate();
            }
            
            //Adding the seat fare to the amount spent till now by that user
            
            sql = "Select discount from Passenger where Username=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, passengerRecord.get(4));
            rs = pst.executeQuery();
            int discount=0;
            while(rs.next()){
                discount = rs.getInt(1);
            }
            
            java.sql.Date discountStartDate = null;
            java.sql.Date discountEndDate = null;
            sql = "Select fromDate,toDate from userExpenditure where username='admin'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                discountStartDate = rs.getDate(1);
                discountEndDate = rs.getDate(2);
            }
            
            //finding current sql date
            long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis);
            System.out.println("today's date = "+date);
            
            int actualFare = fare;
            
            if(date.after(discountStartDate)&&date.before(discountEndDate)){
                actualFare-= ((discount*fare)/100);
            }
            
            
            sql = "Update userExpenditure set amount = amount+? where username = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, actualFare);
            pst.setString(2, passengerRecord.get(4));
            pst.executeUpdate();
            
            
            
            // Inserting details of the ticket booked
            sql="Insert into passrecord values(?,?,?,?,?,?,?,?,?,?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setString(1, passengerRecord.get(4));
            pst.setString(2, passengerRecord.get(5));
            pst.setString(3, passengerRecord.get(6));
            pst.setString(4, passengerRecord.get(7));
            pst.setString(5, passengerRecord.get(2));
            pst.setString(6, passengerRecord.get(8));
            pst.setString(7, passengerRecord.get(9));
            pst.setString(8, String.valueOf(actualFare));
            pst.setInt(9, Integer.parseInt(passengerRecord.get(0)));
            pst.setDate(10, sqlDateForBooking);
            pst.setString(11, passengerRecord.get(3));
            pst.setString(12, seatNumber);
            pst.executeUpdate();
            
            
            return 1;
        }
        catch(Exception e){
            System.out.println("Insertion of record into db failed even after successful booking");
            return 0;
        }finally{
            try{
                if(con!=null)con.close();
            }
            catch(Exception e){
                System.out.println("Connection not closed here in updating database");
            }
        }
    }
    
}
