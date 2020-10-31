/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodpileserver;

import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author abhi
 */
public class User {
    private String Uname,Pass;
    private int result;
    private CachedRowSet crs=null;
    
    private String Name,Email,Cpass;
    
    
    public int userLogin(ArrayList<String>clientData){
        Uname = clientData.get(0);
        Pass = clientData.get(1);
        LoginServerSide lss = new LoginServerSide();
        result = lss.userLogin(Uname, Pass);
        return result;
    }
    
    
    int userSignup(ArrayList<String>clientData){
        Name = clientData.get(0);
        Email = clientData.get(1);
        Uname = clientData.get(2);
        Pass = clientData.get(3);
        Cpass = clientData.get(4);
        SignupServerSide sss = new SignupServerSide();
        result = sss.userSignup(Name,Email,Uname,Pass,Cpass);
        return result;
    }

    
}
