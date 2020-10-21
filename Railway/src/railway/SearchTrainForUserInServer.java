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
    public CachedRowSetImpl searchTrains(String fromStation,String toStation){
        CachedRowSetImpl crs=null;
        Connection con=null;
        ResultSet rs=null;
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
            String sql = "Select * from train where src=? and dest=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, fromStation);
            pst.setString(2, toStation);
            rs = pst.executeQuery();
            crs = new CachedRowSetImpl();
            crs.populate(rs);
//            while(rs.next()){
//                String arrtime = rs.getString("ArrTime");
//                String deptime = rs.getString("DepTime");
//                String SLSeat=rs.getString("SLSeat");
//                String AC1Seat=rs.getString("AC1Seat");
//                String AC2Seat=rs.getString("AC2Seat");
//                String AC3Seat=rs.getString("AC3Seat");
//                System.out.println("arr = "+arrtime+" deptime= "+deptime+" Sl seat = "+SLSeat+" AC1 seat= "+AC1Seat);
////                crs = new CachedRowSetImpl();
////                crs.populate(rs);
////                searchedTrains.add(crs);
//            }
            
            
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
    
    public CachedRowSetImpl cancelTicket(String Uname){
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
