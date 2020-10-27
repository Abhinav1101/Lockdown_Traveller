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
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;
import static railway.JdbcConnection.classForName;
import static railway.JdbcConnection.getConnection;
import static railway.JdbcConnection.password;
import static railway.JdbcConnection.username;
import static railway.JdbcConnection.username;


/**
 *
 * @author abhi
 */
public class SearchTrainForUserInServer {
    private ArrayList<CachedRowSetImpl> searchedTrains = new ArrayList<CachedRowSetImpl>();
    
    public CachedRowSetImpl searchTrains(String fromStation,String toStation,String searchDate){
        CachedRowSetImpl crs=null;
        Connection con=null;
        ResultSet rs=null;
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        java.time.LocalDate textFieldAsDate;
        textFieldAsDate = java.time.LocalDate.parse(searchDate, formatter);
        java.sql.Date sqlSearchDate= java.sql.Date.valueOf(textFieldAsDate);
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
            String sql = "Select train.src,train.dest,train.TrainType,train.train_no"
                    + ",train.name,train.ArrTime,train.DepTime,vacantSeat.sleeper,vacantSeat.ac3,vacantSeat.ac2,vacantSeat.ac1"
                    + " from train inner join vacantSeat on train.train_no = vacantSeat.train_no where "
                    + "train.src=? and train.dest=? and vacantSeat.bookingForDate = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, fromStation);
            pst.setString(2, toStation);
            pst.setDate(3, sqlSearchDate);
            rs = pst.executeQuery();
            crs = new CachedRowSetImpl();
            crs.populate(rs);

            
            
        }catch(Exception e){
            System.out.println("error in searching train");
            e.printStackTrace();
        }finally{
            try{
                if(rs!=null)rs.close();
                if(con!=null)con.close();
            }catch(Exception e){
                System.out.println("error in closing connection and resultset");
            }
        }
        
        return crs;
    }

    public ArrayList cancelledTrainOnThatDate(String searchDate){
        ArrayList<String> cancelledTrain = new ArrayList<>();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
        java.time.LocalDate textFieldAsDate;
        textFieldAsDate = java.time.LocalDate.parse(searchDate, formatter);
        java.sql.Date sqlSearchDate= java.sql.Date.valueOf(textFieldAsDate);
        
        Connection con=null;
        ResultSet rs=null;
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
            String sql = "Select * from cancelledTrain";
            PreparedStatement pst = con.prepareStatement(sql);
            
            rs = pst.executeQuery();
            while(rs.next()){
                if(sqlSearchDate.after(rs.getDate("cancelled_date_from"))&&sqlSearchDate.before(rs.getDate("cancelled_date_to"))){
                    cancelledTrain.add(String.valueOf(rs.getInt("train_no")));
                }
                 
            }



            
            
        }catch(Exception e){
            System.out.println("error in searching cancelled train");
            e.printStackTrace();
        }finally{
            try{
                if(rs!=null)rs.close();
                if(con!=null)con.close();
            }catch(Exception e){
                System.out.println("error in closing connection and resultset");
            }
        }
        
        
        
        
        return cancelledTrain;
    }
    
    
    
    public CachedRowSetImpl fetchTicketToCancel(String Uname){
        CachedRowSetImpl crs=null;
        Connection con=null;
        ResultSet rs=null;
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
            PreparedStatement ps=con.prepareStatement("select * from passrecord where Username=?");
            ps.setString(1, Uname);
            rs=ps.executeQuery();
            crs = new CachedRowSetImpl();
            crs.populate(rs);
        }catch(Exception e){
            System.out.println("error in train cancellation");
        }finally{
            try{
                if(rs!=null)rs.close();
                if(con!=null)con.close();
            }catch(Exception e){
                System.out.println("error in closing connection and resultset");
            }
        }
        return crs;
    }
    
}
