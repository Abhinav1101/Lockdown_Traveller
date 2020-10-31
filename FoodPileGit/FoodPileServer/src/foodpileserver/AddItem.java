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
import java.util.ArrayList;

/**
 *
 * @author abhi
 */
public class AddItem {
    
    private String item_category,Uname,item_name,item_description;
    private int item_quantity,item_price,threshold;
    
    AddItem(ArrayList<String> itemData){
        
        Uname = itemData.get(0);
        item_name = itemData.get(1);
        item_category = itemData.get(2);
        item_description = itemData.get(3);
        item_quantity = Integer.valueOf(itemData.get(4));
        item_price = Integer.valueOf(itemData.get(5));
        threshold = Integer.valueOf(itemData.get(6));  
    }
    
    
    
    public int addingItem(){
        Connection con = null;
        try{
//            System.out.println("yaha tak aaya hai");
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username, password);

            
            
            String sql = "Insert into foodinventory (username,item_name,item_category,item_description,item_price,item_quantity,threshold)"
                        + " values(?,?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Uname);
            pst.setString(2, item_name);
            pst.setString(3, item_category);
            pst.setString(4, item_description);
            pst.setInt(5, item_price);
            pst.setInt(6, item_quantity);
            pst.setInt(7, threshold);
            pst.executeUpdate();
            
            
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("there is error in adding item");
        }finally{
            try{
                if(con!=null){
                    con.close();
                }
            }catch(Exception e){
                System.out.println("Connection not closed in AddItem");
            }
        }
        return 0;
        
    }
}
