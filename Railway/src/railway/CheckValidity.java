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
import java.util.logging.Level;
import java.util.logging.Logger;
import static railway.JdbcConnection.classForName;
import static railway.JdbcConnection.getConnection;
import static railway.JdbcConnection.password;
import static railway.JdbcConnection.username;

/**
 *
 * @author abhi
 */
public class CheckValidity {
    /*
    This class will take care that if all seats of that preffered berth is already booked then
    further booking for that berth in that class will not take place and thus only valid seats
    will be allocated to any passenger
    */
    private boolean validSeat;
    private int totalSeat=0;
    private Connection con=null;
    public boolean checkValidity(String classOfBooking,int counter,int trainNo){
        try {
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
        } catch (Exception ex) {
            Logger.getLogger(CheckValidity.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (classOfBooking) {
            case "Sleeper":
                try{
                    String sql = "Select SLSeat from train where train_no=?";
                    PreparedStatement pst=con.prepareStatement(sql);
                    pst.setInt(1, trainNo);
                    ResultSet rs = pst.executeQuery();
                    while(rs.next()){
                        totalSeat = rs.getInt(1);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }   break;
            case "AC3":
                try{
                    String sql = "Select AC3Seat from train where train_no=?";
                    PreparedStatement pst=con.prepareStatement(sql);
                    pst.setInt(1, trainNo);
                    ResultSet rs = pst.executeQuery();
                    while(rs.next()){
                        totalSeat = rs.getInt(1);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }   break;
            case "AC2":
                try{
                    String sql = "Select AC2Seat from train where train_no=?";
                    PreparedStatement pst=con.prepareStatement(sql);
                    pst.setInt(1, trainNo);
                    ResultSet rs = pst.executeQuery();
                    while(rs.next()){
                        totalSeat = rs.getInt(1);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }   break;
            case "AC1":
                try{
                    String sql = "Select AC1Seat from train where train_no=?";
                    PreparedStatement pst=con.prepareStatement(sql);
                    pst.setInt(1, trainNo);
                    ResultSet rs = pst.executeQuery();
                    while(rs.next()){
                        totalSeat = rs.getInt(1);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }   break;
            default:
                break;
        }
        validSeat = totalSeat>=counter;
        System.out.println("validity = "+validSeat);
        return validSeat;
    }
}
