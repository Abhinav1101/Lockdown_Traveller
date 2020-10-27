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
final class Sleeper extends TrainClasses{
    final int totalSeats=72;
    private Connection con=null;
    private boolean validSeat=false;
    
    Sleeper(int age,String gender){
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
            String berth=BookingTicket.prefferedBerth;
            if(berth.equals("Lower")){
                if((age>=60)||(age>=55&&gender.equals("Female"))){
                    lowerBerth();
                }
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
            String sql = "Select sleeperLower from bookingTicket where train_no=? and bookingForDate=?";
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
            String sql = "Select sleeperMiddle from bookingTicket where train_no=? and bookingForDate=?";
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
            String sql = "Select sleeperUpper from bookingTicket where train_no=? and bookingForDate=?";
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
            String sql = "Select sleeperSideLower from bookingTicket where train_no=? and bookingForDate=?";
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
            String sql = "Select sleeperSideUpper from bookingTicket where train_no=? and bookingForDate=?";
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
        validSeat = check.checkValidity("Sleeper", counter,BookingTicket.trainNo);

        int val = counter/totalSeats;
        coachNumber = "S"+Integer.valueOf(val+1);
        seatNumber = counter%totalSeats;
    }
    @Override
    public boolean validStatus(){
        return validSeat;
    }
}
