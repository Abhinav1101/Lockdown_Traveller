/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railway;
import com.sun.rowset.CachedRowSetImpl;
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
    int flag=-1;
    abstract public void lowerBerth();
    abstract public void middleBerth();
    abstract public void upperBerth();
    abstract public void sideLowerBerth();
    abstract public void sideUpperBerth();
    abstract public void getCoachNumberAndSeatNumber();
    abstract public boolean validStatus();
    public String seat(){
        String classOfBooking,berth;
        int whereToUpdate=0;
        Connection con=null;
        ResultSet rs=null;
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
            String sql =  "Select * from vacantSeat where bookingForDate=? and train_no=?";
            PreparedStatement pst = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setDate(1, BookingTicket.sqlDateForBooking);
            pst.setInt(2,BookingTicket.trainNo);
            rs = pst.executeQuery();
            classOfBooking = BookingTicket.classOfBooking;
            berth = BookingTicket.prefferedBerth;
            if(rs.next()){
            if(classOfBooking.equals("Sleeper")){
                if(berth.equals("Lower")){
                    flag = rs.getInt("sleeperLower");
                    whereToUpdate=1;
                }
                else if(berth.equals("Middle")){
                    flag = rs.getInt("sleeperMiddle");
                    whereToUpdate=2;
                }
                else if(berth.equals("Upper")){
                    flag = rs.getInt("sleeperUpper");
                    whereToUpdate=3;
                }
            }
            else if(classOfBooking.equals("AC3")){
                if(berth.equals("Lower")){
                    flag = rs.getInt("ac3Lower");
                    whereToUpdate=4;
                }
                else if(berth.equals("Middle")){
                    flag = rs.getInt("ac3Middle");
                    whereToUpdate=5;
                }
                else if(berth.equals("Upper")){
                    flag = rs.getInt("ac3Upper");
                    whereToUpdate=6;
                }
            }
            else if(classOfBooking.equals("AC2")){
                if(berth.equals("Lower")){
                    flag = rs.getInt("ac2Lower");
                    whereToUpdate=7;
                }
                else if(berth.equals("Upper")){
                    flag = rs.getInt("ac2Upper");
                    whereToUpdate=8;
                }
            }
            else if(classOfBooking.equals("AC1")){
                if(berth.equals("Lower")){
                    flag = rs.getInt("ac1Lower");
                    whereToUpdate=9;
                }
                else if(berth.equals("Upper")){
                    flag = rs.getInt("ac1Upper");
                    whereToUpdate=10;
                }
            }
            }

        }
        catch(Exception e){
             e.printStackTrace();
            System.out.println("Seat final karne me problem hai abstract TicketClasses me "+e);
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                    
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Connection not closed properly");
            }
        }
        // to book both side of seat in one compartment
        if(flag==1){
            seatNumber+=3;
        }
        if(whereToUpdate!=0){
            updateFlag(whereToUpdate);
        }
        
        return coachNumber+" "+String.valueOf(seatNumber);
    }
    
    private void updateFlag(int whereToUpdate){
        Connection con=null;
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);       
            
            String sql=null;
            if(whereToUpdate==1){
                sql = "Update vacantSeat set sleeperLower=1-sleeperLower where train_no=? and bookingForDate=?";
            }
            else if(whereToUpdate==2){
                sql = "Update vacantSeat set sleeperMiddle=1-sleeperMiddle where train_no=? and bookingForDate=?";
            }
            else if(whereToUpdate==3){
                sql = "Update vacantSeat set sleeperUpper=1-sleeperUpper where train_no=? and bookingForDate=?";
            }
            else if(whereToUpdate==4){
                sql = "Update vacantSeat set ac3Lower=1-ac3Lower where train_no=? and bookingForDate=?";
            }
            else if(whereToUpdate==5){
                sql = "Update vacantSeat set ac3Middle=1-ac3Middle where train_no=? and bookingForDate=?";
            }
            else if(whereToUpdate==6){
                sql = "Update vacantSeat set ac3Upper=1-ac3Upper where train_no=? and bookingForDate=?";
            }
            else if(whereToUpdate==7){
                sql = "Update vacantSeat set ac2Lower=1-ac2Lower where train_no=? and bookingForDate=?";
            }
            else if(whereToUpdate==8){
                sql = "Update vacantSeat set ac2Upper=1-ac2Upper where train_no=? and bookingForDate=?";
            }
            else if(whereToUpdate==9){
                sql = "Update vacantSeat set ac1Lower=1-ac1Lower where train_no=? and bookingForDate=?";
            }
            else if(whereToUpdate==10){
                sql = "Update vacantSeat set ac1Upper=1-ac1Upper where train_no=? and bookingForDate=?";
            }
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, BookingTicket.trainNo);
            pst.setDate(2, BookingTicket.sqlDateForBooking);
            pst.executeUpdate();
            
            
        }
        catch(Exception e){
            System.out.println("fail in updating flag");
            
        }finally{
            try{
//                if(rs!=null)rs.close();
                if(con!=null)con.close();
                
            }
            catch(Exception e){
                System.out.println("Connection not closed here in checking from and to station for booking");
            }
        }
    }
    
}






