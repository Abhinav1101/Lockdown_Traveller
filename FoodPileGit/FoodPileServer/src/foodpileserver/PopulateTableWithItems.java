/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodpileserver;

import com.sun.rowset.CachedRowSetImpl;
import static foodpileserver.JdbcConnection.classForName;
import static foodpileserver.JdbcConnection.getConnection;
import static foodpileserver.JdbcConnection.password;
import static foodpileserver.JdbcConnection.username;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author abhi
 */
public class PopulateTableWithItems {
    /*
    This is the class for populating the table in different fashion according to the choice of user
    */
    
    
    private String orderBy,Uname;
    PopulateTableWithItems(ArrayList<String>clientData){
        Uname = clientData.get(0);
        orderBy = clientData.get(1);
        
    }
    public CachedRowSetImpl populate(){
        CachedRowSetImpl crs = null;
        Connection con = null;
        ResultSet rs = null;
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username, password);
            
            
            
            String sql = null;
            if(orderBy.equals("item_id")){
                sql = "Select * from foodinventory where Username=? order by item_id";
            }
            else if(orderBy.equals("Sort by Price")){
                sql = "Select * from foodinventory where Username=? order by item_price";
            }
            else if(orderBy.equals("Sort by Quantity")){
                sql = "Select * from foodinventory where Username=? order by item_quantity";
            }
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Uname);
            rs = pst.executeQuery();
            crs = new CachedRowSetImpl();
            crs.populate(rs); 
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("there is error in populating item");
        }finally{
            try{
                if(con!=null){
                    con.close();
                }
            }catch(Exception e){
                System.out.println("Connection not closed in populateTable");
            }
        }
        return crs;
    }
}
