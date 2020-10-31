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
public class UpdateItem {
    private String Uname,item_name,item_description;
    private int item_id,item_quantity,item_price,threshold;
    UpdateItem(String typeOfAction,ArrayList<String> updationData){
        if(typeOfAction.equals("checkUpdate")){
            Uname = updationData.get(1);
            item_id = Integer.valueOf(updationData.get(2));
        }
        else if(typeOfAction.equals("confirmUpdation")){
            Uname = updationData.get(1);
            item_id = Integer.valueOf(updationData.get(2));
            item_quantity = Integer.valueOf(updationData.get(3));
            item_price = Integer.valueOf(updationData.get(4));
            item_description = updationData.get(5);
            threshold = Integer.valueOf(updationData.get(6));
        }
    }
    public int checkUpdate(){
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
                
                return 1;
            }
            
            return 0;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("there is error in check update item");
        }finally{
            try{
                if(rs!=null){
                    rs.close();
                }
                if(con!=null){
                    con.close();
                }
            }catch(Exception e){
                System.out.println("Connection not closed in checkUpdation");
            }
        }
        return 0;
    }
    
    public int confirmUpdation(){
        Connection con = null;
        ResultSet rs = null;
        try{

            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username, password);

            
            
            String sql = "Update foodinventory set item_price=?, item_quantity=?, item_description=?,threshold = ? WHERE item_id=? AND username=?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, item_price);
            pst.setInt(2, item_quantity);
            pst.setString(3,item_description);
            pst.setInt(4, threshold);
            pst.setInt(5, item_id);
            pst.setString(6, Uname);
            pst.executeUpdate();
            
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("there is error in confirm update item");
        }finally{
            try{
                if(rs!=null){
                    rs.close();
                }
                if(con!=null){
                    con.close();
                }
            }catch(Exception e){
                System.out.println("Connection not closed in confirmUpdation");
            }
        }
        return 0;
    }
    
}
