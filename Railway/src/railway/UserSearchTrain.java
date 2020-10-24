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
    private String fromStation,toStation,searchDate;
    private ArrayList<CachedRowSetImpl> searchedTrains;
    private CachedRowSet crs;
    
    // variable for cancel ticket
    private String Uname;
    private CachedRowSet crs1;
    
    
    UserSearchTrain(){
        fromStation = Railway.clientData.get(0);
        toStation = Railway.clientData.get(1);
        searchDate = Railway.clientData.get(2);
        
        
    }
    public CachedRowSet userSearchingTrain(){
        SearchTrainForUserInServer search = new SearchTrainForUserInServer();
        crs = search.searchTrains(fromStation, toStation,searchDate);
        return crs;
    }
    
    
    
    public ArrayList listOfCancelledTrain(){
        ArrayList<String> cancelledTrain;
        SearchTrainForUserInServer obj = new SearchTrainForUserInServer();
        cancelledTrain = obj.cancelledTrainOnThatDate(searchDate);
        return cancelledTrain;
    }

}
