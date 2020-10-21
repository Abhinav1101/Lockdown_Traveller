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
import javax.swing.JOptionPane;
import static railway.JdbcConnection.classForName;
import static railway.JdbcConnection.getConnection;
import static railway.JdbcConnection.password;
import static railway.JdbcConnection.username;

/**
 *
 * @author abhi
 */
public class MealBooking {
    private int trainNo,numberOfPlate;
    private String seatNumber,dateOfJourney,menu,Uname;
    java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
    java.time.LocalDate textFieldAsDate;
    java.sql.Date sqlDateOfJourney;

    
    
    MealBooking(String requestType){
        if(requestType.equals("User")){
            trainNo = Integer.valueOf(Railway.clientData.get(0));
            seatNumber = Railway.clientData.get(1);
            dateOfJourney = Railway.clientData.get(2);
            menu = Railway.clientData.get(3);
            numberOfPlate = Integer.valueOf(Railway.clientData.get(4));
            Uname = Railway.clientData.get(5);
            
            textFieldAsDate = java.time.LocalDate.parse(dateOfJourney, formatter);
            sqlDateOfJourney = java.sql.Date.valueOf(textFieldAsDate);
            
        }
        else if(requestType.equals("Pantry")){
            trainNo = Integer.valueOf(Railway.clientData.get(0));
            dateOfJourney = Railway.clientData.get(1);
            textFieldAsDate = java.time.LocalDate.parse(dateOfJourney, formatter);
            sqlDateOfJourney = java.sql.Date.valueOf(textFieldAsDate);
        }
    }
    
    public int bookMeal(){
        
        
        Connection con=null;
        ResultSet rs=null;
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
            String sql =  "Select Username from passrecord where Username=? and DateOfJourney=? and train_no=? and seatNumber=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Uname);
            pst.setDate(2, sqlDateOfJourney);
            pst.setInt(3, trainNo);
            pst.setString(4, seatNumber);
            rs = pst.executeQuery();
            if(rs.next()){
                String sql1 = "Insert into mealOrder(train_no,dateOfOrder,seatNumber,menu,numberOfPlates) values(?,?,?,?,?)";
                pst = con.prepareStatement(sql1);
                pst.setInt(1,trainNo);
                pst.setDate(2, sqlDateOfJourney);
                pst.setString(3, seatNumber);
                pst.setString(4, menu);
                pst.setInt(5,numberOfPlate);
                pst.executeUpdate();
                return 1;
            }
            return 0;

        }
        catch(Exception e){
            
            System.out.println("meal booking for user me issue hai"+e);
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                    
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Connection not closed properly in MealBooking for user");
            }
        }
        
        return 0;
    }
    
    public CachedRowSetImpl searchMealOrder(){
        CachedRowSetImpl crs = null;
        Connection con=null;
        ResultSet rs=null;
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
            String sql =  "Select * from mealOrder where train_no=? and dateOfOrder=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, trainNo);
            pst.setDate(2, sqlDateOfJourney);
            rs = pst.executeQuery();
            crs = new CachedRowSetImpl();
            crs.populate(rs);

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            System.out.println("yaha dikkat hai");
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
