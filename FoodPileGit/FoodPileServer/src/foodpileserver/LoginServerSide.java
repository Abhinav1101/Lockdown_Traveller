/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodpileserver;


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
import static foodpileserver.JdbcConnection.classForName;
import static foodpileserver.JdbcConnection.getConnection;
import static foodpileserver.JdbcConnection.username;
import static foodpileserver.JdbcConnection.password;

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
                String sql =  "Select * from users where Username=? and Password=md5(?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, Uname);
                pst.setString(2, Pass);
                rs = pst.executeQuery();
                if(rs.next()){
                    return 1;
                }
                else{
                    return 0;
                }

            }
            catch(Exception e){
                
                System.out.println("yaha dikkat hai "+e);
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
    
    
    
}
