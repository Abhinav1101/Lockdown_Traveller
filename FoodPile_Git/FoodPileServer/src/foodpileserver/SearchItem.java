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
public class SearchItem {
    private String Uname,search_by,search_detail;
    SearchItem(ArrayList<String>search){
        Uname = search.get(0);
        search_by = search.get(1);
        search_detail = search.get(2);
    }
    public CachedRowSetImpl searchForUser(){
        CachedRowSetImpl crs = null;
        
        Connection con = null;
        ResultSet rs = null;
        try{
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username, password);
            
            
            
            String sql = null;
            if(search_by.equals("Product Name")){
                sql = "Select * from foodinventory where Username=? and item_name like ?";
            }
            else if(search_by.equals("Product Category")){
                sql = "Select * from foodinventory where Username=? and item_category like ?";
            }
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Uname);
            pst.setString(2, "%" + search_detail + "%");
            rs = pst.executeQuery();
            crs = new CachedRowSetImpl();
            crs.populate(rs); 
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("there is error in searching item");
        }finally{
            try{
                if(con!=null){
                    con.close();
                }
            }catch(Exception e){
                System.out.println("Connection not closed in searchItem");
            }
        }
        
        
        return crs;
    }
}
