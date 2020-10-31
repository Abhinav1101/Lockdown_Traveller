/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodpileserver;

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
public class RemoveItem {
    private String Uname;
    private int item_id;
    RemoveItem(ArrayList<String> removalData){
        Uname = removalData.get(1);
        item_id = Integer.valueOf(removalData.get(2));
    }
    public int checkRemoval(){
        Connection con = null;
        ResultSet rs = null;
        try{

            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username, password);

            
            
            String sql = "Select * from foodinventory where Username=? and item_id=?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Uname);
            pst.setInt(2, item_id);
            rs = pst.executeQuery();
            if(rs.next()){
                int res = rs.getInt("item_quantity");
                return res;
            }
            
            return -1;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("there is error in check removal item");
        }finally{
            try{
                if(rs!=null){
                    rs.close();
                }
                if(con!=null){
                    con.close();
                }
            }catch(Exception e){
                System.out.println("Connection not closed in checkRemoval");
            }
        }
        return -1;
    }
    public int confirmRemoval(String quantityToRemove){
        Connection con = null;
        ResultSet rs = null;
        try{

            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username, password);

            String sql = "Update foodinventory set item_quantity=item_quantity-? WHERE item_id=? AND username=?";
                            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.valueOf(quantityToRemove));
            pst.setString(3, Uname);
            pst.setInt(2, item_id);
            int x = pst.executeUpdate();
            if(x==0){
                return 0;
            }
            
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("there is error in confirm Removal");
        }finally{
            try{
                if(rs!=null){
                    rs.close();
                }
                if(con!=null){
                    con.close();
                }
            }catch(Exception e){
                System.out.println("Connection not closed in confirmRemoval");
            }
        }
        return 0;
    }
    public int confirmDeletion(){
        Connection con = null;
        ResultSet rs = null;
        try{

            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username, password);

            String sql = "Delete from foodinventory WHERE item_id=? AND username=?";
                            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, item_id);
            pst.setString(2, Uname);
            
            int x = pst.executeUpdate();
            if(x==0){
                return 0;
            }
            
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("there is error in deleting item");
        }finally{
            try{
                if(rs!=null){
                    rs.close();
                }
                if(con!=null){
                    con.close();
                }
            }catch(Exception e){
                System.out.println("Connection not closed in confirmDeletion");
            }
        }
        return 0;
    }
}
