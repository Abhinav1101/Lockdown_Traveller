/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodpile;

/**
 *
 * @author abhi
 */
public final class JdbcConnection {
    // Singleton Class
    private JdbcConnection(){
        
        //private to avoid instatiation 
    }

    /**
     *classForName is the variable to be entered in Class.ForName() while establishing jdbc connection
     * 
     */
    public static final String classForName="com.mysql.cj.jdbc.Driver";
    public static final String getConnection = "jdbc:mysql://localhost:3306/FoodPile";
    public static final String username="root";
    public static final String password="";
}
