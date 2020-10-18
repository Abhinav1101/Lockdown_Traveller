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
 
public class client {
     
    public static void main(String[] args)  {
        try{
		System.out.println("client started");
		Socket soc = new Socket("localhost",5077);
		BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a string");	
		String str = user.readLine();		
		PrintWriter p = new PrintWriter(soc.getOutputStream(),true); 
		p.println(str);
        }catch(Exception e){
            System.out.println(e);
        }

 
        // sendfile
        
    }
}