public class BookingTicket {
    private boolean bookingStatus=false;
    static int trainNo;
    static public String classOfBooking,prefferedBerth;
    static public LocalDate dateForBooking;
    static public java.sql.Date sqlDateForBooking;
    public String seatNumber;
    
    //variable to insert details into db if booking is successful
//    private String username,passName,gender,fromStation,toStation;
//    private int age,ticketFare;
    
    
    
    BookingTicket(int trainNo,LocalDate dateForBooking,String classOfBooking,String prefferedBerth,String fromStation,String toStation){
        this.trainNo=trainNo;
        this.dateForBooking = dateForBooking;
        this.classOfBooking = classOfBooking;
        this.prefferedBerth = prefferedBerth;
        sqlDateForBooking = java.sql.Date.valueOf(dateForBooking);
        
        
        
        
    }
    
    
    public boolean bookingTicketMethod(String fromStation,String toStation){
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
                
                return false;
            }
            sql = "Select * from cancelledTrain where train_no=?";
            pst=con.prepareStatement(sql);
            pst.setInt(1, trainNo);
            rs = pst.executeQuery();
            java.sql.Date sqlDateFromCancelled;
            java.sql.Date sqlDateToCancelled;
            while(rs.next()){
                sqlDateFromCancelled = rs.getDate("cancelled_date_from");
                sqlDateToCancelled = rs.getDate("cancelled_date_to");
                if(sqlDateForBooking.after(sqlDateFromCancelled)&&sqlDateForBooking.before(sqlDateToCancelled)){
                    
                    return false;
                }
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
        
        
        TrainClasses trainClasses;
        if(this.classOfBooking.equals("Sleeper")){
            trainClasses = new Sleeper();
            bookingStatus = trainClasses.validStatus();
            seatNumber = trainClasses.seat();
        }
        else if(this.classOfBooking.equals("AC3")){
            trainClasses = new AC3();
            bookingStatus = trainClasses.validStatus();
            seatNumber = trainClasses.seat();
        }
        else if(this.classOfBooking.equals("AC2")){
            trainClasses = new AC2();
            bookingStatus = trainClasses.validStatus();
            seatNumber = trainClasses.seat();
        }
        else if(this.classOfBooking.equals("AC1")){
            trainClasses = new AC1();
            bookingStatus = trainClasses.validStatus();
            seatNumber = trainClasses.seat();
        }
        else{
            JOptionPane.showMessageDialog(null, "No such class of train exist");
            return false;
        }
        if(bookingStatus){
            if(trainClasses.flag!=0){
                updateBookingDB();
            }
        }
//        if(bookingStatus)
//            return 1;
//        else
//            return 0;
        return bookingStatus;
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
    
    public static int insertPassengerRecordInDB(ArrayList<String>passengerRecord,String seatNumber){
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
    
    public CachedRowSetImpl fetchTicket(String seatNumber){
        CachedRowSetImpl crs = null;
        
        Connection con=null;
        ResultSet rs = null;
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
            
            String sql = "Select * from passrecord where seatNumber=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,seatNumber);
            rs = pst.executeQuery();
            crs  = new CachedRowSetImpl();
            crs.populate(rs);
            
            
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Ticket print fetch me problem hai");
            
        }finally{
            try{
                if(rs!=null)rs.close();
                if(con!=null)con.close();
            }
            catch(SQLException e){
                System.out.println("Connection not closed here in fetchticket");
            }
        }
        
        
        return crs;
    }
    
}
