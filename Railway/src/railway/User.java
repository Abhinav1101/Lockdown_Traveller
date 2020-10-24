/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railway;

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
    
    
    public int userLogin(){
        Uname = Railway.clientData.get(0);
        Pass = Railway.clientData.get(1);
        LoginServerSide lss = new LoginServerSide();
        result = lss.userLogin(Uname,Pass);
        return result;

    }
    public CachedRowSet userDashboard(){
        DashboardFunctionality dashboard = new DashboardFunctionality();
        Uname = Railway.clientData.get(0);
        crs = dashboard.userDashboard(Uname);
        return crs;
    }
    public int updatePassword(){
        Uname = Railway.clientData.get(0);
        Pass = Railway.clientData.get(1);
        DashboardFunctionality dashboard = new DashboardFunctionality();
        int status = dashboard.updatePassword(Uname,Pass);
        return status;
    }
    int userSignup(){
        Name = Railway.clientData.get(0);
        Email = Railway.clientData.get(1);
        Uname = Railway.clientData.get(2);
        Pass = Railway.clientData.get(3);
        Cpass = Railway.clientData.get(4);
        SignupServerSide sss = new SignupServerSide();
        result = sss.userSignup(Name,Email,Uname,Pass,Cpass);
        return result;
    }

    
}
