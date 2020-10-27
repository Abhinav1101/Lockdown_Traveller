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
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static railway.JdbcConnection.classForName;
import static railway.JdbcConnection.getConnection;
import static railway.JdbcConnection.password;
import static railway.JdbcConnection.username;

/**
 *
 * @author abhi
 */
public class HandlingComplaint {
    
    
    public int handlingComplaint(ArrayList<String> complain){
        long millis=System.currentTimeMillis();  
        java.sql.Date today_date=new java.sql.Date(millis);
        
        Connection con=null;
        ResultSet rs=null;
        try{
            
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
            
            String sql = "INSERT into complainbox(username, date_of_complain, complain_no,complain_description) VALUES('" + complain.get(0) + "','" + today_date + "','" + complain.get(1) + "','" + complain.get(2) + "');";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.execute();
//            JOptionPane.showMessageDialog(,"Your complain is sucessfully registered!Please wait until Admin contacts you.");
            
            return 1;


        }
        catch(Exception e){
                
            System.out.println("yaha complain handle me issue aa rhi bhai "+e);
            return 0;
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
    
    
}
