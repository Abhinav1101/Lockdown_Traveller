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
public class UserLogin {
    private String Uname,Pass;
    public static int result;
    private CachedRowSet crs=null;
    UserLogin(){
        Uname = Railway.clientData.get(0);
        Pass = Railway.clientData.get(1);
        LoginServerSide lss = new LoginServerSide();
        result = lss.userLogin(Uname,Pass);
//        System.out.println("username = "+Uname+" password = "+Pass);
    }
    public CachedRowSet userDashboard(){
        LoginServerSide lss = new LoginServerSide();
        Uname = Railway.clientData.get(0);
        crs = lss.userDashboard(Uname);
        return crs;
    }
    public int updatePassword(){
        Uname = Railway.clientData.get(0);
        Pass = Railway.clientData.get(1);
        LoginServerSide lss = new LoginServerSide();
        int status = lss.updatePassword(Uname,Pass);
        return status;
    }
}
