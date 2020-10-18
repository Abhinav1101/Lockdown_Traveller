/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onsite;

/**
 *
 * @author abhi
 */

import java.net.*;   
import java.io.*;   
import java.util.*;
   
public class server {    
   
  public static void main (String[] args ) {   
     
       
   // while(true) {
        
        
//       System.out.print("Enter File name you want to upload ");
        
        try{
        URL url = new URL("http://download.oracle.com/otn-pub/java/jdk/7u21-b11/jdk-7u21-windows-x64.exe?AuthParam=1372502269_599691fc0025a1f2da7723b644f44ece");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Range", "Bytes=0-24");
	System.out.println("Server is waiting for the connection");
	ServerSocket ss = new ServerSocket(5077);
	Socket soc = ss.accept();
	System.out.println("connection established");
	BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
	String str = in.readLine();
	PrintWriter p = new PrintWriter(soc.getOutputStream(),true);
	//System.out.println("server says "+str);	
        boolean support = urlConnection.getHeaderField("Accept-Ranges").equals("bytes");
        System.out.println("Partial content retrieval support = " + (support ? "Yes" : "No"));
	}catch(Exception e){System.out.println(e);}
 
   // }
  }
}