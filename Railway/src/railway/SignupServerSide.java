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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import static railway.JdbcConnection.classForName;
import static railway.JdbcConnection.getConnection;
import static railway.JdbcConnection.username;
import static railway.JdbcConnection.password;

/**
 *
 * @author abhi
 */
public class SignupServerSide {
    private String Name,Email,Pass,Cpass,Uname;
    
    
//    public SignupServerSide(){
//        userSignup();
//    }
    
    private boolean CheckPasswordStrength(String pass){
      Pattern my_pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
      Matcher my_match = my_pattern.matcher(pass);
      boolean check = my_match.find();
      return check;
        
    }
    private boolean checkUsername(String Uname){
        for(int i=0;i<Uname.length();i++){
            if(Uname.charAt(i)==' '){
                return true;
            }
        }
        return false;
    }
    public int userSignup(String Name,String Email,String Uname,String Pass,String Cpass){
        this.Name = Name;
        this.Uname = Uname;
        this.Email = Email;
        this.Pass = Pass;
        this.Cpass = Cpass;
        
        
        Connection con=null;
        ResultSet rs=null;
        try{
            
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username,password);
            String sql = "Select * from Passenger where Username=? or email=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Uname);
            pst.setString(2, Email);
            rs = pst.executeQuery();
                if(rs.next()){
                    return 1;
                }
                else if(!Pass.equals(Cpass)){
                    return 2;
                }
                else if(Pass.length()<6 || !CheckPasswordStrength(Pass)){
                    return 3;
                }
                else if(checkUsername(Uname)){
                    return 4;
                }
                else{
                    String sql1 = "Insert into Passenger values(?,?,?,md5(?),0)";
                    PreparedStatement pst1 = con.prepareStatement(sql1);
                    pst1.setString(1, Name);
                    pst1.setString(2, Email);
                    pst1.setString(3, Uname);
                    pst1.setString(4, Pass);
                    pst1.executeUpdate();
                    return 5;
                }
            }
            catch(ClassNotFoundException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
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
        return 1;
    }
}
