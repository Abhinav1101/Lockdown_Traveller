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
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;
import static railway.JdbcConnection.classForName;
import static railway.JdbcConnection.getConnection;
import static railway.JdbcConnection.password;
import static railway.JdbcConnection.username;

/**
 *
 * @author abhi
 */
public class Tatkal {
    private String dateOfBooking,classOfBooking,pname,age,username,gender,fromStation,toStation;
    private int trainNo;
    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
    java.time.LocalDate textFieldAsDate;
    java.sql.Date sqlDateOfJourney;
    
    Tatkal(String typeOfAction){
        if(typeOfAction.equals("Book")){
            trainNo = Integer.parseInt(Railway.clientData.get(0));
            dateOfBooking = Railway.clientData.get(1);
            pname = Railway.clientData.get(2);
            age = Railway.clientData.get(3);
            gender = Railway.clientData.get(4);
            fromStation = Railway.clientData.get(5);
            toStation = Railway.clientData.get(6);
            classOfBooking = Railway.clientData.get(7);
            username = Railway.clientData.get(8);

            textFieldAsDate = java.time.LocalDate.parse(dateOfBooking, formatter);
            sqlDateOfJourney = java.sql.Date.valueOf(textFieldAsDate);
        }
        else if(typeOfAction.equals("Search")){
            dateOfBooking = Railway.clientData.get(2);
            fromStation = Railway.clientData.get(0);
            toStation = Railway.clientData.get(1);
            
            textFieldAsDate = java.time.LocalDate.parse(dateOfBooking, formatter);
            sqlDateOfJourney = java.sql.Date.valueOf(textFieldAsDate);
        }

        
    }
    public int bookTatkalTicket(){
        Connection con=null;
        ResultSet rs=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Passenger", "root","");
           
            
            // Fetching the fare for the ticket booked
            String sql1 = null;
            String category = classOfBooking;
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
            pst1.setInt(1, trainNo);
            rs = pst1.executeQuery();
            int fare=0;
            if(rs.next()){
                fare = rs.getInt(1);
            }
            System.out.println("Fare  = "+fare);
            
            //Tatkal charge 200 :)
            fare += 200;
            
            
            // checking which seat is available
            String sql =  "Select * from cancelledTicket where train_no=? and dateOfJourney=? and classOfBooking=? LIMIT 1";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, trainNo);
            pst.setDate(2,sqlDateOfJourney);
            pst.setString(3,classOfBooking);
            rs = pst.executeQuery();
            
            if(rs.next()){
                // Inserting details of the ticket booked
                sql="Insert into passrecord values(?,?,?,?,?,?,?,?,?,?,?,?)";
                pst=con.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, pname);
                pst.setString(3, gender);
                pst.setString(4, age);
                pst.setString(5, classOfBooking);
                pst.setString(6, fromStation);
                pst.setString(7, toStation);
                pst.setString(8, String.valueOf(fare));
                pst.setInt(9, trainNo);
                pst.setDate(10, sqlDateOfJourney);
                pst.setString(11, "Tatkal");
                pst.setString(12, rs.getString("seatNumber"));
                pst.executeUpdate();
                
                //Deleting that ticket from cancelledTicket
                sql = "Delete from cancelledTicket where seatNumber=?";
                pst = con.prepareStatement(sql);
                pst.setString(1, rs.getString("seatNumber"));
                pst.executeUpdate();


                return 1;
            }
            return 0;
    
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("tatkal seat book me dikkat hai "+e);
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
        
        return 0;
    }
    
    public CachedRowSetImpl searchTatkalSeat(){
        CachedRowSetImpl crs=null;
        Connection con=null;
        ResultSet rs=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Passenger", "root","");
           
            String sql = " SELECT cancelledTicket.train_no,"
                    + " sum(case when classOfBooking='sleeper' then 1 else 0 end) as sleeper,"
                    + " sum(case when classOfBooking='ac3' then 1 else 0 end) as ac3,"
                    + " sum(case when classOfBooking='ac2' then 1 else 0 end) as ac2,"
                    + " sum(case when classOfBooking='ac1' then 1 else 0 end) as ac1,"
                    + " train.TrainType,train.name,train.src,train.dest,train.ArrTime,"
                    + " train.DepTime from cancelledTicket INNER JOIN train on"
                    + " cancelledTicket.train_no = train.train_no "
                    + " where cancelledTicket.fromStation=? and cancelledTicket.toStation=? and"
                    + " cancelledTicket.dateOfJourney=? GROUP by cancelledTicket.train_no";
                    
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, fromStation);
            pst.setString(2,toStation);
            pst.setDate(3, sqlDateOfJourney);
            rs = pst.executeQuery();
            crs = new CachedRowSetImpl();
            crs.populate(rs);
            
    
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            System.out.println("tatkal search me issue hai "+e);
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
        
        return crs;
    }
}
