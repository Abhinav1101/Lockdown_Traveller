/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodpileserver;

import static foodpileserver.FoodPileServer.clientData;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author abhi
 */

class ClientThread extends Thread{
    protected Socket s;
    ClientThread(Socket socket){
        s = socket;
    }
    public void run(){
        try{
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            
            
            String str="";
            String typeOfAction = (String)din.readUTF();
            while(!str.equals("null")){
                str = (String)din.readUTF();
                clientData.add(str);
                System.out.println(str);
            }
            if(typeOfAction.equals("addItem")){
                AddItem addItem = new AddItem(clientData);
                int result = addItem.addingItem();
                dout.write(result);
                dout.flush();
                dout.close();
                
            }
            else if(typeOfAction.equals("removeItem")){
                if(clientData.get(0).equals("check")){
                    RemoveItem remove = new RemoveItem(clientData);
                    int result = remove.checkRemoval();
                    dout.write(result);
                    dout.flush();
                    dout.close();
                }
                else if(clientData.get(0).equals("confirmRemoval")){
                    RemoveItem remove = new RemoveItem(clientData);
                    int result = remove.confirmRemoval(clientData.get(3));
                    dout.write(result);
                    dout.flush();
                    dout.close();
                }
                else if(clientData.get(0).equals("confirmDeletion")){
                    RemoveItem remove = new RemoveItem(clientData);
                    int result = remove.confirmDeletion();
                    dout.write(result);
                    dout.flush();
                    dout.close();
                }
            }
            else if(typeOfAction.equals("updateItem")){
                if(clientData.get(0).equals("checkUpdate")){
                    UpdateItem update = new UpdateItem("checkUpdate",clientData);
                    int result = update.checkUpdate();
                    dout.write(result);
                    dout.flush();
                    dout.close();
                }
                else if(clientData.get(0).equals("confirmUpdation")){
                    UpdateItem update = new UpdateItem("confirmUpdation",clientData);
                    int result = update.confirmUpdation();
                    dout.write(result);
                    dout.flush();
                    dout.close();
                }
            }
            else if(typeOfAction.equals("populateTable")){
                PopulateTableWithItems populateTable = new PopulateTableWithItems(clientData);
                CachedRowSet crs = populateTable.populate();
                ObjectOutputStream objectOut = new ObjectOutputStream(s.getOutputStream());
                objectOut.writeObject(crs);
                objectOut.close();
            }
            else if(typeOfAction.equals("searchItem")){
                SearchItem search = new SearchItem(clientData);
                CachedRowSet crs = search.searchForUser();
                ObjectOutputStream objectOut = new ObjectOutputStream(s.getOutputStream());
                objectOut.writeObject(crs);
                objectOut.close();
            }
            else if(typeOfAction.equals("userLogin")){
                User user = new User();
                int result = user.userLogin(clientData);
                dout.write(result);
                dout.flush();
                dout.close();
            }
            else if(typeOfAction.equals("userSignup")){
                User user = new User();
                int result = user.userSignup(clientData);
                dout.write(result);
                dout.flush();
                dout.close();
            }
            else if(typeOfAction.equals("OTP")){
                MailVerification mail = new MailVerification(clientData.get(1));
                if(clientData.get(0).equals("sendOTP")){
                    String result = mail.sendOTP();
                    dout.writeUTF(result);
                    dout.flush();
                    dout.close();
                }
                else{
                    int result = mail.verifyOTP();
                    dout.write(result);
                    dout.flush();
                    dout.close();
                }
            }
//            else if(typeOfAction.equals("verifyOTP")){
//                MailVerification mail = new MailVerification(clientData.get(0),clientData.get(1));
//                
//                int result = mail.sendOTP();
//                dout.write(result);
//                dout.flush();
//                dout.close();
//            }
            
            
            din.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}


public class FoodPileServer {

    /**
     * @param args the command line arguments
     */
    
    static ArrayList<String> clientData = new ArrayList<>();
    
    public static void main(String[] args) {
        // TODO code application logic here
        ServerSocket ss = null;
        Socket s=null;
        try {
            ss = new ServerSocket(6666);
        } catch (IOException ex) {
            Logger.getLogger(FoodPileServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true){
            
            try{
                clientData.clear();
                
                s = ss.accept();
//                ss.close();
                
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
            new ClientThread(s).start();
            
        }
    }
    
}
