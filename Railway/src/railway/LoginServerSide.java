/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railway;

import AdminSide.Login;
import static com.sun.glass.ui.Cursor.setVisible;
import com.sun.rowset.CachedRowSetImpl;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;
import static railway.JdbcConnection.classForName;
import static railway.JdbcConnection.getConnection;
import static railway.JdbcConnection.username;
import static railway.JdbcConnection.password;

/**
 *
 * @author abhi
 */
public class LoginServerSide {
    
    private String Uname,Pass;
    
    private boolean checkUsername(String Uname){
        for(int i=0;i<Uname.length();i++){
            if(Uname.charAt(i)==' '){
                return true;
            }
        }
        return false;
    }
    
    public int userLogin(String Uname,String Pass){
//        String Uname = loginusername.getText();
//        String Pass = loginpass.getText();
        
//        System.out.println("uname = "+Uname+" and pass = "+Pass);
        this.Uname = Uname;
        this.Pass = Pass;
        if(checkUsername(Uname)){
            return 2;
        }
        else{
            Connection con=null;
            ResultSet rs=null;
            try{
                Class.forName(classForName);
                con = DriverManager.getConnection(getConnection, username,password);
                String sql =  "Select * from Passenger where Username=? and Password=md5(?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, Uname);
                pst.setString(2, Pass);
                rs = pst.executeQuery();
                if(rs.next()){
                    
                    if(Uname.equals("admin")&& Pass.equals("admin")){
                        return 0;
                    }
                    else
                        return 1;
                }
                else{
                    return 0;
                }

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
        }
        return 0;
        
    }
    
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
    
}
