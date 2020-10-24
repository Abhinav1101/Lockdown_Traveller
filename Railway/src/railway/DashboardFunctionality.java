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
public class DashboardFunctionality {
    public CachedRowSetImpl userDashboard(String Uname){
        CachedRowSetImpl crs = null;
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
            e.printStackTrace();
            System.out.println("error in dashbord data fetch in login file"+e);
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
    
    public int updatePassword(String Uname,String Pass){
        Connection con=null;
        ResultSet rs=null;
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
            String sql = "Update  Passenger Set Password=md5(?) where Username=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Pass);
            pst.setString(2, Uname);
            pst.executeUpdate();
            return 1;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            System.out.println("Update password me problem hai");
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
    
    
    public CachedRowSet userCancelTicket(){
        CachedRowSet crs=null;
        String Uname=Railway.clientData.get(0);
        SearchTrainForUserInServer cancel = new SearchTrainForUserInServer();
        crs = cancel.fetchTicketToCancel(Uname);
        return crs;
    }
    
    
}
