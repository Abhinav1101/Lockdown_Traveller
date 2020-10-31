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
import java.sql.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author abhi
 */
public class MailVerification {
    private String s1;
    private String Uname,otpEntered;

    MailVerification(String uname) {
        Uname = uname;
    }

    
    
    public String sendOTP(){
        System.out.print("Sending");
        try{
        Properties prop = new Properties();
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port","587");
        String mymail = "bytecode333@gmail.com";
        String pass = "qwerty@123";
        Session session = Session.getInstance(prop,new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(mymail,pass);
                }
              });
        String rec="";
        Class.forName(classForName);
            Connection con = DriverManager.getConnection(getConnection, username,password);
            PreparedStatement ps=con.prepareStatement("select * from users where Username=?");
            ps.setString(1, Uname);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                rec=rs.getString("emailId");
            }
        Message mess = prepareMessage(session,mymail,rec);
        Transport.send(mess); 
        System.out.println("20");
        if(mess != null){
            return s1;
        }
        else{
            return null;
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error in sending otp");
            
        }
//        System.out.print("Message sent");
        return null;
    }

    private Message prepareMessage(Session session, String mymail, String rec) {
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mymail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(rec));
            message.setSubject("hello");
            int otp=(int)(10000000*Math.random());
            otp=otp%10000;
            s1=String.valueOf(otp);
            System.out.println("s1 = "+s1);
//            JOptionPane.showMessageDialog(null, "OTP has been sent to email of "+Login.loggedInUser);
            message.setText("Your otp for email verification is :"+otp);
            return message;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    
    public int verifyOTP(){
        
            try{
                Class.forName(classForName);
                Connection con = DriverManager.getConnection(getConnection, username,password);
                String sql1 = "Update  users Set verify='Yes' where Username=?";
                PreparedStatement pst1 = con.prepareStatement(sql1);
                pst1.setString(1, Uname);
                pst1.executeUpdate();
                return 1;
//            JOptionPane.showMessageDialog(null, "Your account has been verified. Enjoy the application");
//            DashboardFood obj=new DashboardFood();
//            obj.setVisible(true);
//            setVisible(false);
                
            }
            catch(Exception e)
            {
                System.out.println("Error in mail verification");
            }
            return 0;
        
        

    }
    
}
