/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package railway;

import com.sun.rowset.CachedRowSetImpl;
import java.util.ArrayList;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author abhi
 */
public class UserSearchTrain {
    private String fromStation,toStation;
    private ArrayList<CachedRowSetImpl> searchedTrains;
    public static CachedRowSet crs;
    
    // variable for cancel ticket
    private String Uname;
    private CachedRowSet crs1;
    
    
    UserSearchTrain(){
        fromStation = Railway.clientData.get(0);
        toStation = Railway.clientData.get(1);
        SearchTrainForUserInServer search = new SearchTrainForUserInServer();
        crs = search.searchTrains(fromStation, toStation);
        
    }
    
    public CachedRowSet userCancelTicket(){
        CachedRowSet crs=null;
        Uname=Railway.clientData.get(0);
        SearchTrainForUserInServer cancel = new SearchTrainForUserInServer();
        crs1 = cancel.cancelTicket(Uname);
        return crs1;
    }

}
