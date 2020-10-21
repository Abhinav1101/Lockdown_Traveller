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
import static railway.JdbcConnection.classForName;
import static railway.JdbcConnection.getConnection;
import static railway.JdbcConnection.password;
import static railway.JdbcConnection.username;

/**
 *
 * @author abhi
 */
final class AC3 extends TrainClasses{
    final int totalSeats=72;
    private Connection con=null;
    public static boolean validSeat=false;
    AC3(){
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
            String berth=BookingTicket.prefferedBerth;
            if(berth.equals("Lower")){
                lowerBerth();
            }
            else if(berth.equals("Middle")){
                middleBerth();
            }
            else if(berth.equals("Upper")){
                upperBerth();
            }
            else if(berth.equals("SideLower")){
                sideLowerBerth();
            }
            else if(berth.equals("SideUpper")){
                sideUpperBerth();
            }
            else{
                System.out.println("No such berth found");
                return;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        getCoachNumberAndSeatNumber();
    }
    
    int calculateNumber(int a,int b){
        return (8*a+b);
    }
    @Override
    public void lowerBerth() {
        try{
            String sql = "Select ac3Lower from bookingTicket where train_no=? and bookingForDate=?";
            PreparedStatement pst=con.prepareStatement(sql);   
            pst.setInt(1, BookingTicket.trainNo);
            pst.setDate(2, BookingTicket.sqlDateForBooking);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                counter = rs.getInt(1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        counter = calculateNumber(counter,1);
    }

    @Override
    public void middleBerth() {
        try{
            String sql = "Select ac3Middle from bookingTicket where train_no=? and bookingForDate=?";
            PreparedStatement pst=con.prepareStatement(sql);   
            pst.setInt(1, BookingTicket.trainNo);
            pst.setDate(2, BookingTicket.sqlDateForBooking);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                counter = rs.getInt(1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        counter = calculateNumber(counter,2);
    }

    @Override
    public void upperBerth() {
        try{
            String sql = "Select ac3Upper from bookingTicket where train_no=? and bookingForDate=?";
            PreparedStatement pst=con.prepareStatement(sql);   
            pst.setInt(1, BookingTicket.trainNo);
            pst.setDate(2, BookingTicket.sqlDateForBooking);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                counter = rs.getInt(1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        counter = calculateNumber(counter,3);
    }

    @Override
    public void sideLowerBerth() {
        try{
            String sql = "Select ac3SideLower from bookingTicket where train_no=? and bookingForDate=?";
            PreparedStatement pst=con.prepareStatement(sql);   
            pst.setInt(1, BookingTicket.trainNo);
            pst.setDate(2, BookingTicket.sqlDateForBooking);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                counter = rs.getInt(1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        counter = calculateNumber(counter,7);
    }

    @Override
    public void sideUpperBerth() {
        try{
            String sql = "Select ac3SideUpper from bookingTicket where train_no=? and bookingForDate=?";
            PreparedStatement pst=con.prepareStatement(sql);   
            pst.setInt(1, BookingTicket.trainNo);
            pst.setDate(2, BookingTicket.sqlDateForBooking);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                counter = rs.getInt(1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        counter = calculateNumber(counter,8);
    }

    @Override
    public void getCoachNumberAndSeatNumber() {
        CheckValidity check = new CheckValidity();
        validSeat = check.checkValidity("AC3", counter,BookingTicket.trainNo);
        int val = counter/totalSeats;
        coachNumber = "B"+Integer.valueOf(val+1);
        seatNumber = counter%totalSeats;
    }
    
}

